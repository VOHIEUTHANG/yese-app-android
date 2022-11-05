package com.example.group3_project.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.QuestionPackage;
import com.example.group3_project.Database.Entity.Question;
import com.example.group3_project.Database.Entity.QuestionType;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.Database.Entity.WordPair;
import com.example.group3_project.R;
import com.example.group3_project.Router.Home.Activity.Home_StartLessonActivity;
import com.example.group3_project.Router.Home.QuestionPackageAdapter;
import com.example.group3_project.Utils.InitialData;
import com.example.group3_project.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ListView lvPackageListLevel1, lvPackageListLevel2, lvPackageListLevel3;
    QuestionPackageAdapter questionPackageAdapterLevel1, questionPackageAdapterLevel2, questionPackageAdapterLevel3;
    List<QuestionPackage> packageListForLevel1;
    List<QuestionPackage> packageListForLevel2;
    List<QuestionPackage> packageListForLevel3;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        questionPackageAdapterLevel1.notifyDataSetChanged();
        questionPackageAdapterLevel2.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setControl();
        setEvent();
    }

    private void setControl() {
        lvPackageListLevel1 = getView().findViewById(R.id.lvPackageListLevel1);
        lvPackageListLevel2 = getView().findViewById(R.id.lvPackageListLevel2);
        lvPackageListLevel3 = getView().findViewById(R.id.lvPackageListLevel3);
    }

    private void setEvent() {
        unlockLessonForUser();
        packageListForLevel1 = getAllPackagesByLevel(1);
        packageListForLevel2 = getAllPackagesByLevel(2);
        packageListForLevel3 = getAllPackagesByLevel(3);
        questionPackageAdapterLevel1 = new QuestionPackageAdapter(requireContext(), R.layout.home_layout_package, packageListForLevel1);
        questionPackageAdapterLevel2 = new QuestionPackageAdapter(requireContext(), R.layout.home_layout_package, packageListForLevel2);
        questionPackageAdapterLevel3 = new QuestionPackageAdapter(requireContext(), R.layout.home_layout_package, packageListForLevel3);
        lvPackageListLevel1.setAdapter(questionPackageAdapterLevel1);
        lvPackageListLevel2.setAdapter(questionPackageAdapterLevel2);
        lvPackageListLevel3.setAdapter(questionPackageAdapterLevel3);


        lvPackageListLevel1.setOnItemClickListener((adapterView, view, i, l) -> {
            clickOnPackHandler(i, 1);
        });

        lvPackageListLevel2.setOnItemClickListener((adapterView, view, i, l) -> {
            clickOnPackHandler(i, 2);
        });

        lvPackageListLevel3.setOnItemClickListener((adapterView, view, i, l) -> {
            clickOnPackHandler(i, 3);
        });
    }

    public void clickOnPackHandler(int i, int level) {
        QuestionPackage questionPackage = new QuestionPackage();

        switch (level) {
            case 1:
                questionPackage = packageListForLevel1.get(i);
                break;
            case 2:
                questionPackage = packageListForLevel2.get(i);
                break;
        }

        if (questionPackage != null) {
            if (questionPackage.getIsLock() == 0) {
                Intent intent = new Intent(requireContext(), Home_StartLessonActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("package", questionPackage);
                intent.putExtras(extras);
                startActivity(intent);
            } else {
                Toast.makeText(requireContext(), "Bài học chủ đề " + questionPackage.getTopicName() + " đã bị khóa, vui lòng hoàn thành tất cả bài học của level trước đó để mở khóa !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), questionPackage.getTopicName() + " packages is null, try again ! !", Toast.LENGTH_SHORT).show();
        }
    }

    public List<QuestionPackage> getAllPackagesByLevel(int level) {
        return AppDatabase.getInstance(requireContext()).packageDao().getAllPackagesByLevel(level);
    }


    public String getCurrentUserLogin() {
        return Utils.getUsername(requireContext());
    }


    public void unlockLessonForUser() {
        User user = Utils.getCurrentUser(requireContext());
        if (user != null) {
            int userLevel = user.getLevel();
            if (userLevel > 0) {
                switch (userLevel) {
                    case 1:
                        AppDatabase.getInstance(requireContext()).packageDao().lockPackageByLevel(2);
                        AppDatabase.getInstance(requireContext()).packageDao().lockPackageByLevel(3);
                        break;
                    case 2:
                        AppDatabase.getInstance(requireContext()).packageDao().unlockPackageByLevel(2);
                        AppDatabase.getInstance(requireContext()).packageDao().lockPackageByLevel(3);
                        break;
                    case 3:
                        AppDatabase.getInstance(requireContext()).packageDao().unlockPackageByLevel(2);
                        AppDatabase.getInstance(requireContext()).packageDao().unlockPackageByLevel(3);
                        break;
                    default:
                        Toast.makeText(requireContext(), "user level không hợp lệ !", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}