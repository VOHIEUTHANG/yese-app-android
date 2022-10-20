package com.example.group3_project.SubScreen.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.R;

import java.util.concurrent.ExecutionException;

public class SignUp extends Fragment {
    EditText etUsername, etPassword, etDisplayName, etEmail;
    Button btnSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        etUsername = getView().findViewById(R.id.etUsername);
        etPassword = getView().findViewById(R.id.etPassword);
        etDisplayName = getView().findViewById(R.id.etDisplayName);
        etEmail = getView().findViewById(R.id.etEmail);
        btnSignUp = getView().findViewById(R.id.btnSignUp);
    }

    private void setEvent() {
        btnSignUp.setOnClickListener(view -> {
                    String username = etUsername.getText().toString().trim();
                    String password = etPassword.getText().toString();
                    String email = etEmail.getText().toString();
                    String displayName = etDisplayName.getText().toString();
                    if (username.length() == 0 || password.length() == 0 || email.length() == 0 || displayName.length() == 0) {
                        Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin tài khoản !", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = new User(username, displayName, password, email, 0, 0, "default_avatar");
                        try {
                            AppDatabase.getInstance(requireContext()).userDao().insertOneUser(user);
                            Toast.makeText(requireContext(), "Register successful !", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Register failure, try again !", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }
}