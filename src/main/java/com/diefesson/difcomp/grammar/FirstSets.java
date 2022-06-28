package com.diefesson.difcomp.grammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FirstSets extends ElementSet {

    public FirstSets(Map<Element, Set<Element>> firstSet) {
        super(firstSet);
    }

    @Override
    public Set<Element> get(Element key) {
        if (key.type == ElementType.EMPTY || key.type == ElementType.TERMINAL) {
            return Set.of(key);
        }
        return super.get(key);
    }

    public Set<Element> get(List<Element> elements) {
        if (elements.isEmpty()) {
            return Set.of(Element.empty());
        }
        Set<Element> firstSet = new HashSet<>();
        for (Element e : elements) {
            Set<Element> elementFirstSet = new HashSet<>(get(e));
            boolean hadEmpty = elementFirstSet.contains(Element.empty());
            elementFirstSet.remove(Element.empty());
            firstSet.addAll(elementFirstSet);
            if (!hadEmpty) {
                break;
            }
        }
        if (elements.stream().allMatch((e) -> get(e).contains(Element.empty()))) {
            firstSet.add(Element.empty());
        }
        return firstSet;
    }

    public static FirstSets calculateFirstSets(Grammar grammar) {
        FirstSets firstSets = new FirstSets(new HashMap<>());
        boolean updated;
        do {
            updated = false;
            for (Rule rule : grammar.rules()) {
                updated |= compute(firstSets, rule);
            }
        } while (updated);
        return firstSets;
    }

    private static boolean compute(FirstSets firstSets, Rule rule) {
        Set<Element> foundFirstSet = firstSets.get(rule.right());
        return firstSets.add(rule.left, foundFirstSet);
    }

}