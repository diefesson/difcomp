package com.diefesson.diflang;

import java.io.StringReader;
import com.diefesson.diflang.lexer.DifLangLexer;
import com.diefesson.diflang.token.Token;

public class Main {
    public static void main(String[] args) throws Exception {
        DifLangLexer lexer = new DifLangLexer(new StringReader("int intabc"));
        Token token;
        do {
            token = lexer.next();
            System.out.println("Token: %s".formatted(token));
        } while (token.typeId != 0);
    }
}
