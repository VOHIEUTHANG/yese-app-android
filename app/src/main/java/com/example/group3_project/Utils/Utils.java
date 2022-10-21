package com.example.group3_project.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.WordPair;
import com.example.group3_project.MyApplication;
import com.example.group3_project.SessionManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Utils {
    public static int getDrawableResourceIdFromFileName(Context context, String name) {
        name = name.toLowerCase();
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
    public static int getRawResourceIdFromFileName(Context context, String name) {
        name = name.toLowerCase();
        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static void deleteDatabase(Context applicationContext){
        applicationContext.deleteDatabase("yese.db");
    }

    public static User getCurrentUser(Context context){
        SessionManagement sessionManagement = new SessionManagement(context);
        Boolean loginStatus = sessionManagement.getLoginStatus();
        if(!loginStatus) return null;
        String username = sessionManagement.getUsername();
        if(username == null || username.equals("none")) return null;
        return AppDatabase.getInstance(context).userDao().findOneUserByUsername(username);
    }

    public static String getUsername(Context context){
        SessionManagement sessionManagement = new SessionManagement(context);
        Boolean loginStatus = sessionManagement.getLoginStatus();
        if(!loginStatus) return null;
        String username = sessionManagement.getUsername();
        if(username == null || username.equals("none")) return null;
        return username;
    }

    public static String getHashPassword(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static Boolean verifyPassword(String password, String hashPassword){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashPassword);
        return result.verified;
    }


}
