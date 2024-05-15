package com.example.finalproject.model;

public class SportCategoryModel {
    private int id;
    private String key = "";
    private String name = "";
    private String color = "";
    private int drawable;
    private boolean selected = false;

    public SportCategoryModel(int id, String name, String color, int drawable) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.drawable = drawable;
    }

    public SportCategoryModel(int id, String name, String color, int drawable, boolean selected) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.drawable = drawable;
        this.selected = selected;
    }

    public SportCategoryModel(String name, String color, int drawable) {
        this.name = name;
        this.color = color;
        this.drawable = drawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
