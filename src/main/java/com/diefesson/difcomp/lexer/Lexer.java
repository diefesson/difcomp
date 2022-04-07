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
    private final LexerStatistics statistics;

    public Lexer(Reader reader, int horizon) {
        this.scanner = new Scanner(reader);
        this.horizon = horizon;
        this.patterns = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.statistics = new LexerStatistics();
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
                statistics.updateStatistics(match);
                return h.handle(match, scanner);
            }
        }
        if (scanner.findWithinHorizon("\\z", 1) != null) {
            return new Token(0, "");
        }
        String unexpected = scanner.findWithinHorizon("(.|\n)+", horizon);
        throw statistics.createUnexpectedExcepetion(unexpected);
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }

}
