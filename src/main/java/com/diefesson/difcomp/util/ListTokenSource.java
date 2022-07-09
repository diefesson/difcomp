package com.diefesson.difcomp.util;

import java.util.ArrayList;
import java.util.List;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.DocPos;
import com.diefesson.difcomp.lexer.Token;
import com.diefesson.difcomp.lexer.TokenSource;
import com.diefesson.difcomp.lexer.TokenType;

public class ListTokenSource implements TokenSource {

    private int index;
    private List<Token> tokens;

    public ListTokenSource(List<Token> tokens) {
        this.index = 0;
        this.tokens = tokens;
    }

    public int index() {
        return index;
    }

    @Override
    public Token next() throws LexerException {
        return tokens.get(index++);
    }

    @Override
    public Token peek() throws LexerException {
        return tokens.get(index);
    }

    public static ListTokenSource fromTokenTypes(List<TokenType> tokenTypes) {
        int count = 0;
        List<Token> tokens = new ArrayList<>();
        for (TokenType tokenType : tokenTypes) {
            tokens.add(new Token(new DocPos(0, count), tokenType, tokenType.toString()));
        }
        return new ListTokenSource(tokens);
    }
}
