package com.example.finalproject.model;

public class ActivityModel {
    private String key = "";
    private String name = "";
    private String color = "";
    private int drawable;

    public ActivityModel(String name, String color, int drawable) {
        this.name = name;
        this.color = color;
        this.drawable = drawable;
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
}
