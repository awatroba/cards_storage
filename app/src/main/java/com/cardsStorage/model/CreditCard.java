package com.cardsStorage.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class CreditCard {
    @PrimaryKey(autoGenerate = true)
    private int id;
    // TODO: dodać @ForeignKey() i wypisywać karty po userze
    // @ForeignKey()

    private int userId;

    @ColumnInfo(name = "card_name")
    private String name;
    @ColumnInfo(name = "card_number")
    private String number;
    @ColumnInfo(name = "card_date")
    private Date date;
    @ColumnInfo(name = "card_cvv")
    private int cvv;
    // State of the item
    private boolean expandable;

    public CreditCard(String name,String number, Date date, int cvv) {
        this.name=name;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
        this.expandable =false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", cvv=" + cvv +
                ", expanded=" + expandable +
                '}';
    }
}