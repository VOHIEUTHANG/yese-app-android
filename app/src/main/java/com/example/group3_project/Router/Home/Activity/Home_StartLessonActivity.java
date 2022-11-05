package com.example.group3_project.Router.Home.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

public class Home_StartLessonActivity extends AppCompatActivity {

    QuestionPackage questionPackage;
    ImageView btnClose,packageIcon;
    Button btnContinute;
    TextView tvPackageInfo,tvQuestionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_start_lesson);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnClose = findViewById(R.id.imvCloseStartLesson);
        packageIcon = findViewById(R.id.ivPackageIcon);
        tvPackageInfo = findViewById(R.id.tvPackageInfo);
        btnContinute = findViewById(R.id.btnStartLesson);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
    }

    private void setEvent() {
        getQuestionPackage();
        if(questionPackage != null){
            packageIcon.setImageResource(Utils.getDrawableResourceIdFromFileName(this,questionPackage.getIcon()));
            tvPackageInfo.setText(questionPackage.getTopicName() + " - Level " + String.valueOf(questionPackage.getLevel()));
            tvQuestionCount.setText("Tổng cộng "+ String.valueOf(getQuestionCountByPackageID(questionPackage.getId()) + " bài học"));
        }
        btnClose.setOnClickListener(item->{
            finish();
        });
        btnContinute.setOnClickListener(item->{
            if(getQuestionCountByPackageID(questionPackage.getId())==0){
                Toast.makeText(this, "Bài học này chưa có câu hỏi, vui lòng liên hệ admin để thêm dữ liệu !", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(this, Home_LearnLesson.class);
                Bundle extras = new Bundle();
                extras.putSerializable("package", questionPackage);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getQuestionPackage() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionPackage = (QuestionPackage) extras.getSerializable("package");
        }
    }

    public int getQuestionCountByPackageID(int packageID){
        return AppDatabase.getInstance(this).questionDao().getQuestionCountByPackageID(packageID);
    }


}