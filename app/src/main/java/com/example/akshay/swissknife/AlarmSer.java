package com.example.akshay.swissknife;

/**
 * Created by aksha on 2/6/2017.
 */

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmSer extends IntentService
{
    private NotificationManager alarmNotificationManager;

    public AlarmSer()
    {
        super("AlarmSer");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        sendNotification("Time to wake up !");
    }

    private void sendNotification(String msg)
    {
        Log.d("AlarmSer", "Preparing to send notification: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent cInt = PendingIntent.getActivity(this, 0,
                new Intent(this, AlarmTask.class), 0);

        NotificationCompat.Builder alarmNotify = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alarmNotify.setContentIntent(cInt);
        alarmNotificationManager.notify(1, alarmNotify.build());
        Log.d("AlarmSer", "Notification sent.");
    }
}