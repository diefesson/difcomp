package com.diefesson.difcomp;

import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.grammar.RuleSet;

public class QuickTest {

    public final static boolean ENABLE_QUICK_TEST = true;

    public static void quickTest(String[] args) {
        RuleSet rs = RuleSet.builder()
                .rule("S", "X", "S")
                .emptyRule("S")
                .rule("X", 0)
                .rule("X", 1)
                .build();
        for (Rule r : rs.rules()) {
            System.out.println(r);
        }
    }

}
