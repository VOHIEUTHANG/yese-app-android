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

import java.util.Date;

public class Note_update_vocap extends AppCompatActivity {

    EditText edtWord, edtType, edtMeans, edtSynonyms, edtExample, edtTag;
    Button btnUpdate;
    ImageView imvBtnCloseAddWordActivity;
    Vocab vocab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_update_vocap);
        setControl();
        setEvent();
    }

    public void getVocab() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vocab = (Vocab) extras.getSerializable("vocab");
        }
    }

    private void setControl() {
        edtWord = findViewById(R.id.edUDtWord);
        edtType = findViewById(R.id.etUDWordType);
        edtMeans = findViewById(R.id.etUDMeans);
        edtSynonyms = findViewById(R.id.etUDSynonyms);
        edtExample = findViewById(R.id.etUDExample);
        edtTag = findViewById(R.id.etUDTag);
        btnUpdate = findViewById(R.id.btnUpdateVocab);
        imvBtnCloseAddWordActivity = findViewById(R.id.imvBtnCloseAddWordActivity);
    }

    public void renderView() {
        if (vocab != null) {
            edtWord.setText(vocab.getWords());
            edtType.setText(coverEmptyStringCase(vocab.getType()));
            edtMeans.setText(coverEmptyStringCase(vocab.getMeans()));
            edtSynonyms.setText(coverEmptyStringCase(vocab.getSynonymWords()));
            edtExample.setText(coverEmptyStringCase(vocab.getExample()));
            edtTag.setText(coverEmptyStringCase(vocab.getLabel()));
        }
    }

    public String coverEmptyStringCase(String val) {
        return val == null ? "" : val;
    }


    private void setEvent() {
        getVocab();
        renderView();
        btnUpdate.setOnClickListener(item -> {
            String word = edtWord.getText().toString().trim();
            String type = edtType.getText().toString().trim();
            String means = edtMeans.getText().toString().trim();
            String synonyms = edtSynonyms.getText().toString().trim();
            String example = edtExample.getText().toString().trim();
            String tag = edtTag.getText().toString().trim();

            vocab.setType(type);
            vocab.setMeans(means);
            vocab.setSynonymWords(synonyms);
            vocab.setExample(example);
            vocab.setLabel(tag);

            try {
                AppDatabase.getInstance(this).vocabDao().updateVocab(vocab);
                finish();
            }catch(Exception e){
                Toast.makeText(this, "Insert new word failure, please try again !", Toast.LENGTH_SHORT).show();
            }

        });

        imvBtnCloseAddWordActivity.setOnClickListener(item -> {
            finish();
        });
    }


}