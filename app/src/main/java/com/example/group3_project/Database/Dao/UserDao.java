package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.UserWithVocabs;

import java.util.List;

@Dao
public interface UserDao {

    @Transaction
    @Query("SELECT * FROM User")
    List<User> getAllUser();

    @Query("SELECT * FROM User order by diamond desc")
    List<User> getAllUserAndSortByDiamondDesc();

    @Query("SELECT * FROM User WHERE username = :username")
    User findOneUserByUsername(String username);

    @Insert
    void insertOneUser(User user);

    @Insert
    void insertListUser(List<User> userList);

    @Update
    void updateUser(User user);

    @Query("SELECT username from user")
    String[] getAllUsername();

}
