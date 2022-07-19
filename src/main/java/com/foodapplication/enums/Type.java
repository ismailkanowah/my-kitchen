package com.foodapplication.enums;

public enum Type {
    BREAKFAST(0),
    LUNCH(1),
    DINNER(2);

    public final int value;

    private Type(int value) {
        this.value = value;
    }

}
