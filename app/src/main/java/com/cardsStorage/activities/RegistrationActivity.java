package com.cardsStorage.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cardsStorage.R;
import com.cardsStorage.database.DatabaseClient;
import com.cardsStorage.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private final int CHARACTERS_IN_PASS = 7;
    private final  String PASS_INC_REG_ERROR = "Passwords should contain" +
            " digit, special char,upper case and more than "+CHARACTERS_IN_PASS+" characters! ";
    private final static String PASS_ERROR = "Please enter password";
    private final static String PASS_INC_ERROR = "Passwords must be the same!Please enter correct password";
    private final static String LOGIN_INC_REG_ERROR_ERROR = "Login should contain only digit, upper case";
    private final static String LOGIN_ERROR = "Please enter login";
    private final static String LOGIN_UNAVAILABLE_ERROR = "Login unavailable, please choose another one";
    private final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^?!&+=])(?=\\S+$).{4,}$";
    private final static String LOGIN_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

    EditText loginEditText;
    EditText passEditText;
    EditText confPasEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        loginEditText = findViewById(R.id.login);
        passEditText =  findViewById(R.id.password);
        confPasEditText = findViewById(R.id.confirmPassword);

        TextView loginLink = (TextView)findViewById(R.id.lnkLogin);
        loginLink.setMovementMethod(LinkMovementMethod.getInstance());
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLoginActivity();
            }
        });
    }
    public void registration(View view)
    {
        switch (view.getId()) {
            case R.id.createAccount:
                saveAccount();
                break;
        }
    }
    private void moveToLoginActivity(){
        Intent intent= new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void addUser(String login, String pass){
        class SaveUser extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... params) {
                if (DatabaseClient.getInstance(getApplicationContext()).
                        getAppDatabase().userDao().findByLogin(login) != null) {
                    return false;
                }else{
                    User user= new User(login,pass);
                    //adding to database
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .userDao()
                            .insert(user);
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result){
                    moveToLoginActivity();
                }else {
                    loginEditText.setError(LOGIN_UNAVAILABLE_ERROR);
                    loginEditText.requestFocus();
                }
            }
        }
        SaveUser saveUser = new SaveUser();
        saveUser.execute();
    }
    private void saveAccount() {
        final String login = loginEditText.getText().toString().trim();
        final String pass = passEditText.getText().toString();
        final String confPass = confPasEditText.getText().toString();
        if(validateUserData( login,pass, confPass)){
            addUser( login,  pass);
        }
    }

    private boolean isValidPassword(final String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private boolean isValidLogin(final String login) {
        Pattern pattern = Pattern.compile(LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }
    private boolean validateUserData(String login, String pass,String confPass){
        if(pass.isEmpty()){
            passEditText.setError(PASS_ERROR);
            passEditText.requestFocus();
            return false; }
        if(confPass.isEmpty()){
            confPasEditText.setError(PASS_ERROR);
            confPasEditText.requestFocus();
            return false; }
        if(login.isEmpty()){
            loginEditText.setError(LOGIN_ERROR);
            loginEditText.requestFocus();
            return false; }

        if(!isValidLogin(login)){
            loginEditText.setError(LOGIN_INC_REG_ERROR_ERROR);
            loginEditText.requestFocus();
            return false; }
        if(!pass.equals(confPass)){
            confPasEditText.setError(PASS_INC_ERROR);
            confPasEditText.requestFocus();
            return false; }
        if(!isValidPassword(pass)){
            passEditText.setError(PASS_INC_REG_ERROR);
            passEditText.requestFocus();
            return false; }
        return true;
    }
}