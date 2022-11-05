package com.example.group3_project.Router.Home.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
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
import com.example.group3_project.SubScreen.SubActivity_StartGame;
import com.example.group3_project.Utils.Utils;

import java.util.Locale;

public class Home_QuestionType1Fragment extends Fragment {

    private Question question;

    MediaPlayer player;

    TextView etT1UserAnswer;
    ImageView ivT1AudioIcon;
    private TextToSpeech mTTS;

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
        initialVoice();
        ivT1AudioIcon.setOnClickListener(item -> {
            speak(question.getAnswer());
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

    public void speak(String text) {
        if(mTTS == null ){
            Toast.makeText(requireContext(), "TextToSpeech method is null !", Toast.LENGTH_SHORT).show();
            return;
        }
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void initialVoice() {
        mTTS = new TextToSpeech(requireContext(), i -> {
            if (i == TextToSpeech.SUCCESS) {
                int result = mTTS.setLanguage(Locale.ENGLISH);

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(requireContext(), "Language not supported !", Toast.LENGTH_SHORT).show();
                } else {

                }
            } else {
                Toast.makeText(requireContext(), "Initialization failed !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}