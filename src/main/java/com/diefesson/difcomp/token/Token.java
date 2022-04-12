package com.diefesson.difcomp.token;

public class Token {

    public final DocPos position;
    public final int typeId;
    public final String lexeme;

    public Token(DocPos position, int typeId, String lexeme) {
        this.position = position;
        this.typeId = typeId;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "< %s %s %s >".formatted(position, typeId, lexeme);
    }

}
