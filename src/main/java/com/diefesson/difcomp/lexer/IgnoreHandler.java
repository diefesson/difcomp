package com.diefesson.difcomp.lexer;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.Token;

public class IgnoreHandler implements LexerHandler {

    @Override
    public Token handle(String match, Scanner scanner) throws LexerException {
        return null;
    }

}
