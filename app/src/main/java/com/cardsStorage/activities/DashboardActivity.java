package com.cardsStorage.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.cardsStorage.R;
import com.cardsStorage.helpers.CardRecyclerViewAdapter;
import com.cardsStorage.helpers.SessionManagement;
import com.cardsStorage.model.CreditCard;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<CreditCard> creditCards;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        TextView logout = (TextView)findViewById(R.id.lnkLogout);
        logout.setMovementMethod(LinkMovementMethod.getInstance());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);

        initData();
        setRecyclerView();

    }

    private void logout() {
        SessionManagement sessionManagement=new SessionManagement(this);
        sessionManagement.removeSession();
        moveToLoginActivity();

    }

    private void initData() {
        creditCards = new ArrayList<>();
        // TODO: pobierać karty po id usera
        //creditCards.add(new CreditCard("Karta1", "12", new Date(500, 3, 3), 12));
       // creditCards.add(new CreditCard("Karta2", "22", new Date(346, 3, 3), 122));
    }


    public void setRecyclerView() {
        CardRecyclerViewAdapter recyclerViewAdapter = new CardRecyclerViewAdapter(creditCards);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }

    public void addNewCardActRun(View view) {
        moveToAddCardActivity();
    }
    private void moveToAddCardActivity(){
        Intent intent= new Intent(this,AddCardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void moveToLoginActivity(){
        Intent intent= new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}