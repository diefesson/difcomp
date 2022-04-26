package com.diefesson.difcomp.lexer;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.DocPos;
import com.diefesson.difcomp.token.Token;

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
        return new Token(position, -1, comment.toString(), true);
    }

}
