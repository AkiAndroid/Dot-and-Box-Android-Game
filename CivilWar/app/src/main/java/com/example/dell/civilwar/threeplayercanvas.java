package com.example.dell.civilwar;



import android.content.Context;
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





public class threeplayercanvas extends View {
    Paint dotcolor,linecolor,linecolor2,linecolor3;
    public static int i,j;
    Point pt;
    Path connectline,connectline2,connectline3;
    int distx,disty,dist1x,dist1y,initx,inity;
    int dpHeight,dpWidth;
    boolean colorturn=true,imageturn;
    Bitmap ironmanlogo;
    Bitmap captainamericalogo;
    Bitmap johnwick;
    List <Point> pointloc= new ArrayList<Point>();
    List <Point> imagepoint=new ArrayList<Point>();
    List <Point> imagepoint1=new ArrayList<Point>();
    List <Point> imagepoint2=new ArrayList<Point>();
    List <Point> recur=new ArrayList<Point>();
    DisplayMetrics display;
    int chance=0,imagechange=0;
    int a=0,b=0,c=0;

    public threeplayercanvas(Context context , AttributeSet attrs) {
        super(context, attrs);


        dotcolor=new Paint();
        dotcolor.setColor(Color.BLUE);
        dotcolor.setStyle(Paint.Style.FILL);
        connectline=new Path();
        connectline2=new Path();
        connectline3=new Path();

        linecolor=new Paint();
        linecolor.setColor(Color.BLACK);
        linecolor.setStrokeWidth(10f);
        linecolor.setStrokeJoin(Paint.Join.ROUND);
        linecolor.setStyle(Paint.Style.STROKE);
        linecolor2=new Paint();
        linecolor2.setColor(Color.RED);
        linecolor2.setStrokeWidth(10f);
        linecolor2.setStrokeJoin(Paint.Join.ROUND);
        linecolor2.setStyle(Paint.Style.STROKE);
        linecolor3=new Paint();
        linecolor3.setColor(Color.GREEN);
        linecolor3.setStrokeWidth(10f);
        linecolor3.setStrokeJoin(Paint.Join.ROUND);
        linecolor3.setStyle(Paint.Style.STROKE);


        ironmanlogo=BitmapFactory.decodeResource(getResources(),R.drawable.ironmanscoreimage);
        captainamericalogo=BitmapFactory.decodeResource(getResources(),R.drawable.cptainscoreimage);
        johnwick=BitmapFactory.decodeResource(getResources(),R.drawable.buckyscoreimage);

        display= getResources().getDisplayMetrics();
        dpHeight = Math.round(display.heightPixels);
        dpWidth = Math.round(display.widthPixels);

        initx= ((dpWidth-(i-1)*200)/2-200);
        inity=((dpHeight-(j-1)*200)/2-200);





        for(int l=1;l<=i;l++){
            Point pt=new Point(i*200 ,i*200);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float posx=event.getX();
        float posy=event.getY();
        if (chance==0){
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
                                        chance=1;
                                        imagechange=0;
                                        pointloc.add(new Point((pt.x+distx)/2, (pt.y+disty)/2));
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
        else if (chance==1){

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
                                        chance=2;
                                        imagechange=1;
                                        pointloc.add(new Point((pt.x+distx)/2, (pt.y+disty)/2));
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

                                connectline3.moveTo(pt.x,pt.y);
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
                                        connectline3.lineTo(pt.x, pt.y);
                                        chance=0;
                                        imagechange=2;
                                        pointloc.add(new Point((pt.x+distx)/2, (pt.y+disty)/2));
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

                if (pointloc.contains(new Point(initx+l*200+100,inity+k*200)) && pointloc.contains(new Point(initx+l*200,inity+k*200+100)) && pointloc.contains(new Point(initx+l*200+100,inity+k*200+200)) && pointloc.contains(new Point(initx+l*200+200,inity+k*200+100)) && !imagepoint.contains(new Point(initx+l*200,inity+k*200)) && !imagepoint1.contains(new Point(initx+l*200,inity+k*200)) && !imagepoint2.contains(new Point(initx+l*200,inity+k*200))){

                    if (imagechange==0){

                        imagepoint.add(new Point(initx+l*200,inity+k*200));
                        chance=0;
                        a++;
                        threeplayerboard.ironmanscore.setText(""+a);

                        if (a+b+c==(i-1)*(j-1)){
                            if (a>b && a>c){
                                threeplayerboard.ironmanpic1.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.ironmanpic2.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else if (b>a && b>c){
                                threeplayerboard.captainpic1.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.captainpic2.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else if (c>a && c>b){
                                threeplayerboard.buckyimage.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else {
                                threeplayerboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                        }




                    }
                    else if(imagechange==1) {


                        imagepoint1.add(new Point(initx+l*200,inity+k*200));
                        chance=1;
                        b++;
                        threeplayerboard.captainscore.setText(""+ b);

                        if (a+b+c==(i-1)*(j-1)){
                            if (a>b && a>c){
                                threeplayerboard.ironmanpic1.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.ironmanpic2.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else if (b>a && b>c){
                                threeplayerboard.captainpic1.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.captainpic2.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else if (c>a && c>b){
                                threeplayerboard.buckyimage.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else {
                                threeplayerboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                        }
                    }
                    else {
                        imagepoint2.add(new Point(initx+l*200,inity+k*200));
                        chance=2;
                        c++;
                        threeplayerboard.buckyscore.setText(""+ c);

                        if (a+b+c==(i-1)*(j-1)){
                            if (a>b && a>c){
                                threeplayerboard.ironmanpic1.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.ironmanpic2.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else if (b>a && b>c){
                                threeplayerboard.captainpic1.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.captainpic2.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else if (c>a && c>b){
                                threeplayerboard.buckyimage.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
                            }
                            else {
                                threeplayerboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                threeplayerboard.restart.setVisibility(VISIBLE);
                                threeplayerboard.backmenu.setVisibility(VISIBLE);
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
        for (Point ptr2: imagepoint2){
            canvas.drawBitmap(johnwick,ptr2.x,ptr2.y,null);
        }









        canvas.drawPath(connectline,linecolor);
        canvas.drawPath(connectline2,linecolor2);
        canvas.drawPath(connectline3,linecolor3);






    }
}

