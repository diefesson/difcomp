package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.Token;

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
