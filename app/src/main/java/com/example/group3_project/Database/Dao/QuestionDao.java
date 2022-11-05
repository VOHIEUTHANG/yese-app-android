package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.group3_project.Database.Entity.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insertListQuestion(List<Question> questionList);

    @Insert
    void insertQuestion(Question question);

    @Query("SELECT count(id) from Question where rk_packageID = :packageID")
    int getQuestionCountByPackageID(int packageID);

    @Query("DELETE FROM Question WHERE rk_packageID = :packageID AND questionDataInsertType is not null")
    int deleteInsertQuestionByPackageID(int packageID);

    @Query("DELETE FROM Question WHERE questionDataInsertType is not null")
    int deleteAllInsertQuestionByPackageID();

    @Query("SELECT * from Question where rk_packageID = :packageID")
    List<Question> getQuestionListByPackageID(int packageID);

    @Query("SELECT * from Question where rk_packageID = :packageID AND questionDataInsertType is null")
    List<Question> getQuestionListByPackageIDAndIsNormalType(int packageID);

    @Query("SELECT * from Question where rk_packageID = :packageID AND questionDataInsertType is not null")
    List<Question> getQuestionListByPackageIDAndIsInsertType(int packageID);
}
