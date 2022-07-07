package com.diefesson.difcomp.samples;

import static com.diefesson.difcomp.samples.NumberTokens.ONE;
import static com.diefesson.difcomp.samples.NumberTokens.THREE;
import static com.diefesson.difcomp.samples.NumberTokens.TWO;
import static com.diefesson.difcomp.samples.NumberVariables.A;
import static com.diefesson.difcomp.samples.NumberVariables.B;
import static com.diefesson.difcomp.samples.NumberVariables.C;
import static com.diefesson.difcomp.samples.NumberVariables.S;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.Grammar;

public class NumberGrammar {

    private NumberGrammar() {
    }

    public static Grammar numberGrammar() throws GrammarException {
        return Grammar.builder()
                .rule(S, A, B, C)
                .rule(A, ONE)
                .emptyRule(A)
                .rule(B, TWO)
                .emptyRule(B)
                .rule(C, THREE)
                .emptyRule(C)
                .build();
    }

}
