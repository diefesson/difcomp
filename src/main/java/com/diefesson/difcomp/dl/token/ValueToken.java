package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

public abstract class ValueToken<T> extends Token {

    public final T value;

    public ValueToken(DocPos position, int typeId, T value) {
        this(position, typeId, value.toString(), value);
    }

    public ValueToken(DocPos position, int typeId, String lexeme, T value) {
        super(position, typeId, lexeme);
        this.value = value;
    }

}
