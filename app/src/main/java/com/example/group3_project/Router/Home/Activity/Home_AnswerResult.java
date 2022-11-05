package com.example.group3_project.Router.Home.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.Entity.UserPackageCrossRef;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

public class Home_AnswerResult extends AppCompatActivity {

    ImageView ivPackageIcon;
    TextView tvPackageTopic, tvPackageResult;
    Button btnContinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_answer_result);
        setControl();
        setEvent();
    }

    private void setEvent() {
        getResult();
    }

    private void setControl() {
        ivPackageIcon = findViewById(R.id.ivPackageIcon);
        tvPackageTopic = findViewById(R.id.tvPackageTopic);
        tvPackageResult = findViewById(R.id.tvPackageResult);
        btnContinute = findViewById(R.id.btnLessonResultContinute);
    }

    public void getResult() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String packageIcon = extras.getString("packageIcon");
            String packageTopic = extras.getString("packageTopic");
            String answerResult = extras.getString("answerResult");
            ivPackageIcon.setImageResource(Utils.getDrawableResourceIdFromFileName(this, packageIcon));
            tvPackageTopic.setText(packageTopic);
            tvPackageResult.setText("Bạn đã trả lời đúng " + answerResult + " tổng số câu hỏi.");

        }
        btnContinute.setOnClickListener(view->{finish();});
    }


}