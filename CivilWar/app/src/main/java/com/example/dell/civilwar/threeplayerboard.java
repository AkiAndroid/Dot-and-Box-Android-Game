package com.example.dell.civilwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class threeplayerboard extends AppCompatActivity {
    int row,col;
    public static TextView ironmanscore,captainscore,buckyscore;
    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;
    public static ImageView captainpic1,captainpic2,ironmanpic1,ironmanpic2,drawimage,buckyimage;
    public static ImageButton restart,backmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        row=getIntent().getExtras().getInt("row");
        col=getIntent().getExtras().getInt("col");
        threeplayercanvas.i=row;
        threeplayercanvas.j=col;
        setContentView(R.layout.activity_threeplayerboard);


        captainpic1=(ImageView) findViewById(R.id.captainpic1);
        captainpic2=(ImageView) findViewById(R.id.captainpic2);
        ironmanpic1=(ImageView) findViewById(R.id.ironmanpic1);
        ironmanpic2=(ImageView) findViewById(R.id.ironmanpic2);
        drawimage=(ImageView) findViewById(R.id.drawimage1);
        buckyimage=(ImageView) findViewById(R.id.buckywinimage);
        restart=(ImageButton) findViewById(R.id.restartbutton1);
        backmenu=(ImageButton) findViewById(R.id.menubutton1);

        ironmanscore=(TextView) findViewById(R.id.ironmanscore);
        captainscore=(TextView) findViewById(R.id.captainscore);
        buckyscore=(TextView) findViewById(R.id.buckyscore);

        //setting animation
        captainpic1.setTranslationX(-1000f);
        captainpic2.setTranslationX(-1000f);
        ironmanpic1.setTranslationX(-1000f);
        ironmanpic2.setTranslationX(-1000f);
        drawimage.setTranslationX(-1000f);
        buckyimage.setTranslationX(-1000f);





        videoview2=(VideoView) findViewById(R.id.videoview4);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.gamebackgroundvideo);
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


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),threeplayerentry.class);
                startActivity(i);
                finish();
            }
        });

        backmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
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
