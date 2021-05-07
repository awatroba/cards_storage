package com.cardsStorage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cardsStorage.R;

public class AddCardActivity extends AppCompatActivity {
    EditText newCardNameTxt;
    EditText newCardNumberTxt;
    EditText newCardCVVTxt;
    EditText newCardDateTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        newCardNameTxt = findViewById(R.id.newCardNameTxt);
        newCardNumberTxt =  findViewById(R.id.newCardNumberTxt);
        newCardCVVTxt = findViewById(R.id.newCardCVVTxt);
        newCardDateTxt = findViewById(R.id.newCardDateTxt);

        TextView loginLink = (TextView)findViewById(R.id.lnkAddCancel);
        loginLink.setMovementMethod(LinkMovementMethod.getInstance());
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

}