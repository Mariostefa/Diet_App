package com.lib.calories;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String name;
    private int calories;
    private int proteins;
    private int carbohydrates;

    private int fats;

    public FoodItem(String name, int calories, int proteins, int carbohydrates, int fats) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getFats() {
        return fats;
    }
}