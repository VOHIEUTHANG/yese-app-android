package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.group3_project.Database.Entity.QuestionType;

import java.util.List;

@Dao
public interface TypeDao {
    @Insert
    void insertListType(List<QuestionType> questionTypeList);

    @Query("SELECT question from QuestionType where id = :typeID")
    String getQuestionByTypeID (int typeID);
}
