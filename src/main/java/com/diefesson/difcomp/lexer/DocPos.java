package com.diefesson.difcomp.lexer;

public class DocPos {

    // public final String filename;
    public final int row;
    public final int column;

    public DocPos(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "%d:%d".formatted(row + 1, column + 1);
    }

}
