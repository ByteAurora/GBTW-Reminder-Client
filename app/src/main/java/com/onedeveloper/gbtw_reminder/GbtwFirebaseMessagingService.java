package com.onedeveloper.gbtw_reminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class GbtwFirebaseMessagingService {
    public class GbtfFirebaseMessagingService extends FirebaseMessagingService {
        @Override
        public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
            if (remoteMessage.getData().size() > 0) {
                if (/* Check if data needs to be processed by long running job */ true) {
                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                    // scheduleJob();
                } else {
                    // Handle message within 10 seconds
                    //handleNow();
                }
            }

            if (remoteMessage.getNotification() != null) {
                String title = remoteMessage.getNotification().getTitle();
                String message = remoteMessage.getNotification().getBody();
                Log.d("GBTW", "Title: " + title + ", Message: " + message);

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                String channelId = "GBTW";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    String channelName = "GBTW2";
                    NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(0, notificationBuilder.build());
            }else{
                Log.d("GBTW", "Receive data failed");
            }
        }

        @Override
        public void onNewToken(@NonNull String s) {
            super.onNewToken(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            Log.d("GBTW", "알림 메시지: 새로운 토큰 생성");
        }
    }
}
