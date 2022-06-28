package com.diefesson.difcomp.grammar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ElementSet {
    private final Map<Element, Set<Element>> elementSets;

    public ElementSet(Map<Element, Set<Element>> elementSets) {
        this.elementSets = elementSets;
    }

    public Set<Element> keys() {
        return Collections.unmodifiableSet(elementSets.keySet());
    }

    public Set<Element> get(Element key) {
        if (!elementSets.containsKey(key)) {
            return Set.of();
        }
        return Collections.unmodifiableSet(elementSets.get(key));
    }

    protected boolean add(Element key, Set<Element> elements) {
        if (!elementSets.containsKey(key)) {
            elementSets.put(key, new HashSet<>());
        }
        return elementSets.get(key).addAll(elements);
    }
}
