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


public class AddQuestionType1 extends Fragment {

    TextView edtT1Answer;
    Button btnT1AddQuestion;

    private String packageTopic;

    public AddQuestionType1(String packageTopic) {
        this.packageTopic = packageTopic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_question_type1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        edtT1Answer = getView().findViewById(R.id.edtT1Answer);
        btnT1AddQuestion = getView().findViewById(R.id.btnT1AddQuestion);
    }

    private void setEvent(){
        btnT1AddQuestion.setOnClickListener(view->{
            //          Check Did admin chosen package topic yet?
            if(packageTopic == null || packageTopic.length()==0) return;

            String answer = edtT1Answer.getText().toString();

            if(answer !=null && answer.length()>0){
                QuestionPackage questionPackage = Utils.getPackageIDByTopic(packageTopic, requireContext());

                if(questionPackage !=null){
                    Question questionType1 = new Question(1,questionPackage.getId(), answer);
                    try {
                        AppDatabase.getInstance(requireContext()).questionDao().insertQuestion(questionType1);
                        Utils.notifyUser(requireContext(),questionPackage.getId(), packageTopic);
                        edtT1Answer.setText("");

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