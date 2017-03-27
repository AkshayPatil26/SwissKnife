package com.example.akshay.swissknife;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aksha on 2/5/2017.
 */

public class Stopwatch extends Activity {
    Button startButton, pauseButton, lapButton;
    TextView txtTimer;
    Handler customHandler = new Handler();
    LinearLayout container;

    Long startTime=0L, timeinMilliseconds=0L, timeSwapBuff=0L, updateTime=0L;

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeinMilliseconds= SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff+timeinMilliseconds;
            int secs=(int)(updateTime/1000);
            int mins=secs/60;
            secs%=60;
            int milliseconds=(int)(updateTime%100);
            txtTimer.setText(""+mins+":"+String.format("%2d",secs)+":"
                    +String.format("%2d",milliseconds));
            customHandler.postDelayed(this,0);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);

        startButton = (Button)findViewById(R.id.bStart);
        pauseButton = (Button)findViewById(R.id.bPause);
        lapButton = (Button)findViewById(R.id.bLap);
        txtTimer = (TextView)findViewById(R.id.timerValue);
        container = (LinearLayout)findViewById(R.id.container);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();


                customHandler.postDelayed(updateTimerThread,0);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff+=timeinMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });


        lapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater=(LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row,null);
                TextView txtValue = (TextView)addView.findViewById(R.id.txtContent);
                txtValue.setText(txtTimer.getText());
                container.addView(addView);

            }
        });
    }



}
