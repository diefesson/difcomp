package com.diefesson.difcomp;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.grammar.RuleSet;

public class QuickTest {

    public final static boolean ENABLE_QUICK_TEST = true;

    public static void quickTest(String[] args) {
        RuleSet rs = RuleSet.builder()
                .rule("EXPR", 0)
                .rule("EXPR", "SUBEXPR", "BINOP", "SUBEXPR")
                .rule("SUBEXPR", 3)
                .emptyRule("SUBEXPR")
                .rule("BINOP", "PLUS")
                .rule("BINOP", "MINUS")
                .emptyRule("BINOP")
                .rule("PLUS", 1)
                .rule("MINUS", 2)
                .build();
        for (Rule r : rs.rules()) {
            System.out.println(r);
        }
        Map<Element, Set<Element>> fs = FirstSets.calculateFirstSets(rs);
        for (Entry<Element, Set<Element>> e : fs.entrySet()) {
            System.out.println(e.getKey());
            for (Element item : e.getValue()) {
                System.out.println("   " + item);
            }
        }
    }

}
