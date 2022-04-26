package com.diefesson.difcomp.token;

public class Token {

    public final DocPos position;
    public final int typeId;
    public final String lexeme;
    public final boolean ignore;

    public Token(DocPos position, int typeId, String lexeme) {
        this(position, typeId, lexeme, false);
    }

    public Token(DocPos position, int typeId, String lexeme, boolean ignore) {
        this.position = position;
        this.typeId = typeId;
        this.lexeme = lexeme;
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "< %s 0x%08x %s >".formatted(position, typeId, lexeme);
    }

}
