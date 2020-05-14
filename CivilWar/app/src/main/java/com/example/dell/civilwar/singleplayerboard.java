package com.example.dell.civilwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class singleplayerboard extends AppCompatActivity {
    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;
   public static ImageButton restart,backmenu;
   public static ImageView playerwin,computerwin,drawimage;
   public static TextView playerscore,computerscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singleplayercanvas.i= getIntent().getExtras().getInt("row");
        singleplayercanvas.j=getIntent().getExtras().getInt("col");
        singleplayercanvas.choice=getIntent().getExtras().getInt("difficulty");
        setContentView(R.layout.activity_singleplayerboard);

        playerwin=(ImageView) findViewById(R.id.playerwinimage);
        computerwin=(ImageView) findViewById(R.id.computerwinimage);
        drawimage=(ImageView) findViewById(R.id.gamedrawimage);
        restart=(ImageButton) findViewById(R.id.restarbutton3);
        backmenu=(ImageButton) findViewById(R.id.menubutton3);
        playerscore=(TextView) findViewById(R.id.playerscoretext);
        computerscore=(TextView) findViewById(R.id.computerscoreimage);

        playerscore.setText(""+0);
        computerscore.setText(""+0);



        playerwin.setTranslationX(-1000f);
        computerwin.setTranslationX(-1000f);
        drawimage.setTranslationX(-1000f);





        videoview2=(VideoView) findViewById(R.id.videoview6);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.singleplayerentryvid);
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
                Intent i=new Intent(getApplicationContext(),singleplayerentry.class);
                startActivity(i);
                finish();
            }
        });
        backmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
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
