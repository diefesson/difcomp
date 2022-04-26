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
    public static final String OP_ADD = simple("\\+");
    public static final String OP_SUB = simple("-");
    public static final String OP_MUL = simple("\\*");
    public static final String OP_DIV = simple("/");

    // Relational operators
    public static final String OP_EQ = simple("==");
    public static final String OP_LE = simple("<=");
    public static final String OP_GE = simple(">=");
    public static final String OP_LT = simple("<");
    public static final String OP_GT = simple(">");

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
    public static final String PUNC_COMMA = simple(",");
    public static final String PUNC_ARG_OPEN = simple("\\(");
    public static final String PUNC_ARG_CLOSE = simple("\\)");
    public static final String PUNC_BLOCK_OPEN = simple("\\{");
    public static final String PUNC_BLOCK_CLOSE = simple("\\}");
    public static final String PUNC_DEFINITION = simple("=");
    public static final String PUNC_STMNT_END = simple(";");
    public static final String PUNC_TWO_DOTS = simple(":");

    // Keywords
    public static final String KW_IF = annla("if");
    public static final String KW_ELIF = annla("elif");
    public static final String KW_ELSE = annla("else");
    public static final String KW_FUN = annla("fun");
    public static final String KW_RETURN = annla("return");

    // Identifier
    public static final String IDENTIFIER = annla("[[A-z]_][\\w_]*");

    public static final String WHITESPACE = simple(" ");
    public static final String NEW_LINE = simple("[\\n\\r]");

    private Patterns() {
    }

    public static String simple(String pattern) {
        return "\\G(" + pattern + ")";
    }

    // TODO find a better name
    /**
     * Short for "alphanumeric negative look ahead"
     */
    public static String annla(String pattern) {
        return "\\G(" + pattern + ")(?![\\p{L}\\d])";
    }
}
