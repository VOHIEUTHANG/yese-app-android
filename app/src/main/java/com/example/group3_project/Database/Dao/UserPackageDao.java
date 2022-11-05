package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.group3_project.Database.Entity.UserPackageCrossRef;

@Dao
public interface UserPackageDao {

    @Query("SELECT count(*) FROM UserAnswersResult WHERE rk_username  = :username AND rk_packageID = :packageID")
    int UserAnswerPackageCount(String username, int packageID);

    @Query("SELECT * FROM UserAnswersResult WHERE rk_username = :username AND rk_packageID = :packageID")
    UserPackageCrossRef getUserAnswerPackage(String username, int packageID);

    @Update
    void updateAnswerPackage(UserPackageCrossRef userAnswerPackage);
    @Insert
    void insertAnswerPackage(UserPackageCrossRef userAnswerPackage);
}
