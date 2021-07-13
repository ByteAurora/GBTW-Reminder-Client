package com.onedeveloper.gbtw_reminder;

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
        unsubscribeTopic("notice");
        subscribeTopic("update");
        subscribeTopic("event");
        unsubscribeTopic("endEvent");
        unsubscribeTopic("eventWinner");
        subscribeTopic("youtube");
    }

    public void subscribeTopic(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener(task -> {
                    String msg = topic + " subscribe Result: success";
                    if (!task.isSuccessful()) {
                        msg = topic + " subscribe Result: fail";
                    }
                    Log.d("GBTWLOG", msg);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                });
    }

    public void unsubscribeTopic(String topic){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                .addOnCompleteListener(task -> {
                    String msg = topic + " unsubscribe Result: success";
                    if (!task.isSuccessful()) {
                        msg = topic + " unsubscribe Result: fail";
                    }
                    Log.d("GBTWLOG", msg);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                });
    }
}