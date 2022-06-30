package com.diefesson;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import org.junit.Test;
import com.diefesson.difcomp.error.GrammarException;
import com.diefesson.difcomp.grammar.Element;
import com.diefesson.difcomp.grammar.FirstSets;
import com.diefesson.difcomp.grammar.FollowSets;
import com.diefesson.difcomp.grammar.Grammar;

public class FirstFollowTest {

    private static final int a = 1, b = 2, c = 3;

    public Grammar grammar1() throws GrammarException {
        return Grammar.builder()
                .rule("S", "A", "B", "C")
                .rule("A", a)
                .emptyRule("A")
                .rule("B", b)
                .emptyRule("B")
                .rule("C", c)
                .emptyRule("C")
                .build();
    }

    public Grammar grammar2() throws GrammarException {
        return Grammar.builder()
                .rule("I", "S")
                .rule("S", "S", 1, "M")
                .rule("S", "M")
                .rule("M", "M", 2, "C")
                .rule("M", "C")
                .rule("C", 3)
                .build();
    }

    @Test
    public void grammar1First() throws GrammarException {
        FirstSets firstSets = FirstSets.calculateFirstSets(grammar1());
        assertEquals(
                Set.of(
                        Element.variable("S"),
                        Element.variable("A"),
                        Element.variable("B"),
                        Element.variable("C")),
                firstSets.keys());
        assertEquals(
                Set.of(
                        Element.terminal(a),
                        Element.terminal(b),
                        Element.terminal(c),
                        Element.empty()),
                firstSets.get(Element.variable("S")));
        assertEquals(
                Set.of(
                        Element.terminal(a),
                        Element.empty()),
                firstSets.get(Element.variable("A")));
        assertEquals(
                Set.of(
                        Element.terminal(b),
                        Element.empty()),
                firstSets.get(Element.variable("B")));
        assertEquals(
                Set.of(
                        Element.terminal(c),
                        Element.empty()),
                firstSets.get(Element.variable("C")));
    }

    @Test
    public void grammar1Follow() throws GrammarException {
        Grammar grammar = grammar1();
        FirstSets firstSets = FirstSets.calculateFirstSets(grammar);
        FollowSets followSets = FollowSets.calculateFollowSets(grammar, firstSets);
        assertEquals(Set.of(
                Element.variable("S"),
                Element.variable("A"),
                Element.variable("B"),
                Element.variable("C")), followSets.keys());
        assertEquals(
                Set.of(
                        Element.terminal(0)),
                followSets.get(Element.variable("S")));
        assertEquals(
                Set.of(
                        Element.terminal(0),
                        Element.terminal(b),
                        Element.terminal(c)),
                followSets.get(Element.variable("A")));
        assertEquals(
                Set.of(
                        Element.terminal(0),
                        Element.terminal(c)),
                followSets.get(Element.variable("B")));
        assertEquals(
                Set.of(
                        Element.terminal(0)),
                followSets.get(Element.variable("C")));
    }

    // TODO: grammar 2 impl

}
