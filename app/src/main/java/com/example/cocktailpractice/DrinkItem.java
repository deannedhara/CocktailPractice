package com.example.cocktailpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class DrinkItem extends AppCompatActivity {
    private String mImageResource;
    private String mDrinkName;
    private String mDrinkInstructions;

    public DrinkItem(String imageResource, String drinkName, String drinkInstructions){
        mImageResource = imageResource;
        mDrinkName = drinkName;
        mDrinkInstructions = drinkInstructions;
    }

    public String getImageResource(){
        return mImageResource;
    }

    public String getDrinkName(){
        return mDrinkName;
    }

    public String getDrinkInstructions() {return mDrinkInstructions;}

}