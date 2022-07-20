package com.foodapplication;

import com.foodapplication.entity.Ingredient;
import com.foodapplication.entity.Recipe;
import com.foodapplication.entity.Step;
import com.foodapplication.enums.Taste;
import com.foodapplication.enums.Type;

import java.sql.*;
import java.util.*;
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

    public static List<Recipe> getRecipes(String searchText, List<Integer> tastes, List<Integer> types) {
        List<Recipe> recipeList = new ArrayList<>();

        String query = null, tastesValues = null, typesValues = null;
        if (!tastes.isEmpty()) tastesValues = tastes.stream().map(Object::toString).collect(Collectors.joining(","));
        if (!types.isEmpty()) typesValues = types.stream().map(Object::toString).collect(Collectors.joining(","));

        if (Objects.isNull(searchText) && tastes.isEmpty() && types.isEmpty()) {
            query = "SELECT * FROM recipe";
        } else if (Objects.isNull(searchText) && tastes.isEmpty()) {
            query = "SELECT * FROM recipe where type IN (" + typesValues + ")";
        } else if (Objects.isNull(searchText) && types.isEmpty()) {
            query = "SELECT * FROM recipe where taste IN (" + tastesValues + ")";
        } else if (tastes.isEmpty() && types.isEmpty()) {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%'";
        } else if (tastes.isEmpty()) {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND type IN (" + typesValues + ")";
        } else if (types.isEmpty()) {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND taste IN (" + tastesValues + ")";
        } else if (Objects.isNull(searchText)) {
            query = "SELECT * FROM recipe where type IN (" + typesValues + ") AND taste IN (" + tastesValues + ")";
        } else {
            query = "SELECT * FROM recipe where name LIKE '%" + searchText + "%' AND type IN (" + typesValues + ") AND taste IN (" + tastesValues + ")";
        }

        try {
            Statement statement = DBconnect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                recipeList.add(new Recipe(Long.parseLong(rs.getString("id")), rs.getString("name"), rs.getString("description"),
                  Taste.getEnumByValue(Integer.valueOf(rs.getString("taste"))),
                  Type.getEnumByValue(Integer.valueOf(rs.getString("type"))))
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return recipeList;

    }

    public static List<Step> getRecipeSteps(Long recipeId) {
        String query = "SELECT * FROM step WHERE recipeId=" + recipeId + ";";
        List<Step> stepList = new ArrayList<>();
        try {
            Statement statement = DBconnect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                stepList.add(new Step(Long.parseLong(rs.getString("id")), Long.parseLong(rs.getString("recipeId")), Integer.parseInt(rs.getString("stepOrder")), rs.getString("content")));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }

        stepList.sort(Comparator.comparing(Step::getStepOrder));

        return stepList;

    }

    public static List<Ingredient> getAllIngredient() {
        String query = "SELECT * FROM ingredient;";
        List<Ingredient> ingredientList = new ArrayList<>();
        try {
            Statement statement = DBconnect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                ingredientList.add(new Ingredient(Long.parseLong(rs.getString("id")), rs.getString("name")));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return ingredientList;

    }

    public static List<Ingredient> getIngredient(Long recipeId) {
        String query = "SELECT * FROM ingredient WHERE id IN (SELECT ingredientId FROM recipe_ingredient WHERE recipeId=" + recipeId + ");";
        List<Ingredient> ingredientList = new ArrayList<>();
        try {
            Statement statement = DBconnect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                ingredientList.add(new Ingredient(Long.parseLong(rs.getString("id")), rs.getString("name")));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return ingredientList;

    }

    public static void removeRecipe(Long recipeId) {
        String queryStep = "DELETE FROM step WHERE recipeId=" + recipeId + ";";
        String queryIngredient = "DELETE FROM recipe_ingredient WHERE recipeId=" + recipeId + ";";
        String queryRecipe = "DELETE FROM recipe WHERE id=" + recipeId + ";";

        try {
            Statement statement = DBconnect.createStatement();
            statement.executeQuery(queryStep);
            statement.executeQuery(queryIngredient);
            statement.executeQuery(queryRecipe);

        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }

    }

    public static void addRecipe(Recipe recipe, List<Long> ingredientsList) {
        String addRecipeQuery = "INSERT INTO recipe (name,description,taste,type) VALUES (?,?,?,?)";
        int recipeId = 0;
        try {
            PreparedStatement preparedStatement = DBconnect.prepareStatement(addRecipeQuery);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setString(3, String.valueOf(recipe.getTaste().value));
            preparedStatement.setString(4, String.valueOf(recipe.getType().value));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                recipeId = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
        addIngredientsRecipe(recipeId,ingredientsList);
    }

    public static void addIngredientsRecipe(Integer recipeId, List<Long> ingredientsList) {
        String addIngredientsRecipeQuery = "INSERT INTO recipe_ingredient (recipeId,ingredientId) VALUES (?,?);";
        ingredientsList.forEach((i) -> {
            try {
                PreparedStatement preparedStatement = DBconnect.prepareStatement(addIngredientsRecipeQuery);
                preparedStatement.setString(1, recipeId.toString());
                preparedStatement.setString(2, i.toString());
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("SQL error: " + ex.getMessage());
            }
        });
    }
}