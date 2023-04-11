package com.shurik.pizzaplanet.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity); // сохранение
    @Update
    void update(UserEntity userEntity); // обновление

    @Query(value = "SELECT * FROM Users WHERE Password = :s")
    UserEntity[] search(String s); // поиск по паролю
}
