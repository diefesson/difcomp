package com.diefesson.difcomp.grammar;

import java.util.Collections;
import java.util.List;

public class Rule {

    public final Element left;
    private final List<Element> right;

    public Rule(Element left, List<Element> right) {
        this.left = left;
        this.right = right;
    }

    public List<Element> right() {
        return Collections.unmodifiableList(right);
    }

    public boolean isEmpty() {
        return right.get(0).type == ElementType.EMPTY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.variable).append(" ->");
        for (Element r : right) {
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
