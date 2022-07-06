package com.diefesson.difcomp.lexer;

import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.token.Token;

public interface TokenSource {

    Token next() throws LexerException;

    Token peek() throws LexerException;

}
