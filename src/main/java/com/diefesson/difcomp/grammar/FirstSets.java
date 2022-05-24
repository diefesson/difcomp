package com.diefesson.difcomp.grammar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FirstSets {

    private FirstSets() {
    }

    public static Map<Var, Set<GrammarItem>> calculateFirstSets(RuleSet ruleSet) {
        List<Rule> rules = ruleSet.rules();
        Map<Var, Set<GrammarItem>> firstSets = new HashMap<>();
        rules.stream()
                .map((r) -> r.left)
                .distinct()
                .forEach((v) -> firstSets.put(v, new HashSet<>()));
        boolean updated;
        do {
            updated = false;
            for (Rule rule : rules) {
                updated |= compute(firstSets, rule);
            }
        } while (updated);
        return firstSets;
    }

    private static boolean compute(Map<Var, Set<GrammarItem>> firstSets, Rule rule) {
        boolean updated = false;
        Var left = rule.left;
        List<GrammarItem> right = rule.right();
        Set<GrammarItem> firstSet = firstSets.get(left);
        for (int i = 0; i < right.size(); i++) {
            GrammarItem item = right.get(i);
            if (item instanceof Var) {
                Set<GrammarItem> firsts = new HashSet<>(firstSets.get(item));
                Boolean containsEmpty = firsts.remove(Empty.EMPTY);
                updated |= firstSet.addAll(firsts);
                if (!containsEmpty) {
                    break;
                }
            } else {
                updated |= firstSet.add(item);
                break;
            }
            if (i == right.size() - 1) {
                updated |= firstSet.add(Empty.EMPTY);
            }
        }
        return updated;
    }

}