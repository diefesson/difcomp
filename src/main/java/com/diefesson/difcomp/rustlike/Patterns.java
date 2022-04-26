package com.diefesson.difcomp.rustlike;

public final class Patterns {

    // Types
    public static final String TYPE_UNIT = annla("unit");
    public static final String TYPE_S32 = annla("s32");
    public static final String TYPE_F32 = annla("f32");
    public static final String TYPE_BOOL = annla("bool");
    public static final String TYPE_CHAR = annla("char");
    public static final String TYPE_STRING = annla("string");

    // Arithmetic operators
    public static final String OP_ADD = "\\+";
    public static final String OP_SUB = "-";
    public static final String OP_MUL = "\\*";
    public static final String OP_DIV = "/";

    // Relational operators
    public static final String OP_EQ = "==";
    public static final String OP_LE = "<=";
    public static final String OP_GE = ">=";
    public static final String OP_LT = "<";
    public static final String OP_GT = ">";

    // Logical operators
    public static final String OP_NOT = annla("not");
    public static final String OP_AND = annla("and");
    public static final String OP_OR = annla("or");

    public static final String CONST_F32 = annla("(\\d+.\\d*)|(.\\d+)");
    public static final String CONST_I32 = annla("\\d+");
    public static final String CONST_BOOL = annla("true|false");
    public static final String CONST_CHAR = annla("'[\\w ]'");
    public static final String CONST_STRING = annla("\"[\\w ]*\"");

    // Punctuations
    public static final String PUNC_COMMA = ",";
    public static final String PUNC_ARG_OPEN = "\\(";
    public static final String PUNC_ARG_CLOSE = "\\)";
    public static final String PUNC_BLOCK_OPEN = "\\{";
    public static final String PUNC_BLOCK_CLOSE = "\\}";
    public static final String PUNC_DEFINITION = "=";
    public static final String PUNC_STMNT_END = ";";
    public static final String PUNC_TWO_DOTS = ":";

    // Keywords
    public static final String KW_IF = annla("if");
    public static final String KW_ELIF = annla("elif");
    public static final String KW_ELSE = annla("else");
    public static final String KW_FUN = annla("fun");
    public static final String KW_RETURN = annla("return");

    // Identifier
    public static final String IDENTIFIER = annla("[[A-z]_][\\w_]*");

    public static final String COMMENT_LINE = "//";
    public static final String COMMENT_BLOCK_OPEN = "/\\*";
    public static final String COMMENT_BLOCK_CLOSE = "\\*/";

    private Patterns() {
    }

    // TODO find a better name
    /**
     * Short for "alphanumeric negative look ahead"
     */
    public static String annla(String pattern) {
        return "(" + pattern + ")(?![\\w])";
    }
}
