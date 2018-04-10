package com.skylight.gcmdemo.pcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.skylight.gcmdemo.MainActivity;
import com.skylight.gcmdemo.NotificationUtils;
import com.skylight.gcmdemo.R;

public class MessageService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMsg";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //Not getting messages here? See why this maybe: https://goo.gl/39RNJ
        Log.d(TAG, "From:" + remoteMessage.getFrom());


        if(remoteMessage != null){
            sendNotification(remoteMessage);
        }
    }

    private void sendNotification(RemoteMessage remoteMessage){
        String messageBody = "";
        String title="";
        //Check if message contains a data payload
        if(remoteMessage.getData().size() > 0){
            title = "我们人为的显示这个data为Notification";
            messageBody = "data:" + remoteMessage.getData();
            Log.d(TAG,"Message data payload:" + remoteMessage.getData());
        }

        //Check if message contains a notification body
        if(remoteMessage.getNotification() != null){
            Log.d(TAG,"Message Notfication Body:" + remoteMessage.getNotification().getBody());
            title = remoteMessage.getNotification().getTitle();
            messageBody = remoteMessage.getNotification().getBody();
        }

        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = NotificationUtils.createNotificationBuilder(this,notificationManager)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);




        notificationManager.notify(0,notificationBuilder.build());
    }

}
