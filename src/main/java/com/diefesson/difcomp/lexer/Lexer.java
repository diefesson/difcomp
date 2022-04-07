package com.diefesson.difcomp.lexer;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.Token;

public class Lexer implements AutoCloseable {

    private final Scanner scanner;
    private final int horizon;
    private final List<Pattern> patterns;
    private final List<LexerHandler> handlers;

    // Statistics
    private int currentRow = 0;
    private int currentColumn = 0;
    private StringBuilder currentLine = new StringBuilder();

    public Lexer(Reader reader, int horizon) {
        this.scanner = new Scanner(reader);
        this.horizon = horizon;
        this.patterns = new ArrayList<>();
        this.handlers = new ArrayList<>();
    }

    public void on(String pattern, LexerHandler handler) {
        patterns.add(Pattern.compile(pattern));
        handlers.add(handler);
    }

    public Token next() throws LexerException {
        Token token = null;
        while (token == null) {
            token = tryNext();
        }
        return token;
    }

    private Token tryNext() throws LexerException {
        for (int i = 0; i < patterns.size(); i++) {
            Pattern p = patterns.get(i);
            LexerHandler h = handlers.get(i);
            String match = scanner.findWithinHorizon(p, horizon);
            if (match != null) {
                updateStatistics(match);
                return h.handle(match, scanner);
            }
        }
        if (!scanner.hasNext()) {
            return new Token(0, "");
        }
        throw createUnexpectedExcepetion();
    }

    private void updateStatistics(String match) {
        currentLine.append(match);
        for (int lineEndPos = currentLine.indexOf("\n"); lineEndPos != -1; lineEndPos = currentLine.indexOf("\n")) {
            currentRow++;
            System.out.println(lineEndPos);
            currentLine.delete(0, lineEndPos + 1);
        }
        currentColumn = currentLine.length();
    }

    private LexerException createUnexpectedExcepetion() {
        String unexpected = scanner
                .findWithinHorizon("(.|\n)+", horizon)
                .replaceAll("\n", " ");
        StringBuilder message = new StringBuilder();
        message
                .append("unexpected char at %d:%d\n".formatted(currentRow, currentColumn))
                .append(currentLine)
                .append(unexpected)
                .append("\n");
        for (int i = 0; i < currentColumn; i++) {
            message.append("-");
        }
        message.append("^");
        return new LexerException(message.toString());
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }

}
