package com.example.akshay.swissknife;

/**
 * Created by aksha on 2/6/2017.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import java.util.Calendar;

public class AlarmTask extends Activity
{
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmUI;
    private static AlarmTask xyz;
    private TextView alarmTxt;

    public static AlarmTask inst()
    {
        return xyz;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        xyz = this;
    }

    @Override
    protected void onCreate(Bundle InstState)
    {
        super.onCreate(InstState);
        setContentView(R.layout.alarm);
        alarmUI = (TimePicker) findViewById(R.id.alarmUI);
        alarmTxt = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void onToggle(View viewalarm)
    {
        if (((ToggleButton) viewalarm).isChecked())
        {
            Log.d("MyActivity", "Alarm On");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, alarmUI.getCurrentHour());
            cal.set(Calendar.MINUTE, alarmUI.getCurrentMinute());
            Intent alarmIntent = new Intent(AlarmTask.this, AlarmRec.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmTask.this, 0, alarmIntent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        }

        else
        {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
        }
    }

    public void setAlarmText(String alarmText)
    {
        alarmTxt.setText(alarmText);
    }
}