package com.diefesson.difcomp.rustlike;

import java.io.Reader;

import com.diefesson.difcomp.lexer.CommentHandler;
import com.diefesson.difcomp.lexer.CommonPatterns;
import com.diefesson.difcomp.lexer.IgnoreHandler;
import com.diefesson.difcomp.lexer.Lexer;
import com.diefesson.difcomp.lexer.SimpleHandler;

public class RustLikeLexer extends Lexer {

    public RustLikeLexer(Reader reader) {
        this(reader, true);
    }

    public RustLikeLexer(Reader reader, boolean ignore) {
        super(reader, 0, ignore);

        on(CommonPatterns.WHITESPACE, new IgnoreHandler());
        on(CommonPatterns.NEW_LINE, new IgnoreHandler());
        on(Patterns.COMMENT_LINE, new CommentHandler(CommonPatterns.NEW_LINE));
        on(Patterns.COMMENT_BLOCK_OPEN, new CommentHandler(Patterns.COMMENT_BLOCK_CLOSE));

        on(Patterns.OP_ADD, new SimpleHandler(TokenType.OP_ADD.id));
        on(Patterns.OP_SUB, new SimpleHandler(TokenType.OP_SUB.id));
        on(Patterns.OP_MUL, new SimpleHandler(TokenType.OP_MUL.id));
        on(Patterns.OP_DIV, new SimpleHandler(TokenType.OP_DIV.id));

        on(Patterns.OP_EQ, new SimpleHandler(TokenType.OP_EQ.id));
        on(Patterns.OP_LE, new SimpleHandler(TokenType.OP_LE.id));
        on(Patterns.OP_GE, new SimpleHandler(TokenType.OP_GE.id));
        on(Patterns.OP_LT, new SimpleHandler(TokenType.OP_LT.id));
        on(Patterns.OP_GT, new SimpleHandler(TokenType.OP_GT.id));

        on(Patterns.OP_NOT, new SimpleHandler(TokenType.OP_NOT.id));
        on(Patterns.OP_AND, new SimpleHandler(TokenType.OP_AND.id));
        on(Patterns.OP_OR, new SimpleHandler(TokenType.OP_OR.id));

        on(Patterns.CONST_I32, new SimpleHandler(TokenType.CONST_I32.id));
        on(Patterns.CONST_F32, new SimpleHandler(TokenType.CONST_F32.id));
        on(Patterns.CONST_BOOL, new SimpleHandler(TokenType.CONST_BOOL.id));
        on(Patterns.CONST_CHAR, new SimpleHandler(TokenType.CONST_CHAR.id));
        on(Patterns.CONST_STRING, new SimpleHandler(TokenType.CONST_STRING.id));

        on(Patterns.PUNC_COMMA, new SimpleHandler(TokenType.PUNC_COMMA.id));
        on(Patterns.PUNC_ARG_OPEN, new SimpleHandler(TokenType.PUNC_ARG_OPEN.id));
        on(Patterns.PUNC_ARG_CLOSE, new SimpleHandler(TokenType.PUNC_ARG_CLOSE.id));
        on(Patterns.PUNC_BLOCK_OPEN, new SimpleHandler(TokenType.PUNC_BLOCK_OPEN.id));
        on(Patterns.PUNC_BLOCK_CLOSE, new SimpleHandler(TokenType.PUNC_BLOCK_CLOSE.id));
        on(Patterns.PUNC_DEFINITION, new SimpleHandler(TokenType.PUNC_DEFINITION.id));
        on(Patterns.PUNC_STMNT_END, new SimpleHandler(TokenType.PUNC_STMNT_END.id));
        on(Patterns.PUNC_TWO_DOTS, new SimpleHandler(TokenType.PUNC_TWO_DOTS.id));

        on(Patterns.TYPE_UNIT, new SimpleHandler(TokenType.TYPE_UNIT.id));
        on(Patterns.TYPE_S32, new SimpleHandler(TokenType.TYPE_S32.id));
        on(Patterns.TYPE_F32, new SimpleHandler(TokenType.TYPE_F32.id));
        on(Patterns.TYPE_BOOL, new SimpleHandler(TokenType.TYPE_BOOL.id));
        on(Patterns.TYPE_CHAR, new SimpleHandler(TokenType.TYPE_CHAR.id));
        on(Patterns.TYPE_STRING, new SimpleHandler(TokenType.TYPE_STRING.id));

        on(Patterns.KW_IF, new SimpleHandler(TokenType.KW_IF.id));
        on(Patterns.KW_ELIF, new SimpleHandler(TokenType.KW_ELIF.id));
        on(Patterns.KW_ELSE, new SimpleHandler(TokenType.KW_ELSE.id));
        on(Patterns.KW_FUN, new SimpleHandler(TokenType.KW_FUN.id));
        on(Patterns.KW_RETURN, new SimpleHandler(TokenType.KW_RETURN.id));

        on(Patterns.IDENTIFIER, new SimpleHandler(TokenType.IDENTIFIER.id));
    }

}
