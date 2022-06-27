package com.diefesson.difcomp.error;

public class GrammarException extends CompileException {
    public GrammarException(String message) {
        super(message);
    }

    public GrammarException(Throwable cause) {
        super(cause);
    }

    public GrammarException(String message, Throwable cause) {
        super(message, cause);
    }
}
