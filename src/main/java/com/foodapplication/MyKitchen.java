package com.foodapplication;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class MyKitchen extends Application {

    static TextField searchCriteria;
    @Override
    public void start(Stage primaryStage) throws IOException {

        HBox headerHBox = new HBox(300.0);
        headerHBox.setAlignment(Pos.CENTER_LEFT);

        Label programTitle = new Label();
        programTitle.setText("My Kitchen");
        programTitle.setFont(Font.font("Arial", FontWeight.BOLD, 32.0));

        searchCriteria = new TextField();
        searchCriteria.setPromptText("Search recipe");
        searchCriteria.setMinWidth(400.0);
        searchCriteria.setMinHeight(40.0);
        searchCriteria.setFocusTraversable(false);

        headerHBox.getChildren().add(0, programTitle);
        headerHBox.getChildren().add(1, searchCriteria);

        Label tasteTitle = new Label();
        tasteTitle.setText("Select taste");
        tasteTitle.setFont(Font.font("Arial", 24.0));

        CheckBox sweetCheckbox = new CheckBox("Sweet");
        sweetCheckbox.setFocusTraversable(false);
        CheckBox savouryChecbox = new CheckBox("Savoury");
        savouryChecbox.setFocusTraversable(false);

        HBox checkboxContainer = new HBox(50.0);
        checkboxContainer.getChildren().add(0, sweetCheckbox);
        checkboxContainer.getChildren().add(1, savouryChecbox);

        Label mealTitle = new Label();
        mealTitle.setText("Select meal");
        mealTitle.setFont(Font.font("Arial", 24.0));

        CheckBox breakfastCheckbox = new CheckBox("Breakfast");
        breakfastCheckbox.setFocusTraversable(false);
        CheckBox lunchCheckbox = new CheckBox("Lunch");
        lunchCheckbox.setFocusTraversable(false);
        CheckBox dinnerCheckbox = new CheckBox("Dinner");
        dinnerCheckbox.setFocusTraversable(false);

        HBox mealCheckboxContainer = new HBox(50.0);
        mealCheckboxContainer.getChildren().add(0, breakfastCheckbox);
        mealCheckboxContainer.getChildren().add(1, lunchCheckbox);
        mealCheckboxContainer.getChildren().add(2, dinnerCheckbox);

        VBox tasteBox = new VBox(20.0);
        tasteBox.setAlignment(Pos.CENTER_LEFT);
        tasteBox.getChildren().add(0, tasteTitle);
        tasteBox.getChildren().add(1, checkboxContainer);

        VBox mealBox = new VBox(20.0);
        mealBox.setAlignment(Pos.CENTER_LEFT);
        mealBox.getChildren().add(0, mealTitle);
        mealBox.getChildren().add(1, mealCheckboxContainer);

        Label recipeTitle = new Label();
        recipeTitle.setText("Recipe");
        recipeTitle.setAlignment(Pos.CENTER_LEFT);
        recipeTitle.setFont(Font.font("Arial", 24.0));

        Button addRecipeButton = new Button("Add recipe");
        addRecipeButton.setFocusTraversable(false);
        addRecipeButton.setMinWidth(100);
        addRecipeButton.setMinHeight(40);
        addRecipeButton.setAlignment(Pos.CENTER);

        HBox recipeContainer = new HBox(700.0);
        recipeContainer.getChildren().add(0, recipeTitle);
        recipeContainer.getChildren().add(1, addRecipeButton);

        TableView table = new TableView();
        table.setEditable(false);
        TableColumn recipeTitleColumn = new TableColumn("Name");
        recipeTitleColumn.setMinWidth(300.0);
        TableColumn recipeDescriptionColumn = new TableColumn("Description");
        recipeDescriptionColumn.setMinWidth(550.0);
        table.getColumns().addAll(recipeTitleColumn, recipeDescriptionColumn);

        VBox recipeBox = new VBox(10.0);
        recipeBox.setAlignment(Pos.CENTER_LEFT);
        recipeBox.getChildren().add(0, recipeContainer);
        recipeBox.getChildren().add(1, table);

        VBox pageBox = new VBox(50.0);
        pageBox.setAlignment(Pos.CENTER);
        pageBox.getChildren().add(0, headerHBox);
        pageBox.getChildren().add(1, tasteBox);
        pageBox.getChildren().add(2, mealBox);
        pageBox.getChildren().add(3, recipeBox);

        GridPane mainMenu = new GridPane();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.getChildren().add(pageBox);

        Scene scene = new Scene(mainMenu);
        scene.setOnMousePressed(event -> {
            if (!searchCriteria.equals(event.getSource())) {
                searchCriteria.getParent().requestFocus();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("My Kitchen");
        primaryStage.setOnCloseRequest(e -> {
            try {
                e.consume();
                System.exit(0);

            } catch (Exception ex) {
            }
        });
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}