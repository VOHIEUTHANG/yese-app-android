package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "QuestionPackage")
public class QuestionPackage  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String topicName;
    @NonNull
    private String icon;
    @NonNull
    private int level;
//    0 is lock, 1 is unlock
    private int isLock;

    private int haveNewUpdate;

    public QuestionPackage(String topicName, String icon, int level, int isLock) {
        this.topicName = topicName;
        this.icon = icon;
        this.level = level;
        this.isLock = isLock;
    }

    public QuestionPackage(int id, String topicName, @NonNull String icon, int level, int isLock) {
        this.id = id;
        this.topicName = topicName;
        this.icon = icon;
        this.level = level;
        this.isLock = isLock;
    }

    public QuestionPackage(){}

    public int getHaveNewUpdate() {
        return haveNewUpdate;
    }

    public void setHaveNewUpdate(int haveNewUpdate) {
        this.haveNewUpdate = haveNewUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(@NonNull String topicName) {
        this.topicName = topicName;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    @NonNull
    public String getIcon() {
        return icon;
    }

    public void setIcon(@NonNull String icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
