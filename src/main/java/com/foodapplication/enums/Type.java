package com.foodapplication.enums;

public enum Type {
    BREAKFAST(0),
    LUNCH(1),
    DINNER(2);

    public final int value;
    public static String getEnumByValue(int value) {
        for (Type type: Type.values()) {
            if (type.value == value) return type.name();
        }
        return null;
    }

    private Type(int value) {
        this.value = value;
    }

}
