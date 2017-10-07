package com.example.a17255.a8project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class GamePlay extends AppCompatActivity implements GestureDetector.OnGestureListener {

    float x, y;
    float firstX, firstY;

    MyCanvas myCanvas;

    GestureDetectorCompat gestureDetectorCompat;


//    The method needed for implementing gesture listener
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        firstX = motionEvent.getX();
        firstY = motionEvent.getY();

        x = firstX;
        y = firstY;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d("zjm", "OnScroll event received");

        x -= v;
        y -= v1;
        myCanvas.invalidate();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d("zjm", "V on x :" + v + "Event1 : " + motionEvent.toString());
        Log.d("zjm", "V on y :" + v1 + "Event2 : " + motionEvent1.toString());

        x -= v/100;
        y -= v1/100;

        myCanvas.invalidate();

        return false;
    }



    //    Create my own view class
    class MyCanvas extends View {

        public MyCanvas(Context context) {
            super(context);
        }



//        Draw a red ball at the location of that touch
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setColor(Color.RED);


            canvas.drawCircle(x, y, 50, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
//            firstX = event.getX();
//            firstY = event.getY();

//            x = event.getX();
//            y = event.getY();

            Log.d("zjm", "OnTouch event received");

            invalidate();
            gestureDetectorCompat.onTouchEvent(event);
            return true;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        Set Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myCanvas = new MyCanvas(this);

//        setContentView(R.layout.activity_game_play);
        setContentView(myCanvas);

        gestureDetectorCompat = new GestureDetectorCompat(this, this);
    }

}
