package com.example.dell.civilwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;
    ImageButton civilwar;
    ImageButton singleplayer;
    ImageButton threesome;
    ImageButton exitbutton;
    MediaPlayer buttonsong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        civilwar=(ImageButton) findViewById(R.id.civilwarbutton);
        singleplayer=(ImageButton) findViewById(R.id.singleplayerbutton);
        threesome=(ImageButton) findViewById(R.id.threesome);
        exitbutton=(ImageButton) findViewById(R.id.exitbutton);

        buttonsong=MediaPlayer.create(this,R.raw.buttonsound);

        videoview2=(VideoView) findViewById(R.id.videoview2);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.backgvideo1);
        videoview2.setVideoURI(uri);
        videoview2.start();

        videoview2.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

            @Override
            public void onPrepared(MediaPlayer mp) {
                mplayer=mp;

                mplayer.setLooping(true);
                if (currentvideoposition!=0){
                    mplayer.seekTo(currentvideoposition);
                    mplayer.start();
                }
            }
        });

        civilwar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),entryscreen.class);
                startActivity(i);
                finish();
                buttonsong.start();
            }
        });

        singleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),singleplayerentry.class);
                startActivity(i);
                finish();
                buttonsong.start();
            }
        });

        threesome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),threeplayerentry.class);
                startActivity(i);
                finish();
                buttonsong.start();
            }
        });
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
                buttonsong.start();

            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        currentvideoposition= mplayer.getCurrentPosition();
        videoview2.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        videoview2.start();
    }
}
