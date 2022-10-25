package com.example.group3_project;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.group3_project.Database.Entity.User;


public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARE_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user) {
        editor.putBoolean("LOGIN_STATUS", true);
        editor.putString("USERNAME", user.getUsername());
        editor.commit();
    }

    public String getUsername() {
        return sharedPreferences.getString("USERNAME","none");
    }

    public boolean getLoginStatus() {
        return sharedPreferences.getBoolean("LOGIN_STATUS",false);
    }

    public void logout() {
        editor.putBoolean("LOGIN_STATUS", false);
        editor.putString("USERNAME", null);
    }

}
