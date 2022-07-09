package com.diefesson.difcomp.lexer;

public interface TokenType {

    int id();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();

}
