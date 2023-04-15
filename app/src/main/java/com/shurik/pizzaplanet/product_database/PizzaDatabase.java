package com.shurik.pizzaplanet.product_database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {PizzaEntity.class}, version = 1)
public abstract class PizzaDatabase extends RoomDatabase {
    public static final String NAME = "PizzasC";
    public abstract PizzaDAO pizzaDAO();
}