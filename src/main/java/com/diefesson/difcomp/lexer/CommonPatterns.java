package com.diefesson.difcomp.lexer;

public class CommonPatterns {

    public static final String WHITESPACE = "\\G[ \t]+";
    public static final String NEW_LINE = "\\G[(\r\n)\r\n]";
    public static final String ANY = "\\G(.|\r\n|\r|\n)";
    public static final String EOF = "\\G\\z";

}
