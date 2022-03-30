package com.diefesson.diflang.token;

public class StringToken extends ValueToken<String> {

    public StringToken(String value) {
        super(TokenType.CONST_STR, value);
    }

}
