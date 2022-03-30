package com.diefesson.diflang.token;

public class IntToken extends ValueToken<Integer> {

    public IntToken(int value) {
        super(TokenType.CONST_INT, value);
    }

}
