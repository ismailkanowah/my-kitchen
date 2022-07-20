package com.foodapplication.enums;

import com.foodapplication.entity.Recipe;
import javafx.scene.control.TableColumn;

import java.util.Locale;

public enum Type {
    BREAKFAST(0),
    LUNCH(1),
    DINNER(2);

    public final int value;

    public static String getEnumLabel(TableColumn.CellDataFeatures<Recipe, String> type) {
        String lowercaseValue = type.getValue().getType().name().toLowerCase(Locale.ROOT);
        return lowercaseValue.substring(0, 1).toUpperCase(Locale.ROOT) + lowercaseValue.substring(1);
    }
    public static Type getEnumByValue(int value) {
        for (Type type: Type.values()) {
            if (type.value == value) return type;
        }
        return null;
    }
    public static Type getEnumByValue(String value) {
        if (value.equalsIgnoreCase("breakfast")) {
            return BREAKFAST;
        } else if (value.equalsIgnoreCase("lunch")) {
            return LUNCH;
        } else if (value.equalsIgnoreCase("dinner")) {
            return DINNER;
        }
        return null;
    }
    private Type(int value) {
        this.value = value;
    }

}
