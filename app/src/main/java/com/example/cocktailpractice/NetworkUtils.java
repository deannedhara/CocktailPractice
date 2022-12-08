package com.example.cocktailpractice;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class NetworkUtils {
    final static String LETTER_BASE_URL =
            "https://thecocktaildb.com/api/json/v1/1/search.php?";
    final static String PARAM_QUERY = "f";
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    /**
     * Builds the URL used to query Cocktail Database.
     *
     * @param letterSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the Cocktail server.
     */
    public static URL buildUrl(String letterSearchQuery) {
        Uri builtUri = Uri.parse(LETTER_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, letterSearchQuery)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getCocktailList(String queryString) throws IOException{
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String cocktailListJSONString = "";

        try {
            URL cocktailRequestUrl = new URL(queryString);
            //URL url1 = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a");
            urlConnection = (HttpURLConnection) cocktailRequestUrl.openConnection();
            Log.i(LOG_TAG, "made it past urlConnection");

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            Log.i(LOG_TAG, "made it past inputStream");

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            cocktailListJSONString = builder.toString();

        } catch (Exception e) {
            e.getMessage();

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, cocktailListJSONString);
        return cocktailListJSONString;
    }
   }

