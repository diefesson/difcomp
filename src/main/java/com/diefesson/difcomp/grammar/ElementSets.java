package com.diefesson.difcomp.grammar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ElementSets {
    private final Map<Element, Set<Element>> elementSets;

    public ElementSets(Map<Element, Set<Element>> elementSets) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element key : keys()) {
            Set<Element> set = get(key);
            sb.append(key).append(" : ").append(set).append('\n');
        }
        return sb.toString();
    }
}
