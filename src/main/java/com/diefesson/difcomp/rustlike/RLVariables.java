package com.diefesson.difcomp.rustlike;

import com.diefesson.difcomp.parser.VariableType;

public enum RLVariables implements VariableType {

    TYPE,
    CONST,

    // Operator expressions
    EXPR,
    OR_EXPR,
    AND_EXPR,
    NOT_EXPR,
    REL_EXPR,
    SUM_EXPR,
    MUL_EXPR,
    CONV_EXPR,

    // Leaf expressions
    CONST_EXPR,
    ID_EXPR,
    SUB_EXPR,
    CALL_EXPR,
    LEAF_EXPR,

    // Conditional statemennts
    IF_STMNT,
    ELIF_STMNT,
    ELIF_STMNTS,
    ELSE_STMNT,
    COND_STMNT,

    // Other statements
    NORMAL_STMNT,
    RETURN_STMNT,
    STMNT,
    STMNTS,
    BLOCK,

    // Functions and calls
    ARGS,
    PARAM,
    PARAMS,
    CALL_ARGS,
    FUNCTION_PARAMS,
    FUNCTION_HEADER,
    FUNCTION,
    FUNCTIONS,
    PROGRAM

}
