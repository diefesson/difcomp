package com.diefesson.difcomp;

import static com.diefesson.difcomp.lexer.CommonTokens.END;
import static com.diefesson.difcomp.parser.ActionType.ACCEPT;
import static java.util.stream.Collectors.joining;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.diefesson.difcomp.error.CompileException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.lexer.Lexer;
import com.diefesson.difcomp.lexer.Token;
import com.diefesson.difcomp.parser.Action;
import com.diefesson.difcomp.parser.SLRParser;
import com.diefesson.difcomp.rustlike.RLLexer;
import com.diefesson.difcomp.rustlike.RLParser;

public class Main {

    public final static String SAMPLE_PATH = "samples/rustlike.txt";

    public static void runLexer(boolean debug, String path) {
        try (
                Reader reader = new FileReader(path);
                Lexer lexer = new RLLexer(reader, debug);) {
            Token token;
            do {
                token = lexer.next();
                System.out.printf("%08X %-20s | %-6s | %s%n",
                        token.type.id(),
                        token.type,
                        token.position,
                        token.lexeme);
            } while (token.type != END);
        } catch (CompileException e) {
            System.out.println("Compile error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runParser(String path) {
        try (
                Reader reader = new FileReader(path);
                Lexer lexer = new RLLexer(reader);) {
            SLRParser parser = new RLParser(lexer);
            int cycle = 1;
            Action action;
            System.out.println("---------|");
            do {
                String states = parser.states().stream().map(Object::toString).collect(joining(", "));
                String elements = parser.elements().stream().map(Element::simpleName).collect(joining(", "));
                Token token = lexer.peek();
                System.out.printf("%-8s | %05d%n", "CYCLE", cycle++);
                System.out.printf("%-8s | %s%n", "NEXT", token);
                System.out.printf("%-8s | %s%n", "STATES", states);
                System.out.printf("%-8s | %s%n", "ELEMENTS", elements);
                action = parser.cycle();
                System.out.printf("%-8s | %s%n", "ACTION", action);
                System.out.println("---------|");
            } while (action.type != ACCEPT);
        } catch (CompileException e) {
            System.out.println("Compile error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (QuickTest.ENABLE_QUICK_TEST) {
            QuickTest.quickTest(args);
            return;
        }
        if (args.length != 2) {
            printUsage();
            return;
        }
        String command = args[0];
        String path = args[1];
        switch (command) {
            case "lexer":
                runLexer(false, path);
                break;
            case "lexerdebug":
                runLexer(true, path);
                break;
            case "parser":
                runParser(path);
                break;
            default:
                printUsage();
        }
    }

    public static void printUsage() {
        System.out.println("usage: <command> <path>");
        System.out.println("where <command> is \"lexer\" or \"lexerdebug\" or \"parser\"");
        System.out.println("and <path> is the source file");
    }
}
