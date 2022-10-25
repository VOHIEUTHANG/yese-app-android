package com.example.group3_project.Router.Home.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

public class Home_QuestionType1Fragment extends Fragment {

    private Question question;

    MediaPlayer player;

    TextView etT1UserAnswer;
    ImageView ivT1AudioIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_type1, container, false);
    }

    public Home_QuestionType1Fragment(Question question) {
        this.question = question;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        ivT1AudioIcon = getView().findViewById(R.id.ivT1AudioIcon);
        etT1UserAnswer = getView().findViewById(R.id.etT1UserAnswer);
    }

    private void setEvent() {
        ivT1AudioIcon.setOnClickListener(item -> {
            playAudioHandler(question.getAudioFile());
        });
        etT1UserAnswer.addTextChangedListener(new TextWatcher() {
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

    public void playAudioHandler(String audioFileName) {
        if (player == null) {
            player = MediaPlayer.create(requireContext(), Utils.getRawResourceIdFromFileName(requireContext(), audioFileName));
        }
        player.start();
        player.setOnCompletionListener(mediaPlayer -> {
            if (player != null) {
                player.release();
                player = null;
            }
        });
    }


}