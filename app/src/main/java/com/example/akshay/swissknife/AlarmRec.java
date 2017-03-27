package com.example.akshay.swissknife;

/**
 * Created by aksha on 2/6/2017.
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmRec extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        AlarmTask inst = AlarmTask.inst();
        inst.setAlarmText(" Alarm ! ");

        Uri notifyUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (notifyUri == null)
        {
            notifyUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        Ringtone ring = RingtoneManager.getRingtone(context, notifyUri);
        ring.play();

        ComponentName cn = new ComponentName(context.getPackageName(),
                AlarmSer.class.getName());
        startWakefulService(context, (intent.setComponent(cn)));
        setResultCode(Activity.RESULT_OK);
    }
}