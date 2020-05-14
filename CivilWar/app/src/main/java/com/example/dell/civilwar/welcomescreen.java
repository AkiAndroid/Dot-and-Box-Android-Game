package com.example.dell.civilwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class welcomescreen extends AppCompatActivity {

private static int splashtime=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeintent);
                finish();
            }
        }, splashtime);
    }


    }


