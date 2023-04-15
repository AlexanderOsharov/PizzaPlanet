package com.shurik.pizzaplanet.product_database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.shurik.pizzaplanet.model.Pizza;

import java.util.List;

@Dao
public interface PizzaDAO {

    @Query("select * from Pizza")
    List<PizzaEntity> getAll(); // получение всех пицц

    @Insert
    void save(PizzaEntity pizza); // сохранение пиццы

    @Update
    void update(PizzaEntity pizza); // обновление пиццы

    @Delete
    void delete(PizzaEntity pizza); // удаление пиццы

    @Query(value = "SELECT * FROM Pizza WHERE pizzaTitle = :pizzaTitle")
    PizzaEntity search(String pizzaTitle); // поиск по названию пиццы
}