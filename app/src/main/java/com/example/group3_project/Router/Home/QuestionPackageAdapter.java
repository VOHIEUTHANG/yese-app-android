package com.example.group3_project.Router.Home;

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.UserPackageCrossRef;
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

        CardView cvWrapper = convertView.findViewById(R.id.cvWrapper);
        ImageView ivPackageIcon = convertView.findViewById(R.id.ivPackageIcon);
        ImageView ivPackageFinishIcon = convertView.findViewById(R.id.ivPackageFinish);
        ImageView  ivPackageStatus = convertView.findViewById(R.id.ivPackageStatusIcon);
        TextView tvPackageTopic = convertView.findViewById(R.id.tvPackageTopic);
        TextView tvPackageQuestionCount = convertView.findViewById(R.id.tvPackageQuestionCount);

        QuestionPackage questionPackage = questionPackageList.get(position);

        int isUserAnswer = AppDatabase.getInstance(context).userPackageDao().UserAnswerPackageCount(Utils.getUsername(context), questionPackage.getId());

        ivPackageIcon.setImageResource(Utils.getDrawableResourceIdFromFileName(context,questionPackage.getIcon()));
        if(questionPackage.getIsLock() == 0){
            ivPackageStatus.setImageResource(Utils.getDrawableResourceIdFromFileName(context, "ic_lock_open"));
        }else{
//            ivPackageIcon.getBackground().setAlpha(128);
        }

        tvPackageTopic.setText(questionPackage.getTopicName());

        tvPackageQuestionCount.setText(String.valueOf(getQuestionCountByPackageID(questionPackage.getId())) + " lessons");

//      xử lý giao diện mà người dùng đã trả lời rồi
        if(isUserAnswer > 0 && questionPackage.getIsLock() == 0){
            UserPackageCrossRef questionAnswerResult = AppDatabase.getInstance(context).userPackageDao().getUserAnswerPackage(Utils.getUsername(context), questionPackage.getId());
            if(questionAnswerResult.getIsFinish() == 1){
                ivPackageFinishIcon.setImageResource(R.drawable.ic_check_finish);
            }
            tvPackageQuestionCount.setText(String.valueOf(questionAnswerResult.getCorrectAnswerCount()) + "/" + String.valueOf(getQuestionCountByPackageID(questionPackage.getId())) + " true answers");
        }

        return convertView;
    }

    public int getQuestionCountByPackageID(int packageID){
        return AppDatabase.getInstance(context).questionDao().getQuestionCountByPackageID(packageID);
    }
}


