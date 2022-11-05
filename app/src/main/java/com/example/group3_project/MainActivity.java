package com.example.group3_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Fragment.ChartFragment;
import com.example.group3_project.Fragment.GameFragment;
import com.example.group3_project.Fragment.HomeFragment;
import com.example.group3_project.Fragment.NoteFragment;
import com.example.group3_project.Fragment.UserFragment;
import com.example.group3_project.Router.Notify.Notify_DisplayNotify;
import com.example.group3_project.SubScreen.SubActivity_SignInSignUp;
import com.example.group3_project.Utils.Utils;
import com.example.group3_project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static User currentUser;

    TextView tvNotifyCount, tvDiamondCount;
    ImageView ivUserAvatar;
    ConstraintLayout openNotify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setControl();
        setEvent();
    }

    public void setEvent() {
        setCurrentUserView();
        checkUserLogin();
        initView();
        openNotify.setOnClickListener(view->{
            finish();
            startActivity(new Intent(this, Notify_DisplayNotify.class));
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setCurrentUserView();
    }

    private void setCurrentUserView() {
        currentUser = getCurrentUser();

        if (currentUser != null) {
            int notifyCount = getNotifyNotReadedCount(currentUser.getUsername());
            tvNotifyCount.setText(String.valueOf(notifyCount));
            tvDiamondCount.setText(String.valueOf(currentUser.getDiamond()));
            ivUserAvatar.setImageResource(Utils.getDrawableResourceIdFromFileName(this, currentUser.getAvatar()));
        }
    }

    public int getNotifyNotReadedCount(String username) {
        return AppDatabase.getInstance(this).notifyDao().getNotReadNotifyCountByUsername(username);
    }


    public void initView() {
        replaceFragment(new HomeFragment());

        binding.bnvButtonNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.chart:
                    replaceFragment(new ChartFragment());
                    break;
                case R.id.game:
                    GameFragment gameFragment = new GameFragment();
                    replaceFragment(gameFragment);
                    break;
                case R.id.note:
                    replaceFragment(new NoteFragment());
                    break;
                case R.id.user:
                    replaceFragment(new UserFragment());
                    break;
            }
            return true;
        });
    }

//    public void setLoginUser(){
//        ((MyApplication) this.getApplication()).setUsername(currentUsername);
//    }

    private void setControl() {
        tvNotifyCount = findViewById(R.id.tvNotifyCount);
        tvDiamondCount = findViewById(R.id.tvDiamondCount);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        openNotify = findViewById(R.id.openNotify);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragment);
        fragmentTransaction.commit();
    }

    public void checkUserLogin() {
        String username = Utils.getUsername(this);
        if (username == null) {
            Intent intent = new Intent(this, SubActivity_SignInSignUp.class);
        }
    }


    public User getCurrentUser() {
        return Utils.getCurrentUser(this);
    }

}