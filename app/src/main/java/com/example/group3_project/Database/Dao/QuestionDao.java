package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.group3_project.Database.Entity.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insertListQuestion(List<Question> questionList);

    @Insert
    void insertQuestion(Question question);
}
