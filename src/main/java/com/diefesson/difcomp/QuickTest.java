package com.diefesson.difcomp;

import java.util.List;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Item;
import com.diefesson.difcomp.parser.Action;
import com.diefesson.difcomp.parser.SLRKey;
import com.diefesson.difcomp.parser.SLRTable;

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
            List<Item> items = Item.computeItems(grammar);
            SLRTable slrTable = SLRTable.compute(grammar);
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.println(i + ":");
                System.out.println(item);
            }
            for (SLRKey key : slrTable.keys()) {
                System.out.println(key + ":");
                for (Action action : slrTable.getList(key)) {
                    System.out.println("\t" + action);
                }
            }
        } catch (GrammarException e) {
            e.printStackTrace();
        }
    }

}
