package com.foodapplication;

import com.foodapplication.entity.Ingredient;
import com.foodapplication.entity.Recipe;
import com.foodapplication.enums.Taste;
import com.foodapplication.enums.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static List<Recipe> getRecipes(String searchText, List<Taste> tastes, List<Type> types) {
        List<Recipe> recipeList = new ArrayList<>();

        String query = null;
        String tastesValues = Arrays.stream(Type.values()).map(Enum::name).collect(Collectors.joining(","));
        String typesValues = Arrays.stream(Taste.values()).map(Enum::name).collect(Collectors.joining(","));

        if (Objects.isNull(searchText) && Objects.isNull(tastes) && Objects.isNull(types)) {
            query = "SELECT * FROM recipe";
        } else if (Objects.isNull(tastes) && Objects.isNull(types)) {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%'";
        } else if (Objects.isNull(tastes)) {
            //TODO pas suposer typesValues sa ?
            //TODO type bizin replace par values depi parameter :/
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND type IN (" + tastesValues + ")";
        } else if (Objects.isNull(types)) {
            //TODO pas suposer tastesValues sa ?
            //TODO type bizin replace par values depi parameter :/
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND type IN (" + typesValues + ")";
        } else if (Objects.isNull(searchText) && Objects.isNull(tastes)) {
            query = "SELECT * FROM recipe where type IN (" + typesValues + ")";
        } else if (Objects.isNull(searchText) && Objects.isNull(types)) {
            query = "SELECT * FROM recipe where type IN (" + tastesValues + ")";
        } //TODO else if nothing is null ?

        try {
            Statement statement = DBconnect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                recipeList.add(new Recipe(Long.parseLong(rs.getString("id")), rs.getString("name"), rs.getString("description"),
                        Taste.valueOf(rs.getString("taste")), Type.valueOf(rs.getString("type"))));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return recipeList;

    }
}
