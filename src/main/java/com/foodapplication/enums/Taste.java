package com.foodapplication.enums;

public enum Taste {
    SWEET(0),
    SAVOURY(1);

    public final int value;

    private Taste(int value) {
        this.value = value;
    }
}
