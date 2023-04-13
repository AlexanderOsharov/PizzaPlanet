package com.shurik.pizzaplanet.pizzasearch;

public class Pizza {
    private String pizzaName;
    private String pizzaComposition;
    private String imageUrl;
    private String price;

    public Pizza(String pizzaName, String pizzaComposition, String imageUrl, String price) {
        this.pizzaName = pizzaName;
        this.pizzaComposition = pizzaComposition;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
