package com.diefesson.difcomp.grammar;

import java.util.ArrayList;
import java.util.List;

public class RuleSetBuilder {

    private final List<Rule> rules;

    public RuleSetBuilder() {
        rules = new ArrayList<>();
    }

    public RuleSetBuilder rule(String left, Object... right) {
        if (left == null) {
            throw new IllegalArgumentException("left can't be null");
        }
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

    public RuleSet build() {
        return new RuleSet(rules);
    }

}
