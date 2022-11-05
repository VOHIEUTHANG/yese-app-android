package com.example.group3_project.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.group3_project.Database.Entity.DiamondGameHistory;

import java.util.List;

@Dao
public interface DiamondGameHistoryDao {

    @Query("SELECT * FROM DiamondGameHistory WHERE fk_username = :username order by day asc")
    List<DiamondGameHistory> getAllUserDiamondHistoryOrderByDateASC(String username);

    @Insert
    void insertUserDiamondHistory(DiamondGameHistory diamondGameHistory);

    @Insert
    void insertListUserDiamondHistory(List<DiamondGameHistory> diamondGameHistoryList);

    @Update
    void updateUserDiamondHistory(DiamondGameHistory diamondGameHistory);

    @Query("SELECT * FROM DIAMONDGAMEHISTORY WHERE fk_username = :username AND day = :date limit 1")
    public DiamondGameHistory getDiamondGameHistoryByUsernameAndDate(String username, String date);


}
