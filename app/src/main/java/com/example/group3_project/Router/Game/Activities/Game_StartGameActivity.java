package com.example.group3_project.Router.Game.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.group3_project.R;

public class Game_StartGameActivity extends AppCompatActivity {
    Button btnStartGame;
    ImageView imvBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start_game);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnStartGame = findViewById(R.id.btnStartGame);
        imvBtnClose = findViewById(R.id.imvBtnClose);
    }

    private void setEvent() {
        btnStartGame.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Game_WordMatchActivity.class);
            finish();
            startActivity(intent);
        });

        imvBtnClose.setOnClickListener(view -> {
            finish();
        });
    }


}