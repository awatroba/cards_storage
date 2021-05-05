package com.cardsStorage.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class CreditCard {
    @PrimaryKey(autoGenerate = true)
    private int id;
    // @ForeignKey()

    private int userId;

    @ColumnInfo(name = "card_number")
    private String number;
    @ColumnInfo(name = "card_date")
    private Date date;
    @ColumnInfo(name = "card_cvv")
    private int cvv;

    public CreditCard(String number, Date date, int cvv) {
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}