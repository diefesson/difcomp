package com.diefesson.difcomp;

import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.grammar.FollowSets;
import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.grammar.Grammar;

public class QuickTest {

    public final static boolean ENABLE_QUICK_TEST = true;

    public static void quickTest(String[] args) {
        try {
            Grammar grammar = Grammar.builder()
                    .rule("I", "S")
                    .rule("S", "S", 1, "M")
                    .rule("S", "M")
                    .rule("M", "M", 2, "C")
                    .rule("M", "C")
                    .rule("C", 3)
                    .build();
            for (Rule r : grammar.rules()) {
                System.out.println(r);
            }
            FirstSets firstSets = FirstSets.calculateFirstSets(grammar);
            FollowSets followSets = FollowSets.calculateFollowSets(grammar, firstSets);
            for (Element e : grammar.lefts()) {
                System.out.println(e);
                for (Element item : followSets.get(e)) {
                    System.out.println("   " + item);
                }
            }
        } catch (GrammarException e) {
            e.printStackTrace();
        }
    }

}
