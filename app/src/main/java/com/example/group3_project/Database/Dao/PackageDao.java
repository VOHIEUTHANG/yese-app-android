package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.PackageWithQuestions;

import java.util.List;

@Dao
public interface PackageDao {

    @Insert
    void insertListPackage(List<QuestionPackage> packageList);

    @Transaction
    @Query("SELECT * FROM QuestionPackage")
    public List<PackageWithQuestions> getAllPackage();
}
