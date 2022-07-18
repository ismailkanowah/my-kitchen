package com.foodapplication.entity;

public class RecipeIngredient {

    private Long id;

    private Long recipeId;

    private Long ingredientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public RecipeIngredient(Long id, Long recipeId, Long ingredientId) {
        this.id = id;
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public RecipeIngredient(Long recipeId, Long ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }
}
