package com.example.group3_project.Router.Notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.group3_project.Database.AppDatabase.AppDatabase;
import com.example.group3_project.Database.Entity.Notify;
import com.example.group3_project.MainActivity;
import com.example.group3_project.R;
import com.example.group3_project.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Notify_DisplayNotify extends AppCompatActivity {

    ImageView ivBackToMain;
    ListView lvNotifications;
    NotiAdapter notiAdapter;
    List<Notify> notiList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_display_notify);
        setControl();
        setEvent();
    }

    private void setEvent() {
        getAllNofityByUsername();
        notiAdapter = new NotiAdapter(this,R.layout.notify_layout_item, notiList);
        lvNotifications.setAdapter(notiAdapter);


        ivBackToMain.setOnClickListener(view->{
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });

        lvNotifications.setOnItemClickListener((adapterView, view, i, l) -> {
            Notify notify = (Notify) adapterView.getItemAtPosition(i);
            notify.setIsReaded(1);
            AppDatabase.getInstance(this).notifyDao().updateNotify(notify);
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    private void setControl() {
        lvNotifications = findViewById(R.id.lvNotifications);
        ivBackToMain = findViewById(R.id.ivBackToMain);
    }

    public void getAllNofityByUsername(){
        String username =Utils.getUsername(this);
        if( username != null){
            notiList = AppDatabase.getInstance(this).notifyDao().getAllNotifyByUsername(username);
        }
    }
}