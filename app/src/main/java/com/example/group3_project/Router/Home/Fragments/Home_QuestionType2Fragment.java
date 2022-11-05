package com.example.group3_project.Router.Home.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.Date;


public class Home_QuestionType2Fragment extends Fragment {

    private Question question;
    TextView tvT2VnWord;
    ImageView ivT2QuestionIcon, ivAddToNote;
    EditText etT2UserAnswer;

    public Home_QuestionType2Fragment(Question question) {
        this.question = question;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_type2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvT2VnWord = getView().findViewById(R.id.tvT2VnWord);
        ivT2QuestionIcon = getView().findViewById(R.id.ivT2QuestionIcon);
        etT2UserAnswer = getView().findViewById(R.id.etT2UserAnswer);
        ivAddToNote = getView().findViewById(R.id.ivAddToNote);
    }

    private void setEvent() {
        tvT2VnWord.setText(question.getVnWord());
        if (question.getQuestionDataInsertType() != null && question.getQuestionDataInsertType().equals("image")) {
            ivT2QuestionIcon.setImageBitmap(question.getImageBitmap());
        } else {
            ivT2QuestionIcon.setImageResource(Utils.getDrawableResourceIdFromFileName(requireContext(), question.getImage()));
        }
//       add current word to user note;
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
        etT2UserAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String userAnswer = String.valueOf(editable).trim();
                MyApplication.setUserAnswer(userAnswer);
            }
        });

    }



}