package com.example.group3_project;

import android.app.Application;
import android.media.MediaPlayer;

public class MyApplication extends Application {

    public static String username;
    public static MediaPlayer player;

    public static String  getUsername() {
        return username;
    }

    public void setPalyer(MediaPlayer player){this.player = player;}

    public static MediaPlayer getPlayer(){return player;}

    public void setUsername(String username) {
        this.username = username;
    }
}
