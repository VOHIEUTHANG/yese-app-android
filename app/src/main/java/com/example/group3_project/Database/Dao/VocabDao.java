package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.group3_project.Database.Entity.Vocab;

import java.util.List;

@Dao
public interface VocabDao {
    @Insert
    void insertOneVocab(Vocab vocab);

    @Insert
    void insertVocabList(List<Vocab> vocabList);

    @Query("SELECT * FROM Vocab")
    List<Vocab> getAllVocabs();

    @Query("SELECT * FROM Vocab  WHERE rk_username = :username")
    List<Vocab> getAllVocabByUsername(String username);

    @Query("SELECT distinct(label) FROM Vocab WHERE rk_username = :username and label is not null")
    List<String> getTagListByUsername(String username);

    @Query("SELECT * FROM Vocab WHERE rk_username = :username AND words LIKE '%' || :subString || '%' ")
    List<Vocab> filterWordBySubString(String username, String subString);

    @Query("SELECT count(*) FROM Vocab WHERE rk_username = :username AND words = :word")
    int getVocabCountByUsernameAndWord(String username, String word);

    @Delete
    void deleteOneVocab(Vocab vocab);

    @Update
    void updateVocab(Vocab vocab);
}
