package com.diefesson.difcomp.grammar;

import java.util.Objects;

public class Element {

    public final ElementType type;
    public final int tokenId;
    public final String variable;

    private Element(ElementType type, int tokenId, String variable) {
        this.type = type;
        this.tokenId = tokenId;
        this.variable = variable;
    }

    public static Element terminal(int token) {
        return new Element(ElementType.TERMINAL, token, null);
    }

    public static Element variable(String variable) {
        return new Element(ElementType.VARIABLE, -1, variable);
    }

    public static Element empty() {
        return new Element(ElementType.EMPTY, -1, null);
    }

    public String simpleName() {
        switch (type) {
            case TERMINAL:
                return String.valueOf(tokenId);
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
                return tokenId;
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
                    && tokenId == other.tokenId
                    && Objects.equals(variable, other.variable);
        }
    }

    @Override
    public String toString() {
        switch (type) {
            case TERMINAL:
                return "< Terminal %s >".formatted(tokenId);
            case VARIABLE:
                return "< Variable %s >".formatted(variable);
            case EMPTY:
                return "< Empty >";
            default:
                throw new IllegalStateException();
        }
    }

}
