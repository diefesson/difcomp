package com.diefesson.diflang.token;

public enum TokenType {

    KW_INT(0, "int"),
    KW_PRINT(1, "print"),
    OP_ASSING(2, "="),
    OP_ADD(3, "+"),
    OP_SUB(4, "-"),
    OP_MUL(5, "*"),
    OP_DIV(6, "/"),
    IDENTIFIER(7, null),
    CONST_STR(8, null),
    CONST_INT(9, null),
    PUNC_END(10, "\n"),
    END(11, null);

    public final int id;
    public final String symbol;

    TokenType(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    boolean hasSymbol() {
        return this.symbol != null;
    }

}
