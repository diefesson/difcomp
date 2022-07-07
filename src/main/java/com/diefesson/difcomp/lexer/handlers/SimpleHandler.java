package com.diefesson.difcomp.lexer.handlers;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.DocPos;
import com.diefesson.difcomp.lexer.Token;
import com.diefesson.difcomp.lexer.TokenType;

public class SimpleHandler implements LexerHandler {

    private final TokenType tokenType;

    public SimpleHandler(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public Token handle(DocPos position, String match, Scanner scanner) throws LexerException {
        return new Token(position, tokenType, match);
    }

}
