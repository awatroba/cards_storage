package com.cardsStorage.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user_login")
    private String login;
    @ColumnInfo(name = "user_password")
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}