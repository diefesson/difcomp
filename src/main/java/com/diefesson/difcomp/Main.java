package com.diefesson.difcomp;

import java.io.FileReader;
import java.io.Reader;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.rustlike.RustLikeLexer;
import com.diefesson.difcomp.rustlike.TokenType;
import com.diefesson.difcomp.token.Token;

public class Main {

    public static String samplePath = "samples/rustlike.txt";

    public static void main(String[] args) throws Exception {
        try (
                Reader reader = new FileReader(samplePath);
                RustLikeLexer lexer = new RustLikeLexer(reader, false);) {
            Token token;
            do {
                token = lexer.next();
                System.out.println("%s %s".formatted(TokenType.fromId(token.typeId), token));
            } while (token.typeId != 0);
        } catch (LexerException e) {
            System.out.println(e.getMessage());
        }
    }
}
