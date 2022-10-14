package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WordPair")
public class WordPair {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int wordKey;
    @NonNull
    private String vnMean;
    @NonNull
    private String enMean;

    public WordPair(){}

    public WordPair( int key, String vnMean, String enMean) {
        this.wordKey = key;
        this.vnMean = vnMean;
        this.enMean = enMean;
    }

    public WordPair( String vnMean, String enMean) {
        this.vnMean = vnMean;
        this.enMean = enMean;
    }

    public int getWordKey() {
        return wordKey;
    }

    public void setWordKey(int wordKey) {
        this.wordKey = wordKey;
    }

    public String getVnMean() {
        return vnMean;
    }

    public void setVnMean(String vnMean) {
        this.vnMean = vnMean;
    }

    public String getEnMean() {
        return enMean;
    }

    public void setEnMean(String enMean) {
        this.enMean = enMean;
    }

    @Override
    public String toString() {
        return "Word{" +
                "key=" + wordKey +
                ", vnMean='" + vnMean + '\'' +
                ", enMean='" + enMean + '\'' +
                '}';
    }
}
