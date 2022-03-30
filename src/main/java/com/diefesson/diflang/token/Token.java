package com.diefesson.diflang.token;

public class Token {

    private final TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public String getSymbol() {
        return type.symbol;
    }

    @Override
    public String toString() {
        String className = getClass().getName();
        String typeName = type.name();
        String symbol = getSymbol();
        return "< %s %s %s >".formatted(className, typeName, symbol);
    }

}
