package com.diefesson.difcomp.token;

public abstract class ValueToken<T> extends Token {

    public final T value;

    public ValueToken(int typeId, T value) {
        this(typeId, value.toString(), value);
    }

    public ValueToken(int typeId, String lexeme, T value) {
        super(typeId, value.toString());
        this.value = value;
    }

}
