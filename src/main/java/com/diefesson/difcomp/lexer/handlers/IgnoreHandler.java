package com.diefesson.difcomp.lexer.handlers;

import static com.diefesson.difcomp.lexer.CommonTokens.INVALID;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.DocPos;
import com.diefesson.difcomp.lexer.Token;

public class IgnoreHandler implements LexerHandler {

    @Override
    public Token handle(DocPos position, String match, Scanner scanner) throws LexerException {
        return new Token(position, INVALID, match, true);
    }

}
