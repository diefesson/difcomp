package com.diefesson;

import static com.diefesson.difcomp.rustlike.RLGrammar.rlGrammar;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.parser.Action;
import com.diefesson.difcomp.parser.SLRKey;
import com.diefesson.difcomp.parser.SLRTable;
import com.diefesson.difcomp.rustlike.RLTokens;

import org.junit.Test;

public class RustLikeTest {

    @Test
    public void uniqueTokenIds() {
        RLTokens[] tts = RLTokens.values();
        for (int i = 0; i < tts.length; i++) {
            for (int j = i + 1; j < tts.length; j++) {
                RLTokens tti = tts[i];
                RLTokens ttj = tts[j];
                assertNotEquals("%s and %s have conflicting ids".formatted(tti, ttj),
                        tti.id,
                        ttj.id);
            }
        }
    }

    @Test
    public void grammarBuild() throws GrammarException {
        rlGrammar();
    }

    @Test
    public void noAmbiguity() throws GrammarException {
        SLRTable table = SLRTable.compute(rlGrammar());
        for (SLRKey key : table.keys()) {
            List<Action> actions = table.getList(key);
            assertEquals(
                    "Conflict: " + actions.stream().map(Object::toString).collect(joining(", ")),
                    1,
                    actions.size());
        }
    }
}
