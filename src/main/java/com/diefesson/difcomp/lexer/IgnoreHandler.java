package com.diefesson.difcomp.lexer;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

public class IgnoreHandler implements LexerHandler {

    @Override
    public Token handle(DocPos position, String match, Scanner scanner) throws LexerException {
        return new Token(position, -1, match, true);
    }

}
