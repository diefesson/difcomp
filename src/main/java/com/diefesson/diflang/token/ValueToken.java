package com.diefesson.diflang.token;

public abstract class ValueToken<T> extends Token {

    public final T value;

    public ValueToken(int type, T value) {
        super(type, value.toString());
        this.value = value;
    }

}
