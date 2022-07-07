package com.diefesson.difcomp.lexer;

public enum CommonTokens implements TokenType {

    INVALID(-1),
    END(0);

    private final int id;

    private CommonTokens(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

}
