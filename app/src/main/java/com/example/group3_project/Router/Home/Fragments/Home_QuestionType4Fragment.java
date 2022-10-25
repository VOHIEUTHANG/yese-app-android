package com.example.group3_project.Router.Home.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.Date;


public class Home_QuestionType4Fragment extends Fragment {

    private Question question;

    RadioGroup rgRadioGroup;
    RadioButton rbKey1,rbKey2,rbKey3;
    ImageView ivAddToNote;

    public Home_QuestionType4Fragment(Question question) {
        this.question = question;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_type4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        rgRadioGroup = getView().findViewById(R.id.rgT4KeyAnswers);
        rbKey1 = getView().findViewById(R.id.rbT4Key1);
        rbKey2 = getView().findViewById(R.id.rbT4Key2);
        rbKey3 = getView().findViewById(R.id.rbT4Key3);
        ivAddToNote = getView().findViewById(R.id.ivAddToNote);
    }

    private void setEvent() {
        String keysString = question.getKeysArray();
        String[] keysArray = keysString.split(" ");
        if(keysArray.length >= 3){
            rbKey1.setText(keysArray[0]);
            rbKey2.setText(keysArray[1]);
            rbKey3.setText(keysArray[2]);
        }

        ivAddToNote.setOnClickListener(view -> {
            Vocab newVocab = new Vocab(question.getAnswer(), question.getVnWord(), Utils.getUsername(requireContext()), new Date());

            int isVocabSave = AppDatabase.getInstance(requireContext()).vocabDao().getVocabCountByUsernameAndWord(Utils.getUsername(requireContext()), question.getAnswer());
            if (isVocabSave > 0) {
                Toast.makeText(requireContext(), "Bạn đã thêm từ vựng này vào note rồi !", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    AppDatabase.getInstance(requireContext()).vocabDao().insertOneVocab(newVocab);
                    Toast.makeText(requireContext(), "Thêm từ vào user note thành công !", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Xảy ra lỗi khi thêm từ vào note !", Toast.LENGTH_SHORT).show();
                }
            }

        });

        rgRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            int selectedId = rgRadioGroup.getCheckedRadioButtonId();
            switch (selectedId) {
                case R.id.rbT4Key1:
                    RadioButton btn1 = getView().findViewById(selectedId);
                    MyApplication.setUserAnswer(btn1.getText().toString().trim());
                    break;
                case R.id.rbT4Key2:
                    RadioButton btn2 = getView().findViewById(selectedId);
                    MyApplication.setUserAnswer(btn2.getText().toString().trim());
                    break;
                case R.id.rbT4Key3:
                    RadioButton btn3 = getView().findViewById(selectedId);
                    MyApplication.setUserAnswer(btn3.getText().toString().trim());
                    break;
            }
        });
    }

}