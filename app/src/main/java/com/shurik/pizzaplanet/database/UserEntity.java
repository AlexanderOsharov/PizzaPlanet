package com.shurik.pizzaplanet.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserEntity {


    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "Name")
    String name;

    @ColumnInfo(name = "Phone")
    String phone;

    @ColumnInfo(name = "Mail")
    String mail;

    @ColumnInfo(name = "Password")
    String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
