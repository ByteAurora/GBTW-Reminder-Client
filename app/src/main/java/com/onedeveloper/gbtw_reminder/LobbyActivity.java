package com.onedeveloper.gbtw_reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class LobbyActivity extends AppCompatActivity {
    private ImageButton btnTitleBarOpenDrawer;
    private ImageButton btnTitleBarPushSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        init();
    }

    private void init(){
        btnTitleBarOpenDrawer = findViewById(R.id.btn_titlebar_opendrawer);
        btnTitleBarPushSetting = findViewById(R.id.btn_titlebar_pushsetting);

        btnTitleBarPushSetting.setOnClickListener((v) -> {
            Intent intent = new Intent(LobbyActivity.this, PushSettingActivity.class);
            startActivity(intent);
        });
    }
}