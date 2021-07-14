package com.onedeveloper.gbtw_reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

// Test device token: d1VRBeqsSJiyR6djvMqrNH:APA91bHojG43VZL3M9PTp6T_RLLWpcQocPBLB4_Rog_7cQuN1sF8-3t0jTTsD-qI16PSu5HbILXFBg6eY7TFP5dCpYdS4Y4tWehcRYgN2z29-5NU64KuiNqtt99gBGAeuTkVwaOfMJ62

public class GbtwFirebaseMessagingService extends FirebaseMessagingService {
    private final static String GBTW_NOTIFICATION_GROUP = "GBTW NOTIFICATION GROUP";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.d("GBTW LOG", "Data: " + remoteMessage.getData());
        } else if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            Log.d("GBTW LOG", "Notification: {Title: " + title + ", Message: " + message + "}");

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 153, intent, PendingIntent.FLAG_ONE_SHOT);
            String channelId = "GBTWLOG";

            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelId)
                    .setGroup(GBTW_NOTIFICATION_GROUP)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pendingIntent);

            NotificationCompat.Builder notificationGroup = new NotificationCompat.Builder(this, channelId)
                    .setContentTitle(title)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setGroup(GBTW_NOTIFICATION_GROUP)
                    .setGroupSummary(true)
                    .setContentIntent(pendingIntent);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            SharedPreferences sharedPreferences = getSharedPreferences("SP_GBTW", MODE_PRIVATE);
            int notificationSequence = sharedPreferences.getInt("LAST_NOTIFICATION_SEQUENCE", 0) + 1;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelName = "GBTW";
                NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
                notification.setChannelId(channelId);
            }

            notificationManager.notify(notificationSequence, notification.build());
            notificationManager.notify(0, notificationGroup.build());
            sharedPreferences.edit().putInt("LAST_NOTIFICATION_SEQUENCE", notificationSequence).apply();
        } else {
            Log.d("GBTWLOG", "Notification: Receive data failed : " + remoteMessage.getData());
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("GBTWLOG", "Create New Token: Success");
    }

    public static void subscribeTopic(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener(task -> {
                    String msg = topic + " subscribe Result: success";
                    if (!task.isSuccessful()) {
                        msg = topic + " subscribe Result: fail";
                    }
                    Log.d("GBTWLOG", msg);
                });
    }

    public static void unSubscribeTopic(String topic){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                .addOnCompleteListener(task -> {
                    String msg = topic + " unsubscribe Result: success";
                    if (!task.isSuccessful()) {
                        msg = topic + " unsubscribe Result: fail";
                    }
                    Log.d("GBTWLOG", msg);
                });
    }
}
