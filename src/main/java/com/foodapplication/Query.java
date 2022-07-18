package com.foodapplication;

import com.foodapplication.entity.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query extends Database {

    private static final Connection DBconnect = getConnection();


    public void addIngredient(Ingredient ingredient) {
        String SQL = "INSERT INTO ingredient (name) values (?)";
        try {
            PreparedStatement preparedStatement = DBconnect.prepareStatement(SQL);
            preparedStatement.setString(1, ingredient.getName());

            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
    }

}
