package com.diefesson.difcomp.error;

import com.diefesson.difcomp.lexer.DocPos;

public class UnknownCharSequenceException extends LexerException {

    public final DocPos position;

    public UnknownCharSequenceException(DocPos position) {
        super("unknown char sequence at %s".formatted(position));
        this.position = position;
    }

}
