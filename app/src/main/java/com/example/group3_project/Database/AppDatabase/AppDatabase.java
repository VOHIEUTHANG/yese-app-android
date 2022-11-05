package com.example.group3_project.Database.AppDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.group3_project.Database.Converters.Converter;
import com.example.group3_project.Database.Dao.DiamondGameHistoryDao;
import com.example.group3_project.Database.Dao.NotifyDao;
import com.example.group3_project.Database.Dao.PackageDao;
import com.example.group3_project.Database.Dao.QuestionDao;
import com.example.group3_project.Database.Dao.TypeDao;
import com.example.group3_project.Database.Dao.UserDao;
import com.example.group3_project.Database.Dao.UserPackageDao;
import com.example.group3_project.Database.Dao.VocabDao;
import com.example.group3_project.Database.Dao.WordPairDao;
import com.example.group3_project.Database.Entity.DiamondGameHistory;
import com.example.group3_project.Database.Entity.Notify;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionType;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.UserPackageCrossRef;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.Database.Entity.WordPair;

@Database(entities = {User.class, WordPair.class, Vocab.class, QuestionPackage.class, Question.class, QuestionType.class, UserPackageCrossRef.class, Notify.class, DiamondGameHistory.class}, version = 7)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "yese.db";
    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract WordPairDao wordPairDao();

    public abstract VocabDao vocabDao();

    public abstract QuestionDao questionDao();

    public abstract PackageDao packageDao();

    public abstract TypeDao questionTypeDao();

    public abstract UserPackageDao userPackageDao();

    public abstract NotifyDao notifyDao();

    public abstract DiamondGameHistoryDao diamondGameHistoryDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
}
