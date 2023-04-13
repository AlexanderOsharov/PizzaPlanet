package com.shurik.pizzaplanet.pizzasearch;

public class Pizza {
    private final String pizzaName;
    private final String pizzaDescription;
    private String imageUrl;
    private final String pizzaPrise;

    public Pizza(String pizzaName, String pizzaDescription, String pizzaPrise) {
        this.pizzaName = pizzaName;
        this.pizzaDescription = pizzaDescription;
        this.pizzaPrise = pizzaPrise;
    }

    public Pizza(String pizzaName, String pizzaDescription, String imageUrl, String pizzaPrise) {
        this.pizzaName = pizzaName;
        this.pizzaDescription = pizzaDescription;
        this.imageUrl = imageUrl;
        this.pizzaPrise = pizzaPrise;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String pizzaDescription() {
        return pizzaDescription;
    }

    public String getPizzaImageUrl() {
        return imageUrl;
    }

    public String getPizzaPrise() {
        return pizzaPrise;
    }
}
