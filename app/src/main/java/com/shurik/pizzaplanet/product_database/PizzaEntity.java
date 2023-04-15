package com.shurik.pizzaplanet.product_database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Pizza")
public class PizzaEntity { // класс нашего пользователя

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "picProductUrl")
    @NotNull
    public String picProductUrl;

    @ColumnInfo(name = "pizzaTitle")
    @NotNull
    public String pizzaTitle;

    @ColumnInfo(name = "pizzaPrice")
    public int pizzaPrice;

    @ColumnInfo(name = "pizzaCount")
    public int pizzaCount;

    public void setId(int id) {
        this.id = id;
    }

    public void setPicProductUrl(@NotNull String picProductUrl) {
        this.picProductUrl = picProductUrl;
    }

    public void setPizzaTitle(@NotNull String pizzaTitle) {
        this.pizzaTitle = pizzaTitle;
    }

    public void setPizzaPrice(int pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public void setPizzaCount(int pizzaCount) {
        this.pizzaCount = pizzaCount;
    }

    public int getId() {
        return id;
    }

    @NotNull
    public String getPicProductUrl() {
        return picProductUrl;
    }

    @NotNull
    public String getPizzaTitle() {
        return pizzaTitle;
    }

    public int getPizzaPrice() {
        return pizzaPrice;
    }

    public int getPizzaCount() {
        return pizzaCount;
    }

    public PizzaEntity(@NonNull String picProductUrl, @NonNull String pizzaTitle, int pizzaPrice, int pizzaCount) {
        this.picProductUrl = picProductUrl;
        this.pizzaTitle = pizzaTitle;
        this.pizzaPrice = pizzaPrice;
        this.pizzaCount = pizzaCount;
    }
}