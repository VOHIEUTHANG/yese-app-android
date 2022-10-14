package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.group3_project.Database.Entity.WordPair;

import java.util.List;

@Dao
public interface WordPairDao {
    @Insert
    void insertWordPair(WordPair wordPair);

    @Insert
    public long[] insertListWordPair(List<WordPair> wordList);

    @Query("DELETE FROM WordPair")
    public void dropALlData();

    @Query("SELECT * FROM wordpair")
    List<WordPair> getAllWordPair();

}
