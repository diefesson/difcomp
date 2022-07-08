package com.diefesson.difcomp.lexer;

import com.diefesson.difcomp.grammar.Element;

public class Token {

    public final DocPos position;
    public final TokenType type;
    public final String lexeme;
    public final boolean ignore;

    public Token(DocPos position, TokenType type, String lexeme) {
        this(position, type, lexeme, false);
    }

    public Token(DocPos position, TokenType type, String lexeme, boolean ignore) {
        this.position = position;
        this.type = type;
        this.lexeme = lexeme;
        this.ignore = ignore;
    }

    public Element element() {
        return Element.terminal(type);
    }

    @Override
    public String toString() {
        return "< %s 0x%08x %s %s >".formatted(position, type.id(), type, lexeme);
    }

}
