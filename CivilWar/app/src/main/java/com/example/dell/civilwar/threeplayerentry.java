package com.example.dell.civilwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

public class threeplayerentry extends AppCompatActivity {
    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;
    EditText row;
    EditText col;
    ImageButton beginwar,backmenu;
    int x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threeplayerentry);

        row=(EditText) findViewById(R.id.row3);

        col=(EditText) findViewById(R.id.col3);

        beginwar=(ImageButton) findViewById(R.id.beginwar3);
        backmenu=(ImageButton) findViewById(R.id.backmenu1);

        videoview2=(VideoView) findViewById(R.id.videoview3);

        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.civilwarvideo);
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

        beginwar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String row1=row.getText().toString();
                String col1=col.getText().toString();


                if(row1.matches("") && col1.matches("")){
                    Toast.makeText(threeplayerentry.this, "Enter row and col", Toast.LENGTH_SHORT).show();
                }
                else if (row1.matches("")){
                    Toast.makeText(threeplayerentry.this, "Enter row", Toast.LENGTH_SHORT).show();
                }
                else if(col1.matches("")){
                    Toast.makeText(threeplayerentry.this, "Enter col", Toast.LENGTH_SHORT).show();
                }
                else {
                    x=Integer.parseInt(row1);
                    y=Integer.parseInt(col1);
                    Intent i = new Intent(getApplicationContext(), threeplayerboard.class);
                    i.putExtra("row", x);
                    i.putExtra("col", y);
                    startActivity(i);
                    finish();

                }
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

