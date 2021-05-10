package com.cardsStorage.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cardsStorage.R;
import com.cardsStorage.database.DatabaseClient;
import com.cardsStorage.helpers.SessionManagement;
import com.cardsStorage.model.User;

public class LoginActivity extends AppCompatActivity {
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
                moveToRegistrationActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession(){
        SessionManagement sessionManagement=new SessionManagement(this);
        int userId = sessionManagement.getSession();
        //check if user if logged in
        if(userId != -1){
            //if user is logged in-> move to dashboardActivity
            moveToDashboardActivity();
        }
    }
    private void saveUserInSession(User user){
        SessionManagement sessionManagement=new SessionManagement(this);
        sessionManagement.saveSession(user);
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
                    saveUserInSession(result);

                    moveToDashboardActivity();
                }else {
                    password.setError(LOGIN_ERROR);
                    password.requestFocus();
                }
            }
        }
        new LoginUser().execute();
    }
    private void moveToDashboardActivity(){
        Intent intent= new Intent(LoginActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void moveToRegistrationActivity(){
        Intent intent= new Intent(LoginActivity.this,RegistrationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}