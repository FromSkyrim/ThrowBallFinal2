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

    float mCurrentX, mCurrentY;
    float firstX, firstY, currentSpeedX, currentSpeedY;

    MyCanvas myCanvas;

    GestureDetectorCompat gestureDetectorCompat;


//    The method needed for implementing gesture listener
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        firstX = motionEvent.getX();
        firstY = motionEvent.getY();

        mCurrentX = firstX;
        mCurrentY = firstY;
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

        mCurrentX -= v;
        mCurrentY -= v1;
        myCanvas.invalidate();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d("zjm", "V on mCurrentX :" + v + "Event1 : " + motionEvent.toString());
        Log.d("zjm", "V on mCurrentY :" + v1 + "Event2 : " + motionEvent1.toString());
        currentSpeedX = v;
        currentSpeedY = v1;
        return false;
    }



    //    Create my own view class
    class MyCanvas extends View {
        private Paint paint;
        private final int FRAME_PER_SECOND = 60;
        private final int DURATION_PER_FRAME_IN_MS = Math.round(1000 / FRAME_PER_SECOND);

        private final int BALL_RADIUS = 50;

        public MyCanvas(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.RED);
        }



//        Draw a red ball at the location of that touch
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (currentSpeedX == 0 && currentSpeedY == 0) {
                drawBall(canvas);
            } else {

                mCurrentX = mCurrentX + currentSpeedX / 1000 * DURATION_PER_FRAME_IN_MS;
                mCurrentY = mCurrentY + currentSpeedY / 1000 * DURATION_PER_FRAME_IN_MS;
                drawBall(canvas);
                postInvalidateDelayed(DURATION_PER_FRAME_IN_MS);
            }
        }

        private void drawBall(Canvas canvas) {
            canvas.drawCircle(mCurrentX, mCurrentY, BALL_RADIUS, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            Log.d("zjm", "OnTouch event received");

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
