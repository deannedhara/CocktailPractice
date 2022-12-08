package com.example.cocktailpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Deanne Dhara
 * Cocktail Finder lets users search for cocktail recipes by entering a letter.
 * Currently, the recipe returned has instructions but is missing ingredients with
 * measurements. This will be added in a future release.
 */
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText mEditTextLetter;
    private String urlQuery;

    ExecutorService executorService;
    String jsonResult;
    Drink[] drinking;
    ArrayList<DrinkItem> mDrinkItemList;
    ImageView mMainImage;
    String mainImageUrl = "https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextLetter = findViewById(R.id.editText_letter);
        mMainImage = findViewById(R.id.image_main);

        Picasso.get().load(mainImageUrl).into(mMainImage);
        executorService = Executors.newSingleThreadExecutor();

        Button search = findViewById(R.id.button_search_letter);
        search.setOnClickListener(this::useExecutorService);
    }

    public void makeLetterSearchQuery(){
        String letterQuery = mEditTextLetter.getText().toString();
        URL letterSearchURL = NetworkUtils.buildUrl(letterQuery);
        urlQuery = letterSearchURL.toString();
    }

    public void useExecutorService(View view){
        Log.i(LOG_TAG, "start of useExecutorService method");
        Runnable runnable = () -> {
            makeLetterSearchQuery();
            try {
                jsonResult = NetworkUtils.getCocktailList(urlQuery);
                Log.i(LOG_TAG, "**jsonResult: " + jsonResult);
                Log.i(LOG_TAG, "jsonResult is type: " + jsonResult.getClass());
            }
            catch (IOException e){
                e.printStackTrace();
            }

            try {
                drinking = createDrinksFromJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i(LOG_TAG, "made it past createDrinksFromJason");

            Intent secondActivityIntent = new Intent(this, DrinkListActivity.class);
            secondActivityIntent.putStringArrayListExtra("strArray", this.getDrinkStringList());
            secondActivityIntent.putStringArrayListExtra("strImageList", this.getImageStringList());
            secondActivityIntent.putStringArrayListExtra("strInstructionsList", this.getInstructionStringList());

            startActivity(secondActivityIntent);
        };

        executorService.submit(runnable);
    }

    public Drink[] createDrinksFromJson() throws JSONException {
        // use json class to create drink objects

        String name;
        String image;
        String instructions;

        // converting jsonResult to JSONobject
        JSONObject obj = new JSONObject(jsonResult);

        // check to see if search is empty
        if (obj.isNull("drinks")){
            Log.i(LOG_TAG, "json obj.isNull*******");
            //restart program
            //TODO add error message
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(i);
            overridePendingTransition(0, 0);
        }

        // "drinks" is the key, value is array of cocktail objects
        JSONArray JSONCocktailObjects = obj.getJSONArray("drinks");
        Log.i(LOG_TAG, "JSONCocktailObjects: " + JSONCocktailObjects.toString());

        Drink[] drinkObjects = new Drink[JSONCocktailObjects.length()];

        for (int i = 0; i < JSONCocktailObjects.length(); i++) {
            JSONObject drinkObject = JSONCocktailObjects.getJSONObject(i);

            name = drinkObject.getString("strDrink");
            image = drinkObject.getString("strDrinkThumb");
            instructions = drinkObject.getString("strInstructions");
            Log.i(LOG_TAG, name);

            drinkObjects[i] = new Drink(name, image, instructions);
        }
        return drinkObjects;
    }

    public ArrayList<String> getDrinkStringList(){
        ArrayList<String> strNameList = new ArrayList<>();

        for (Drink drink : drinking){
            strNameList.add(drink.getName());
        }
        return strNameList;
    }

    public ArrayList<String> getImageStringList(){
        ArrayList<String> strImageList = new ArrayList<>();

        for (Drink drink : drinking){
            strImageList.add(drink.getImageString());
        }
        return strImageList;
    }

    public ArrayList<String> getInstructionStringList(){
        ArrayList<String> strInstructionsList = new ArrayList<>();

        for (Drink drink : drinking){
            strInstructionsList.add(drink.getInstructions());
        }
        return strInstructionsList;
    }
}
