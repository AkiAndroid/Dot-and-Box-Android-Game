package com.example.dell.civilwar;

/*import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import static com.example.dell.dotgrid.R.styleable.View;

/**
 * Created by DELL on 07-05-2020.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class boardcanvas extends View {
    Paint dotcolor,linecolor,linecolor2;

    Point pt;
    Path connectline,connectline2;
    int distx,disty,dist1x,dist1y,initx,inity;
    int dpHeight,dpWidth;
    boolean colorturn=true,imageturn;
    Bitmap ironmanlogo;
    Bitmap captainamericalogo;
    List<Point> pointloc= new ArrayList<Point>();
    List <Point> imagepoint=new ArrayList<Point>();
    List <Point> imagepoint1=new ArrayList<Point>();
    List <Point> recur=new ArrayList<Point>();
    DisplayMetrics display;
    public static int i,j;
    int m=0,n=0;
    public static int score,score1;




    public boardcanvas(Context context , AttributeSet attrs) {
        super(context, attrs);

        score=imagepoint.size();
        score1=imagepoint1.size();


        dotcolor = new Paint();
        dotcolor.setColor(Color.BLUE);
        dotcolor.setStyle(Paint.Style.FILL);
        connectline = new Path();
        connectline2 = new Path();

        linecolor = new Paint();
        linecolor.setColor(Color.BLACK);
        linecolor.setStrokeWidth(10f);
        linecolor.setStrokeJoin(Paint.Join.ROUND);
        linecolor.setStyle(Paint.Style.STROKE);
        linecolor2 = new Paint();
        linecolor2.setColor(Color.RED);
        linecolor2.setStrokeWidth(10f);
        linecolor2.setStrokeJoin(Paint.Join.ROUND);
        linecolor2.setStyle(Paint.Style.STROKE);

        ironmanlogo = BitmapFactory.decodeResource(getResources(), R.drawable.ironmanscoreimage);
        captainamericalogo = BitmapFactory.decodeResource(getResources(), R.drawable.cptainscoreimage);

        display = getResources().getDisplayMetrics();
        dpHeight = Math.round(display.heightPixels);
        dpWidth = Math.round(display.widthPixels);

        initx = ((dpWidth - (i - 1) * 200) / 2 - 200);
        inity = ((dpHeight - (j - 1) * 200) / 2 - 200);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float posx=event.getX();
        float posy=event.getY();
        if (colorturn){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:

                    for (int l=1;l<=i;l++){
                        for (int k=1;k<=j;k++){
                            Point pt=new Point(initx+l*200,inity+k*200);
                            if(Math.abs(posx-pt.x)<=50 && Math.abs(posx-pt.x)>=0 && Math.abs(posy-pt.y)<=50 && Math.abs(posy-pt.y)>=0){

                                connectline.moveTo(pt.x,pt.y);
                                distx=pt.x;
                                disty=pt.y;



                            }
                        }
                    }

                    break;
                case MotionEvent.ACTION_UP:

                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx+l * 200, inity+k * 200);
                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {
                                if (Math.sqrt((pt.x - distx) * (pt.x - distx) + (pt.y - disty) * (pt.y - disty)) <= 200) {
                                    if(!pointloc.contains(new Point((pt.x+distx)/2, (pt.y+disty)/2))){
                                        connectline.lineTo(pt.x, pt.y);
                                        colorturn=false;
                                        imageturn=true;
                                        pointloc.add(new Point((pt.x+distx)/2, (pt.y+disty)/2));
                                        gameboard.dotsound.start();
                                        invalidate();

                                    }








                                }
                            }
                        }
                    }


                default:
                    return super.onTouchEvent(event);

            }
        }
        else {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:

                    for (int l=1;l<=i;l++){
                        for (int k=1;k<=j;k++){
                            Point pt=new Point(initx+l*200,inity+k*200);
                            if(Math.abs(posx-pt.x)<=50 && Math.abs(posx-pt.x)>=0 && Math.abs(posy-pt.y)<=50 && Math.abs(posy-pt.y)>=0){

                                connectline2.moveTo(pt.x,pt.y);
                                distx=pt.x;
                                disty=pt.y;



                            }
                        }
                    }

                    break;
                case MotionEvent.ACTION_UP:

                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx+l * 200,inity+ k * 200);
                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {
                                if (Math.sqrt((pt.x - distx) * (pt.x - distx) + (pt.y - disty) * (pt.y - disty)) <= 200) {
                                    if (!pointloc.contains(new Point((pt.x+distx)/2, (pt.y+disty)/2))){
                                        connectline2.lineTo(pt.x, pt.y);
                                        colorturn=true;
                                        imageturn=false;
                                        pointloc.add(new Point((pt.x+distx)/2, (pt.y+disty)/2));
                                        gameboard.dotsound.start();
                                        invalidate();
                                    }







                                }
                            }
                        }
                    }


                default:
                    return super.onTouchEvent(event);

            }

        }


        return true;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int l = 1; l<=i; l++){
            for(int k=1;k<=j;k++){

                Point pt=new Point(initx+l*200 ,inity+k*200);
                canvas.drawCircle(pt.x,pt.y,20,dotcolor);


            }
        }

        for(int l=1; l<=i;l++){
            for (int k=1;k<=j;k++){
                Point pt=new Point(initx+l*200 ,inity+k*200);

                if (pointloc.contains(new Point(initx+l*200+100,inity+k*200)) && pointloc.contains(new Point(initx+l*200,inity+k*200+100)) && pointloc.contains(new Point(initx+l*200+100,inity+k*200+200)) && pointloc.contains(new Point(initx+l*200+200,inity+k*200+100)) && !imagepoint.contains(new Point(initx+l*200,inity+k*200)) && !imagepoint1.contains(new Point(initx+l*200,inity+k*200))){

                    if (imageturn){

                        imagepoint.add(new Point(initx+l*200,inity+k*200));
                        colorturn=true;
                        m++;
                        gameboard.ironmanscore.setText(""+ m);

                        if (m+n==(i-1)*(j-1)){
                            if (m>n){
                                gameboard.ironmanpart1.animate().translationXBy(1000f).setDuration(3000);
                               gameboard.ironmanpart2.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.backmenu.setVisibility(VISIBLE);
                                gameboard.restart.setVisibility(VISIBLE);



                            }
                            else if (m == n){
                             gameboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.restart.setVisibility(VISIBLE);
                                gameboard.backmenu.setVisibility(VISIBLE);
                            }
                            else {
                                gameboard.captainpart1.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.captainpart2.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.restart.setVisibility(VISIBLE);
                                gameboard.backmenu.setVisibility(VISIBLE);


                            }


                        }



                    }
                    else {


                        imagepoint1.add(new Point(initx+l*200,inity+k*200));
                        colorturn=false;
                        n++;
                        gameboard.captainscore.setText( "" + n);
                        if (m+n==(i-1)*(j-1)){
                            if (m>n){
                                gameboard.ironmanpart1.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.ironmanpart2.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.restart.setVisibility(VISIBLE);
                                gameboard.backmenu.setVisibility(VISIBLE);



                            }
                            else if (m == n){
                                gameboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.restart.setVisibility(VISIBLE);
                                gameboard.backmenu.setVisibility(VISIBLE);
                            }
                            else {
                                gameboard.captainpart1.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.captainpart2.animate().translationXBy(1000f).setDuration(3000);
                                gameboard.restart.setVisibility(VISIBLE);
                                gameboard.backmenu.setVisibility(VISIBLE);


                            }


                        }



                    }

                }
            }
        }
        for(Point ptr: imagepoint ){
            canvas.drawBitmap(ironmanlogo,ptr.x,ptr.y,null);

        }
        for (Point ptr1: imagepoint1){
            canvas.drawBitmap(captainamericalogo,ptr1.x,ptr1.y,null);

        }








        canvas.drawPath(connectline,linecolor);


        canvas.drawPath(connectline2,linecolor2);




    }
}


