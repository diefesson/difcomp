package com.diefesson.difcomp;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Item;
import com.diefesson.difcomp.grammar.Rule;

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
            Item item = Item.start(grammar);
            System.out.println("Kernel: ");
            for (Rule rule : item.kernel()) {
                System.out.printf("\t%s%n", rule);
            }
            System.out.println("Closure: ");
            for (Rule rule : item.closure()) {
                System.out.printf("\t%s%n", rule);
            }

        } catch (GrammarException e) {
            e.printStackTrace();
        }
    }

}
