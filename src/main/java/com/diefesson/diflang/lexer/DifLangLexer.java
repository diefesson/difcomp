package com.diefesson.diflang.lexer;

import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.diefesson.diflang.error.LexerException;
import com.diefesson.diflang.token.IdentifierToken;
import com.diefesson.diflang.token.IntToken;
import com.diefesson.diflang.token.StringToken;
import com.diefesson.diflang.token.Token;
import com.diefesson.diflang.token.TokenType;

public class DifLangLexer extends Lexer {

    private int lineCount = 0;

    public DifLangLexer(Reader reader) {
        super(reader, 32);
        // TODO: reorganize classes packages
        // TODO: reorganize token priority
        on(Patterns.WHITESPACE, new IgnoreHandler());
        on(Patterns.KW_INT, new SimpleHandler(TokenType.KW_INT.id));
        on(Patterns.KW_PRINT, new SimpleHandler(TokenType.KW_PRINT.id));
        on(Patterns.OP_ASSING, new SimpleHandler(TokenType.OP_ASSING.id));
        on(Patterns.OP_ADD, new SimpleHandler(TokenType.OP_ADD.id));
        on(Patterns.OP_SUB, new SimpleHandler(TokenType.OP_SUB.id));
        on(Patterns.OP_MUL, new SimpleHandler(TokenType.OP_MUL.id));
        on(Patterns.OP_DIV, new SimpleHandler(TokenType.OP_DIV.id));
        on(Patterns.PUNC_END, new SimpleHandler(TokenType.PUNC_END.id));
        on(Patterns.IDENTIFIER, this::handleIdentifier);
        on(Patterns.CONST_INT, this::handleInt);
        on(Patterns.CONST_STRING, this::handleString);
    }

    private Token handleIdentifier(String match, Scanner scanner) {
        return new IdentifierToken(match);
    }

    private Token handleInt(String match, Scanner scanner) throws LexerException {
        try {
            int value = Integer.parseInt(match);
            return new IntToken(value);
        } catch (NumberFormatException e) {
            throw new LexerException("error parsing int", e);
        }
    }

    private Token handleString(String match, Scanner scanner) throws LexerException {
        try {
            String processed = processString(match);
            return new StringToken(processed);
        } catch (NoSuchElementException e) {
            throw new LexerException("string lexing exception at line %d ".formatted(lineCount), e);
        }
    }

    private String processString(String original) {
        return original.substring(1, original.length() - 1);
    }

}
