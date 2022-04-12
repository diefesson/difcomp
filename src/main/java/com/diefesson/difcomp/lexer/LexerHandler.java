package com.diefesson.difcomp.lexer;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

@FunctionalInterface
public interface LexerHandler {

    Token handle(DocPos position, String match, Scanner scanner) throws LexerException;

}
