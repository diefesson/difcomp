package com.diefesson.difcomp.dl;

import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.diefesson.difcomp.dl.token.IdentifierToken;
import com.diefesson.difcomp.dl.token.IntToken;
import com.diefesson.difcomp.dl.token.StringToken;
import com.diefesson.difcomp.dl.token.TokenType;
import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.IgnoreHandler;
import com.diefesson.difcomp.lexer.Lexer;
import com.diefesson.difcomp.lexer.SimpleHandler;
import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

public class DifLangLexer extends Lexer {

    private int lineCount = 0;

    public DifLangLexer(Reader reader) {
        super(reader, 32);
        on(Patterns.WHITESPACE, new IgnoreHandler());
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

    private Token handleIdentifier(DocPos position, String match, Scanner scanner) {
        switch (match) {
            case Patterns.KW_INT:
                return new Token(position, TokenType.KW_INT.id, match);
            case Patterns.KW_PRINT:
                return new Token(position, TokenType.KW_PRINT.id, match);
            default:
                return new IdentifierToken(position, match);
        }
    }

    private Token handleInt(DocPos position, String match, Scanner scanner) throws LexerException {
        try {
            int value = Integer.parseInt(match);
            return new IntToken(position, value);
        } catch (NumberFormatException e) {
            throw new LexerException("error parsing int", e);
        }
    }

    private Token handleString(DocPos position, String match, Scanner scanner) throws LexerException {
        try {
            String processed = processString(match);
            return new StringToken(position, match, processed);
        } catch (NoSuchElementException e) {
            throw new LexerException("string lexing exception at line %d ".formatted(lineCount), e);
        }
    }

    private String processString(String original) {
        return original.substring(1, original.length() - 1).replaceAll("\\\"", "\"");
    }

}
