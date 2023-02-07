package com.example.tmapandroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import javax.annotation.Nonnull;

public class MyFirebaseMsgService extends FirebaseMessagingService {
    private static final String TAG = "MsgService";

    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Token : " + token);
    }

    public void onMessageReceived(@Nonnull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification());
        }
    }

    private void sendNotification(RemoteMessage.Notification messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(67108864);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 1073741824);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(2);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        NotificationCompat.Builder notificationBuilder = getNotificationBuilder(notificationManager, "hou", "first");
        notificationBuilder.setSmallIcon(C2135R.mipmap.ic_launcher).setContentTitle(messageBody.getTitle()).setContentText(messageBody.getBody()).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);
        notificationManager.notify(0, notificationBuilder.build());
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Builder getNotificationBuilder(NotificationManager notificationManager, String channelid, CharSequence channelName) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelid);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(channelid, channelName, 4);
            channel.enableLights(true);
            channel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationManager.createNotificationChannel(channel);
            builder.setSmallIcon(C2135R.C2137drawable.ic_launcher_foreground);
            return builder;
        }
        builder.setSmallIcon(C2135R.mipmap.ic_launcher);
        return builder;
    }
}
