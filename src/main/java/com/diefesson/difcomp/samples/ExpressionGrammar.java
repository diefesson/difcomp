package com.diefesson.difcomp.samples;

import static com.diefesson.difcomp.samples.ExpressionTokens.CONST_VALUE;
import static com.diefesson.difcomp.samples.ExpressionTokens.OP_ADD;
import static com.diefesson.difcomp.samples.ExpressionTokens.OP_MUL;
import static com.diefesson.difcomp.samples.ExpressionVariables.EXPRESSION;
import static com.diefesson.difcomp.samples.ExpressionVariables.MULTI;
import static com.diefesson.difcomp.samples.ExpressionVariables.SUM;
import static com.diefesson.difcomp.samples.ExpressionVariables.VALUE;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.Grammar;

public class ExpressionGrammar {

    private ExpressionGrammar() {
    };

    public static Grammar expressionGrammar() throws GrammarException {
        return Grammar.builder()
                .rule(EXPRESSION, SUM)
                .rule(SUM, SUM, OP_ADD, MULTI)
                .rule(SUM, MULTI)
                .rule(MULTI, MULTI, OP_MUL, VALUE)
                .rule(MULTI, VALUE)
                .rule(VALUE, CONST_VALUE)
                .build();
    }
}
