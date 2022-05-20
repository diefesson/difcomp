package com.diefesson.difcomp.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RuleSet {

    private final List<Rule> rules;

    public RuleSet(List<Rule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    public List<Rule> rules() {
        return Collections.unmodifiableList(rules);
    }

    public static RuleSetBuilder builder() {
        return new RuleSetBuilder();
    }

}
