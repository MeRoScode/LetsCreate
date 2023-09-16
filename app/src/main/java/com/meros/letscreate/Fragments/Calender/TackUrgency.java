package com.meros.letscreate.Fragments.Calender;

public enum TackUrgency {
    GREEN(4),WHITE(3),YELLOW(2),ORANGE(1),RED(0);

    private final int value ;
    TackUrgency(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
