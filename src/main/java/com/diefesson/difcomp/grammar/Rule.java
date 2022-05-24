package com.diefesson.difcomp.grammar;

import java.util.Collections;
import java.util.List;

public class Rule {

    public final Var left;
    private final List<GrammarItem> right;

    public Rule(Var left, List<GrammarItem> right) {
        this.left = left;
        this.right = right;
    }

    public List<GrammarItem> right() {
        return Collections.unmodifiableList(right);
    }

    public boolean isEmpty() {
        return right.get(0).equals(Empty.EMPTY);
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

    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Rule)) {
            return false;
        }
        Rule o = (Rule) other;
        return left.equals(o.left) && right.equals(o.right);
    }
}
