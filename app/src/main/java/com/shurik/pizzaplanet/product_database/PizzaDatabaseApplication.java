package com.shurik.pizzaplanet.product_database;

import android.app.Application;

import androidx.room.Room;

public class PizzaDatabaseApplication extends Application {

    private static PizzaDatabaseApplication instance;

    public static PizzaDatabaseApplication getInstance() {
        return instance;
    }

    private static PizzaDatabase pizzaDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        pizzaDatabase = Room.databaseBuilder(this, PizzaDatabase.class, PizzaDatabase.NAME).fallbackToDestructiveMigration().build();
    }

    public static PizzaDatabase getPizzaDatabase() {
        return pizzaDatabase;
    }
}


