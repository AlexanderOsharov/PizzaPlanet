package com.shurik.pizzaplanet.pizzasearch;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MenuMatch {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://us-restaurant-menus.p.rapidapi.com/restaurants/search?query=<query>")
                .method("GET", null)
                .addHeader("x-rapidapi-host", "us-restaurant-menus.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "<api_key>")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
