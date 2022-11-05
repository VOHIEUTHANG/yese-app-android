package com.example.group3_project.SubScreen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.group3_project.Admin.AdminActivity;
import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.MainActivity;
import com.example.group3_project.R;
import com.example.group3_project.SessionManagement;
import com.example.group3_project.SubScreen.SubActivity_ChooseLevel;
import com.example.group3_project.SubScreen.SubActivity_SignInSignUp;
import com.example.group3_project.Utils.Utils;


public class Login extends Fragment {
    EditText etUsername, etPassword;
    TextView tvLoginStatus;
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
//        Hide login status notify text line when user input again.
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvLoginStatus.setText(" ");
            }
        });
        //        Hide login status notify text line when user input again.
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvLoginStatus.setText(" ");
            }
        });
//        Handle login clicked
        btnLogin.setOnClickListener(view -> {
                    String username = etUsername.getText().toString().trim();
                    String password = etPassword.getText().toString();
                    if (username.length() == 0 || password.length() == 0) {
                        Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin tài khoản !", Toast.LENGTH_SHORT).show();
                    } else {
                        loginHandler(username,password);
                    }
                }
        );
    }

    private void setControl() {
        etUsername = getView().findViewById(R.id.etUsername);
        etPassword = getView().findViewById(R.id.etPassword);
        tvLoginStatus = getView().findViewById(R.id.tvLoginStatus);
        btnLogin = getView().findViewById(R.id.btnLogin);
    }

    public void loginHandler(String username, String password){
        User user = AppDatabase.getInstance(requireContext()).userDao().findOneUserByUsername(username);
        if(user == null){
            tvLoginStatus.setText("Wrong username or password, try again !");
        }else{
            String hashPassword = user.getPassword();
//            Login successfully
            if(Utils.verifyPassword(password,hashPassword)){
                SessionManagement sessionManagement = new SessionManagement(getContext());
                sessionManagement.saveSession(user);
                if(user.getIsAdmin() == 1){
//                    ADMIN RULE
                    startActivity(new Intent(getContext(), AdminActivity.class));
                }else{
//                    USER RULE
                    //              Move to next activity
                    if(user.getLevel() <= 0 ){
//                    user has not selected level.
                        startActivity(new Intent(getContext(), SubActivity_ChooseLevel.class));
                    }else{
//                    user has selected level.
                        startActivity( new Intent(getContext(), MainActivity.class));
                    }
                }


            }else{
                tvLoginStatus.setText("Wrong username or password, try again !");
            }
        }
    }

}