package com.diefesson.difcomp.parser;

import com.diefesson.difcomp.grammar.Element;

public class SLRKey {

    public final int state;
    public final Element element;

    public SLRKey(int state, Element element) {
        this.state = state;
        this.element = element;
    }

    public static SLRKey key(int state, Element element) {
        return new SLRKey(state, element);
    }

    @Override
    public String toString() {
        return "< %d %s >".formatted(state, element.simpleName());
    }

}
