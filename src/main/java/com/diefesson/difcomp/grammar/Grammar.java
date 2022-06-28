package com.diefesson.difcomp.grammar;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Grammar {

    private final List<Rule> rules;

    public Grammar(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> rules() {
        return Collections.unmodifiableList(rules);
    }

    public List<Element> lefts() {
        return rules.stream().map((r) -> r.left).distinct().collect(Collectors.toList());
    }

    public static GrammarBuilder builder() {
        return new GrammarBuilder();
    }

}
