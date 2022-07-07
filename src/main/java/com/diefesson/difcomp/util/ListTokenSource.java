package com.diefesson.difcomp.util;

import java.util.List;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.TokenSource;
import com.diefesson.difcomp.token.Token;

public class ListTokenSource implements TokenSource {

    private int index;
    private List<Token> tokens;

    public ListTokenSource(List<Token> tokens) {
        this.index = 0;
        this.tokens = tokens;
    }

    @Override
    public Token next() throws LexerException {
        return tokens.get(index++);
    }

    @Override
    public Token peek() throws LexerException {
        return tokens.get(index);
    }
}
