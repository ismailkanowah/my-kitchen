package com.foodapplication;

import com.foodapplication.entity.Ingredient;
import com.foodapplication.entity.Recipe;
import com.foodapplication.entity.Step;
import com.foodapplication.enums.Taste;
import com.foodapplication.enums.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddRecipe {

    static Stage addRecipeStage;
    static TableView ingredientsTable, stepsTable;
    static ToggleGroup tasteGroup, typeGroup;
    static TextField nameField, descriptionField;

    static RadioButton sweetRadio, savouryRadio, breakfastRadio, lunchRadio, dinnerRadio;
    static ObservableList<Ingredient> ingredientData;
    static ObservableList<Step> stepData;


    public static void addRecipe() throws Exception {

        ingredientData = FXCollections.observableArrayList(Query.getAllIngredient());
        stepData = FXCollections.observableArrayList();

        addRecipeStage = new Stage();
        addRecipeStage.setTitle("Add recipe");
        addRecipeStage.setResizable(false);
        addRecipeStage.initModality(Modality.APPLICATION_MODAL);

        GridPane addRecipePane = new GridPane();
        addRecipePane.setAlignment(Pos.CENTER);
        addRecipePane.setVgap(30.0);

        Label programTitle = new Label();
        programTitle.setText("My Kitchen");
        programTitle.setFont(Font.font("Arial", FontWeight.BOLD, 32.0));

        Label recipeTitle = new Label();
        recipeTitle.setText("Add recipe");
        recipeTitle.setFont(Font.font("Arial", 28.0));

        Button cancelButton = new Button("Cancel");
        cancelButton.setFocusTraversable(false);
        cancelButton.setMinWidth(100);
        cancelButton.setMinHeight(40);
        cancelButton.setAlignment(Pos.CENTER);
        cancelButton.setOnAction(action -> addRecipeStage.close());

        Button saveButton = new Button("Add");
        saveButton.setFocusTraversable(false);
        saveButton.setMinWidth(100);
        saveButton.setMinHeight(40);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setOnAction(action -> {
            RadioButton selectedTasteButton = (RadioButton) tasteGroup.getSelectedToggle();
            RadioButton selectedTypeButton = (RadioButton) typeGroup.getSelectedToggle();
            Recipe newRecipe = new Recipe(nameField.getText(), descriptionField.getText(), Taste.getEnumByValue(selectedTasteButton.getText()), Type.getEnumByValue(selectedTypeButton.getText()));
            List<Ingredient> selectedIngredients = ingredientsTable.getSelectionModel().getSelectedItems();
            List<Long> selectedIngredientId = selectedIngredients.stream().map(x -> x.getId()).collect(Collectors.toList());
            List<Step> steps = stepData.stream().map(data ->  new Step(data.getStepOrder(), data.getContent())).collect(Collectors.toList());
            Query.addRecipe(newRecipe, selectedIngredientId, steps);
            addRecipeStage.close();
            MyKitchen.updateRecipeList(MyKitchen.searchText);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recipe created");
            alert.setHeaderText("Recipe " + newRecipe.getName() + " created!");
            alert.setContentText("Continue");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }
        });

        HBox buttonBox = new HBox(20.0);
        buttonBox.getChildren().add(0, cancelButton);
        buttonBox.getChildren().add(1, saveButton);

        HBox titleBox = new HBox(300.0);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.getChildren().add(0, programTitle);
        titleBox.getChildren().add(1, recipeTitle);
        titleBox.getChildren().add(2, buttonBox);

        nameField = new TextField();
        nameField.setPromptText("Recipe name");
        nameField.setMinWidth(300.0);
        nameField.setFocusTraversable(false);

        descriptionField = new TextField();
        descriptionField.setPromptText("Recipe description");
        descriptionField.setMinWidth(600.0);
        descriptionField.setFocusTraversable(false);

        HBox textfieldBox = new HBox(100.0);
        textfieldBox.setAlignment(Pos.CENTER_LEFT);
        textfieldBox.getChildren().add(0, nameField);
        textfieldBox.getChildren().add(1, descriptionField);

        Label tasteTitle = new Label();
        tasteTitle.setText("Choose taste:");
        tasteTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24.0));

        tasteGroup = new ToggleGroup();

        sweetRadio = new RadioButton("Sweet");
        sweetRadio.setFocusTraversable(false);
        sweetRadio.setToggleGroup(tasteGroup);
        sweetRadio.setSelected(true);

        savouryRadio = new RadioButton("Savoury");
        savouryRadio.setFocusTraversable(false);
        savouryRadio.setToggleGroup(tasteGroup);

        HBox tasteBox = new HBox(50.0);
        tasteBox.setAlignment(Pos.CENTER_LEFT);
        tasteBox.getChildren().add(0, tasteTitle);
        tasteBox.getChildren().add(1, sweetRadio);
        tasteBox.getChildren().add(2, savouryRadio);

        Label typeTitle = new Label();
        typeTitle.setText("Choose meal:");
        typeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24.0));

        typeGroup = new ToggleGroup();

        breakfastRadio = new RadioButton("Breakfast");
        breakfastRadio.setFocusTraversable(false);
        breakfastRadio.setToggleGroup(typeGroup);
        breakfastRadio.setSelected(true);

        lunchRadio = new RadioButton("Lunch");
        lunchRadio.setFocusTraversable(false);
        lunchRadio.setToggleGroup(typeGroup);

        dinnerRadio = new RadioButton("Dinner");
        dinnerRadio.setFocusTraversable(false);
        dinnerRadio.setToggleGroup(typeGroup);

        HBox typeBox = new HBox(50.0);
        typeBox.setAlignment(Pos.CENTER_LEFT);
        typeBox.getChildren().add(0, typeTitle);
        typeBox.getChildren().add(1, breakfastRadio);
        typeBox.getChildren().add(2, lunchRadio);
        typeBox.getChildren().add(3, dinnerRadio);

        HBox radioBox = new HBox(100.0);
        radioBox.setAlignment(Pos.CENTER_LEFT);
        radioBox.getChildren().add(0, tasteBox);
        radioBox.getChildren().add(1, typeBox);

        ingredientsTable = new TableView();
        ingredientsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ingredientsTable.getSelectionModel().setCellSelectionEnabled(true);
        ingredientsTable.setEditable(false);
        ingredientsTable.setMinWidth(1000.0);
        ingredientsTable.setMaxWidth(1000.0);
        ingredientsTable.setMinHeight(200.0);
        ingredientsTable.setMaxHeight(200.0);

        TableColumn<Ingredient, String> ingredientColumn = new TableColumn<>("Name (Hold 'Ctrl' and click to select multiple)");
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ingredientColumn.setResizable(false);
        ingredientColumn.setSortable(false);
        ingredientColumn.setMinWidth(900.0);
        ingredientColumn.setMaxWidth(900.0);

        if (ingredientData != null && !ingredientData.isEmpty()) {
            ingredientData.sort(Comparator.comparing(Ingredient::getName));
        }

        ingredientsTable.setItems(ingredientData);
        ingredientsTable.getColumns().addAll(ingredientColumn);

        stepsTable = new TableView();
        stepsTable.setEditable(false);
        stepsTable.setMaxWidth(500.0);
        stepsTable.setMaxWidth(500.0);
        stepsTable.setMinHeight(300.0);
        stepsTable.setMaxHeight(300.0);

        TableColumn<Ingredient, String> orderColumn = new TableColumn<>("Order");
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("stepOrder"));
        orderColumn.setResizable(false);
        orderColumn.setSortable(false);
        orderColumn.setMinWidth(100.0);
        orderColumn.setMaxWidth(100.0);

        TableColumn<Ingredient, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        contentColumn.setResizable(false);
        contentColumn.setSortable(false);
        contentColumn.setMinWidth(380.0);
        contentColumn.setMaxWidth(380.0);

        stepsTable.setItems(stepData);
        stepsTable.getColumns().addAll(orderColumn, contentColumn);

        TextField descriptionText = new TextField();
        descriptionText.setMinHeight(300.0);
        descriptionText.setMaxHeight(300.0);
        descriptionText.setMinWidth(300.0);
        descriptionText.setMaxWidth(300.0);

        Button addDescriptionButton = new Button("Add step");
        addDescriptionButton.setFocusTraversable(false);
        addDescriptionButton.setMinWidth(200);
        addDescriptionButton.setMinHeight(40);
        addDescriptionButton.setAlignment(Pos.CENTER);
        addDescriptionButton.setOnAction(action -> {
            Step newStep = new Step(Long.parseLong("1"), Long.parseLong("1"), stepData == null ? 1 : stepData.size() + 1, descriptionText.getText());
            stepData.add(stepData == null ? 0 : stepData.size(), newStep);
            descriptionText.clear();
        });

        HBox stepBox = new HBox(50.0);
        stepBox.setAlignment(Pos.CENTER_LEFT);
        stepBox.getChildren().add(0, stepsTable);
        stepBox.getChildren().add(1, descriptionText);
        stepBox.getChildren().add(2, addDescriptionButton);

        VBox container = new VBox(30.0);
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(0, titleBox);
        container.getChildren().add(1, textfieldBox);
        container.getChildren().add(2, radioBox);
        container.getChildren().add(3, ingredientsTable);
        container.getChildren().add(4, stepBox);

        addRecipePane.add(container, 0, 0);

        Scene scene = new Scene(addRecipePane);

        addRecipeStage.setScene(scene);
        addRecipeStage.setMaximized(true);
        addRecipeStage.showAndWait();

    }

}
