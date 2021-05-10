package com.cardsStorage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cardsStorage.R;
import com.cardsStorage.database.DatabaseClient;
import com.cardsStorage.model.CreditCard;
import com.cardsStorage.model.User;

import java.sql.Date;
import java.util.Calendar;


public class AddCardActivity extends AppCompatActivity {
    private EditText newCardNameTxt;
    private EditText newCardNumberTxt;
    private EditText newCardCVVTxt;
    private EditText newCardDateTxt;
    private DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        newCardNameTxt = findViewById(R.id.newCardNameTxt);
        newCardNumberTxt =  findViewById(R.id.newCardNumberTxt);
        newCardCVVTxt = findViewById(R.id.newCardCVVTxt);
        newCardDateTxt = findViewById(R.id.newCardDateTxt);

        newCardDateTxt.setInputType(InputType.TYPE_NULL);
        newCardDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddCardActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                newCardDateTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
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

    public void addNewCard(View view) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCvv(123);
        creditCard.setName("fe");
        if(validateCardData( creditCard )){
            addCard(creditCard);
        }
    }
    private boolean validateCardData(CreditCard creditCard){
        return true;
    }
    private void addCard(CreditCard creditCard){
        class SaveCard extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... params) {
                if (DatabaseClient.getInstance(getApplicationContext()).
                        getAppDatabase().cardDao().findByNumber(creditCard.getNumber()) != null) {
                    return false;
                }else{
                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .cardDao()
                            .insert(creditCard);
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result){
                    moveToDashboardActivity();
                }else {
                }
            }
        }
        SaveCard saveCard = new SaveCard();
        saveCard.execute();
    }
    private void moveToDashboardActivity(){
        Intent intent= new Intent(this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}