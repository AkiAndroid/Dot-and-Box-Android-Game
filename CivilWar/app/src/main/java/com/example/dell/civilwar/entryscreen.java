package com.example.dell.civilwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import static com.example.dell.civilwar.boardcanvas.i;

public class entryscreen extends AppCompatActivity {
    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;
    ImageButton beginwar;
    EditText norow,nocol;
    int row,col;
    ImageButton backmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entryscreen);

        beginwar=(ImageButton) findViewById(R.id.beginwar);
        backmenu=(ImageButton) findViewById(R.id.backmenu2);


        norow=(EditText) findViewById(R.id.norow);
        nocol=(EditText) findViewById(R.id.nocol);





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

                String row1=norow.getText().toString();
                String col1=nocol.getText().toString();



                if (norow.getText().toString().matches("") && nocol.getText().toString().matches("")){
                    Toast.makeText(entryscreen.this, "Enter rows and cols", Toast.LENGTH_SHORT).show();

                }
                else if (norow.getText().toString().matches("")){
                    Toast.makeText(entryscreen.this, "Enter row", Toast.LENGTH_SHORT).show();
                }
                else if (nocol.getText().toString().matches("")){
                    Toast.makeText(entryscreen.this, "Enter col", Toast.LENGTH_SHORT).show();
                }
                else {
                    row= Integer.parseInt(norow.getText().toString());
                    col=Integer.parseInt(nocol.getText().toString());
                    Intent i=new Intent(getApplicationContext(),gameboard.class);
                    i.putExtra("row",row);
                    i.putExtra("col",col);
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
