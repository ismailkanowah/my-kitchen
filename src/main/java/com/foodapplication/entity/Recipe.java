package com.foodapplication.entity;

import com.foodapplication.enums.Taste;
import com.foodapplication.enums.Type;

public class Recipe {

    private Long id;

    private String name;

    private String description;

    private Taste taste;

    private Type type;

    public Taste getTaste() {
        return taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Recipe(Long id, String name, String description, Taste taste, Type type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taste = taste;
        this.type = type;
    }

    public Recipe(String name, String description, Taste taste, Type type) {
        this.name = name;
        this.description = description;
        this.taste = taste;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taste=" + taste +
                ", type=" + type +
                '}';
    }
}
