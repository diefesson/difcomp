package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.DocPos;

public class IntToken extends ValueToken<Integer> {

    public IntToken(DocPos position, int value) {
        super(position, TokenType.CONST_INT.id, value);
    }

}
