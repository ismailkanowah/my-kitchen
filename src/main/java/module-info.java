module com.example.foodapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;


    opens com.foodapplication to javafx.fxml;
    exports com.foodapplication;
}