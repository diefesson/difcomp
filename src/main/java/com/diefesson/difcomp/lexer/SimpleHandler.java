package com.diefesson.difcomp.lexer;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

public class SimpleHandler implements LexerHandler {

    private final int typeId;

    public SimpleHandler(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public Token handle(DocPos position, String match, Scanner scanner) throws LexerException {
        return new Token(position, typeId, match);
    }

}
