package com.diefesson.difcomp.token;

public class Token {

    public final int typeId;
    public final String lexeme;

    public Token(int typeId, String lexeme) {
        this.typeId = typeId;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "< %s \"%s\" >".formatted(typeId, lexeme);
    }

}
