package com.diefesson.difcomp.lexer;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.DocPos;

class LexerStatistics {

    private int row = 0;
    private int column = 0;
    private StringBuilder line = new StringBuilder();

    public DocPos position() {
        return new DocPos(row, column);
    }

    public void updateStatistics(String match) {
        line.append(match);
        for (int lineEndPos = line.indexOf("\n"); lineEndPos != -1; lineEndPos = line.indexOf("\n")) {
            row++;
            line.delete(0, lineEndPos + 1);
        }
        column = line.length();
    }

    public LexerException createUnexpectedExcepetion(String unexpected) {
        unexpected = unexpected
                .replaceAll("\n", " ");
        StringBuilder message = new StringBuilder();
        message
                .append("unexpected char sequence at %s\n".formatted(position()))
                .append(line)
                .append(unexpected)
                .append("\n");
        for (int i = 0; i < column; i++) {
            message.append("-");
        }
        message.append("^");
        return new LexerException(message.toString());
    }
}