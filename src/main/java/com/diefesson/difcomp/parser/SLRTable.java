package com.diefesson.difcomp.parser;

import static com.diefesson.difcomp.grammar.Element.terminal;
import static com.diefesson.difcomp.grammar.ElementType.VARIABLE;
import static com.diefesson.difcomp.lexer.CommonTokens.END;
import static com.diefesson.difcomp.parser.Action.accept;
import static com.diefesson.difcomp.parser.Action.go;
import static com.diefesson.difcomp.parser.Action.reduce;
import static com.diefesson.difcomp.parser.Action.shift;
import static com.diefesson.difcomp.parser.SLRKey.key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.diefesson.difcomp.error.ParserException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.grammar.FollowSets;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Item;
import com.diefesson.difcomp.grammar.Rule;

public class SLRTable {

    private final Map<SLRKey, List<Action>> table;

    protected SLRTable() {
        this.table = new LinkedHashMap<>();
    }

    public Set<SLRKey> keys() {
        return Collections.unmodifiableSet(table.keySet());
    }

    public List<Action> getList(SLRKey key) {
        List<Action> actions = table.get(key);
        if (actions != null) {
            return Collections.unmodifiableList(actions);
        } else {
            return List.of();
        }
    }

    public Action get(SLRKey key) throws ParserException {
        List<Action> actions = getList(key);
        if (actions.size() == 0) {
            throw new ParserException("no action for %s".formatted(key));
        }
        if (actions.size() > 1) {
            throw new ParserException("ambiguous action for %s".formatted(key));
        }
        return actions.get(0);
    }

    public boolean hasEntry(SLRKey key) {
        return getList(key).size() > 0;
    }

    protected void add(SLRKey key, Action action) {
        List<Action> actions = table.get(key);
        if (actions == null) {
            actions = new ArrayList<>();
            table.put(key, actions);
        }
        actions.add(action);
    }

    public static SLRTable compute(Grammar grammar) {
        SLRTable slrTable = new SLRTable();
        FirstSets firstSets = FirstSets.calculateFirstSets(grammar);
        FollowSets followSets = FollowSets.calculateFollowSets(grammar, firstSets);
        List<Item> items = Item.computeItems(grammar);
        for (int state = 0; state < items.size(); state++) {
            Item item = items.get(state);
            for (Element direction : item.directions()) {
                Item next = item.next(grammar, direction);
                int nextState = items.indexOf(next);
                if (direction.type == VARIABLE) {
                    slrTable.add(key(state, direction), go(nextState));
                } else {
                    slrTable.add(key(state, direction), shift(nextState));
                }
            }
            for (Rule rule : item.kernel()) {
                if (rule.isFinal()) {
                    if (rule.left == grammar.rules().get(0).left) {
                        slrTable.add(key(state, terminal(END)), accept());
                    } else {
                        Set<Element> follows = followSets.get(rule.left);
                        for (Element follow : follows) {
                            int ruleIndex = grammar.rules().indexOf(rule.reset());
                            slrTable.add(key(state, follow), reduce(ruleIndex));
                        }
                    }
                }
            }
        }
        return slrTable;
    }

}
