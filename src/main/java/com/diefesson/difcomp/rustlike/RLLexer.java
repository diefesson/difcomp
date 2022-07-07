package com.diefesson.difcomp.rustlike;

import java.io.Reader;

import com.diefesson.difcomp.lexer.CommonPatterns;
import com.diefesson.difcomp.lexer.Lexer;
import com.diefesson.difcomp.lexer.handlers.CommentHandler;
import com.diefesson.difcomp.lexer.handlers.IgnoreHandler;
import com.diefesson.difcomp.lexer.handlers.SimpleHandler;

public class RLLexer extends Lexer {

    public RLLexer(Reader reader) {
        this(reader, false);
    }

    public RLLexer(Reader reader, boolean debug) {
        super(reader, 0, debug);

        on(CommonPatterns.WHITESPACE, new IgnoreHandler());
        on(CommonPatterns.NEW_LINE, new IgnoreHandler());
        on(RLPatterns.COMMENT_LINE, new CommentHandler(CommonPatterns.NEW_LINE));
        on(RLPatterns.COMMENT_BLOCK_OPEN, new CommentHandler(RLPatterns.COMMENT_BLOCK_CLOSE));

        on(RLPatterns.OP_ADD, new SimpleHandler(RLTokens.OP_ADD));
        on(RLPatterns.OP_SUB, new SimpleHandler(RLTokens.OP_SUB));
        on(RLPatterns.OP_MUL, new SimpleHandler(RLTokens.OP_MUL));
        on(RLPatterns.OP_DIV, new SimpleHandler(RLTokens.OP_DIV));

        on(RLPatterns.OP_EQ, new SimpleHandler(RLTokens.OP_EQ));
        on(RLPatterns.OP_LE, new SimpleHandler(RLTokens.OP_LE));
        on(RLPatterns.OP_GE, new SimpleHandler(RLTokens.OP_GE));
        on(RLPatterns.OP_LT, new SimpleHandler(RLTokens.OP_LT));
        on(RLPatterns.OP_GT, new SimpleHandler(RLTokens.OP_GT));

        on(RLPatterns.OP_NOT, new SimpleHandler(RLTokens.OP_NOT));
        on(RLPatterns.OP_AND, new SimpleHandler(RLTokens.OP_AND));
        on(RLPatterns.OP_OR, new SimpleHandler(RLTokens.OP_OR));

        on(RLPatterns.CONST_F32, new SimpleHandler(RLTokens.CONST_F32));
        on(RLPatterns.CONST_S32, new SimpleHandler(RLTokens.CONST_S32));
        on(RLPatterns.CONST_BOOL, new SimpleHandler(RLTokens.CONST_BOOL));
        on(RLPatterns.CONST_CHAR, new SimpleHandler(RLTokens.CONST_CHAR));
        on(RLPatterns.CONST_STRING, new SimpleHandler(RLTokens.CONST_STRING));

        on(RLPatterns.PUNC_COMMA, new SimpleHandler(RLTokens.PUNC_COMMA));
        on(RLPatterns.PUNC_ARG_OPEN, new SimpleHandler(RLTokens.PUNC_ARG_OPEN));
        on(RLPatterns.PUNC_ARG_CLOSE, new SimpleHandler(RLTokens.PUNC_ARG_CLOSE));
        on(RLPatterns.PUNC_BLOCK_OPEN, new SimpleHandler(RLTokens.PUNC_BLOCK_OPEN));
        on(RLPatterns.PUNC_BLOCK_CLOSE, new SimpleHandler(RLTokens.PUNC_BLOCK_CLOSE));
        on(RLPatterns.PUNC_DEFINITION, new SimpleHandler(RLTokens.PUNC_DEFINITION));
        on(RLPatterns.PUNC_STMNT_END, new SimpleHandler(RLTokens.PUNC_STMNT_END));
        on(RLPatterns.PUNC_TWO_DOTS, new SimpleHandler(RLTokens.PUNC_TWO_DOTS));

        on(RLPatterns.TYPE_UNIT, new SimpleHandler(RLTokens.TYPE_UNIT));
        on(RLPatterns.TYPE_S32, new SimpleHandler(RLTokens.TYPE_S32));
        on(RLPatterns.TYPE_F32, new SimpleHandler(RLTokens.TYPE_F32));
        on(RLPatterns.TYPE_BOOL, new SimpleHandler(RLTokens.TYPE_BOOL));
        on(RLPatterns.TYPE_CHAR, new SimpleHandler(RLTokens.TYPE_CHAR));
        on(RLPatterns.TYPE_STRING, new SimpleHandler(RLTokens.TYPE_STRING));

        on(RLPatterns.KW_IF, new SimpleHandler(RLTokens.KW_IF));
        on(RLPatterns.KW_ELIF, new SimpleHandler(RLTokens.KW_ELIF));
        on(RLPatterns.KW_ELSE, new SimpleHandler(RLTokens.KW_ELSE));
        on(RLPatterns.KW_FUN, new SimpleHandler(RLTokens.KW_FUN));
        on(RLPatterns.KW_RETURN, new SimpleHandler(RLTokens.KW_RETURN));

        on(RLPatterns.IDENTIFIER, new SimpleHandler(RLTokens.IDENTIFIER));
    }

}
