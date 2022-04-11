package com.diefesson.difcomp.dl.token;

public class IdentifierToken extends ValueToken<String> {
    public IdentifierToken(String value) {
        super(TokenType.IDENTIFIER.id, value);
    }
}
