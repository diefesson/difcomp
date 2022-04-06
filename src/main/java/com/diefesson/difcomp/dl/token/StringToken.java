package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.ValueToken;

public class StringToken extends ValueToken<String> {

    public StringToken(String lexeme, String value) {
        super(TokenType.CONST_STR.id, lexeme, value);
    }

}
