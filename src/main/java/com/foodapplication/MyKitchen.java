package com.foodapplication;

import com.foodapplication.entity.Recipe;
import com.foodapplication.enums.Taste;
import com.foodapplication.enums.Type;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyKitchen extends Application {

    static TextField searchCriteria;
    static TableView table;
    static CheckBox sweetCheckbox, savouryChecbox, breakfastCheckbox, lunchCheckbox, dinnerCheckbox;
    static String searchText;
    static ObservableList<Recipe> recipeObservableList;

    @Override
    public void start(Stage primaryStage) throws IOException {

        recipeObservableList = FXCollections.observableArrayList(Query.getRecipes(null, new ArrayList(), new ArrayList()));

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
        searchCriteria.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                searchText = searchCriteria.getText() + keyEvent.getText();
                if (searchCriteria.getText() == "") {
                    searchText = null;
                }
                updateRecipeList(searchText);
            }
        });

        headerHBox.getChildren().add(0, programTitle);
        headerHBox.getChildren().add(1, searchCriteria);

        Label tasteTitle = new Label();
        tasteTitle.setText("Select taste");
        tasteTitle.setFont(Font.font("Arial", 24.0));

        sweetCheckbox = new CheckBox("Sweet");
        sweetCheckbox.setFocusTraversable(false);
        sweetCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateRecipeList(searchText);
            }
        });
        savouryChecbox = new CheckBox("Savoury");
        savouryChecbox.setFocusTraversable(false);
        savouryChecbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateRecipeList(searchText);
            }
        });

        HBox checkboxContainer = new HBox(50.0);
        checkboxContainer.getChildren().add(0, sweetCheckbox);
        checkboxContainer.getChildren().add(1, savouryChecbox);

        Label mealTitle = new Label();
        mealTitle.setText("Select meal");
        mealTitle.setFont(Font.font("Arial", 24.0));

        breakfastCheckbox = new CheckBox("Breakfast");
        breakfastCheckbox.setFocusTraversable(false);
        breakfastCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateRecipeList(searchText);
            }
        });
        lunchCheckbox = new CheckBox("Lunch");
        lunchCheckbox.setFocusTraversable(false);
        lunchCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateRecipeList(searchText);
            }
        });
        dinnerCheckbox = new CheckBox("Dinner");
        dinnerCheckbox.setFocusTraversable(false);
        dinnerCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateRecipeList(searchText);
            }
        });

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
        addRecipeButton.setOnAction(action -> {
            try {
                AddRecipe.addRecipe();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        HBox recipeContainer = new HBox(700.0);
        recipeContainer.getChildren().add(0, recipeTitle);
        recipeContainer.getChildren().add(1, addRecipeButton);

        table = new TableView();
        table.setEditable(false);

        TableColumn<Recipe, String> recipeTitleColumn = new TableColumn<>("Name");
        recipeTitleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        recipeTitleColumn.setResizable(false);
        recipeTitleColumn.setSortable(false);
        recipeTitleColumn.setMinWidth(150.0);

        TableColumn<Recipe, String> recipeDescriptionColumn = new TableColumn<>("Description");
        recipeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        recipeDescriptionColumn.setResizable(false);
        recipeDescriptionColumn.setSortable(false);
        recipeDescriptionColumn.setMinWidth(525.0);

        TableColumn<Recipe, String> tasteColumn = new TableColumn<>("Taste");
        tasteColumn.setCellValueFactory(value -> new SimpleStringProperty(Taste.getEnumLabel(value)));
        tasteColumn.setResizable(false);
        tasteColumn.setSortable(false);
        tasteColumn.setMinWidth(50.0);

        TableColumn<Recipe, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(value -> new SimpleStringProperty(Type.getEnumLabel(value)));
        typeColumn.setResizable(false);
        typeColumn.setSortable(false);
        typeColumn.setMinWidth(50.0);

        table.setItems(recipeObservableList);
        table.getColumns().addAll(recipeTitleColumn, recipeDescriptionColumn, tasteColumn, typeColumn);
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Recipe selectedRecipe = (Recipe) table.getSelectionModel().getSelectedItem();
                    try {
                        ViewRecipe.viewRecipe(selectedRecipe);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

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
        scene.setOnMousePressed(mouseEvent -> {
            if (!searchCriteria.equals(mouseEvent.getSource())) {
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

    public static void updateRecipeList(String searchText) {
        List<Integer> tastes = new ArrayList<>();
        List<Integer> types = new ArrayList<>();

        if (sweetCheckbox.isSelected()) {
            tastes.add(Taste.SWEET.value);
        }
        if (savouryChecbox.isSelected()) {
            tastes.add(Taste.SAVOURY.value);
        }
        if (breakfastCheckbox.isSelected()) {
            types.add(Type.BREAKFAST.value);
        }
        if (lunchCheckbox.isSelected()) {
            types.add(Type.LUNCH.value);
        }
        if (dinnerCheckbox.isSelected()) {
            types.add(Type.DINNER.value);
        }

        recipeObservableList = FXCollections.observableArrayList(Query.getRecipes(searchText, tastes, types));
        table.setItems(recipeObservableList);
    }


    public static void main(String[] args) {
        launch();
    }
}