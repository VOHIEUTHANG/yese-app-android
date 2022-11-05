package com.example.group3_project.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Notify;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.WordPair;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.SessionManagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

   public static QuestionPackage getPackageIDByTopic(String topic, Context context){
        return AppDatabase.getInstance(context).packageDao().getPackageByTopic(topic);
   }

   public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
   }

    public static void notifyUser(Context context, int questionPackageID, String packageTopic ) {
        List<Notify> notifyList = new ArrayList<>();
        String date = Utils.getCurrentTime();
        String[] usernameList = AppDatabase.getInstance(context).userDao().getAllUsername();
        for (int i = 0; i < usernameList.length; i++) {
            Notify notify = new Notify("Cập nhật câu hỏi trong chủ đề " + packageTopic, questionPackageID, "Tiếp nhận thử thách mới ngay nào !", usernameList[i], date, 0);
            notifyList.add(notify);
        }
        AppDatabase.getInstance(context).notifyDao().insertNotifyList(notifyList);
    }


    public static void showInsertNewQuestionSuccess(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.admin_layout_bottom_popup_insert_success);

        showDialog(dialog);
    }


    public static void showDialog(Dialog dialog) {
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


}
