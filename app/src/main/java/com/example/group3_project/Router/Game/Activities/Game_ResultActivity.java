package com.example.group3_project.Router.Game.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;

public class Game_ResultActivity extends AppCompatActivity {
    TextView tvDiamondCount,tvWordPairCount,tvWrongAnswerCount,tvDiamond;
    Button btnContinute;
    private int userDiamond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvDiamondCount = findViewById(R.id.tvDiamondCount);
        tvWordPairCount = findViewById(R.id.tvWordsCount);
        tvWrongAnswerCount = findViewById(R.id.tvWrongAnswerCount);
        tvDiamond = findViewById(R.id.tvDiamond);
        btnContinute = findViewById(R.id.btnContinute);

    }

    private void setEvent() {
        btnContinute.setOnClickListener(view -> {
            finish();
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String wordCount = extras.getString("wordsCount");
            String wrongAnswer = extras.getString("wrongAnswer");
            String diamond = extras.getString("diamond");

            if(wordCount != null){
                tvWordPairCount.setText("Rất tốt! Bạn đã ghép được tổng cộng "+ wordCount + " cặp từ");
            }
            if(wrongAnswer != null){
                tvWrongAnswerCount.setText("Bạn đã ghép sai " + wrongAnswer + " lần");
            }
            if(diamond != null){
                userDiamond = Integer.parseInt(diamond);
                tvDiamond.setText("+" + diamond);
                tvDiamondCount.setText("Bạn đã nhận được "+ diamond + " kim cương");
                updateUserDiamond();
            }
        }
    }

    public String getCurrentUserLogin(){
        String username = ((MyApplication) this.getApplication()).getUsername();
        return username;
    }

    public void updateUserDiamond(){
        String username = getCurrentUserLogin();
        User currentUser = AppDatabase.getInstance(this).userDao().findOneUserByUsername(username);
        if(currentUser != null){
            int currentDiamond = currentUser.getDiamond();
            int updateDiamondValue = currentDiamond + userDiamond;
            currentUser.setDiamond(updateDiamondValue);
            try {
                AppDatabase.getInstance(this).userDao().updateUser(currentUser);
            }catch(Exception e){
                Toast.makeText(this, "Update user diamond failure !", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this, "Error, User not found !", Toast.LENGTH_SHORT).show();
        }

    }


}