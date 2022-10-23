package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "QuestionType")
public class QuestionType {
    @PrimaryKey
    @NonNull
    private int id;
    @NonNull
    private String question;

    public QuestionType() {
    }

    public QuestionType(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }
}
