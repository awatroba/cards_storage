package com.cardsStorage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cardsStorage.R;
import com.cardsStorage.database.DatabaseClient;
import com.cardsStorage.model.User;

public class MainActivity extends AppCompatActivity {
    private final static String LOGIN_ERROR = "Password incorrect!";

    EditText login;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView register = (TextView)findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());

        login = (EditText)findViewById(R.id.txtLogin);
        password = (EditText)findViewById(R.id.txtPwd);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    public void login(View view){
        final String loginTxt = login.getText().toString().trim();
        final String passTxt = password.getText().toString();
        class LoginUser extends AsyncTask<Void, Void, User> {
            @Override
            protected User doInBackground(Void... params) {
                return DatabaseClient.getInstance(getApplicationContext()).
                        getAppDatabase().userDao().findByLogin(loginTxt);
            }
            @Override
            protected void onPostExecute(User result) {
                if (result!=null && result.getPassword().equals(passTxt)){
                    //close this Activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                }else {
                    password.setError(LOGIN_ERROR);
                    password.requestFocus();
                }
            }
        }
        new LoginUser().execute();
    }

}