package com.diefesson.diflang.token;

public abstract class ValueToken<T> extends Token {

    private final T value;

    public ValueToken(TokenType type, T value) {
        super(type);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String getSymbol() {
        return value.toString();
    }
}
