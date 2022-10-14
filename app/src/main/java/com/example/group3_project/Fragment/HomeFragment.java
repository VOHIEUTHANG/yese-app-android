package com.example.group3_project.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Database.Entity.Vocab;
import com.example.group3_project.Database.Entity.WordPair;
import com.example.group3_project.MyApplication;
import com.example.group3_project.R;
import com.example.group3_project.Utils.InitialData;
import com.example.group3_project.Utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    Button btnInitData;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void setEvent() {
        btnInitData.setOnClickListener(view ->{
//            Utils.deleteDatabase(requireContext());
            initData();
        });
    }


    private void setControl() {
        btnInitData = getView().findViewById(R.id.btnInitData);
    }

    public void addWordPairData(){
        List<WordPair> wordPairList = new ArrayList<>();
        wordPairList.addAll(InitialData.getWordPairList());
        try {
            AppDatabase.getInstance(requireContext()).wordPairDao().insertListWordPair(wordPairList);
            Toast.makeText(requireContext(), "Insert word list succeffully !", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
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
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(requireContext(), "Insert user list failure !", Toast.LENGTH_SHORT).show();
        }
    }

    private void addVocabsData() {
        List<Vocab> vocabList = new ArrayList<>();
        if(getCurrentUserLogin() != null){
            vocabList.addAll(InitialData.getVocabList(getCurrentUserLogin()));
            try {
                AppDatabase.getInstance(requireContext()).vocabDao().insertVocabList(vocabList);
                Toast.makeText(requireContext(), "Insert vocabs list succeffully !", Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(requireContext(), "Insert vocabs list failure !", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(requireContext(), "User is not logged in yet !", Toast.LENGTH_SHORT).show();
        }

    }

    public void initData(){
        addWordPairData();
        addUserData();
        addVocabsData();
    }

    public String getCurrentUserLogin(){
        String username = ((MyApplication) requireActivity().getApplication()).getUsername();
        return username;
    }


}