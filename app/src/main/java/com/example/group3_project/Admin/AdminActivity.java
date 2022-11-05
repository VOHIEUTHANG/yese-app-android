package com.example.group3_project.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.group3_project.Admin.Fragments.AddQuestionType1;
import com.example.group3_project.Admin.Fragments.AddQuestionType2;
import com.example.group3_project.Admin.Fragments.AddQuestionType3;
import com.example.group3_project.Admin.Fragments.AddQuestionType4;
import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.R;

public class AdminActivity extends AppCompatActivity {

    ImageView btnClose;
    AutoCompleteTextView autoCompleteTextViewPackage, autoCompleteTextViewQuestionType;

    String[] packageTopicList;
    String[] questionTypeList = {"Type 1", "Type 2", "Type 3", "Type 4"};

    String selectedPackageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnClose = findViewById(R.id.btnClose);
        autoCompleteTextViewPackage = findViewById(R.id.autoCompletePackage);
        autoCompleteTextViewQuestionType = findViewById(R.id.autoCompleteQuestionType);
    }

    private void setEvent() {
        initValue();
        btnClose.setOnClickListener(view -> {
            finish();
        });

        ArrayAdapter<String> packageAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, packageTopicList);
        ArrayAdapter<String> questionAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, questionTypeList);
        autoCompleteTextViewPackage.setAdapter(packageAdapter);
        autoCompleteTextViewQuestionType.setAdapter(questionAdapter);

        autoCompleteTextViewPackage.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedPackageName = adapterView.getItemAtPosition(i).toString();
        });

        autoCompleteTextViewQuestionType.setOnItemClickListener((adapterView, view, i, l) -> {
            if (selectedPackageName != null && selectedPackageName.length() > 0) {
                String item = adapterView.getItemAtPosition(i).toString();
                switch (item) {
                    case "Type 1":
                        replaceFragment(new AddQuestionType1(selectedPackageName));
                        break;
                    case "Type 2":
                        replaceFragment(new AddQuestionType2(selectedPackageName));
                        break;
                    case "Type 3":
                        replaceFragment(new AddQuestionType3(selectedPackageName));
                        break;
                    case "Type 4":
                        replaceFragment(new AddQuestionType4(selectedPackageName));
                        break;
                }
            } else {
                Toast.makeText(this, "You must choose package before choose question type !", Toast.LENGTH_SHORT).show();
            }

        });

        replaceFragment(new AddQuestionType4("Cơ bản"));
    }

    public void initValue() {
        packageTopicList = AppDatabase.getInstance(this).packageDao().getAllPackageTopic();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flAddQuestionContainer, fragment);
        fragmentTransaction.commit();
    }
}