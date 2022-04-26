package com.diefesson.difcomp.lexer;

import com.diefesson.difcomp.token.DocPos;

class LexerStatistics {

    private int row = 0;
    private int column = 0;

    public DocPos position() {
        return new DocPos(row, column);
    }

    public void update(String match) {
        StringBuilder buffer = new StringBuilder(match);
        int linePos = buffer.indexOf("\n");
        while (linePos != -1) {
            buffer.delete(0, linePos + 1);
            row++;
            linePos = buffer.indexOf("\n");
        }
        column = buffer.length();
    }
}