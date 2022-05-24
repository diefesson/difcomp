package com.diefesson.difcomp;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.grammar.GrammarItem;
import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.grammar.RuleSet;
import com.diefesson.difcomp.grammar.Var;

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
        Map<Var, Set<GrammarItem>> fs = FirstSets.calculateFirstSets(rs);
        for (Entry<Var, Set<GrammarItem>> e : fs.entrySet()) {
            System.out.println(e.getKey());
            for (GrammarItem item : e.getValue()) {
                System.out.println("   " + item);
            }
        }
    }

}
