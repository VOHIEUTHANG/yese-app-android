package com.example.group3_project.Router.Game.Words;

public class Word {
    private int key;
    private String mean;

    public Word() {
    }

    public Word(int key, String mean) {
        this.key = key;
        this.mean = mean;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return "Word{" +
                "key=" + key +
                ", mean='" + mean + '\'' +
                '}';
    }
}
