package com.example.dell.civilwar;



        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Path;
        import android.graphics.Point;
        import android.graphics.Rect;
        import android.graphics.RectF;
        import android.graphics.Region;
        import android.util.AttributeSet;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.os.Handler;

        import java.text.AttributedCharacterIterator;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;





public class singleplayercanvas extends View {
    Paint dotcolor, linecolor, linecolor2;
    public static int i, j;
    Point pt, rdpt, rdpt1;
    Path connectline, connectline2;
    int distx, disty, dist1x, dist1y, initx, inity;
    int dpHeight, dpWidth;
    boolean colorturn = true, imageturn;
    Bitmap ironmanlogo;
    Bitmap captainamericalogo;
    List<Point> pointloc = new ArrayList<Point>();
    List<Point> imagepoint = new ArrayList<Point>();
    List<Point> imagepoint1 = new ArrayList<Point>();
    List<Point> recur = new ArrayList<Point>();
    List<Point> recur1 = new ArrayList<Point>();
    List<Point> check1 = new ArrayList<Point>();
    DisplayMetrics display;
    List<Rect> singleplayerpath = new ArrayList<Rect>();
    int f, g;
    int a, b;
    public static int choice;


    public singleplayercanvas(Context context, AttributeSet attrs) {
        super(context, attrs);


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

        ironmanlogo = BitmapFactory.decodeResource(getResources(), R.drawable.playerimage);
        captainamericalogo = BitmapFactory.decodeResource(getResources(), R.drawable.computerimage);

        display = getResources().getDisplayMetrics();
        dpHeight = Math.round(display.heightPixels);
        dpWidth = Math.round(display.widthPixels);

        initx = ((dpWidth - (i - 1) * 200) / 2 - 200);
        inity = ((dpHeight - (j - 1) * 200) / 2 - 200);

        for (int l = 1; l <= i - 1; l++) {
            for (int k = 1; k <= j - 1; k++) {
                check1.add(new Point(initx + (l * 200) + 100, inity + (k * 200) + 100));
            }
        }


        for (int l = 1; l <= i; l++) {
            for (int k = 1; k <= j; k++) {
                recur.add(new Point(initx + l * 200, inity + k * 200));
                recur1.add(new Point(initx + l * 200, inity + k * 200));
            }

        }
        for (Point pt3 : recur) {
            for (Point pt4 : recur1) {
                if (Math.sqrt((pt3.x - pt4.x) * (pt3.x - pt4.x) + (pt3.y - pt4.y) * (pt3.y - pt4.y)) == 200 && !singleplayerpath.contains(new Rect(pt3.x, pt3.y, pt4.x, pt4.y)) && !singleplayerpath.contains(new Rect(pt4.x, pt4.y, pt3.x, pt3.y))) {
                    singleplayerpath.add(new Rect(pt3.x, pt3.y, pt4.x, pt4.y));
                }
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float posx = event.getX();
        float posy = event.getY();
        if (colorturn) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:


                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx + l * 200, inity + k * 200);

                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {

                                connectline.moveTo(pt.x, pt.y);
                                distx = pt.x;
                                disty = pt.y;


                            }
                        }
                    }


