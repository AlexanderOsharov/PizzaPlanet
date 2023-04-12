package com.shurik.pizzaplanet.pizzasearch;

import android.location.Location;
import android.util.Log;

import com.shurik.pizzaplanet.Constants;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SpoonacularAPI {

    private static final String SPOONACULAR_API_ENDPOINT = "https://api.spoonacular.com/food/menuItems/search";

    private final OkHttpClient client = new OkHttpClient();

    public void getNearbyRestaurants(Location location) {
        String apiKey = Constants.SPOONACULAR_API;
        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        String query = "pizza";

        Request request = new Request.Builder()
                .url(SPOONACULAR_API_ENDPOINT + "/restaurants?apiKey=" + apiKey + "&latitude=" + latitude + "&longitude=" + longitude + "&query=" + query)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            JSONArray restaurants = json.getJSONArray("results");
            Log.e("Jsoooooooooooooooon", responseBody);

            for (int i = 0; i < restaurants.length(); i++) {
                JSONObject restaurant = restaurants.getJSONObject(i);
                String restaurantName = restaurant.getString("name");
                String restaurantAddress = restaurant.getString("vicinity");

                getMenuItems(restaurantName, restaurantAddress);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e("Errororororororoororororoororroroororoor", "Jsooooooooooooooooooooooooon");
        }
    }


    // Метод для получения информации о пиццах из ресторана по адресу и названию
    public void getMenuItems(String restaurantName, String restaurantAddress) {
        String apiKey = Constants.SPOONACULAR_API;

        String query = restaurantName + " " + restaurantAddress;

        Request request = new Request.Builder()
                .url(SPOONACULAR_API_ENDPOINT + "?apiKey=" + apiKey + "&query=" + query)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);

            JSONArray menuItems = json.getJSONArray("menuItems");
            for (int i = 0; i < menuItems.length(); i++) {
                JSONObject menuItem = menuItems.getJSONObject(i);
                String name = menuItem.getString("title");
                JSONArray ingredientsArray = menuItem.getJSONArray("ingredients");
                for (int j = 0; j < ingredientsArray.length(); j++) {
                    JSONObject ingredient = ingredientsArray.getJSONObject(j);
                    String ingredientName = ingredient.getString("name");
                    String ingredientImageURL = ingredient.getString("image");
                    // Дальнейшая обработка полученных данных
                    System.out.println("Pizza name: " + name);
                    System.out.println("Pizza ingredient: " + ingredientName);
                    System.out.println("Pizza ingredient image URL: " + ingredientImageURL);

                    Log.e("Pizza name: ", name);
                    Log.e("Pizza ingredient: ", ingredientName);
                    Log.e("Pizza ingredient image URL: ", ingredientImageURL);
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
