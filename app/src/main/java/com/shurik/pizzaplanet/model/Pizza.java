package com.shurik.pizzaplanet.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Pizza {

    // название пиццы
    private String title;

    // описание
    private String desciption;

    // изображение
    private String pic;

    // цена
    private String fee;

    // количество
    private int quantity = 1;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Pizza(String title, String desciption, String pic, String fee) {
        this.title = title;
        this.desciption = desciption;
        this.pic = pic;
        this.fee = fee;
    }

    public String getTitle() {
        return title;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getPic() {
        return pic;
    }

    public String getFee() {
        return fee;
    }


}