                    break;
                case MotionEvent.ACTION_UP:

                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx + l * 200, inity + k * 200);
                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {
                                if (Math.sqrt((pt.x - distx) * (pt.x - distx) + (pt.y - disty) * (pt.y - disty)) <= 200) {
                                    if (!pointloc.contains(new Point((pt.x + distx) / 2, (pt.y + disty) / 2))) {
                                        connectline.lineTo(pt.x, pt.y);
                                        colorturn = false;
                                        imageturn = true;
                                        pointloc.add(new Point((pt.x + distx) / 2, (pt.y + disty) / 2));
                                        if (singleplayerpath.contains(new Rect(distx, disty, pt.x, pt.y))) {
                                            singleplayerpath.remove(new Rect(distx, disty, pt.x, pt.y));

                                        } else {
                                            singleplayerpath.remove(new Rect(pt.x, pt.y, distx, disty));
                                        }

                                        postInvalidate();


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


    protected void singleplayer() {
        if (choice == 1) {
            if (!colorturn) {
                if (!(singleplayerpath.size() == 0)) {
                    Random initindex1 = new Random();
                    g = initindex1.nextInt(singleplayerpath.size());
                    Rect paths = singleplayerpath.get(g);


                    connectline2.moveTo(paths.left, paths.top);
                    distx = paths.left;
                    disty = paths.top;


                    connectline2.lineTo(paths.right, paths.bottom);
                    colorturn = true;
                    imageturn = false;
                    pointloc.add(new Point((paths.left + paths.right) / 2, (paths.top + paths.bottom) / 2));

                    if (singleplayerpath.contains(new Rect(paths.left, paths.top, paths.right, paths.bottom))) {
                        singleplayerpath.remove(new Rect(paths.left, paths.top, paths.right, paths.bottom));

                    } else {
                        singleplayerpath.remove(new Rect(paths.right, paths.bottom, paths.left, paths.top));
                    }

                    postInvalidate();


                } else {
                    if (a + b == (i - 1) * (j - 1)) {
                        if (a > b) {
                            singleplayerboard.playerwin.animate().translationXBy(1000f).setDuration(3000);
                            singleplayerboard.backmenu.setVisibility(VISIBLE);
                            singleplayerboard.restart.setVisibility(VISIBLE);
                        } else if (b > a) {
                            singleplayerboard.computerwin.animate().translationXBy(1000f).setDuration(3000);
                            singleplayerboard.backmenu.setVisibility(VISIBLE);
                            singleplayerboard.restart.setVisibility(VISIBLE);
                        } else {
                            singleplayerboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                            singleplayerboard.backmenu.setVisibility(VISIBLE);
                            singleplayerboard.restart.setVisibility(VISIBLE);
                        }
                    }

                }
            }
        }
        if (choice == 2) {
            if (!colorturn) {


                boolean pointdrawn = false;

                for (Point pt3 : check1) {

                    if ((pointloc.contains(new Point(pt3.x, pt3.y + 100)) && pointloc.contains(new Point(pt3.x, pt3.y - 100)) && pointloc.contains(new Point(pt3.x + 100, pt3.y))) || (pointloc.contains(new Point(pt3.x, pt3.y - 100)) && pointloc.contains(new Point(pt3.x + 100, pt3.y)) && pointloc.contains(new Point(pt3.x - 100, pt3.y))) || (pointloc.contains(new Point(pt3.x + 100, pt3.y)) && pointloc.contains(new Point(pt3.x - 100, pt3.y)) && pointloc.contains(new Point(pt3.x, pt3.y + 100))) || (pointloc.contains(new Point(pt3.x - 100, pt3.y)) && pointloc.contains(new Point(pt3.x, pt3.y + 100)) && pointloc.contains(new Point(pt3.x, pt3.y - 100)))) {

                        for (Rect rect : singleplayerpath) {

                            Point center = new Point((rect.left + rect.right) / 2, (rect.top + rect.bottom) / 2);
                            Log.i("val", "" + center.x + " " + center.y);

                            if ((center.equals(pt3.x, pt3.y + 100) || center.equals(pt3.x, pt3.y - 100) || center.equals(pt3.x + 100, pt3.y) || center.equals(pt3.x - 100, pt3.y)) && !pointdrawn) {

                                pointdrawn = true;
                                connectline2.moveTo(rect.left, rect.top);
                                connectline2.lineTo(rect.right, rect.bottom);

                                colorturn = true;
                                imageturn = false;
                                pointloc.add(new Point((rect.left + rect.right) / 2, (rect.top + rect.bottom) / 2));

                                if (singleplayerpath.contains(new Rect(rect.left, rect.top, rect.right, rect.bottom))) {
                                    singleplayerpath.remove(new Rect(rect.left, rect.top, rect.right, rect.bottom));

                                } else {
                                    singleplayerpath.remove(new Rect(rect.right, rect.bottom, rect.left, rect.top));
                                }

                                postInvalidate();
                                break;


                            }

                        }

                    }


                }


                if (!pointdrawn) {
                    if (!(singleplayerpath.size() == 0)) {
                        Random initindex1 = new Random();
                        g = initindex1.nextInt(singleplayerpath.size());
                        Rect paths = singleplayerpath.get(g);

                        connectline2.moveTo(paths.left, paths.top);
                        distx = paths.left;
                        disty = paths.top;


                        connectline2.lineTo(paths.right, paths.bottom);
                        colorturn = true;
                        imageturn = false;
                        pointloc.add(new Point((paths.left + paths.right) / 2, (paths.top + paths.bottom) / 2));

                        if (singleplayerpath.contains(new Rect(paths.left, paths.top, paths.right, paths.bottom))) {
                            singleplayerpath.remove(new Rect(paths.left, paths.top, paths.right, paths.bottom));

                        } else {
                            singleplayerpath.remove(new Rect(paths.right, paths.bottom, paths.left, paths.top));
                        }

                        postInvalidate();
                    }
                    else {
                        if (a+b==(i-1)*(j-1)){
                            if (a>b){
                                singleplayerboard.playerwin.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                            else if (b>a) {
                                singleplayerboard.computerwin.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                            else {
                                singleplayerboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                        }

                    }


                }

            }
        }

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
                Point pt1=new Point(initx+l*200 ,inity+k*200);

                if (pointloc.contains(new Point(initx+l*200+100,inity+k*200)) && pointloc.contains(new Point(initx+l*200,inity+k*200+100)) && pointloc.contains(new Point(initx+l*200+100,inity+k*200+200)) && pointloc.contains(new Point(initx+l*200+200,inity+k*200+100)) && !imagepoint.contains(new Point(initx+l*200,inity+k*200)) && !imagepoint1.contains(new Point(initx+l*200,inity+k*200))){
                    Log.i("check","" + imageturn);
                    if (imageturn){

                        imagepoint.add(new Point(initx+l*200,inity+k*200));
                        colorturn=true;
                        a++;
                        singleplayerboard.playerscore.setText(""+a);
                        if (a+b==(i-1)*(j-1)){
                            if (a>b){
                                singleplayerboard.playerwin.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                            else if (b>a) {
                                singleplayerboard.computerwin.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                            else {
                                singleplayerboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                        }



                    }
                    else {


                        imagepoint1.add(new Point(initx+l*200,inity+k*200));
                        colorturn=false;
                        b++;
                        singleplayerboard.computerscore.setText(""+b);

                        if (a+b==(i-1)*(j-1)){
                            if (a>b){
                                singleplayerboard.playerwin.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                            else if (b>a) {
                                singleplayerboard.computerwin.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
                            }
                            else {
                                singleplayerboard.drawimage.animate().translationXBy(1000f).setDuration(3000);
                                singleplayerboard.backmenu.setVisibility(VISIBLE);
                                singleplayerboard.restart.setVisibility(VISIBLE);
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
        singleplayer();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                singleplayer();
            }
        }, 10000);











    }

}







