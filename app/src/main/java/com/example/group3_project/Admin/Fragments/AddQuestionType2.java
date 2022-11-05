package com.example.group3_project.Admin.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddQuestionType2 extends Fragment {

    Button btnT2ImageFileUpload;
    ImageView ivT2ImageUpload;
    EditText edtT2VnWord, edtT2Answer;
    Button btnT2AddQuestion;

    private Uri imageUri;
    private Bitmap imgBitmap;
    private final int REQUEST_CODE = 1;
    public String packageTopic;


    public AddQuestionType2(String packageTopic) {
        this.packageTopic = packageTopic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_question_type2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnT2ImageFileUpload = getView().findViewById(R.id.btnT2ImageFileUpload);
        ivT2ImageUpload = getView().findViewById(R.id.ivT2ImageUpload);
        edtT2VnWord = getView().findViewById(R.id.edtT2VnWord);
        edtT2Answer = getView().findViewById(R.id.edtT2Answer);
        btnT2AddQuestion = getView().findViewById(R.id.btnT2AddQuestion);
    }

    private void setEvent() {
        btnT2ImageFileUpload.setOnClickListener(view -> {
            selectImage();
        });
        btnT2AddQuestion.setOnClickListener(view -> {

            //Check Did admin chosen package topic yet?
            if (packageTopic == null || packageTopic.length() == 0) return;

            String vnWord = edtT2VnWord.getText().toString();
            String enWord = edtT2Answer.getText().toString();
            if (vnWord.length() > 0 && enWord.length() > 0 && imgBitmap != null) {
                //                  Show loading status
                QuestionPackage questionPackage = Utils.getPackageIDByTopic(packageTopic, requireContext());

                if (questionPackage != null) {
                    Question newQuestionType2 = new Question(2, questionPackage.getId(), vnWord, enWord, imgBitmap, "image");
                    try {
                        AppDatabase.getInstance(requireContext()).questionDao().insertQuestion(newQuestionType2);

//                      Notify Section
                        Utils.notifyUser(requireContext(), questionPackage.getId(), packageTopic);

//                      reset form input
                        edtT2Answer.setText("");
                        edtT2VnWord.setText("");
                        ivT2ImageUpload.setImageURI(null);
                        imgBitmap = null;

                        Utils.showInsertNewQuestionSuccess(requireContext());

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(requireContext(), "Insert new question to package " + packageTopic + " failure", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                Toast.makeText(requireContext(), "Value is invalid, check again !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            try {
                imgBitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                if (!checkSizeOfImage(imgBitmap)) {
                    Toast.makeText(requireContext(), "Kích cỡ ảnh vượt quá 1024Kb, vui lòng chọn ảnh nhỏ hơn! ", Toast.LENGTH_SHORT).show();
                } else {
                    ivT2ImageUpload.setImageURI(imageUri);
                }

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Conver from URI to Bigmap failured !", Toast.LENGTH_SHORT).show();
            }
//            uploadImage();
        }
    }

    public boolean checkSizeOfImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        long lengthbmp = byteArray.length;
        int iamgeSizeByMB = (int) lengthbmp / 1024 / 1024;
        return iamgeSizeByMB <= 1;
    }



}