package com.diefesson.difcomp.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rule {

    public final Var left;
    private final List<GrammarItem> right;

    public Rule(Var left, List<GrammarItem> right) {
        this.left = left;
        this.right = new ArrayList<>(right);
    }

    public List<GrammarItem> right() {
        return Collections.unmodifiableList(right);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.name).append(" ->");
        for (GrammarItem r : right) {
            sb.append(" ").append(r);
        }
        return sb.toString();
    }
}
