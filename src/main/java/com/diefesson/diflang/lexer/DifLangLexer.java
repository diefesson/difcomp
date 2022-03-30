package com.diefesson.diflang.lexer;

import java.util.Scanner;

import com.diefesson.diflang.CompileException;
import com.diefesson.diflang.token.Token;

public class DifLangLexer implements Lexer, AutoCloseable {

    private final Scanner scanner;

    public DifLangLexer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Token next() throws CompileException {
        throw new CompileException("Not implemented");
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }

}
