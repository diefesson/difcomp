package com.diefesson.diflang.error;

public class CompileException extends Exception {

    public CompileException(String message) {
        super(message);
    }

    public CompileException(Throwable cause) {
        super(cause);
    }

    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }

}
