package com.example.group3_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.User;
import com.example.group3_project.Fragment.BookFragment;
import com.example.group3_project.Fragment.GameFragment;
import com.example.group3_project.Fragment.HomeFragment;
import com.example.group3_project.Fragment.NoteFragment;
import com.example.group3_project.Fragment.UserFragment;
import com.example.group3_project.Utils.Utils;
import com.example.group3_project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final String currentUsername = "devostack2k";
    private static User currentUser;

    TextView tvStreakCount,tvDiamondCount;
    ImageView ivUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setControl();
        setEvent();
    }

    public void setEvent(){
        setCurrentUserView();
        initView();
        setLoginUser();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setCurrentUserView();
    }

    private void setCurrentUserView() {
        currentUser = getCurrentUser();
        if(currentUser != null){
            tvStreakCount.setText(String.valueOf(currentUser.getStreak()));
            tvDiamondCount.setText(String.valueOf(currentUser.getDiamond()));
            ivUserAvatar.setImageResource(Utils.getResourceId(this,currentUser.getAvatar()));
        }
    }

    public void initView(){
        replaceFragment(new HomeFragment());

        binding.bnvButtonNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.books:
                    replaceFragment(new BookFragment());
                    break;
                case R.id.game:
//                  pass username to game fragment
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username",currentUsername);
                    GameFragment gameFragment = new GameFragment();
//                    gameFragment.setArguments(bundle);
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

    public void setLoginUser(){
        ((MyApplication) this.getApplication()).setUsername(currentUsername);
    }

    private void setControl() {
        tvStreakCount = findViewById(R.id.tvStreakCount);
        tvDiamondCount = findViewById(R.id.tvDiamondCount);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragment);
        fragmentTransaction.commit();
    }

    public User getCurrentUser(){
        User user = null;
        try {
             user = AppDatabase.getInstance(this).userDao().findOneUserByUsername(currentUsername);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Get user from database failured !", Toast.LENGTH_SHORT).show();
        }
        return user;
    }

}