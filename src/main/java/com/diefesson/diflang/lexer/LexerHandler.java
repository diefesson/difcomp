package com.diefesson.diflang.lexer;

import java.util.Scanner;

import com.diefesson.diflang.error.LexerException;
import com.diefesson.diflang.token.Token;

@FunctionalInterface
public interface LexerHandler {

    Token handle(String match, Scanner scanner) throws LexerException;

}
