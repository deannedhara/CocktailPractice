package com.example.cocktailpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class RecipeActivity extends AppCompatActivity {
    private TextView mDrinkName;
    private ImageView mDrinkImage;
    private TextView mDrinkInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mDrinkName = findViewById(R.id.drink_name_activity_recipe);
        mDrinkImage = findViewById(R.id.image_activity_recipe);
        mDrinkInstructions = findViewById(R.id.instructions_recipe);

        String name = getIntent().getExtras().getString("name");
        String imageUri = getIntent().getExtras().getString("image");
        String instructions = getIntent().getExtras().getString("instructions");

        mDrinkInstructions.setText(instructions);
        mDrinkName.setText(name);
        Picasso.get().load(imageUri).into(mDrinkImage);
    }
}