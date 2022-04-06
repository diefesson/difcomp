package com.diefesson.difcomp.dl;

public final class Patterns {

    public static final String WHITESPACE = "\\G ";
    public static final String KW_INT = "\\Gint";
    public static final String KW_PRINT = "\\Gprint";
    public static final String OP_ASSING = "\\G=";
    public static final String OP_ADD = "\\G\\+";
    public static final String OP_SUB = "\\G-";
    public static final String OP_MUL = "\\G\\*";
    public static final String OP_DIV = "\\G/";
    public static final String PUNC_END = "\\G;";
    public static final String IDENTIFIER = "\\G[a-zA-Z_][a-zA-Z0-9_]*";
    public static final String CONST_INT = "\\G[0-9]+";
    public static final String CONST_STRING = "\\G\\\"[a-zA-Z0-9 ]*\\\"";

    private Patterns() {
    }

}
