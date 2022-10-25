package com.example.group3_project;

import android.app.Application;
import android.media.MediaPlayer;

public class MyApplication extends Application {

    public static String username;
    public static MediaPlayer player;
    public static String userAnswer = "";

    public static String  getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static MediaPlayer getPlayer(){return player;}

    public static void setPlayer(MediaPlayer player) {
        MyApplication.player = player;
    }

    public static String getUserAnswer() {
        return userAnswer;
    }

    public static void setUserAnswer(String userAnswer) {
        MyApplication.userAnswer = userAnswer;
    }
}
