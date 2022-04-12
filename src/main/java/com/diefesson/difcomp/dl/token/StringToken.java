package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.DocPos;

public class StringToken extends ValueToken<String> {

    public StringToken(DocPos position, String lexeme, String value) {
        super(position, TokenType.CONST_STR.id, lexeme, value);
    }

}
