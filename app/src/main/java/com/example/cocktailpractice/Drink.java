package com.example.cocktailpractice;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class Drink {
    private String name;
    private String image;
    private String instructions;
   // private String[] ingredients;
  //  private String[] measurements;

    public Drink(String name, String image, String instructions){
        this.name = name;
        this.image = image;
        this.instructions = instructions;
       // System.arraycopy(ingredients, 0, this.ingredients, 0, ingredients.length);
      //  System.arraycopy(measurements, 0, this.measurements, 0, measurements.length);
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
