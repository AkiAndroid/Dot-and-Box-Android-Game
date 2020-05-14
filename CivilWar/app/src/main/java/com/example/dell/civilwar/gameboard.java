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

public class gameboard extends AppCompatActivity {
    int row,col;
    public static TextView ironmanscore;
    public static TextView captainscore;
    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;
    int h1,h2;
    public static ImageView ironmanpart1,ironmanpart2;
    public static ImageView captainpart1,captainpart2;
    public static ImageView drawimage;
    public static MediaPlayer dotsound;
    public static ImageButton restart,backmenu;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        row=getIntent().getExtras().getInt("row");
        col=getIntent().getExtras().getInt("col");
        boardcanvas.i=row;
        boardcanvas.j=col;
        setContentView(R.layout.activity_gameboard);

        h1=boardcanvas.score;
        h2=boardcanvas.score1;

        dotsound=MediaPlayer.create(this,R.raw.buttonsound);

        //animation part
        ironmanpart1=(ImageView) findViewById(R.id.ironmanpic1);
        ironmanpart2=(ImageView) findViewById(R.id.ironmanpart2);
        captainpart1=(ImageView) findViewById(R.id.captainpart1);
        captainpart2=(ImageView) findViewById(R.id.captainpart2);
        drawimage=(ImageView) findViewById(R.id.drawgameimage);
        restart=(ImageButton) findViewById(R.id.restartbutton);
        backmenu=(ImageButton) findViewById(R.id.backtomenubutton);

       //setting up for animation
        ironmanpart1.setTranslationX(-1000f);
        ironmanpart2.setTranslationX(-1000f);
        captainpart1.setTranslationX(-1000f);
        captainpart2.setTranslationX(-1000f);
        drawimage.setTranslationX(-1000f);





        ironmanscore=(TextView) findViewById(R.id.ironmanscore);
        captainscore=(TextView) findViewById(R.id.captainscore);

        ironmanscore.setText("" + 0);
        captainscore.setText("" +  0);

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
                Intent i=new Intent(getApplicationContext(),entryscreen.class);
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
