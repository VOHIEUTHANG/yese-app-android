package com.example.group3_project.Router.Home.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.Locale;


public class Home_QuestionType3Fragment extends Fragment {

    private Question question;

    MediaPlayer player;
    private TextToSpeech mTTS;

    ImageView ivT3Audiofile;
    RadioGroup rgRadioGroup;
    RadioButton rbKey1,rbKey2,rbKey3;

    public Home_QuestionType3Fragment(Question question) {
        this.question = question;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_type3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        ivT3Audiofile = getView().findViewById(R.id.ivT3Audiofile);
        rgRadioGroup = getView().findViewById(R.id.rgT3KeyAnswers);
        rbKey1 = getView().findViewById(R.id.rbT3Key1);
        rbKey2 = getView().findViewById(R.id.rbT3Key2);
        rbKey3 = getView().findViewById(R.id.rbT3Key3);
    }

    private void setEvent() {
        initialVoice();
        String keysString = question.getKeysArray();
        String[] keysArray = keysString.split(" ");
        if(keysArray.length >= 3){
            rbKey1.setText(keysArray[0]);
            rbKey2.setText(keysArray[1]);
            rbKey3.setText(keysArray[2]);
        }

        ivT3Audiofile.setOnClickListener(view -> {
            if(question.getAnswer().length() > 0){
                speak(question.getAnswer());
            }else{
                Toast.makeText(requireContext(), "Answer is not valid, check again!", Toast.LENGTH_SHORT).show();
            }
        });

        rgRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            int selectedId = rgRadioGroup.getCheckedRadioButtonId();
            switch (selectedId) {
                case R.id.rbT3Key1:
                    RadioButton btn1 = getView().findViewById(selectedId);
                    MyApplication.setUserAnswer(btn1.getText().toString().trim());
                    break;
                case R.id.rbT3Key2:
                    RadioButton btn2 = getView().findViewById(selectedId);
                    MyApplication.setUserAnswer(btn2.getText().toString().trim());
                    break;
                case R.id.rbT3Key3:
                    RadioButton btn3 = getView().findViewById(selectedId);
                    MyApplication.setUserAnswer(btn3.getText().toString().trim());
                    break;
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