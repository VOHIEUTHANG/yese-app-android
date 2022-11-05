package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.PackageWithQuestions;

import java.util.List;

@Dao
public interface PackageDao {

    @Insert
    void insertListPackage(List<QuestionPackage> packageList);

    @Transaction
    @Query("SELECT * FROM QuestionPackage where level = :level")
    public List<QuestionPackage> getAllPackagesByLevel(int level);

    @Query("UPDATE QuestionPackage SET isLock = 0 WHERE level = :level")
    public void unlockPackageByLevel(int level);

    @Query("UPDATE QuestionPackage SET isLock = 1 WHERE level = :level")
    public void lockPackageByLevel(int level);

    @Query("SELECT topicName FROM QuestionPackage")
    public String[] getAllPackageTopic();

    @Query("SELECT * FROM QuestionPackage WHERE topicName = :topic limit 1")
    public QuestionPackage getPackageByTopic(String topic);

    @Query("SELECT icon FROM QuestionPackage WHERE id = :id")
    public String getPackageIconById(int id);


}
