package com.example.group3_project.Database.AppDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.group3_project.Database.Converters.DateConverter;
import com.example.group3_project.Database.Dao.UserDao;
import com.example.group3_project.Database.Dao.VocabDao;
import com.example.group3_project.Database.Dao.WordPairDao;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.Database.Entity.WordPair;

@Database(entities = {User.class, WordPair.class, Vocab.class}, version = 2)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "yese.db";
    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract WordPairDao wordPairDao();

    public abstract VocabDao vocabDao();


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
}
