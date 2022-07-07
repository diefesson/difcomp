package com.diefesson;

import static org.junit.Assert.assertNotEquals;

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
}
