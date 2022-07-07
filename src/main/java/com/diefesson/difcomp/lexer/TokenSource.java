package com.diefesson.difcomp.lexer;

import com.diefesson.difcomp.error.LexerException;

public interface TokenSource {

    Token next() throws LexerException;

    Token peek() throws LexerException;

}
