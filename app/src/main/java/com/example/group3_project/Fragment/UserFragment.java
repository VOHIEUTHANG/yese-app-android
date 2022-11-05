package com.example.group3_project.Fragment;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.R;
import com.example.group3_project.SessionManagement;
import com.example.group3_project.SubScreen.SubActivity_SignInSignUp;
import com.example.group3_project.Utils.Utils;


public class UserFragment extends Fragment {

    AudioManager audioManager;
    SeekBar sbAdjustVolume;
    EditText edUsername,edDisplayName,edUserEmail;
    ImageView ivUserAvatar;
    Button btnUpdateUser,btnLogout;

    private static User user;

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        edUsername = getView().findViewById(R.id.edUsername);
        edDisplayName = getView().findViewById(R.id.edDisplayName);
        edUserEmail = getView().findViewById(R.id.edUserEmail);
        ivUserAvatar = getView().findViewById(R.id.ivUserAvatar);
        btnUpdateUser = getView().findViewById(R.id.btnUpdateUser);
        btnLogout = getView().findViewById(R.id.btnLogout);
        sbAdjustVolume = getView().findViewById(R.id.sbAdjustVolume);
    }

    public void configSeekbar(){
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        sbAdjustVolume.setMax(maxVolume);
        sbAdjustVolume.setProgress(currentValue);
    }

    private void setEvent() {
//      Khởi tạo audio management
        audioManager = (AudioManager) requireContext().getSystemService(Context.AUDIO_SERVICE);
        configSeekbar();
        initUserInfor();
        sbAdjustVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnUpdateUser.setOnClickListener(item->{
            String displayName = edDisplayName.getText().toString().trim();
            String email = edUserEmail.getText().toString().trim();
            if(!Utils.isValidEmail(email)){
                Toast.makeText(requireContext(), "Email is not valid, please check again !", Toast.LENGTH_SHORT).show();
                return;
            }
            if(displayName.length() <= 0){
                Toast.makeText(requireContext(), "Display name is empty, please check again !", Toast.LENGTH_SHORT).show();
                return;
            }
            updateUser(email,displayName);
        });
        btnLogout.setOnClickListener(item -> {
            SessionManagement sessionManagement = new SessionManagement(getContext());
            sessionManagement.logout();
            requireActivity().finish();
            startActivity(new Intent(requireContext(), SubActivity_SignInSignUp.class));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    public void initUserInfor(){
        user = Utils.getCurrentUser(requireContext());
        if(user != null){
            edUsername.setText(user.getUsername());
            edDisplayName.setText(user.getDisplayName());
            if(user.getEmail() != null){
                edUserEmail.setText(user.getEmail());
            }
            if(user.getAvatar() != null){
                ivUserAvatar.setImageResource(Utils.getDrawableResourceIdFromFileName(requireContext(),user.getAvatar()));
            }else{
                ivUserAvatar.setImageResource(Utils.getDrawableResourceIdFromFileName(requireContext(),"default_avatar"));
            }
        }else{
            Toast.makeText(requireContext(), "User not found !", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUser(String email, String displayName){
        user.setDisplayName(displayName);
        user.setEmail(email);
        try {
            AppDatabase.getInstance(requireContext()).userDao().updateUser(user);
            Toast.makeText(requireContext(), "Update user successfully !", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(requireContext(), "Update user failure !", Toast.LENGTH_SHORT).show();
        }
    }
}