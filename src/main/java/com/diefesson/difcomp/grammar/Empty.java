package com.diefesson.difcomp.grammar;

public final class Empty extends GrammarItem {

    public static final Empty EMPTY = new Empty();

    private Empty() {
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Empty;
    }

    @Override
    public String toString() {
        return Empty.class.getSimpleName();
    }

}
