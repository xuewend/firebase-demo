package com.skylight.gcmdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationUtils extends ContextWrapper {
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";

    public NotificationUtils(Context context){
        super(context);
    }

    public static NotificationCompat.Builder createNotificationBuilder(Context context,NotificationManager manager){
        if(Build.VERSION.SDK_INT>=26){
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            return new NotificationCompat.Builder(context.getApplicationContext(), id);
        }else{
            return new NotificationCompat.Builder(context.getApplicationContext(),null);
        }
    }



}
