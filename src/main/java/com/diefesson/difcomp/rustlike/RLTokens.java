package com.diefesson.difcomp.rustlike;

import com.diefesson.difcomp.lexer.TokenType;

public enum RLTokens implements TokenType {

    // Types
    TYPE_UNIT(0x0001),
    TYPE_S32(0x0002),
    TYPE_F32(0x0003),
    TYPE_BOOL(0x0004),
    TYPE_CHAR(0x0005),
    TYPE_STRING(0x0006),

    // Arithmetic Operators
    OP_ADD(0x0100),
    OP_SUB(0x0101),
    OP_MUL(0x0102),
    OP_DIV(0x0103),

    // Relational operators
    OP_EQ(0x0104),
    OP_LE(0x0105),
    OP_GE(0x0106),
    OP_LT(0x0107),
    OP_GT(0X0108),

    // Logical operators
    OP_NOT(0x0109),
    OP_AND(0x010b),
    OP_OR(0x010a),

    // Constants
    CONST_S32(0x0200),
    CONST_F32(0x0201),
    CONST_BOOL(0x0202),
    CONST_CHAR(0x0203),
    CONST_STRING(0x0204),

    // Punctuations
    PUNC_COMMA(0x0300),
    PUNC_ARG_OPEN(0x0301),
    PUNC_ARG_CLOSE(0x0302),
    PUNC_BLOCK_OPEN(0x0303),
    PUNC_BLOCK_CLOSE(0x0304),
    PUNC_DEFINITION(0x0305),
    PUNC_STMNT_END(0x0306),
    PUNC_TWO_DOTS(0x0307),

    // Keywords
    KW_IF(0X0400),
    KW_ELIF(0X0401),
    KW_ELSE(0X0402),
    KW_FUN(0x0403),
    KW_RETURN(0x0404),
    KW_AS(0x0405),

    // Identifier
    ID(0x0500),
    ;

    public final int id;

    private RLTokens(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

}
