package com.diefesson.difcomp.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.diefesson.difcomp.error.ParserException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.ElementType;
import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.grammar.FollowSets;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Item;
import com.diefesson.difcomp.grammar.Rule;

public class SLRTable {

    // TODO: use a composity key?
    private final Map<Integer, Map<Element, List<Action>>> table;

    public SLRTable(Map<Integer, Map<Element, List<Action>>> table) {
        this.table = table;
    }

    public List<Action> getList(int state, Element reading) {
        Map<Element, List<Action>> elementAxis = table.get(state);
        if (elementAxis != null) {
            List<Action> actions = elementAxis.get(reading);
            if (actions != null) {
                return Collections.unmodifiableList(actions);
            }
        }
        return List.of();
    }

    public Action get(int state, Element reading) throws ParserException {
        List<Action> actions = getList(state, reading);
        if (actions.size() == 0) {
            throw new ParserException("no action for %d %s".formatted(state, reading));
        }
        if (actions.size() > 1) {
            throw new ParserException("ambiguous action for %d %s".formatted(state, reading));
        }
        return actions.get(0);
    }

    private void add(int state, Element reading, Action action) {
        Map<Element, List<Action>> elementAxis = table.get(state);
        if (elementAxis == null) {
            elementAxis = new HashMap<>();
            table.put(state, elementAxis);
        }
        List<Action> actions = elementAxis.get(reading);
        if (actions == null) {
            actions = new ArrayList<>();
            elementAxis.put(reading, actions);
        }
        actions.add(action);
    }

    public static SLRTable compute(Grammar grammar) {
        SLRTable slrTable = new SLRTable(new HashMap<>());
        FirstSets firstSets = FirstSets.calculateFirstSets(grammar);
        FollowSets followSets = FollowSets.calculateFollowSets(grammar, firstSets);
        List<Item> items = Item.computeItems(grammar);
        for (int state = 0; state < items.size(); state++) {
            Item item = items.get(state);
            for (Element direction : item.directions()) {
                Item next = item.next(grammar, direction);
                int nextState = items.indexOf(next);
                if (direction.type == ElementType.VARIABLE) {
                    slrTable.add(state, direction, Action.go(nextState));
                } else {
                    slrTable.add(state, direction, Action.shift(nextState));
                }
            }
            for (Rule rule : item.kernel()) {
                if (rule.isFinal()) {
                    if (rule.left == grammar.rules().get(0).left) {
                        slrTable.add(state, Element.terminal(0), Action.accept());
                    } else {
                        Set<Element> follows = followSets.get(rule.left);
                        for (Element follow : follows) {
                            int ruleIndex = grammar.rules().indexOf(rule.reset());
                            slrTable.add(state, follow, Action.reduce(ruleIndex));
                        }
                    }
                }
            }
        }
        return slrTable;
    }

}
