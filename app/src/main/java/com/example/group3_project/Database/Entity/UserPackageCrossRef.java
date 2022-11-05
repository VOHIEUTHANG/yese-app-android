package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "UserAnswersResult", primaryKeys = {"rk_username", "rk_packageID"})
public class UserPackageCrossRef implements Serializable {
    @NonNull
    private String rk_username;
    @NonNull
    private int rk_packageID;
    private int progressPercent;
    private int correctAnswerCount;
    private int isFinish = 0;
    private Date answerAt;


    public UserPackageCrossRef() {
    }

    public UserPackageCrossRef(String rk_username, int rk_packageID, int progressPercent, int correctAnswerCount, int isFinish, Date answerAt) {
        this.rk_username = rk_username;
        this.rk_packageID = rk_packageID;
        this.progressPercent = progressPercent;
        this.correctAnswerCount = correctAnswerCount;
        this.isFinish = isFinish;
        this.answerAt = answerAt;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public Date getAnswerAt() {
        return answerAt;
    }

    public void setAnswerAt(Date answerAt) {
        this.answerAt = answerAt;
    }

    public String getRk_username() {
        return rk_username;
    }

    public void setRk_username(String rk_username) {
        this.rk_username = rk_username;
    }

    public int getRk_packageID() {
        return rk_packageID;
    }

    public void setRk_packageID(int rk_packageID) {
        this.rk_packageID = rk_packageID;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    public int getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(int correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }
}
