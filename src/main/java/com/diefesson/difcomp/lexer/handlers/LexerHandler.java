package com.diefesson.difcomp.lexer.handlers;

import java.util.Scanner;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.DocPos;
import com.diefesson.difcomp.lexer.Token;

@FunctionalInterface
public interface LexerHandler {

    Token handle(DocPos position, String match, Scanner scanner) throws LexerException;

}
