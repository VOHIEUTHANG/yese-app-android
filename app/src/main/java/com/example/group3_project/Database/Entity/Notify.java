package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName ="Notify")
public class Notify {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String title;
    @NonNull
    private int fk_packageID;
    private String content;
    private String fk_username;
    private String createAt;
    private int isReaded;

    public Notify(){};

    public Notify(@NonNull String title, int fk_packageID, String content, String fk_username, String createAt, int isReaded) {
        this.title = title;
        this.fk_packageID = fk_packageID;
        this.content = content;
        this.fk_username = fk_username;
        this.createAt = createAt;
        this.isReaded = isReaded;
    }

    public Notify(@NonNull String title, int fk_packageID, String fk_username, String createAt, int isReaded) {
        this.title = title;
        this.fk_packageID = fk_packageID;
        this.fk_username = fk_username;
        this.createAt = createAt;
        this.isReaded = isReaded;
    }

    public int getFk_packageID() {
        return fk_packageID;
    }

    public void setFk_packageID(int fk_packageID) {
        this.fk_packageID = fk_packageID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFk_username() {
        return fk_username;
    }

    public void setFk_username(String fk_username) {
        this.fk_username = fk_username;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(int isReaded) {
        this.isReaded = isReaded;
    }
}
