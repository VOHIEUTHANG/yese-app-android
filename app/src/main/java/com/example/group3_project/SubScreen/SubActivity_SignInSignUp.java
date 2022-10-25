package com.example.group3_project.SubScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.group3_project.R;
import com.example.group3_project.Router.Game.OnWordButtonClickListener;
import com.example.group3_project.SubScreen.Fragment.Login;
import com.example.group3_project.SubScreen.Fragment.SignUp;

public class SubActivity_SignInSignUp extends AppCompatActivity {
    TextView loginFragment,signUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sign_in_sign_up);
        setControl();
        setEvent();
    }

    private void setControl() {
        loginFragment = findViewById(R.id.tvLogin);
        signUpFragment = findViewById(R.id.tvSignUp);
    }

// Call back to switch to login fragment when user sign up success.
    public class switchToLoginFragmentCallBack implements OnWordButtonClickListener {
        @Override
        public void onButtonClick(int key) {
            switchToLoginFragment();
        }
    }

    private void setEvent() {
        initView();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flLoginContainer, fragment);
        fragmentTransaction.commit();
    }

    public void initView(){
        replaceFragment(new Login());
        signUpFragment.setTextColor(Color.rgb(200,200,200));
        loginFragment.setOnClickListener(view ->{
            switchToLoginFragment();
        });
        signUpFragment.setOnClickListener(view ->{
            replaceFragment(new SignUp(new switchToLoginFragmentCallBack()));
            signUpFragment.setTextColor(Color.rgb(255,255,255));
            loginFragment.setTextColor(Color.rgb(200,200,200));
        });
    }

    public void switchToLoginFragment(){
        replaceFragment(new Login());
        loginFragment.setTextColor(Color.rgb(255,255,255));
        signUpFragment.setTextColor(Color.rgb(200,200,200));
    }

}