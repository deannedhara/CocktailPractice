package com.example.cocktailpractice;

/**
 * @author Deanne Dhara
 * This class creates Drink objects.
 */

public class Drink {
    private String name;
    private String image;
    private String instructions;

    public Drink(String name, String image, String instructions){
        this.name = name;
        this.image = image;
        this.instructions = instructions;
    }

    public String getName(){
        return name;
    }

    public String getImageString(){
        return image;
    }

    public String getInstructions(){
        return instructions;
    }

}
