package com.diefesson.difcomp.grammar;

import java.util.Objects;

import com.diefesson.difcomp.token.TokenType;

public class Element {

    public final ElementType type;
    public final TokenType tokenType;
    public final String variable;

    private Element(ElementType type, TokenType tokenType, String variable) {
        this.type = type;
        this.tokenType = tokenType;
        this.variable = variable;
    }

    public static Element terminal(TokenType tokenType) {
        return new Element(ElementType.TERMINAL, tokenType, null);
    }

    public static Element variable(String variable) {
        return new Element(ElementType.VARIABLE, null, variable);
    }

    public static Element empty() {
        return new Element(ElementType.EMPTY, null, null);
    }

    public String simpleName() {
        switch (type) {
            case TERMINAL:
                return tokenType.toString();
            case VARIABLE:
                return variable;
            case EMPTY:
                return "e";
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public int hashCode() {
        switch (type) {
            case TERMINAL:
                return tokenType.hashCode();
            case VARIABLE:
                return variable.hashCode();
            case EMPTY: // case EMPTY:
                return 0;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Element)) {
            return false;
        } else {
            Element other = (Element) obj;
            return Objects.equals(type, other.type)
                    && Objects.equals(tokenType, other.tokenType)
                    && Objects.equals(variable, other.variable);
        }
    }

    @Override
    public String toString() {
        switch (type) {
            case TERMINAL:
                return "< Terminal %s >".formatted(tokenType);
            case VARIABLE:
                return "< Variable %s >".formatted(variable);
            case EMPTY:
                return "< Empty >";
            default:
                throw new IllegalStateException();
        }
    }

}
