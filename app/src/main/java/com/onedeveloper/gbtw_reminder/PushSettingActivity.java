package com.onedeveloper.gbtw_reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PushSettingActivity extends AppCompatActivity {
    private SwitchCompat switchPushSettingNotice;
    private SwitchCompat switchPushSettingNoticeCoupon;
    private SwitchCompat switchPushSettingUpdate;
    private SwitchCompat switchPushSettingEvent;
    private SwitchCompat switchPushSettingEndEvent;
    private SwitchCompat switchPushSettingEventWinner;
    private SwitchCompat switchPushSettingYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_setting_activity);

        init();
    }

    private void init(){
        switchPushSettingNotice = findViewById(R.id.switch_pushsetting_notice);
        switchPushSettingNoticeCoupon = findViewById(R.id.switch_pushsetting_noticecoupon);
        switchPushSettingUpdate = findViewById(R.id.switch_pushsetting_update);
        switchPushSettingEvent = findViewById(R.id.switch_pushsetting_event);
        switchPushSettingEndEvent = findViewById(R.id.switch_pushsetting_eventwinner);
        switchPushSettingEventWinner = findViewById(R.id.switch_pushsetting_endevent);
        switchPushSettingYoutube = findViewById(R.id.switch_pushsetting_youtube);

        SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
        switchPushSettingNotice.setChecked(SharedPreferenceManager.load(Constants.SP_KEY_PUSHSETTING_NOTICE, false));
        switchPushSettingNoticeCoupon.setChecked(SharedPreferenceManager.load(Constants.SP_KEY_PUSHSETTING_NOTICECOUPON, false));
        switchPushSettingUpdate.setChecked(SharedPreferenceManager.load(Constants.SP_KEY_PUSHSETTING_UPDATE, false));
        switchPushSettingEvent.setChecked(SharedPreferenceManager.load(Constants.SP_KEY_PUSHSETTING_EVENT, false));
        switchPushSettingEndEvent.setChecked(SharedPreferenceManager.load(Constants.SP_KEY_PUSHSETTING_ENDEVENT, false));
        switchPushSettingEventWinner.setChecked(SharedPreferenceManager.load(Constants.SP_KEY_PUSHSETTING_EVENTWINNER, false));
        switchPushSettingYoutube.setChecked(SharedPreferenceManager.load(Constants.SP_KEY_PUSHSETTING_YOUTUBE, false));
        SharedPreferenceManager.close();

        switchPushSettingNotice.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_NOTICE, b);
            SharedPreferenceManager.close();

            if(b){
                GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_NOTICE);
            }else{
                GbtwFirebaseMessagingService.unSubscribeTopic(Constants.FIREBASE_TOPIC_NOTICE);
            }
        });
        switchPushSettingNoticeCoupon.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_NOTICECOUPON, b);
            SharedPreferenceManager.close();

            if(b){
                GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_NOTICECOUPON);
            }else{
                GbtwFirebaseMessagingService.unSubscribeTopic(Constants.FIREBASE_TOPIC_NOTICECOUPON);
            }
        });
        switchPushSettingUpdate.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_UPDATE, b);
            SharedPreferenceManager.close();

            if(b){
                GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_UPDATE);
            }else{
                GbtwFirebaseMessagingService.unSubscribeTopic(Constants.FIREBASE_TOPIC_UPDATE);
            }
        });
        switchPushSettingEvent.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_EVENT, b);
            SharedPreferenceManager.close();

            if(b){
                GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_EVENT);
            }else{
                GbtwFirebaseMessagingService.unSubscribeTopic(Constants.FIREBASE_TOPIC_EVENT);
            }
        });
        switchPushSettingEndEvent.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_ENDEVENT, b);
            SharedPreferenceManager.close();

            if(b){
                GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_ENDEVENT);
            }else{
                GbtwFirebaseMessagingService.unSubscribeTopic(Constants.FIREBASE_TOPIC_ENDEVENT);
            }
        });
        switchPushSettingEventWinner.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_EVENTWINNER, b);
            SharedPreferenceManager.close();

            if(b){
                GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_EVENTWINNER);
            }else{
                GbtwFirebaseMessagingService.unSubscribeTopic(Constants.FIREBASE_TOPIC_EVENTWINNER);
            }
        });
        switchPushSettingYoutube.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.open(PushSettingActivity.this, Constants.SP_NAME);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_YOUTUBE, b);
            SharedPreferenceManager.close();

            if(b){
                GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_YOUTUBE);
            }else{
                GbtwFirebaseMessagingService.unSubscribeTopic(Constants.FIREBASE_TOPIC_YOUTUBE);
            }
        });
    }
}