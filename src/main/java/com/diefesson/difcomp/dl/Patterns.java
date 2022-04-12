package com.diefesson.difcomp.dl;

public final class Patterns {

    public static final String WHITESPACE = "\\G( |\n|\r|\r\n)+";
    public static final String KW_INT = "int";
    public static final String KW_PRINT = "print";
    public static final String OP_ASSING = "\\G=";
    public static final String OP_ADD = "\\G\\+";
    public static final String OP_SUB = "\\G-";
    public static final String OP_MUL = "\\G\\*";
    public static final String OP_DIV = "\\G/";
    public static final String PUNC_END = "\\G;";
    public static final String IDENTIFIER = "\\G[a-zA-Z_][a-zA-Z0-9_]*";
    public static final String CONST_INT = "\\G[0-9]+";
    public static final String CONST_STRING = "\\G\"[\\p{L}\\p{Punct} ]*?(?<!\\\\)\"";

    private Patterns() {
    }

}
