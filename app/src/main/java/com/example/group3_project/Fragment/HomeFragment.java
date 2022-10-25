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
    ListView lvPackageListLevel1,lvPackageListLevel2;
    QuestionPackageAdapter questionPackageAdapterLevel1,questionPackageAdapterLevel2;
    List<QuestionPackage> packageListForLevel1;
    List<QuestionPackage> packageListForLevel2;

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
//        initData();
        setControl();
        setEvent();
    }

    private void setControl() {
        lvPackageListLevel1 = getView().findViewById(R.id.lvPackageListLevel1);
        lvPackageListLevel2 = getView().findViewById(R.id.lvPackageListLevel2);
    }

    private void setEvent() {
        packageListForLevel1 = getAllPackagesByLevel(1);
        packageListForLevel2 = getAllPackagesByLevel(2);
        questionPackageAdapterLevel1 = new QuestionPackageAdapter( requireContext(), R.layout.home_layout_package, packageListForLevel1);
        questionPackageAdapterLevel2 = new QuestionPackageAdapter(requireContext(), R.layout.home_layout_package,packageListForLevel2 );
        lvPackageListLevel1.setAdapter(questionPackageAdapterLevel1);
        lvPackageListLevel2.setAdapter(questionPackageAdapterLevel2);


        lvPackageListLevel1.setOnItemClickListener((adapterView, view, i, l) -> {
            QuestionPackage questionPackage = packageListForLevel1.get(i);
            if(questionPackage.getIsLock() == 0){
                Intent intent = new Intent(requireContext(), Home_StartLessonActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("package", questionPackage);
                intent.putExtras(extras);
                startActivity(intent);
            }else{
                Toast.makeText(requireContext(),questionPackage.getTopicName() + " packages is locked !", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public List<QuestionPackage> getAllPackagesByLevel(int level){
        return AppDatabase.getInstance(requireContext()).packageDao().getAllPackagesByLevel(level);
    }

    public void addWordPairData() {
        List<WordPair> wordPairList = new ArrayList<>();
        wordPairList.addAll(InitialData.getWordPairList());
        try {
            AppDatabase.getInstance(requireContext()).wordPairDao().insertListWordPair(wordPairList);
            Toast.makeText(requireContext(), "Insert word list succeffully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Insert word list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addUserData() {
        List<User> userList = new ArrayList<>();
        userList.addAll(InitialData.getUserList());
        try {
            AppDatabase.getInstance(requireContext()).userDao().insertListUser(userList);
            Toast.makeText(requireContext(), "Insert user list succeffully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Insert user list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addVocabsData() {
        List<Vocab> vocabList = new ArrayList<>();
        if (getCurrentUserLogin() != null) {
            vocabList.addAll(InitialData.getVocabList(getCurrentUserLogin()));
            try {
                AppDatabase.getInstance(requireContext()).vocabDao().insertVocabList(vocabList);
                Toast.makeText(requireContext(), "Insert vocabs list succeffully !", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Insert vocabs list failure !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "User is not logged in yet !", Toast.LENGTH_SHORT).show();
        }

    }

    private void addQuestionTypeData() {
        List<QuestionType> questionTypeList = new ArrayList<>();
        questionTypeList.addAll(InitialData.getQuestionTypeList());
        try {
            AppDatabase.getInstance(requireContext()).questionTypeDao().insertListType(questionTypeList);
            Toast.makeText(requireContext(), "Insert question type list succeffully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Insert question type list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addPackageData() {
        List<QuestionPackage> questionPackageList = new ArrayList<>();
        questionPackageList.addAll(InitialData.getPackageList());
        try {
            AppDatabase.getInstance(requireContext()).packageDao().insertListPackage(questionPackageList);
            Toast.makeText(requireContext(), "Insert package list succeffully !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Insert package list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addQuestionList() {
        List<Question> questions = new ArrayList<>();
        questions.addAll(InitialData.getQuestionList());
        try {
            AppDatabase.getInstance(requireContext()).questionDao().insertListQuestion(questions);
            Toast.makeText(requireContext(), "Insert question list successful !", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Insert question list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    public void initData() {
//        addWordPairData();
//        addUserData();
//        addVocabsData();
//        addQuestionTypeData();
//        addPackageData();
        addQuestionList();
    }

    public String getCurrentUserLogin() {
        return Utils.getUsername(requireContext());
    }


}