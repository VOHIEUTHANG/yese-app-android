package com.example.group3_project.Router.Home;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class QuestionPackageAdapter extends ArrayAdapter {
    Context context;
    int layoutItem;
    List<QuestionPackage> questionPackageList;


    public QuestionPackageAdapter(@NonNull Context context, int resource, @NonNull List<QuestionPackage> questionPackageList) {
        super(context, resource, questionPackageList);
        this.context = context;
        this.layoutItem = resource;
        this.questionPackageList = questionPackageList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutItem,null);

        ImageView ivPackageIcon = convertView.findViewById(R.id.ivPackageIcon);
        ImageView  ivPackageStatus = convertView.findViewById(R.id.ivPackageStatusIcon);
        TextView tvPackageTopic = convertView.findViewById(R.id.tvPackageTopic);
        TextView tvPackageQuestionCount = convertView.findViewById(R.id.tvPackageQuestionCount);

        QuestionPackage questionPackage = questionPackageList.get(position);

        ivPackageIcon.setImageResource(Utils.getDrawableResourceIdFromFileName(context,questionPackage.getIcon()));
        if(questionPackage.getIsLock() == 0){
            ivPackageStatus.setImageResource(Utils.getDrawableResourceIdFromFileName(context, "ic_lock_open"));
        }
        tvPackageTopic.setText(questionPackage.getTopicName());
        tvPackageQuestionCount.setText(String.valueOf(getQuestionCountByPackageID(questionPackage.getId())) + " câu hỏi");

        return convertView;
    }

    public int getQuestionCountByPackageID(int packageID){
        return AppDatabase.getInstance(context).questionDao().getQuestionCountByPackageID(packageID);
    }
}


