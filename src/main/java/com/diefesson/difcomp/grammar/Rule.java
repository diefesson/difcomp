package com.diefesson.difcomp.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rule {

    public final Element left;
    private final List<Element> prefix;
    private final List<Element> right;

    public Rule(Element left, List<Element> prefix, List<Element> right) {
        this.left = left;
        this.prefix = prefix;
        this.right = right;
    }

    public Rule(Element left, List<Element> right) {
        this(left, List.of(), right);
    }

    public List<Element> prefix() {
        return Collections.unmodifiableList(prefix);
    }

    public List<Element> right() {
        return Collections.unmodifiableList(right);
    }

    public Rule advance() {
        List<Element> newPrefix = new ArrayList<>(prefix);
        List<Element> newRight = new ArrayList<>(right);
        newPrefix.add(newRight.remove(0));
        return new Rule(left, newPrefix, newRight);
    }

    public boolean isFinal() {
        return right.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(left.variable).append(" ->");
        for (Element element : right) {
            builder.append(" ").append(element);
        }
        return builder.toString();
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
