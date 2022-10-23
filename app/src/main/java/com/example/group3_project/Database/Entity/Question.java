package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Question")
public class Question {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private int rk_typeID;
    @NonNull
    private int rk_packageID;
    private String audioFile;
    private String answer;
    private String image;
    private String VnWord;
    private String keysArray;

    //    Question type 1
    public Question(int typeID, int packageID, String audioFile, String answer) {
        this.rk_typeID = typeID;
        this.rk_packageID = packageID;
        this.audioFile = audioFile;
        this.answer = answer;
    }

    //    Question type 2
    public Question(int typeID, int packageID, String image, String VnWord, String answer, int avoidOverloadingErrors) {
        this.rk_typeID = typeID;
        this.rk_packageID = packageID;
        this.image = image;
        this.VnWord = VnWord;
        this.answer = answer;
    }

    //    Question type 3
    public Question(int typeID, int packageID, String audioFile, String keysArray, String answer) {
        this.rk_typeID = typeID;
        this.rk_packageID = packageID;
        this.audioFile = audioFile;
        this.keysArray = keysArray;
        this.answer = answer;
    }

    //    Question type 4
    public Question(int typeID, int packageID, String VnWord, String keysArray, String answer, boolean avoidOverloadingErrors) {
        this.rk_typeID = typeID;
        this.rk_packageID = packageID;
        this.VnWord = VnWord;
        this.keysArray = keysArray;
        this.answer = answer;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRk_typeID() {
        return rk_typeID;
    }

    public void setRk_typeID(int rk_typeID) {
        this.rk_typeID = rk_typeID;
    }

    public int getRk_packageID() {
        return rk_packageID;
    }

    public void setRk_packageID(int rk_packageID) {
        this.rk_packageID = rk_packageID;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVnWord() {
        return VnWord;
    }

    public void setVnWord(String vnWord) {
        VnWord = vnWord;
    }

    public String getKeysArray() {
        return keysArray;
    }

    public void setKeysArray(String keysArray) {
        this.keysArray = keysArray;
    }
}
