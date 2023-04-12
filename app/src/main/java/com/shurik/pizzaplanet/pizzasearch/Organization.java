package com.shurik.pizzaplanet.pizzasearch;

import java.util.List;

public class Organization {
    private String address;
    private String name;
    private String id;
    private Pizza pizzaList;

    public Organization(String address, String name, String id) {
        this.address = address;
        this.name = name;
        this.id = id;
    }

    public Organization(String address, String name, String id, String html) {
        this.address = address;
        this.name = name;
        this.id = id;
        pizzaList = new Pizza(html);
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
                "ID: " + id;
    }
}
