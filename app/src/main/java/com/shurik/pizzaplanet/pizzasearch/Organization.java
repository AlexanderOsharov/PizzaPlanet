package com.shurik.pizzaplanet.pizzasearch;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String address;
    private String name;
    private String id;
    private List<Pizza> pizzas;

    public Organization(String address, String name, String id) {
        this.address = address;
        this.name = name;
        this.id = id;
    }

    public Organization(String address, String name, String id, ArrayList<Pizza> pizzas) {
        this.address = address;
        this.name = name;
        this.id = id;
        this.pizzas = pizzas;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Pizza> getPizzaList() {
        return pizzas;
    }
}
