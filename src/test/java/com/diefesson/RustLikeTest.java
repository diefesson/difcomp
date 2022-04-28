package com.diefesson;

import static org.junit.Assert.assertNotEquals;

import com.diefesson.difcomp.rustlike.TokenType;

import org.junit.Test;

public class RustLikeTest {
    @Test
    public void uniqueTokenIds() {
        TokenType[] tts = TokenType.values();
        for (int i = 0; i < tts.length; i++) {
            for (int j = i + 1; j < tts.length; j++) {
                TokenType tti = tts[i];
                TokenType ttj = tts[j];
                assertNotEquals("%s and %s have conflicting ids".formatted(tti, ttj),
                        tti.id,
                        ttj.id);
            }
        }
    }
}
