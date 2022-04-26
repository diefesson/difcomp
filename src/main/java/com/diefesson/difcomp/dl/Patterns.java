package com.diefesson.difcomp.dl;

public final class Patterns {

    public static final String WHITESPACE = "( |\n|\r|\r\n)+";
    public static final String KW_INT = "int";
    public static final String KW_PRINT = "print";
    public static final String OP_ASSING = "=";
    public static final String OP_ADD = "\\+";
    public static final String OP_SUB = "-";
    public static final String OP_MUL = "\\*";
    public static final String OP_DIV = "/";
    public static final String PUNC_END = ";";
    public static final String IDENTIFIER = "[a-zA-Z_][a-zA-Z0-9_]*";
    public static final String CONST_INT = "[0-9]+";
    public static final String CONST_STRING = "\"[\\p{L}\\p{Punct} ]*?(?<!\\\\)\"";

    private Patterns() {
    }

}
