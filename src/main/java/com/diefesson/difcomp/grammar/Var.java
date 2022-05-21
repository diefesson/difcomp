package com.diefesson.difcomp.grammar;

public class Var extends GrammarItem {

    public final String name;

    public Var(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Var && ((Var) other).name.equals(name);
    }

    @Override
    public String toString() {
        return "%s(%s)".formatted(Var.class.getSimpleName(), name);
    }

}
