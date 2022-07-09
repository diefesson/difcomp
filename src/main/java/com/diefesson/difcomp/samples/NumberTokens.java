package com.diefesson.difcomp.samples;

import com.diefesson.difcomp.lexer.TokenType;

public enum NumberTokens implements TokenType {

    ONE(0x01),
    TWO(0x02),
    THREE(0x03);

    private final int id;

    NumberTokens(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

}