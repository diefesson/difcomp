package com.diefesson.difcomp.lexer.handlers;

import static com.diefesson.difcomp.lexer.CommonTokens.INVALID;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.lexer.CommonPatterns;
import com.diefesson.difcomp.lexer.DocPos;
import com.diefesson.difcomp.lexer.Token;

public class CommentHandler implements LexerHandler {

    private final Pattern end;

    public CommentHandler(String end) {
        this.end = Pattern.compile("\\G(" + end + ")");
    }

    @Override
    public Token handle(DocPos position, String match, Scanner scanner) throws LexerException {
        StringBuilder comment = new StringBuilder(match);
        for (;;) {
            String m = scanner.findWithinHorizon(end, 0);
            if (m != null) {
                comment.append(m);
                break;
            }
            m = scanner.findWithinHorizon(CommonPatterns.ANY, 0);
            if (m != null) {
                comment.append(m);
            } else {
                throw new LexerException("unclosed comment starting at %s".formatted(position));
            }
        }
        return new Token(position, INVALID, comment.toString(), true);
    }

}
