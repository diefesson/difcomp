package com.diefesson.difcomp.parser;

import java.util.Objects;

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
    public int hashCode() {
        return 2 * state + 3 * element.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SLRKey)) {
            return false;
        }
        SLRKey other = (SLRKey) obj;
        return state == other.state && Objects.equals(element, other.element);
    }

    @Override
    public String toString() {
        return "( %d, %s )".formatted(state, element.simpleName());
    }

}
