package com.cardsStorage.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cardsStorage.model.CreditCard;
import com.cardsStorage.model.User;

import java.util.List;

@Dao
public interface CardDao {
    @Query("SELECT * FROM creditCard where userId LIKE :userId")
    List<CreditCard> getAll(int userId);

    @Query("SELECT * FROM creditCard WHERE id IN (:cardId)")
    List<CreditCard> loadAllById(int[] cardId);

    @Query("SELECT * FROM creditCard WHERE card_number LIKE :number  LIMIT 1")
    CreditCard findByNumber(String number);

    @Query("SELECT * FROM creditCard WHERE card_cvv LIKE :cvv  LIMIT 1")
    CreditCard findByCVV(String cvv);

    @Insert
    void insertAll(CreditCard... creditCards);

    @Insert
    void insert(CreditCard creditCard);

    @Delete
    void delete(CreditCard creditCard);
}