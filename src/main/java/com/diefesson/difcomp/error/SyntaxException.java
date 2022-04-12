package com.diefesson.difcomp.error;

public class SyntaxException extends CompileException {

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(Throwable cause) {
        super(cause);
    }

    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

}
