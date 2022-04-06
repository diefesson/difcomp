package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.ValueToken;

public class IntToken extends ValueToken<Integer> {

    public IntToken(int value) {
        super(TokenType.CONST_INT.id, value);
    }

}
