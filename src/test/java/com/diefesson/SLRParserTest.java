package com.diefesson;

import static com.diefesson.difcomp.lexer.CommonTokens.END;
import static com.diefesson.difcomp.samples.ExpressionGrammar.expressionGrammar;
import static com.diefesson.difcomp.samples.ExpressionTokens.CONST_VALUE;
import static com.diefesson.difcomp.samples.ExpressionTokens.OP_ADD;

import java.util.List;

import org.junit.Test;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.error.LexerException;
import com.diefesson.difcomp.error.ParserException;
import com.diefesson.difcomp.grammar.Grammar;
import com.diefesson.difcomp.lexer.TokenSource;
import com.diefesson.difcomp.parser.Action;
import com.diefesson.difcomp.parser.ActionType;
import com.diefesson.difcomp.parser.SLRParser;
import com.diefesson.difcomp.util.ListTokenSource;

public class SLRParserTest {

    @Test
    public void slrParserTest() throws GrammarException, LexerException, ParserException {
        Grammar grammar = expressionGrammar();
        TokenSource tokens = ListTokenSource.fromTokenTypes(
                List.of(CONST_VALUE, OP_ADD, CONST_VALUE, END));
        SLRParser parser = new SLRParser(grammar, tokens);
        Action action;
        do {
            action = parser.cycle();
        } while (action.type != ActionType.ACCEPT);
    }

}
