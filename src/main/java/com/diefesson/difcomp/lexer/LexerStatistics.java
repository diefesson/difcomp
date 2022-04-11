package com.diefesson.difcomp.lexer;

import com.diefesson.difcomp.error.LexerException;

class LexerStatistics {

    private int currentRow = 0;
    private int currentColumn = 0;
    private StringBuilder currentLine = new StringBuilder();

    void updateStatistics(String match) {
        currentLine.append(match);
        for (int lineEndPos = currentLine.indexOf("\n"); lineEndPos != -1; lineEndPos = currentLine.indexOf("\n")) {
            currentRow++;
            currentLine.delete(0, lineEndPos + 1);
        }
        currentColumn = currentLine.length();
    }

    LexerException createUnexpectedExcepetion(String unexpected) {
        unexpected = unexpected
                .replaceAll("\n", " ");
        StringBuilder message = new StringBuilder();
        message
                .append("unexpected char at %d:%d\n".formatted(currentRow + 1, currentColumn + 1))
                .append(currentLine)
                .append(unexpected)
                .append("\n");
        for (int i = 0; i < currentColumn; i++) {
            message.append("-");
        }
        message.append("^");
        return new LexerException(message.toString());
    }
}