package com.cardsStorage.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cardsStorage.R;
import com.cardsStorage.helpers.CardRecyclerViewAdapter;
import com.cardsStorage.model.CreditCard;

import java.sql.Date;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<CreditCard> creditCards;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        recyclerView = findViewById(R.id.recyclerView);

        initData();
        setRecyclerView();

    }

    private void initData() {
        creditCards = new ArrayList<>();

        // TODO: pobierać karty po id usera
        creditCards.add(new CreditCard("Karta1", "12", new Date(500, 3, 3), 12));
        creditCards.add(new CreditCard("Karta2", "22", new Date(346, 3, 3), 122));
    }


    public void setRecyclerView() {
        CardRecyclerViewAdapter recyclerViewAdapter = new CardRecyclerViewAdapter(creditCards);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }
}