package com.diefesson.diflang.lexer;

import com.diefesson.diflang.CompileException;
import com.diefesson.diflang.token.Token;

public interface Lexer {

    Token next() throws CompileException;

}
