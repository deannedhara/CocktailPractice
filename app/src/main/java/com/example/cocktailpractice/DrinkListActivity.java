package com.example.cocktailpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DrinkListActivity extends AppCompatActivity implements SelectListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails_by_letter);

        ArrayList<DrinkItem> drinkList = new ArrayList<>();
        ArrayList<DrinkItem> imageList = new ArrayList<>();

        ArrayList<String> instructionsStrList = getIntent().getExtras().getStringArrayList("strInstructionsList");
        ArrayList<String> drinkStrList =getIntent().getExtras().getStringArrayList("strArray");
        ArrayList<String> imageStrList =getIntent().getExtras().getStringArrayList("strImageList");

        for (int i = 0; i < drinkStrList.size(); i++){
            Log.i("INFO", drinkStrList.get(i));
            String imageUrl = imageStrList.get(i);
            drinkList.add(new DrinkItem(imageUrl, drinkStrList.get(i), instructionsStrList.get(i)));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DrinkAdapter(drinkList, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(DrinkItem drinkItem) {
        Intent recipeIntent = new Intent(this, RecipeActivity.class);
        recipeIntent.putExtra("name", drinkItem.getDrinkName());
        recipeIntent.putExtra("image", drinkItem.getImageResource());
        recipeIntent.putExtra("instructions", drinkItem.getDrinkInstructions());
        Log.i("INFO", "getImageResource: " + drinkItem.getImageResource());
        startActivity(recipeIntent);
    }
}