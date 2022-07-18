package com.foodapplication.entity;

public class Step {

    private Long id;

    private Long recipeId;

    private Integer stepOrder;

    private String content;

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

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Step(Long id, Long recipeId, Integer stepOrder, String content) {
        this.id = id;
        this.recipeId = recipeId;
        this.stepOrder = stepOrder;
        this.content = content;
    }

    public Step(Long recipeId, Integer stepOrder, String content) {
        this.recipeId = recipeId;
        this.stepOrder = stepOrder;
        this.content = content;
    }
}
