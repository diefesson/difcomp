package com.diefesson.difcomp.lexer;

import static com.diefesson.difcomp.token.CommonTokens.END;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.error.UnknownCharSequenceException;
import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

public class Lexer implements TokenSource, AutoCloseable {

    private final Scanner scanner;
    private final int horizon;
    private final boolean debug;
    private final List<Pattern> patterns;
    private final List<LexerHandler> handlers;
    private final LexerStatistics statistics;
    private Token current;

    public Lexer(Reader reader) {
        this(reader, 0);
    }

    public Lexer(Reader reader, int horizon) {
        this(reader, horizon, false);
    }

    public Lexer(Reader reader, int horizon, boolean debug) {
        this.scanner = new Scanner(reader);
        this.horizon = horizon;
        this.debug = debug;
        this.patterns = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.statistics = new LexerStatistics();
        this.current = null;
    }

    public void on(String pattern, LexerHandler handler) {
        patterns.add(Pattern.compile("\\G(" + pattern + ")"));
        handlers.add(handler);
    }

    @Override
    public Token next() throws LexerException {
        if (current == null) {
            return findNext();
        } else {
            Token token = current;
            current = null;
            return token;
        }
    }

    @Override
    public Token peek() throws LexerException {
        if (current == null) {
            current = findNext();
        }
        return current;
    }

    private Token findNext() throws LexerException {
        Token token;
        do {
            token = tryNext();
        } while (!debug && token.ignore);
        return token;
    }

    private Token tryNext() throws LexerException {
        DocPos position = statistics.position();
        for (int i = 0; i < patterns.size(); i++) {
            Pattern p = patterns.get(i);
            LexerHandler h = handlers.get(i);
            String match = scanner.findWithinHorizon(p, horizon);
            if (match != null) {
                Token token = h.handle(position, match, scanner);
                statistics.update(token.lexeme);
                return token;
            }
        }
        if (scanner.findWithinHorizon("\\G\\z", 1) != null) {
            return new Token(position, END, "");
        }
        throw new UnknownCharSequenceException(position);
    }

    @Override
    public void close() {
        scanner.close();
    }

}
