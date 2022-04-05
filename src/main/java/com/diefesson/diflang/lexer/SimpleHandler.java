package com.diefesson.diflang.lexer;

import java.util.Scanner;

import com.diefesson.diflang.error.LexerException;
import com.diefesson.diflang.token.Token;

public class SimpleHandler implements LexerHandler {

    private final int typeId;

    public SimpleHandler(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public Token handle(String match, Scanner scanner) throws LexerException {
        return new Token(typeId, match);
    }

}
