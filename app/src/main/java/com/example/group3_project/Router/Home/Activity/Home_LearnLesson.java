package com.example.group3_project.Router.Home.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.UserPackageCrossRef;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Router.Game.Activities.Game_ResultActivity;
import com.example.group3_project.Router.Home.Fragments.Home_QuestionType1Fragment;
import com.example.group3_project.Router.Home.Fragments.Home_QuestionType2Fragment;
import com.example.group3_project.Router.Home.Fragments.Home_QuestionType3Fragment;
import com.example.group3_project.Router.Home.Fragments.Home_QuestionType4Fragment;
import com.example.group3_project.Router.Note.Activities.Note_update_vocap;
import com.example.group3_project.Utils.Utils;

import java.util.Date;
import java.util.List;

public class Home_LearnLesson extends AppCompatActivity {

    QuestionPackage questionPackage;
    ImageView btnExitLesson;
    ProgressBar pgLearnLessonProgress;
    TextView tvQuestionCount,tvResultTitle,tvResultCorrectAnswer,tvQuestionContent;
    Button btnCheckAnswer;

    private int currentQuestionIndex = 0;
    private boolean isAnswer;
    private int questionCount;

    private int answerCount;
    private int correctAnswerCount;

    List<Question> questionList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_learn_lesson);
        setControl();
        setEvent();
    }


    private void setControl() {
        btnExitLesson = findViewById(R.id.btnExitLesson);
        pgLearnLessonProgress = findViewById(R.id.pgLearnLessonProgress);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);
        tvResultTitle = findViewById(R.id.tvResultTitle);
        tvResultCorrectAnswer = findViewById(R.id.tvResultCorrectAnswer);
        tvQuestionContent = findViewById(R.id.tvQuestionContent);
    }

    private void setEvent(){
        getQuestionPackage();
        getAllQuestionOfPackage(questionPackage.getId());
        initView();
        getQuestionCountByPackageID(questionPackage.getId());
        pgLearnLessonProgress.setMax(questionCount);
        tvQuestionCount.setText(String.valueOf(questionCount));
        btnExitLesson.setOnClickListener(view->{finish();});
        btnCheckAnswer.setOnClickListener(view->{
            Question question = getCurrentQuestion();
            if(isAnswer){
//              Reset value
                isAnswer = false;
                MyApplication.setUserAnswer("");
                tvResultTitle.setText("");
                tvResultCorrectAnswer.setText("");
                btnCheckAnswer.setBackgroundColor(Color.rgb(55,106,237));
                btnCheckAnswer.setText("Kiểm tra");
                // next lesson handler
                loadNextQuestion();
            }else{
                answerCount++;
                if(MyApplication.getUserAnswer().equals(getCurrentQuestion().getAnswer())){
                    btnCheckAnswer.setBackgroundColor(Color.rgb(0,200,0));
                    btnCheckAnswer.setText("tiếp tục");
                    tvResultTitle.setText("Chính xác !");
                    correctAnswerCount++;
                }else{
                    btnCheckAnswer.setBackgroundColor(Color.rgb(250,120,110));
                    btnCheckAnswer.setText("tiếp tục");
                    tvResultTitle.setText("Đáp án đúng :");
                    tvResultCorrectAnswer.setText(question.getAnswer());
                }
                isAnswer = true;
                pgLearnLessonProgress.setProgress(currentQuestionIndex+1);
            }

        });
    }

    public void loadNextQuestion(){
        if(currentQuestionIndex >=0 && currentQuestionIndex < questionCount-1){
            currentQuestionIndex++;
            initView();
        }else{
            finishLesson();
        }
    }

    public void finishLesson(){
        //            finish();
        int userAnswerPackageCount = AppDatabase.getInstance(this).userPackageDao().UserAnswerPackageCount(Utils.getUsername(this),questionPackage.getId());

        UserPackageCrossRef userAnswerResult = new UserPackageCrossRef(Utils.getUsername(this),questionPackage.getId(),100,correctAnswerCount,1,new Date());

        if(userAnswerPackageCount == 0){
//                User not answered package yet => create user answer result
            AppDatabase.getInstance(this).userPackageDao().insertAnswerPackage(userAnswerResult);
        }else{
//               User answered package => update user answer result
            AppDatabase.getInstance(this).userPackageDao().updateAnswerPackage(userAnswerResult);
        }

        Intent intent = new Intent(this, Home_AnswerResult.class);
        Bundle extras = new Bundle();

        extras.putString("packageIcon", questionPackage.getIcon());
        extras.putString("packageTopic",questionPackage.getTopicName());
        extras.putString("answerResult", String.valueOf(correctAnswerCount)  + "/" + String.valueOf(questionCount));
        intent.putExtras(extras);
        finish();
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flLearnLessonFragment, fragment);
        fragmentTransaction.commit();
    }

    public void initView(){
        Question question = getCurrentQuestion();
        if(question == null) return;

        String questionOfCurrentLesson = AppDatabase.getInstance(this).questionTypeDao().getQuestionByTypeID(question.getRk_typeID());
        if(question.getRk_typeID()==4){
            questionOfCurrentLesson +=  " " + question.getVnWord() + " ?";
        }
        tvQuestionContent.setText(questionOfCurrentLesson);
        switch (getCurrentQuestion().getRk_typeID()){
            case 1:
                replaceFragment(new Home_QuestionType1Fragment(question));
                break;
            case 2:
                replaceFragment(new Home_QuestionType2Fragment(question));
                break;
            case 3:
                replaceFragment(new Home_QuestionType3Fragment(question));
                break;
            case 4:
                replaceFragment(new Home_QuestionType4Fragment(question));
                break;
            default:
                Toast.makeText(this, "Invalid question type !", Toast.LENGTH_SHORT).show();
        }
    }


    public void getQuestionPackage() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionPackage = (QuestionPackage) extras.getSerializable("package");
        }
    }

    public void getQuestionCountByPackageID(int packageID){
        questionCount = AppDatabase.getInstance(this).questionDao().getQuestionCountByPackageID(packageID);
    }

    public void getAllQuestionOfPackage(int packageID){
        try {
            questionList = AppDatabase.getInstance(Home_LearnLesson.this).questionDao().getQuestionListByPackageID(packageID);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Truy vấn dữ liệu câu hỏi từ database thất bại, try again !" , Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public Question getCurrentQuestion(){
        if(questionList != null && currentQuestionIndex >= 0 && currentQuestionIndex < questionList.size()){
            return questionList.get(currentQuestionIndex);
        }
        return null;
    }


}