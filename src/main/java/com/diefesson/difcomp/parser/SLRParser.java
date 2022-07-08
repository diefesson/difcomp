package com.diefesson.difcomp.parser;

import static com.diefesson.difcomp.grammar.Element.empty;
import static com.diefesson.difcomp.parser.SLRKey.key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.error.ParserException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.lexer.TokenSource;

public class SLRParser {

    private final Grammar grammar;
    private final SLRTable table;
    private final LinkedList<Integer> states;
    private final LinkedList<Element> elements;
    private final TokenSource tokens;
    private boolean pendingGo = false;

    public SLRParser(Grammar grammar, TokenSource tokens) {
        this.grammar = grammar;
        this.table = SLRTable.compute(grammar);
        this.states = new LinkedList<>(List.of(0));
        this.elements = new LinkedList<>();
        this.tokens = tokens;
    }

    public List<Integer> states() {
        return Collections.unmodifiableList(states);
    }

    public List<Element> elements() {
        return Collections.unmodifiableList(elements);
    }

    // TODO: AST generation

    public Action cycle() throws LexerException, ParserException {
        Element peekedNext = tokens.peek().element();
        Element peekedTop = elements.peekLast();
        int peekedState = states.peekLast();
        SLRKey actionKey = key(peekedState, peekedNext);
        SLRKey emptyKey = key(peekedState, empty());
        SLRKey goKey = key(peekedState, peekedTop);
        Action action;
        if (pendingGo) {
            action = table.get(goKey);
            states.add(action.value);
            pendingGo = false;
        } else if (table.hasEntry(actionKey)) {
            action = table.get(actionKey);
            switch (action.type) {
                case SHIFT:
                    states.addLast(action.value);
                    elements.addLast(tokens.next().element());
                    break;
                case REDUCE:
                    Rule rule = grammar.rules().get(action.value);
                    int ruleSize = rule.right().size();
                    dropStates(ruleSize);
                    consumeElements(ruleSize);
                    elements.addLast(rule.left);
                    pendingGo = true;
                    break;
                case ACCEPT:
                    break;
                default:
                    throw new IllegalStateException("unexpected action: %s".formatted(action));
            }
        } else if (table.hasEntry(emptyKey)) {
            action = table.get(emptyKey);
            states.addLast(action.value);
        } else {
            throw new ParserException("no action");
        }
        return action;
    }

    private void dropStates(int count) {
        for (int i = 0; i < count; i++) {
            states.pollLast();
        }
    }

    private List<Element> consumeElements(int count) {
        List<Element> consumed = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            consumed.add(elements.pollLast());
        }
        return consumed;
    }

}