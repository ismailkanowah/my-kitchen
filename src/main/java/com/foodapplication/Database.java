package com.foodapplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static String url = "jdbc:mysql://localhost/food_db";
    private static String username = "root";
    private static String password = "";

    private static Connection con;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connection Established");
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

}
