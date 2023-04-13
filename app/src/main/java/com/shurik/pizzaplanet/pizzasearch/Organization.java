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

    public Organization(String address, String name, String id, String html, ArrayList<Pizza> pizzas) {
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

    @Override
    public String toString() {
        return "Addres: " + address + "\n" +
                "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Pizza" + pizzas.toString();
    }

    public List<Pizza> getPizzaList() {
        return pizzas;
    }
}
