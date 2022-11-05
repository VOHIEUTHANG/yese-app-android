package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey
    @NonNull
    private String username;
    @NonNull
    private String displayName;
    @NonNull
    private String password;
    private String email;
    @ColumnInfo(defaultValue = "0")
    private int streak;
    @ColumnInfo(defaultValue = "0")
    private int diamond;
    @ColumnInfo(defaultValue = "default_avatar")
    private String avatar;
//    Level 1  -> 3 ; from mới học = 1, sơ cấp = 2 to trung cấp = 3
    private int level;
    private int isAdmin;

    public User(String username, String displayName, String password, String email, int streak, int diamond, String avatar) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.streak = streak;
        this.diamond = diamond;
        this.avatar = avatar;
    }

    public User(String username, String password, String displayName, int diamond,String avatar , int isAdmin) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.diamond = diamond;
        this.avatar = avatar;
        this.isAdmin = isAdmin;
    }

    public User(String username, String displayName, String password) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
    }

    public User(String displayName, int diamond , String avatar) {
        this.displayName = displayName;
        this.diamond = diamond;
        this.avatar = avatar;
    }

    public User(String username,String displayName, String avatar, int diamond ,int streak ) {
        this.username = username;
        this.streak = streak;
        this.displayName = displayName;
        this.diamond = diamond;
        this.avatar = avatar;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public User(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar(){return avatar;}

    public void setAvatar(String avatar){this.avatar = avatar; }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", streak=" + streak +
                ", diamond=" + diamond +
                ", avatar='" + avatar + '\'' +
                ", level=" + level +
                '}';
    }
}
