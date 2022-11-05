package com.example.group3_project.Router.Game.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Router.Game.OnWordButtonClickListener;
import com.example.group3_project.Router.Game.WordAdapter;
import com.example.group3_project.Router.Game.Words.Word;
import com.example.group3_project.Database.Entity.WordPair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Game_WordMatchActivity extends AppCompatActivity {

    TextView tvTimer, tvHeart1,tvHeart2,tvHeart3,tvWordLength,tvCorrectAnswers;
    ProgressBar pgGameProgress;
    RecyclerView rcvVnWordList,rcvEnWordList;
    WordAdapter vnWordAdapter,enWordAdapter;
    ImageButton btnExistGame;

    private int progressValue = 0;
    CountDownTimer waitTimer;
    private long duration = TimeUnit.MINUTES.toMillis(1);
    private ArrayList<WordPair> wordsList = new ArrayList<>();
    private ArrayList<Word> enWordList = new ArrayList<>();
    private ArrayList<Word> vnWordList = new ArrayList<>();

    private int correctAnswerCount = 0;

    private int vnKey = -1, enKey = -1;
    private int hearts = 3;
    private int wordpairPackageNumber = 1;
    private final int wordsCountPerAScreen = 6;
    private int score = 0;
    private int reciveDiamond;

    private boolean isShowDialog = false;



    public class vnWordColCallback implements OnWordButtonClickListener {
        @Override
        public void onButtonClick(int key) {
            vnKey = key;
            wordMatchHandler();
        }
    }

    public class enWordColCallback implements OnWordButtonClickListener{
        @Override
        public void onButtonClick(int key) {
            enKey = key;
            wordMatchHandler();
        }
    }

    public void wordMatchHandler(){
//      Case for success answer
        if(enKey == vnKey){
            vnWordAdapter.hiddenSelectedItem();
            enWordAdapter.hiddenSelectedItem();
            vnKey = -1;
            enKey = -1;
//          Finish current questions handler
            correctAnswerCount++;
            score++;

            tvCorrectAnswers.setText(String.valueOf(score));
            setValueForProgressBar(score);

            if(correctAnswerCount == wordsCountPerAScreen){
                boolean loadDataStatus = loadData();
                if(loadDataStatus){
//                    Load more data
                    updateAdapterData();
                    correctAnswerCount = 0;
                }else{
//                    Not enought wordPair data to load into activity.

                    showAnswerAllQuestionsGameDialog();
                }
            }
        }else if(enKey != -1  && vnKey != -1){
//          Logic for minus hearts;
            if(hearts > 0 ){
                hearts--;
                switch (hearts){
                    case 2:
                        tvHeart3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_favorite_24_gray,0,0,0);
                        break;
                    case 1:
                        tvHeart2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_favorite_24_gray,0,0,0);
                        break;
                    case 0:
                        tvHeart1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_favorite_24_gray,0,0,0);
                        showWrongAnswerGameDialog();
                        break;
                    default:
                        Toast.makeText(Game_WordMatchActivity.this, "Invalid hearts count !", Toast.LENGTH_SHORT).show();
                }
            }else{

            }

