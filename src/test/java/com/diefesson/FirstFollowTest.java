package com.diefesson;

import static com.diefesson.difcomp.samples.ExpressionTokens.CONST_VALUE;
import static com.diefesson.difcomp.samples.ExpressionTokens.OP_ADD;
import static com.diefesson.difcomp.samples.ExpressionTokens.OP_MUL;
import static com.diefesson.difcomp.samples.ExpressionVariables.EXPRESSION;
import static com.diefesson.difcomp.samples.ExpressionVariables.MULTI;
import static com.diefesson.difcomp.samples.ExpressionVariables.SUM;
import static com.diefesson.difcomp.samples.ExpressionVariables.VALUE;
import static com.diefesson.difcomp.samples.NumberTokens.ONE;
import static com.diefesson.difcomp.samples.NumberTokens.THREE;
import static com.diefesson.difcomp.samples.NumberTokens.TWO;
import static com.diefesson.difcomp.samples.NumberVariables.A;
import static com.diefesson.difcomp.samples.NumberVariables.B;
import static com.diefesson.difcomp.samples.NumberVariables.C;
import static com.diefesson.difcomp.samples.NumberVariables.S;
import static com.diefesson.difcomp.grammar.Element.empty;
import static com.diefesson.difcomp.grammar.Element.terminal;
import static com.diefesson.difcomp.grammar.Element.variable;
import static com.diefesson.difcomp.token.CommonTokens.END;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.grammar.FollowSets;
import com.diefesson.difcomp.grammar.Grammar;

public class FirstFollowTest {

        public Grammar abcGrammar() throws GrammarException {
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

        public Grammar expressionGrammar() throws GrammarException {
                return Grammar.builder()
                                .rule(EXPRESSION, SUM)
                                .rule(SUM, SUM, OP_ADD, MULTI)
                                .rule(SUM, MULTI)
                                .rule(MULTI, MULTI, OP_MUL, VALUE)
                                .rule(MULTI, VALUE)
                                .rule(VALUE, CONST_VALUE)
                                .build();
        }

        @Test
        public void grammar1First() throws GrammarException {
                FirstSets firstSets = FirstSets.calculateFirstSets(abcGrammar());
                assertEquals(
                                Set.of(variable(S), variable(A), variable(B), variable(C)),
                                firstSets.keys());
                assertEquals(
                                Set.of(terminal(ONE), terminal(TWO), terminal(THREE), empty()),
                                firstSets.get(variable(S)));
                assertEquals(
                                Set.of(terminal(ONE), empty()),
                                firstSets.get(variable(A)));
                assertEquals(
                                Set.of(terminal(TWO), empty()),
                                firstSets.get(variable(B)));
                assertEquals(
                                Set.of(terminal(THREE), empty()),
                                firstSets.get(variable(C)));
        }

        @Test
        public void grammar1Follow() throws GrammarException {
                Grammar grammar = abcGrammar();
                FirstSets firstSets = FirstSets.calculateFirstSets(grammar);
                FollowSets followSets = FollowSets.calculateFollowSets(grammar, firstSets);
                assertEquals(
                                Set.of(variable(S), variable(A), variable(B), variable(C)),
                                followSets.keys());
                assertEquals(
                                Set.of(terminal(END)),
                                followSets.get(variable(S)));
                assertEquals(
                                Set.of(terminal(END), terminal(TWO), terminal(THREE)),
                                followSets.get(variable(A)));
                assertEquals(
                                Set.of(terminal(END), terminal(THREE)),
                                followSets.get(variable(B)));
                assertEquals(
                                Set.of(terminal(END)),
                                followSets.get(variable(C)));
        }

        // TODO: grammar 2 impl

}
