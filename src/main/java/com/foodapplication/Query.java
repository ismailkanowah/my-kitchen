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

        String query = null, tastesValues = null, typesValues = null;
        if (!tastes.isEmpty()) tastesValues = tastes.stream().map(Enum::name).collect(Collectors.joining(","));
        if (!types.isEmpty()) typesValues = types.stream().map(Enum::name).collect(Collectors.joining(","));

        System.out.println(tastesValues);

        if (Objects.isNull(searchText) && tastes.isEmpty() && types.isEmpty()) {
            query = "SELECT * FROM recipe";
        } else if (tastes.isEmpty() && types.isEmpty()) {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%'";
        } else if (tastes.isEmpty()) {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND type IN (" + typesValues + ")";
        } else if (types.isEmpty()) {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND taste IN (" + tastesValues + ")";
        } else if (Objects.isNull(searchText) && tastes.isEmpty()) {
            query = "SELECT * FROM recipe where type IN (" + typesValues + ")";
        } else if (Objects.isNull(searchText) && types.isEmpty()) {
            query = "SELECT * FROM recipe where type IN (" + tastesValues + ")";
        } else {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND type IN (" + typesValues + ") AND taste IN (" + tastesValues + ")";
        }

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
