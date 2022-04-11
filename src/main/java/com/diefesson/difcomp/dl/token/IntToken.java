package com.diefesson.difcomp.dl.token;

public class IntToken extends ValueToken<Integer> {

    public IntToken(int value) {
        super(TokenType.CONST_INT.id, value);
    }

}
