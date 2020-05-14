package com.example.dell.civilwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

public class singleplayerentry extends AppCompatActivity {

    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;
    ImageButton easybutton,expertbutton,puristbutton,backmenu;
    EditText rowentry,colentry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayerentry);

        easybutton=(ImageButton) findViewById(R.id.easybutton);
        puristbutton=(ImageButton) findViewById(R.id.puristbutton);
        rowentry=(EditText) findViewById(R.id.rowentry);
        colentry=(EditText) findViewById(R.id.colentry);
        backmenu=(ImageButton) findViewById(R.id.backmenu3);





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


        easybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowentry.getText().toString().matches("") && colentry.getText().toString().matches("")){
                    Toast.makeText(singleplayerentry.this, "Enter row and column", Toast.LENGTH_SHORT).show();
                }
                else if (rowentry.getText().toString().matches("")){
                    Toast.makeText(singleplayerentry.this, "Enter row", Toast.LENGTH_SHORT).show();
                }
                else if (colentry.getText().toString().matches("")){
                    Toast.makeText(singleplayerentry.this, "Enter col", Toast.LENGTH_SHORT).show();
                }
                else {
                    int row=Integer.parseInt(rowentry.getText().toString());
                    int col=Integer.parseInt(colentry.getText().toString());
                    Intent i =new Intent(getApplicationContext(),singleplayerboard.class);
                    i.putExtra("row",row);
                    i.putExtra("col",col);
                    i.putExtra("difficulty",1);
                    startActivity(i);
                    finish();
                }

            }
        });

        puristbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowentry.getText().toString().matches("") && colentry.getText().toString().matches("")){
                    Toast.makeText(singleplayerentry.this, "Enter row and column", Toast.LENGTH_SHORT).show();
                }
                else if (rowentry.getText().toString().matches("")){
                    Toast.makeText(singleplayerentry.this, "Enter row", Toast.LENGTH_SHORT).show();
                }
                else if (colentry.getText().toString().matches("")){
                    Toast.makeText(singleplayerentry.this, "Enter col", Toast.LENGTH_SHORT).show();
                }
                else {
                    int row=Integer.parseInt(rowentry.getText().toString());
                    int col=Integer.parseInt(colentry.getText().toString());
                    Intent i =new Intent(getApplicationContext(),singleplayerboard.class);
                    i.putExtra("row",row);
                    i.putExtra("col",col);
                    i.putExtra("difficulty",2);
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
