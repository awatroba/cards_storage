package com.cardsStorage.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.cardsStorage.model.User;

public class SessionManagement {
    private static final String SHARED_PREF_NAME = "SESSION";
    private static final String SESSION_KEY = "SESSION_USER";
    int PRIVATE_MODE = 0;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManagement(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }
    //save session of user whenever user is logged in
    public void saveSession(User user){
        editor.putInt(SESSION_KEY,user.getId()).commit();
    }
    //remove session
    public void removeSession(){
        editor.putInt(SESSION_KEY,-1).commit();


    }
    //return user id whose session is saved
    public int getSession(){
        return sharedPreferences.getInt(SESSION_KEY,-1 );
    }


}
