package com.foodapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FoodController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}