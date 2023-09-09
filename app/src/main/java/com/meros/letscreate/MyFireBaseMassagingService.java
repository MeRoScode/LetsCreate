package com.meros.letscreate;

import android.app.Notification;
import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMassagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        if (message.getNotification() == null) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Notification notification = new NotificationCompat.Builder(this,
                    "LetsCreate")
                    .setContentText(message.getNotification().getBody())
                    .setSmallIcon(android.R.drawable.sym_action_chat).build();
            notificationManager.notify(1001, notification);
    }
}
