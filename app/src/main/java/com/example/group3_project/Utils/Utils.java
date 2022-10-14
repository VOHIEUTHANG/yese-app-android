package com.example.group3_project.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.WordPair;
import com.example.group3_project.MyApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
    public static int getResourceId(Context context, String name) {
        name = name.toLowerCase();
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static void deleteDatabase(Context applicationContext){
        applicationContext.deleteDatabase("yese.db");
    }
}