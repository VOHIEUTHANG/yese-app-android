package com.example.group3_project.Router.Note.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.Date;

public class Note_add_new_vocab extends AppCompatActivity {

    EditText edtWord,edtType,edtMeans,edtSynonyms,edtExample,edtTag;
    Button btnAddWord;
    ImageView imvBtnCloseAddWordActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add_new_vocab);
        setControl();
        setEvent();
    }

    private void setControl() {
        edtWord = findViewById(R.id.edtWord);
        edtType = findViewById(R.id.etWordType);
        edtMeans = findViewById(R.id.etMeans);
        edtSynonyms = findViewById(R.id.etSynonyms);
        edtExample = findViewById(R.id.etExample);
        edtTag = findViewById(R.id.etTag);
        btnAddWord = findViewById(R.id.btnAddWord);
        imvBtnCloseAddWordActivity = findViewById(R.id.imvBtnCloseAddWordActivity);
    }

    private void setEvent() {
        btnAddWord.setOnClickListener(item->{
            String word = edtWord.getText().toString().trim();
            String type = edtType.getText().toString().trim();
            String means = edtMeans.getText().toString().trim();
            String synonyms = edtSynonyms.getText().toString().trim();
            String example = edtExample.getText().toString().trim();
            String tag = edtTag.getText().toString().trim();

            if(word.length() == 0) {
                Toast.makeText(this, "You can't add a empty word, please check a again !", Toast.LENGTH_SHORT).show();
                return;
            }
            if(getCurrentUserLogin() == null){
                Toast.makeText(this, "User is not logged in yet, please login and try again !", Toast.LENGTH_SHORT).show();
                return;
            }

            Vocab vocab = new Vocab(word,type,means,synonyms,example,tag,getCurrentUserLogin(),new Date());

            try {
                AppDatabase.getInstance(this).vocabDao().insertOneVocab(vocab);
                finish();
            }catch(Exception e){
                Toast.makeText(this, "Insert new word failure, please try again !", Toast.LENGTH_SHORT).show();
            }

        });

        imvBtnCloseAddWordActivity.setOnClickListener(item->{
            finish();
        });

    }

    public String getCurrentUserLogin(){
        return Utils.getUsername(this);
    }


}