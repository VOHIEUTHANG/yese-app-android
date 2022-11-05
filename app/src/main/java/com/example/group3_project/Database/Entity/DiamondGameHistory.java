package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="DiamondGameHistory")
public class DiamondGameHistory {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String fk_username;
    @NonNull
    private int diamond;
    @NonNull
    private String day;
//    format: day/month/year

    public DiamondGameHistory(){};

    public DiamondGameHistory(@NonNull String fk_username, int diamond, @NonNull String day) {
        this.fk_username = fk_username;
        this.diamond = diamond;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFk_username() {
        return fk_username;
    }

    public void setFk_username(@NonNull String fk_username) {
        this.fk_username = fk_username;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    @NonNull
    public String getDay() {
        return day;
    }

    public void setDay(@NonNull String day) {
        this.day = day;
    }
}
