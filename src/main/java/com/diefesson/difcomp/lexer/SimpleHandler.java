package com.diefesson.difcomp.lexer;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.TokenType;
import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

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
