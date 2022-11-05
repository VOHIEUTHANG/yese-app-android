package com.example.group3_project.SubScreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.DiamondGameHistory;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.QuestionType;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.WordPair;
import com.example.group3_project.R;
import com.example.group3_project.Utils.InitialData;
import com.example.group3_project.Utils.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubActivity_StartGame extends AppCompatActivity {

    Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_start_game);

        btnStartGame = findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(view->{
            finish();
            startActivity(new Intent(SubActivity_StartGame.this, SubActivity_SignInSignUp.class));
        });
        createData();
    }

    public void createData(){
//        Utils.deleteDatabase(this);
//        initData();
    }


    public void initData() {
        addWordPairData();
        addUserData();
        addQuestionTypeData();
        addPackageData();
        addQuestionList();
        addDiamondEarnHistoryList();
    }

    public void addWordPairData() {
        List<WordPair> wordPairList = new ArrayList<>();
        wordPairList.addAll(InitialData.getWordPairList());
        try {
            AppDatabase.getInstance(this).wordPairDao().insertListWordPair(wordPairList);
            Toast.makeText(this, "Insert word list successfully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Insert word list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addUserData() {
        List<User> userList = new ArrayList<>();
        userList.addAll(InitialData.getUserList());
        try {
            AppDatabase.getInstance(this).userDao().insertListUser(userList);
            Toast.makeText(this, "Insert user list successfully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Insert user list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addQuestionTypeData() {
        List<QuestionType> questionTypeList = new ArrayList<>();
        questionTypeList.addAll(InitialData.getQuestionTypeList());
        try {
            AppDatabase.getInstance(this).questionTypeDao().insertListType(questionTypeList);
            Toast.makeText(this, "Insert question type list successfully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Insert question type list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addPackageData() {
        List<QuestionPackage> questionPackageList = new ArrayList<>();
        questionPackageList.addAll(InitialData.getPackageList());
        try {
            AppDatabase.getInstance(this).packageDao().insertListPackage(questionPackageList);
            Toast.makeText(this, "Insert package list successfully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Insert package list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addQuestionList() {
        List<Question> questions = new ArrayList<>();
        questions.addAll(InitialData.getQuestionList());

        AppDatabase.getInstance(this).questionDao().insertListQuestion(questions);
        try {
            Toast.makeText(this, "Insert question list successful !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Insert question list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addDiamondEarnHistoryList() {
        List<DiamondGameHistory> diamondGameHistory = new ArrayList<>();
        diamondGameHistory.addAll(InitialData.getDiamondGameHistoryList());
        try {
            AppDatabase.getInstance(this).diamondGameHistoryDao().insertListUserDiamondHistory(diamondGameHistory);
            Toast.makeText(this, "Insert diamond game history list successful !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Insert diamond game history list failure !", Toast.LENGTH_SHORT).show();
        }
    }


}