package com.diefesson.difcomp.grammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FollowSets extends ElementSet {

    public FollowSets(Map<Element, Set<Element>> followSets) {
        super(followSets);
    }

    public static FollowSets calculateFollowSets(Grammar grammar, FirstSets firstSets) {
        FollowSets followSets = new FollowSets(new HashMap<>());
        followSets.add(grammar.rules().get(0).left, Set.of(Element.terminal(0)));
        boolean updated;
        do {
            updated = false;
            for (Rule rule : grammar.rules()) {
                updated |= compute(followSets, firstSets, rule);
            }
        } while (updated);
        return followSets;
    }

    private static boolean compute(FollowSets followSets, FirstSets firstSets, Rule rule) {
        Element left = rule.left;
        List<Element> right = rule.right();
        boolean updated = false;
        for (int i = 0; i < rule.right().size(); i++) {
            Element element = rule.right().get(i);
            if (element.type != ElementType.VARIABLE) {
                continue;
            }
            Set<Element> nextFirstSet = new HashSet<>(firstSets.get(right.subList(i + 1, right.size())));
            boolean hadEmpty = nextFirstSet.contains(Element.empty());
            nextFirstSet.remove(Element.empty());
            updated |= followSets.add(element, nextFirstSet);
            if (hadEmpty) {
                updated |= followSets.add(element, followSets.get(left));
            }
        }
        return updated;
    }

}
