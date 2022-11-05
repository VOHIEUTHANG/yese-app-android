package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.group3_project.Database.Entity.Notify;
import com.example.group3_project.Database.Entity.QuestionPackage;

import java.util.List;

@Dao
public interface NotifyDao {

    @Query("SELECT * FROM Notify WHERE fk_username = :username order by id desc")
    List<Notify> getAllNotifyByUsername(String username);

    @Insert
    public void insertNotify(Notify notify);

    @Query("SELECT count(*) FROM Notify WHERE fk_username = :username AND isReaded = 0")
    int getNotReadNotifyCountByUsername(String username);

    @Insert
    public void insertNotifyList(List<Notify> notify);

    @Update
    public void updateNotify(Notify notify);

}
