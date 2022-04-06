package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.ValueToken;

public class IdentifierToken extends ValueToken<String> {
    public IdentifierToken(String value) {
        super(TokenType.IDENTIFIER.id, value);
    }
}
