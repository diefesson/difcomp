package com.diefesson.difcomp.grammar;

import java.util.Collections;
import java.util.List;

public class Grammar {

    private final List<Rule> rules;

    public Grammar(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> rules() {
        return Collections.unmodifiableList(rules);
    }

    public static GrammarBuilder builder() {
        return new GrammarBuilder();
    }

}
