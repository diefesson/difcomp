package com.diefesson.difcomp;

import static com.diefesson.difcomp.parser.ActionType.ACCEPT;
import static com.diefesson.difcomp.rustlike.RLGrammar.rlGrammar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.error.ParserException;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Item;
import com.diefesson.difcomp.lexer.TokenSource;
import com.diefesson.difcomp.parser.Action;
import com.diefesson.difcomp.parser.SLRKey;
import com.diefesson.difcomp.parser.SLRParser;
import com.diefesson.difcomp.parser.SLRTable;
import com.diefesson.difcomp.rustlike.RLLexer;

public class QuickTest {

    public final static boolean ENABLE_QUICK_TEST = true;

    public static void quickTest(String[] args) {
        try {
            Grammar grammar = rlGrammar();
            TokenSource tokens = new RLLexer(new FileReader("samples/rustlike.txt"));
            List<Item> items = Item.computeItems(grammar);
            SLRTable table = SLRTable.compute(grammar);
            SLRParser parser = new SLRParser(grammar, tokens);
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.printf("%d: %s%n", i, item);
            }
            System.out.println("===");
            for (SLRKey key : table.keys()) {
                System.out.println(key + ":");
                for (Action a : table.getList(key)) {
                    System.out.println("\t" + a);
                }
            }
            System.out.println("====");
            Action action;
            do {
                String states = parser.states().stream().map(Object::toString).collect(Collectors.joining(","));
                String elements = parser.elements().stream().map(Object::toString).collect(Collectors.joining(","));
                System.out.println("Next:" + tokens.peek());
                System.out.println("States: " + states);
                System.out.println("Elements: " + elements);
                action = parser.cycle();
                System.out.println("Action: " + action);
                System.out.println();
            } while (action.type != ACCEPT);
        } catch (GrammarException | LexerException | ParserException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
