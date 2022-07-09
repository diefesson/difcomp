package com.diefesson.difcomp.rustlike;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.lexer.TokenSource;
import com.diefesson.difcomp.parser.SLRParser;

public class RLParser extends SLRParser {

    public RLParser(TokenSource tokens) throws GrammarException {
        super(RLGrammar.rlGrammar(), tokens);
    }

}
