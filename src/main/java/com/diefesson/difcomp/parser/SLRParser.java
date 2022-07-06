package com.diefesson.difcomp.parser;

import static com.diefesson.difcomp.parser.SLRKey.key;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.error.ParserException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.lexer.TokenSource;

public class SLRParser {

    private final Grammar grammar;
    private final SLRTable table;
    private final Deque<Integer> states;
    private final Deque<Element> elements;
    private final TokenSource tokens;

    public SLRParser(Grammar grammar, TokenSource tokens) {
        this.grammar = grammar;
        this.table = SLRTable.compute(grammar);
        this.states = new LinkedList<>(List.of(0));
        this.elements = new LinkedList<>();
        this.tokens = tokens;
    }

    // TODO: AST generation

    public boolean cycle() throws LexerException, ParserException {
        SLRKey key = key(states.peek(), tokens.peek().element());
        if (table.hasEntry(key)) {
            Action action = table.get(key);
            switch (action.type) {
                case SHIFT:
                    states.addFirst(action.value);
                    elements.addFirst(tokens.peek().element());
                    return false;
                case REDUCE:
                    Rule rule = grammar.rules().get(action.value);
                    int ruleSize = rule.right().size();
                    for (int i = 0; i < ruleSize; i++) {
                        states.pollFirst();
                        elements.pollFirst();
                    }
                    elements.addFirst(rule.left);
                    Action go = table.get(key(states.peekFirst(), elements.peekFirst()));
                    states.add(go.value);
                    return false;
                case ACCEPT:
                    return true;
                default:
                    throw new IllegalStateException();
            }
        } else {
            throw new ParserException("unexpected %d token");
        }
    }

}