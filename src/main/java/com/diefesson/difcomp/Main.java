package com.diefesson.difcomp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.Lexer;
import com.diefesson.difcomp.rustlike.RustLikeLexer;
import com.diefesson.difcomp.token.CommonTokens;
import com.diefesson.difcomp.token.Token;

public class Main {

    public final static String SAMPLE_PATH = "samples/rustlike.txt";

    public static void runLexer(boolean debug, String path) {
        try (
                Reader reader = new FileReader(path);
                Lexer lexer = new RustLikeLexer(reader, debug);) {
            Token token;
            do {
                token = lexer.next();
                System.out.printf("%08x %-20s | %-6s | %s%n",
                        token.type.id(),
                        token.type,
                        token.position,
                        token.lexeme);
            } while (token.type != CommonTokens.END);
        } catch (LexerException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.printf("Could not find file %s%n", path);
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
            default:
                printUsage();
        }
    }

    public static void printUsage() {
        System.out.println("usage: <command> <path>");
        System.out.println("where <command> is \"lexer\" or \"lexerdebug\"");
        System.out.println("and <path> is the source file");
    }
}
