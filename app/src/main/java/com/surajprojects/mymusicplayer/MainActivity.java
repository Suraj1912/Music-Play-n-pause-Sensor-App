package com.surajprojects.mymusicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play, pause;
    MediaPlayer player;

    SensorManager manager;
    Sensor proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar actionBar = getSupportActionBar();
//
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("03A9F4"));
//
//        actionBar.setBackgroundDrawable(colorDrawable);

        player = MediaPlayer.create(this,R.raw.song);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proxy = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        manager.registerListener(proxylistener,proxy,SensorManager.SENSOR_DELAY_NORMAL);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });
    }

    SensorEventListener proxylistener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.values[0] == 0){
                player.pause();
            }else {
                player.start();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
