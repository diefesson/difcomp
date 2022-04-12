package com.diefesson.difcomp;

import java.io.FileReader;

import com.diefesson.difcomp.dl.DifLangLexer;
import com.diefesson.difcomp.dl.token.TokenType;
import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.Token;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            DifLangLexer lexer = new DifLangLexer(new FileReader("testfile.txt"));
            Token token;
            do {
                token = lexer.next();
                System.out.println("Token: %s %s".formatted(TokenType.ofId(token.typeId), token));
            } while (token.typeId != 0);
        } catch (LexerException e) {
            System.out.println(e.getMessage());
        }
    }
}