//           Case for incorrect answer
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    vnWordAdapter.showWrongAnswer();
                    enWordAdapter.showWrongAnswer();
                    vnKey = -1;
                    enKey = -1;
                }
            }, 300L); // execute after 300 milliseconds

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity_word_match);

        setControl();
        setEvent();
    }



    @Override
    public void onResume() {
        super.onResume();
        enWordAdapter.notifyDataSetChanged();
        vnWordAdapter.notifyDataSetChanged();
    }

    public void setControl(){
        tvTimer = findViewById(R.id.tvTimer);
        pgGameProgress = findViewById(R.id.pgGameProgress);
        rcvVnWordList = findViewById(R.id.rcvVnWordList);
        rcvEnWordList = findViewById(R.id.rcvEnWordList);
        tvHeart1 = findViewById(R.id.tvHeart1);
        tvHeart2 = findViewById(R.id.tvHeart2);
        tvHeart3 = findViewById(R.id.tvHeart3);
        tvWordLength = findViewById(R.id.tvWordsLenght);
        tvCorrectAnswers = findViewById(R.id.tvCorrectAnswers);
        btnExistGame = findViewById(R.id.btnExistGame);
    }

    public void setEvent(){
        existGameHandler();
        timerHanlder();
        setWordListData();
        setupProgressBar();
        createWordListView();
    }

    private void existGameHandler() {
        btnExistGame.setOnClickListener(view -> {
            score = 0;
            showExistGameDialog();
        });
    }


    public void createWordListView()    {
        LinearLayoutManager linearLayoutManagerForLeftCol = new LinearLayoutManager(Game_WordMatchActivity.this, RecyclerView.VERTICAL, false);
        LinearLayoutManager linearLayoutManagerForRightCol = new LinearLayoutManager(Game_WordMatchActivity.this, RecyclerView.VERTICAL, false);
        rcvVnWordList.setLayoutManager(linearLayoutManagerForLeftCol);
        rcvEnWordList.setLayoutManager(linearLayoutManagerForRightCol);

        vnWordAdapter = new WordAdapter(linearLayoutManagerForLeftCol,getApplicationContext(),new vnWordColCallback());
        enWordAdapter = new WordAdapter(linearLayoutManagerForRightCol ,getApplicationContext(),new enWordColCallback() );

        if(wordsList.size() > 0){
            loadData();

            vnWordAdapter.setData(vnWordList);
            enWordAdapter.setData(enWordList);

            rcvVnWordList.setAdapter(vnWordAdapter);
            rcvEnWordList.setAdapter(enWordAdapter);

        }else{
            Toast.makeText(Game_WordMatchActivity.this, "Wordpairs is empty !", Toast.LENGTH_SHORT ).show();
        }

    }

    public void addAnimation(){
        LayoutAnimationController layoutAnimationLeftToRight = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_left_to_right);
        LayoutAnimationController layoutAnimationRightToLeft = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_right_to_left);
        rcvVnWordList.setLayoutAnimation(layoutAnimationLeftToRight);
        rcvEnWordList.setLayoutAnimation(layoutAnimationRightToLeft);
    }


    public void updateAdapterData(){
        enWordAdapter.showAllElements();
        vnWordAdapter.showAllElements();

        enWordAdapter.notifyDataSetChanged();
        vnWordAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateAdapterData();
    }

    public boolean loadData(){
        int startIndex = (wordpairPackageNumber - 1) * wordsCountPerAScreen;
        int endIndex = startIndex + wordsCountPerAScreen;
        Log.d("startIndex",String.valueOf(startIndex));
        Log.d("endIndex",String.valueOf(endIndex));

        if(wordsList.size() >= endIndex - 1){
            cleartData();
            for(int i = startIndex ; i< endIndex ; i++){
                WordPair currentWords = wordsList.get(i);
                enWordList.add(new Word(currentWords.getWordKey(), currentWords.getEnMean()));
                vnWordList.add(new Word(currentWords.getWordKey(), currentWords.getVnMean()));
            }
            Collections.shuffle(enWordList);
            Collections.shuffle(vnWordList);

            wordpairPackageNumber++;
            addAnimation();
            return true;
        }else{
            return false;
        }
    }

    public void cleartData(){
        enWordList.clear();
        vnWordList.clear();
    }

    public ArrayList<WordPair> getWordList(){
        ArrayList<WordPair> wordPairList = new ArrayList<>();
        try {
            wordPairList.addAll(AppDatabase.getInstance(this).wordPairDao().getAllWordPair());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Load pair of words data from database failure !", Toast.LENGTH_SHORT).show();
        }
        Collections.shuffle(wordPairList);
        return wordPairList;
    }

    public void setWordListData(){
        this.wordsList = getWordList();
    }


    public void timerHanlder(){
        waitTimer = new CountDownTimer(duration, 1000) {
        @Override
            public void onTick(long l) {
             String sDuration = String.format(Locale.ENGLISH,"%02d:%02d"
                    , TimeUnit.MILLISECONDS.toMinutes(l)
                    , TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

             tvTimer.setText(sDuration);
         }
        @Override
        public void onFinish() {
            if(isShowDialog == false){
                showTimeoutGameDialog();
            }
        }
    }.start();

    }

    public int calculatorDiamond(){
        return score - 3 + hearts > 0 ? score - 3 + hearts : 0;
    }

    public void setupProgressBar(){
        int wordsListLength = wordsList.size();
        int maxQuestionsCount =  wordsListLength - wordsListLength % wordsCountPerAScreen;
        tvWordLength.setText(String.valueOf(maxQuestionsCount));
        pgGameProgress.setMax(maxQuestionsCount);
    }
    public void setValueForProgressBar(int value){
       pgGameProgress.setProgress(value);
    }

    public void showExistGameDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_layout_bottom_popup_exist);

        Button btnContinute = dialog.findViewById(R.id.btnContinute);
        Button btnStop = dialog.findViewById(R.id.btnStop);

        if(btnContinute != null){
            btnContinute.setOnClickListener(view ->{
                dialog.hide();
            });
        }

        btnStop.setOnClickListener(view -> {
            dialog.hide();
            if(waitTimer != null) {
                waitTimer.cancel();
                waitTimer = null;
            }
            finish();
        });

        showDialog(dialog);
    }

    public void showTimeoutGameDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_layout_bottom_popup_timeout);

        Button btnStop = dialog.findViewById(R.id.btnStop);

        btnStop.setOnClickListener(view -> {
            goToResultActivity(dialog);
        });

        showDialog(dialog);
    }

    public void showWrongAnswerGameDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_layout_bottom_popup_wrong_answer_time);

        Button btnStop = dialog.findViewById(R.id.btnStop);

        btnStop.setOnClickListener(view -> {
            goToResultActivity(dialog);
        });

        showDialog(dialog);
    }

    public void showAnswerAllQuestionsGameDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_layout_bottom_popup_finish);

        Button btnContinute = dialog.findViewById(R.id.btnContinute);

        btnContinute.setOnClickListener(view -> {
            goToResultActivity(dialog);
        });

        showDialog(dialog);
    }

    public void showDialog(Dialog dialog){
        dialog.show();
        isShowDialog = true;
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void goToResultActivity(Dialog dialog){
        dialog.hide();
        if(waitTimer != null) {
            waitTimer.cancel();
            waitTimer = null;
        }
        sendResultToResultActivity();
    }

    public void sendResultToResultActivity(){
        Intent intent = new Intent(this,Game_ResultActivity.class);
        Bundle extras = new Bundle();

        extras.putString("wordsCount", String.valueOf(score));
        extras.putString("diamond",String.valueOf( calculatorDiamond()));
        extras.putString("wrongAnswer", String.valueOf(3 - hearts));
        intent.putExtras(extras);
        finish();
        startActivity(intent);
    }

}