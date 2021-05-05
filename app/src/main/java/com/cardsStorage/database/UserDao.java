package com.cardsStorage.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.cardsStorage.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllById(int[] userIds);

    @Query("SELECT * FROM user WHERE user_login LIKE :login  LIMIT 1")
    User findByLogin(String login);

    @Insert
    void insertAll(User... users);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

}