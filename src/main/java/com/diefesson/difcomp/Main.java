package com.diefesson.difcomp;

import java.io.StringReader;

import com.diefesson.difcomp.dl.DifLangLexer;
import com.diefesson.difcomp.token.Token;

public class Main {
    public static void main(String[] args) throws Exception {
        DifLangLexer lexer = new DifLangLexer(new StringReader("a\na"));
        Token token;
        do {
            token = lexer.next();
            System.out.println("Token: %s".formatted(token));
        } while (token.typeId != 0);
    }
}
