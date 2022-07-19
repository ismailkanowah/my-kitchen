package com.foodapplication.enums;

public enum Taste {
    SWEET(0),
    SAVOURY(1);

    public final int value;

    public static Taste getEnumByValue(int value) {
        for (Taste taste: Taste.values()) {
            if (taste.value == value) return taste;
        }
        return null;
    }

    private Taste(int value) {
        this.value = value;
    }

}
