package com.example.group3_project.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.MyApplication;
import com.example.group3_project.Router.Game.Activities.Game_StartGameActivity;
import com.example.group3_project.R;
import com.example.group3_project.Router.Game.UserRankAdapter;

import java.util.ArrayList;

public class GameFragment extends Fragment {

    ListView lvUserRankList;
    CardView cvBtnPlayGame;

    UserRankAdapter userRankAdapter;
    ArrayList<User> userList = new ArrayList<>();

    public GameFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setControl();
        setEvent();
    }

    public void setEvent(){
        setUserListData();
        userRankAdapter = new UserRankAdapter(requireContext(),R.layout.game_layout_user_rank, userList, getCurrentUserLogin());
        lvUserRankList.setAdapter(userRankAdapter);

        cvBtnPlayGame.setOnClickListener(view -> {
            Intent intent = new Intent( requireContext(), Game_StartGameActivity.class);
            startActivity(intent);
        });
    }

    private void setControl() {
        cvBtnPlayGame = getView().findViewById(R.id.cvBtnPlayGame);
        lvUserRankList = getView().findViewById(R.id.lvUserRankList);
    }

    public void setUserListData(){
        userList.clear();
        try {
            userList.addAll(AppDatabase.getInstance(requireContext()).userDao().getAllUserAndSortByDiamondDesc());
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(requireContext(), "Get all user list from database failure !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUserListData();
        userRankAdapter.notifyDataSetChanged();
    }

    public String getCurrentUserLogin(){
        String username = ((MyApplication) requireActivity().getApplication()).getUsername();
        return username;
    }

}