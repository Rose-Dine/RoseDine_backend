package com.rosedine.rosedine.dto;

import lombok.Getter;
import lombok.Setter;

public class UserPreferences {
    private boolean isVegan;
    private boolean isVegetarian;
    private boolean isGlutenFree;
    @Setter
    @Getter
    private int Protein;
    @Setter
    private int Carbohydrates;
    @Getter
    @Setter
    private int Fats;
    @Getter
    @Setter
    private int Calories;

    // Constructors
    public UserPreferences() {}

    public UserPreferences(boolean isVegan, boolean isVegetarian, boolean isGlutenFree, int targetProtein, int targetCarbs, int targetFats, int targetCalories) {
        this.isVegan = isVegan;
        this.isVegetarian = isVegetarian;
        this.isGlutenFree = isGlutenFree;
        this.Protein = targetProtein;
        this.Carbohydrates = targetCarbs;
        this.Fats = targetFats;
        this.Calories = targetCalories;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean isGlutenFree) {
        this.isGlutenFree = isGlutenFree;
    }

    public int getCarbs() {
        return Carbohydrates;
    }
}
