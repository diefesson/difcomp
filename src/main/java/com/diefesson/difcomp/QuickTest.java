package com.diefesson.difcomp;

import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.grammar.Grammar;

public class QuickTest {

    public final static boolean ENABLE_QUICK_TEST = true;

    public static void quickTest(String[] args) {
        try {
            Grammar rs = Grammar.builder()
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
            FirstSets fs = FirstSets.calculateFirstSets(rs);
            for (Element e : fs.keys()) {
                System.out.println(e);
                for (Element item : fs.get(e)) {
                    System.out.println("   " + item);
                }
            }
        } catch (GrammarException e) {
            e.printStackTrace();
        }
    }

}
