package com.diefesson.difcomp.error;

public class LexerException extends CompileException {

    public LexerException(String message) {
        super(message);
    }

    public LexerException(Throwable cause) {
        super(cause);
    }

    public LexerException(String message, Throwable cause) {
        super(message, cause);
    }

}
