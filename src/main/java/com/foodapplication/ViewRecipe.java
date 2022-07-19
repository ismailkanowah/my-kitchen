package com.foodapplication;

import com.foodapplication.entity.Ingredient;
import com.foodapplication.entity.Recipe;
import com.foodapplication.entity.Step;
import com.foodapplication.enums.Taste;
import com.foodapplication.enums.Type;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewRecipe {

    static Stage viewRecipeStage;
    static TableView ingredientsTable, stepsTable;
    static ObservableList<Ingredient> ingredientData;
    static ObservableList<Step> stepData;


    public static void viewRecipe(Recipe recipe) throws Exception {

        ingredientData = FXCollections.observableArrayList(Query.getIngredient(recipe.getId()));
        stepData = FXCollections.observableArrayList(Query.getRecipeSteps(recipe.getId()));

        viewRecipeStage = new Stage();
        viewRecipeStage.setTitle("View recipe" + recipe.getName());
        viewRecipeStage.setResizable(false);
        viewRecipeStage.initModality(Modality.APPLICATION_MODAL);

        GridPane viewRecipePane = new GridPane();
        viewRecipePane.setAlignment(Pos.CENTER);
        viewRecipePane.setVgap(30.0);

        Label programTitle = new Label();
        programTitle.setText("My Kitchen");
        programTitle.setFont(Font.font("Arial", FontWeight.BOLD, 32.0));

        Label recipeTitle = new Label();
        recipeTitle.setText(recipe.getName());
        recipeTitle.setFont(Font.font("Arial", 28.0));

        Button backButton = new Button("Back");
        backButton.setFocusTraversable(false);
        backButton.setMinWidth(100);
        backButton.setMinHeight(40);
        backButton.setAlignment(Pos.CENTER);
        backButton.setOnAction(action -> viewRecipeStage.close());

        HBox titleBox = new HBox(350.0);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.getChildren().add(0, programTitle);
        titleBox.getChildren().add(1, recipeTitle);
        titleBox.getChildren().add(2, backButton);

        ingredientsTable = new TableView();
        ingredientsTable.setEditable(false);
        ingredientsTable.setMinWidth(400.0);
        ingredientsTable.setMaxWidth(400.0);
        ingredientsTable.setMinHeight(300.0);
        ingredientsTable.setMaxHeight(300.0);

        TableColumn<Ingredient, String> ingredientColumn = new TableColumn<>("Name");
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ingredientColumn.setResizable(false);
        ingredientColumn.setSortable(false);
        ingredientColumn.setMinWidth(380.0);
        ingredientColumn.setMaxWidth(380.0);

        ingredientsTable.setItems(ingredientData);
        ingredientsTable.getColumns().addAll(ingredientColumn);

        Text descriptionText = new Text(recipe.getDescription());
        descriptionText.setFont(Font.font("Arial", 24.0));
        descriptionText.setWrappingWidth(600.0);

        HBox ingredientBox = new HBox(50.0);
        ingredientBox.setAlignment(Pos.CENTER_LEFT);
        ingredientBox.getChildren().add(0, ingredientsTable);
        ingredientBox.getChildren().add(1, descriptionText);

        stepsTable = new TableView();
        stepsTable.setEditable(false);
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
        contentColumn.setMinWidth(800.0);
        contentColumn.setMaxWidth(800.0);

        stepsTable.setItems(stepData);
        stepsTable.getColumns().addAll(orderColumn, contentColumn);

        VBox container = new VBox(30.0);
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(0, titleBox);
        container.getChildren().add(1, ingredientBox);
        container.getChildren().add(2, stepsTable);

        viewRecipePane.add(container, 0, 0);

        Scene scene = new Scene(viewRecipePane);

        viewRecipeStage.setScene(scene);
        viewRecipeStage.setMaximized(true);
        viewRecipeStage.showAndWait();



    }

}
