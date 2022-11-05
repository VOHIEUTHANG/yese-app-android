package com.example.group3_project.Admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

public class AddQuestionType3 extends Fragment {

    TextView edtT3Keylist,edtT3Answer;
    Button btnT3AddQuestion;

    private String packageTopic;

    public AddQuestionType3(String packageTopic) {
        this.packageTopic = packageTopic;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_question_type3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        edtT3Keylist = getView().findViewById(R.id.edtT3Keylist);
        edtT3Answer = getView().findViewById(R.id.edtT3Answer);
        btnT3AddQuestion = getView().findViewById(R.id.btnT3AddQuestion);
    }

    public void setEvent(){
        btnT3AddQuestion.setOnClickListener(view->{
            //          Check Did admin chosen package topic yet?
            if(packageTopic == null || packageTopic.length()==0) return;

            String answer = edtT3Answer.getText().toString();
            String keyArray = edtT3Keylist.getText().toString();

            if (answer.length() >= 1 && keyArray.length() >= 5) {
                QuestionPackage questionPackage = Utils.getPackageIDByTopic(packageTopic, requireContext());

                if(questionPackage !=null){
                    Question questionType3 = new Question(3, questionPackage.getId(),keyArray, answer);
                    try {
                        AppDatabase.getInstance(requireContext()).questionDao().insertQuestion(questionType3);

                        Utils.notifyUser(requireContext(),questionPackage.getId(), packageTopic);

                        edtT3Answer.setText("");
                        edtT3Keylist.setText("");

                        Utils.showInsertNewQuestionSuccess(requireContext());

                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(requireContext(), "Insert new question to package " + packageTopic + " failure", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}