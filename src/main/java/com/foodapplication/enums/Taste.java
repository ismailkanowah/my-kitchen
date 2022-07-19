package com.foodapplication.enums;

import com.foodapplication.entity.Recipe;
import javafx.scene.control.TableColumn;

import java.util.Locale;

public enum Taste {
    SWEET(0),
    SAVOURY(1);

    public final int value;

    public static String getEnumLabel(TableColumn.CellDataFeatures<Recipe, String> taste) {
        String lowercaseValue = taste.getValue().getTaste().name().toLowerCase(Locale.ROOT);
        return lowercaseValue.substring(0, 1).toUpperCase(Locale.ROOT) + lowercaseValue.substring(1);
    }
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
