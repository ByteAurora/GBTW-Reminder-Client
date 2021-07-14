package com.onedeveloper.gbtw_reminder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Intent intent = new Intent(MainActivity.this, PushSettingActivity.class);
        startActivity(intent);
    }

    private void init() {
        /*
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("GBTWLOG", "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    // Log and toast
                    Log.d("GBTWLOG", "Application Token: " + token);
                });
        */
        SharedPreferenceManager.open(MainActivity.this, Constants.SP_NAME);

        if(SharedPreferenceManager.load(Constants.SP_KEY_INIT, false) == false){
            SharedPreferenceManager.save(Constants.SP_KEY_TRIAL, true);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_NOTICE, true);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_UPDATE, true);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_EVENT, true);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_ENDEVENT, true);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_EVENTWINNER, true);
            SharedPreferenceManager.save(Constants.SP_KEY_PUSHSETTING_YOUTUBE, true);

            GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_NOTICE);
            GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_UPDATE);
            GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_EVENT);
            GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_ENDEVENT);
            GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_EVENTWINNER);
            GbtwFirebaseMessagingService.subscribeTopic(Constants.FIREBASE_TOPIC_YOUTUBE);

            SharedPreferenceManager.save(Constants.SP_KEY_INIT, true);
        }
    }


}