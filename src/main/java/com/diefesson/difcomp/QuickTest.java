package com.diefesson.difcomp;

import static com.diefesson.difcomp.parser.ActionType.ACCEPT;
import static com.diefesson.difcomp.rustlike.RLGrammar.rlGrammar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.error.ParserException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.grammar.Rule;
import com.diefesson.difcomp.lexer.TokenSource;
import com.diefesson.difcomp.parser.Action;
import com.diefesson.difcomp.parser.SLRParser;
import com.diefesson.difcomp.rustlike.RLLexer;
import com.diefesson.difcomp.rustlike.RLParser;

public class QuickTest {

    public final static boolean ENABLE_QUICK_TEST = false;

    public static void quickTest(String[] args) {
        try {
            Grammar grammar = rlGrammar();
            TokenSource tokens = new RLLexer(new FileReader("samples/rustlike.txt"));
            SLRParser parser = new RLParser(tokens);
            // Grammar
            for (int i = 0; i < grammar.rules().size(); i++) {
                Rule rule = grammar.rules().get(i);
                System.out.printf("%d: %s%n", i, rule);
            }
            // Parsing
            Action action;
            do {
                String states = parser.states().stream().map(Object::toString).collect(Collectors.joining(", "));
                String elements = parser.elements().stream().map(Element::simpleName).collect(Collectors.joining(", "));
                System.out.println("Next:" + tokens.peek());
                System.out.println("States: " + states);
                System.out.println("Elements: " + elements);
                action = parser.cycle();
                System.out.println();
                System.out.println("Action: " + action);
                System.out.println();
            } while (action.type != ACCEPT);
        } catch (GrammarException | LexerException | ParserException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
