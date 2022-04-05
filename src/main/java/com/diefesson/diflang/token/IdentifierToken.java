package com.diefesson.diflang.token;

public class IdentifierToken extends ValueToken<String> {
    public IdentifierToken(String value) {
        super(TokenType.IDENTIFIER.id, value);
    }
}
