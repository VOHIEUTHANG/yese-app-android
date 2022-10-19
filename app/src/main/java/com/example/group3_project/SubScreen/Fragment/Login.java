package com.example.group3_project.SubScreen.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.group3_project.R;


public class Login extends Fragment {
    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(view -> {
                    String username = etUsername.getText().toString().trim();
                    String password = etPassword.getText().toString();
                    if (username.length() == 0 || password.length() == 0) {
                        Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin tài khoản !", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("DEVO", username);
                        Log.d("DEVO", password);
                    }
                }
        );
    }

    private void setControl() {
        etUsername = getView().findViewById(R.id.etUsername);
        etPassword = getView().findViewById(R.id.etPassword);
        btnLogin = getView().findViewById(R.id.btnLogin);
    }
}