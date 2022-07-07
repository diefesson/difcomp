package com.diefesson.difcomp.grammar;

import static com.diefesson.difcomp.grammar.Element.empty;
import static com.diefesson.difcomp.grammar.Element.terminal;
import static com.diefesson.difcomp.grammar.Element.variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.token.TokenType;

public class GrammarBuilder {

    private final List<Rule> rules;

    public GrammarBuilder() {
        rules = new ArrayList<>();
    }

    public Grammar build() throws GrammarException {
        checkRefs();
        return new Grammar(rules);
    }

    public GrammarBuilder emptyRule(String left) {
        checkLeft(left);
        rules.add(new Rule(variable(left), List.of(empty())));
        return this;
    }

    public GrammarBuilder rule(String left, Object... right) {
        checkLeft(left);
        checkRight(right);
        List<Element> ruleRight = new ArrayList<>();
        for (Object r : right) {
            if (r instanceof String) {
                ruleRight.add(variable((String) r));
            } else if (r instanceof TokenType) {
                ruleRight.add(terminal((TokenType) r));
            } else {
                throw new IllegalArgumentException("Invalid argument: " + r);
            }
        }
        rules.add(new Rule(variable(left), ruleRight));
        return this;
    }

    private static void checkLeft(String left) {
        if (left == null) {
            throw new IllegalArgumentException("left can't be null");
        }
    }

    private void checkRight(Object... right) {
        if (right.length == 0) {
            throw new IllegalArgumentException("right can't be empty");
        }
        for (Object r : right) {
            if (r == null) {
                throw new IllegalArgumentException("right can't contain null");
            }
            if (!(r instanceof String || r instanceof TokenType)) {
                throw new IllegalArgumentException("right should contain only variable names and terminal token types");
            }
        }
    }

    private void checkRefs() throws GrammarException {
        Set<Element> leftVars = rules
                .stream()
                .map((r) -> r.left)
                .collect(Collectors.toSet());
        Set<Element> rightVars = rules
                .stream()
                .map((r) -> r.right())
                .flatMap((es) -> es.stream())
                .filter((e) -> e.type == ElementType.VARIABLE)
                .collect(Collectors.toSet());
        Set<Element> missingOnRight = new HashSet<>(leftVars);
        missingOnRight.removeAll(rightVars);
        missingOnRight.remove(rules.get(0).left); // Don't consider the start rule
        Set<Element> missingOnLeft = new HashSet<>(rightVars);
        missingOnLeft.removeAll(leftVars);
        if (!missingOnRight.isEmpty()) {
            String vars = missingOnRight
                    .stream()
                    .map((v) -> v.variable)
                    .collect(Collectors.joining(", "));
            throw new GrammarException("Variable(s) %s are never generated".formatted(vars));
        }
        ;
        if (!missingOnLeft.isEmpty()) {
            String vars = missingOnLeft
                    .stream()
                    .map((v) -> v.variable)
                    .collect(Collectors.joining(", "));
            throw new GrammarException("Variable(s) %s are not defined".formatted(vars));
        }
    }

}
