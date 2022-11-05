package com.example.group3_project.Database.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Vocab")
public class Vocab implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String words;
    private String type;
    @NonNull
    private String means;
    @ColumnInfo(defaultValue = "none")
    private String synonymWords;
    private String example;
    @ColumnInfo(defaultValue = "new words")
    private String label;
    private Date createAt;
    @NonNull
    public String rk_username;


    public Vocab() {
    }

    public Vocab(@NonNull String words, String type, String means, String rk_username, Date createAt) {
        this.words = words;
        this.type = type;
        this.means = means;
        this.rk_username = rk_username;
        this.createAt = createAt;
    }

    public Vocab(@NonNull String words, String type, String means, String synonymWords, String example, String label, String rk_username, Date createAt) {
        this.words = words;
        this.type = type;
        this.means = means;
        this.synonymWords = synonymWords;
        this.example = example;
        this.label = label;
        this.createAt = createAt;
        this.rk_username = rk_username;
    }

    public Vocab(@NonNull String words,  String means, String rk_username, Date createAt) {
        this.words = words;
        this.means = means;
        this.rk_username = rk_username;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRk_username() {return rk_username;}

    public void setRk_username(String rk_username) {
        this.rk_username = rk_username;
    }

    @NonNull
    public String getWords() {
        return words;
    }

    public void setWords(@NonNull String words) {
        this.words = words;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getSynonymWords() {
        return synonymWords;
    }

    public void setSynonymWords(String synonymWords) {
        this.synonymWords = synonymWords;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Vocab{" +
                "words='" + words + '\'' +
                ", type='" + type + '\'' +
                ", means='" + means + '\'' +
                ", synonymWords='" + synonymWords + '\'' +
                ", example='" + example + '\'' +
                ", label='" + label + '\'' +
                ", createAt=" + createAt +
                ", rk_username='" + rk_username + '\'' +
                '}';
    }
}
