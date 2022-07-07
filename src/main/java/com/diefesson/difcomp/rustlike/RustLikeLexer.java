package com.diefesson.difcomp.rustlike;

import java.io.Reader;

import com.diefesson.difcomp.lexer.CommentHandler;
import com.diefesson.difcomp.lexer.CommonPatterns;
import com.diefesson.difcomp.lexer.IgnoreHandler;
import com.diefesson.difcomp.lexer.Lexer;
import com.diefesson.difcomp.lexer.SimpleHandler;

public class RustLikeLexer extends Lexer {

    public RustLikeLexer(Reader reader) {
        this(reader, false);
    }

    public RustLikeLexer(Reader reader, boolean debug) {
        super(reader, 0, debug);

        on(CommonPatterns.WHITESPACE, new IgnoreHandler());
        on(CommonPatterns.NEW_LINE, new IgnoreHandler());
        on(Patterns.COMMENT_LINE, new CommentHandler(CommonPatterns.NEW_LINE));
        on(Patterns.COMMENT_BLOCK_OPEN, new CommentHandler(Patterns.COMMENT_BLOCK_CLOSE));

        on(Patterns.OP_ADD, new SimpleHandler(RLTokens.OP_ADD));
        on(Patterns.OP_SUB, new SimpleHandler(RLTokens.OP_SUB));
        on(Patterns.OP_MUL, new SimpleHandler(RLTokens.OP_MUL));
        on(Patterns.OP_DIV, new SimpleHandler(RLTokens.OP_DIV));

        on(Patterns.OP_EQ, new SimpleHandler(RLTokens.OP_EQ));
        on(Patterns.OP_LE, new SimpleHandler(RLTokens.OP_LE));
        on(Patterns.OP_GE, new SimpleHandler(RLTokens.OP_GE));
        on(Patterns.OP_LT, new SimpleHandler(RLTokens.OP_LT));
        on(Patterns.OP_GT, new SimpleHandler(RLTokens.OP_GT));

        on(Patterns.OP_NOT, new SimpleHandler(RLTokens.OP_NOT));
        on(Patterns.OP_AND, new SimpleHandler(RLTokens.OP_AND));
        on(Patterns.OP_OR, new SimpleHandler(RLTokens.OP_OR));

        on(Patterns.CONST_F32, new SimpleHandler(RLTokens.CONST_F32));
        on(Patterns.CONST_S32, new SimpleHandler(RLTokens.CONST_S32));
        on(Patterns.CONST_BOOL, new SimpleHandler(RLTokens.CONST_BOOL));
        on(Patterns.CONST_CHAR, new SimpleHandler(RLTokens.CONST_CHAR));
        on(Patterns.CONST_STRING, new SimpleHandler(RLTokens.CONST_STRING));

        on(Patterns.PUNC_COMMA, new SimpleHandler(RLTokens.PUNC_COMMA));
        on(Patterns.PUNC_ARG_OPEN, new SimpleHandler(RLTokens.PUNC_ARG_OPEN));
        on(Patterns.PUNC_ARG_CLOSE, new SimpleHandler(RLTokens.PUNC_ARG_CLOSE));
        on(Patterns.PUNC_BLOCK_OPEN, new SimpleHandler(RLTokens.PUNC_BLOCK_OPEN));
        on(Patterns.PUNC_BLOCK_CLOSE, new SimpleHandler(RLTokens.PUNC_BLOCK_CLOSE));
        on(Patterns.PUNC_DEFINITION, new SimpleHandler(RLTokens.PUNC_DEFINITION));
        on(Patterns.PUNC_STMNT_END, new SimpleHandler(RLTokens.PUNC_STMNT_END));
        on(Patterns.PUNC_TWO_DOTS, new SimpleHandler(RLTokens.PUNC_TWO_DOTS));

        on(Patterns.TYPE_UNIT, new SimpleHandler(RLTokens.TYPE_UNIT));
        on(Patterns.TYPE_S32, new SimpleHandler(RLTokens.TYPE_S32));
        on(Patterns.TYPE_F32, new SimpleHandler(RLTokens.TYPE_F32));
        on(Patterns.TYPE_BOOL, new SimpleHandler(RLTokens.TYPE_BOOL));
        on(Patterns.TYPE_CHAR, new SimpleHandler(RLTokens.TYPE_CHAR));
        on(Patterns.TYPE_STRING, new SimpleHandler(RLTokens.TYPE_STRING));

        on(Patterns.KW_IF, new SimpleHandler(RLTokens.KW_IF));
        on(Patterns.KW_ELIF, new SimpleHandler(RLTokens.KW_ELIF));
        on(Patterns.KW_ELSE, new SimpleHandler(RLTokens.KW_ELSE));
        on(Patterns.KW_FUN, new SimpleHandler(RLTokens.KW_FUN));
        on(Patterns.KW_RETURN, new SimpleHandler(RLTokens.KW_RETURN));

        on(Patterns.IDENTIFIER, new SimpleHandler(RLTokens.IDENTIFIER));
    }

}
