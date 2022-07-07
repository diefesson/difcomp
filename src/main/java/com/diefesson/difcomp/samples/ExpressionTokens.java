package com.diefesson.difcomp.samples;

import com.diefesson.difcomp.token.TokenType;

public enum ExpressionTokens implements TokenType {
    OP_ADD(0x01),
    OP_MUL(0x02),
    CONST_VALUE(0x03);

    private final int id;

    ExpressionTokens(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }
}