package com.diefesson.difcomp.dl.token;

import com.diefesson.difcomp.token.DocPos;

public class IdentifierToken extends ValueToken<String> {
    public IdentifierToken(DocPos position, String value) {
        super(position, TokenType.IDENTIFIER.id, value);
    }
}
