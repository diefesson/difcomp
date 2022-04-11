package com.diefesson.difcomp.dl.token;

public class StringToken extends ValueToken<String> {

    public StringToken(String lexeme, String value) {
        super(TokenType.CONST_STR.id, lexeme, value);
    }

}
