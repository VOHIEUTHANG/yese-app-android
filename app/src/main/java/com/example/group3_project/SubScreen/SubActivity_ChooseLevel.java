package com.example.group3_project.SubScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.MainActivity;
import com.example.group3_project.R;
import com.example.group3_project.SessionManagement;
import com.example.group3_project.Utils.Utils;

public class SubActivity_ChooseLevel extends AppCompatActivity {
    RadioGroup rgRadioGroup;
    RadioButton rbRadioButon;
    Button btnContinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_choose_level);
        setControl();
        setEvent();
    }

    private void setControl() {
        rgRadioGroup = findViewById(R.id.rgLevelRadioGroup);
        btnContinute = findViewById(R.id.btnContinute);
    }

    private void setEvent() {
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        btnContinute.setOnClickListener(view ->{
            int level = 0;
            User user = Utils.getCurrentUser(this);

            int selectedId = rgRadioGroup.getCheckedRadioButtonId();
            switch (selectedId){
                case R.id.btn1:
                    level = 1;
                    break;
                case R.id.btn2:
                    level = 2;
                    break;
                case R.id.btn3:
                    level = 3;
                    break;
            }
            user.setLevel(level);
            if(updateUserLevel(user)){
                startActivity(new Intent(SubActivity_ChooseLevel.this, MainActivity.class));
            }else{
                Toast.makeText(this, "Cập nhật cơ sở dữ liệu xảy ra lỗi, vui lòng kiểm tra lại !", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public boolean updateUserLevel(User user){
        try {
            AppDatabase.getInstance(this).userDao().updateUser(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}