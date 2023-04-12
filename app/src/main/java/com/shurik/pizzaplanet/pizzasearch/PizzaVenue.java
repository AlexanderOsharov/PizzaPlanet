package com.shurik.pizzaplanet.pizzasearch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PizzaVenue {
    private String name;
    private String address;
    private String pizzaName;
    private String pizzaComposition;
    private String imageUrl;

    public PizzaVenue(String name, String address, String pizzaName, String pizzaComposition, String imageUrl) {
        this.name = name;
        this.address = address;
        this.pizzaName = pizzaName;
        this.pizzaComposition = pizzaComposition;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaComposition() {
        return pizzaComposition;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

