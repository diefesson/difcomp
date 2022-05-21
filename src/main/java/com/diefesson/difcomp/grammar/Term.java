package com.diefesson.difcomp.grammar;

public class Term extends GrammarItem {

    public final int tokenId;

    public Term(int tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public int hashCode() {
        return tokenId;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Term && ((Term) other).tokenId == tokenId;
    }

    @Override
    public String toString() {
        return "%s(%d)".formatted(Term.class.getSimpleName(), tokenId);
    }

}
