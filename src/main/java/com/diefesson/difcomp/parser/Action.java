package com.diefesson.difcomp.parser;

public class Action {
    public final ActionType type;
    public final int value;

    private Action(ActionType type, int value) {
        this.type = type;
        this.value = value;
    }

    public static Action shift(int value) {
        return new Action(ActionType.SHIFT, value);
    }

    public static Action reduce(int value) {
        return new Action(ActionType.REDUCE, value);
    }

    public static Action go(int value) {
        return new Action(ActionType.GO, value);
    }

    public static Action accept() {
        return new Action(ActionType.ACCEPT, -1);
    }
}
