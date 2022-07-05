package com.diefesson.difcomp.grammar;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Item {
    private final Set<Rule> kernel, closure;

    public Item(Set<Rule> kernel, Set<Rule> closure) {
        this.kernel = kernel;
        this.closure = closure;
    }

    public Set<Rule> kernel() {
        return Collections.unmodifiableSet(kernel);
    }

    public Set<Rule> closure() {
        return Collections.unmodifiableSet(closure);
    }

    public Set<Element> directions() {
        return closure.stream()
                .map((rule) -> rule.right().get(0))
                .collect(Collectors.toSet());
    }

    public Item next(Grammar grammar, Element reading) {
        Set<Rule> nextKernel = computeKernel(closure, reading);
        Set<Rule> nextClosure = computeClosure(grammar, nextKernel);
        return new Item(nextKernel, nextClosure);
    }

    public Set<Item> nexts(Grammar grammar) {
        return closure.stream()
                .map((rule) -> rule.right().get(0))
                .map((reading) -> next(grammar, reading))
                .collect(Collectors.toSet());
    }

    @Override
    public int hashCode() {
        return kernel.hashCode() + closure.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Item)) {
            return false;
        }
        Item o = (Item) other;
        return kernel.equals(o.kernel) && closure.equals(o.closure);
    }

    public static Item start(Grammar grammar) {
        Rule firstRule = grammar.rules().get(0);
        Set<Rule> kernel = Set.of(firstRule);
        Set<Rule> closure = computeClosure(grammar, kernel);
        return new Item(kernel, closure);
    }

    private static Set<Rule> computeKernel(Set<Rule> from, Element reading) {
        return from.stream()
                .filter((rule) -> !rule.right().isEmpty())
                .filter((rule) -> rule.right().get(0).equals(reading))
                .map((rule) -> rule.advance())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Set<Rule> computeClosure(Grammar grammar, Set<Rule> kernel) {
        Set<Rule> closure = new LinkedHashSet<>();
        Queue<Rule> queue = new LinkedList<>();
        closure.addAll(kernel);
        queue.addAll(kernel);
        while (!queue.isEmpty()) {
            Rule next = queue.poll();
            if (!next.isFinal()) {
                Element rightFirst = next.right().get(0);
                Set<Rule> dependencies = grammar.rules()
                        .stream()
                        .filter((r) -> r.left.equals(rightFirst))
                        .collect(Collectors.toSet());
                for (Rule dependecy : dependencies) {
                    if (closure.add(dependecy)) {
                        queue.add(dependecy);
                    }
                }
            }
        }
        return closure;
    }

}