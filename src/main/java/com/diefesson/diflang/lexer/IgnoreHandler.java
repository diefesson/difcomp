package com.diefesson.diflang.lexer;

import java.util.Scanner;

import com.diefesson.diflang.error.LexerException;
import com.diefesson.diflang.token.Token;

public class IgnoreHandler implements LexerHandler {

    @Override
    public Token handle(String match, Scanner scanner) throws LexerException {
        return null;
    }

}
