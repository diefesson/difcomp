package com.diefesson.difcomp.grammar;

import java.util.ArrayList;
import java.util.List;

public class RuleSetBuilder {

    private final List<Rule> rules;

    public RuleSetBuilder() {
        rules = new ArrayList<>();
    }

    public RuleSet build() {
        checkRightRefs();
        return new RuleSet(rules);
    }

    public RuleSetBuilder emptyRule(String left) {
        checkLeft(left);
        rules.add(new Rule(new Var(left), List.of(Empty.EMPTY)));
        return this;
    }

    public RuleSetBuilder rule(String left, Object... right) {
        checkLeft(left);
        checkRight(right);
        List<GrammarItem> ruleRight = new ArrayList<>();
        for (Object r : right) {
            if (r instanceof String) {
                ruleRight.add(new Var((String) r));
            } else if (r instanceof Integer) {
                ruleRight.add(new Term((Integer) r));
            }
        }
        rules.add(new Rule(new Var(left), ruleRight));
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
            if (!(r instanceof String || r instanceof Integer)) {
                throw new IllegalArgumentException("right should contain only variable names and terminal token ids");
            }
        }
    }

    private void checkRightRefs() {
        for (int ri = 0; ri < rules.size(); ri++) {
            Rule rule = rules.get(ri);
            for (int pi = 0; pi < rule.right().size(); pi++) {
                GrammarItem production = rule.right().get(pi);
                if (production instanceof Var &&
                        rules.stream().noneMatch((ru) -> ru.left.equals(production))) {
                    String message = "rule %d, grammar item %d for %s refers to unknown %s".formatted(
                            ri, pi, rule.left, production);
                    throw new IllegalStateException(message);
                }
            }
        }
    }

}
