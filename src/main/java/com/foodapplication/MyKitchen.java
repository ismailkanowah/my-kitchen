package com.foodapplication;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MyKitchen extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {


        GridPane mainMenu = new GridPane();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Stock Control System");
        vbox.getChildren().add(programTitle);

        mainMenu.getChildren().add(vbox);

        Scene scene = new Scene(mainMenu);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Stock Control System");
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