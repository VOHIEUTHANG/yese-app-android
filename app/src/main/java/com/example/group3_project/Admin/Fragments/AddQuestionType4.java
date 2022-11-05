package com.example.group3_project.Admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Notify;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AddQuestionType4 extends Fragment {

    EditText edtT1VnWord, edtT1KeyArrays, edtAnswer;
    Button btnT1AddQuestion;

    private String packageTopic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_question_type4, container, false);
    }

    public AddQuestionType4(String packageTopic) {
        this.packageTopic = packageTopic;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        edtT1VnWord = getView().findViewById(R.id.edtT1VnWord);
        edtT1KeyArrays = getView().findViewById(R.id.edtT1KeyArrays);
        edtAnswer = getView().findViewById(R.id.edtAnswer);
        btnT1AddQuestion = getView().findViewById(R.id.btnT1AddQuestion);
    }

    private void setEvent() {
        btnT1AddQuestion.setOnClickListener(view -> {
//          Check Did admin chosen package topic yet?
            if(packageTopic == null || packageTopic.length()==0) return;

            String vnWordVal = edtT1VnWord.getText().toString();
            String keyArraysVal = edtT1KeyArrays.getText().toString();
            String answerVal = edtAnswer.getText().toString();

            if (vnWordVal.length() >= 2 && keyArraysVal.length() >= 8 && answerVal.length() >= 2) {

                QuestionPackage questionPackage = Utils.getPackageIDByTopic(packageTopic, requireContext());

                if (questionPackage != null) {
                    Question questiontT4 = new Question(4, questionPackage.getId(), vnWordVal, keyArraysVal, answerVal);
                    try {
                        AppDatabase.getInstance(requireContext()).questionDao().insertQuestion(questiontT4);

                        Utils.notifyUser(requireContext(),questionPackage.getId(), packageTopic);

                        edtT1VnWord.setText("");
                        edtT1KeyArrays.setText("");
                        edtAnswer.setText("");

                        Utils.showInsertNewQuestionSuccess(requireContext());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(requireContext(), "Insert new question to package " + packageTopic + " failure", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                Toast.makeText(requireContext(), "Value of these inputs are invalid !", Toast.LENGTH_SHORT).show();
            }
        });
    }



}