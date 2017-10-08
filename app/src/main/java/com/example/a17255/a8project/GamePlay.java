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
import android.widget.TextView;

public class GamePlay extends AppCompatActivity implements GestureDetector.OnGestureListener {

    TextView textView;

    float mCurrentX, mCurrentY;
    float firstX, firstY, currentSpeedX, currentSpeedY;
    int score = 0;
    GraphicsItem ball;

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
        private final int SCORE_LOCATION_X = 50;
        private final int SCORE_LOCATION_Y = 50;

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

//                detect screen bound and change direction when reach screen bound
                if (mCurrentX > canvas.getWidth()) {
                    score += 5;
                    currentSpeedX = -currentSpeedX;
                }
                if (mCurrentX < 0) {
                    score += 5;
                    currentSpeedX = -currentSpeedX;
                }
                if (mCurrentY > canvas.getHeight()) {
                    score += 5;
                    currentSpeedY = -currentSpeedY;
                }
                if (mCurrentY < 0) {
                    score += 5;
                    currentSpeedY = -currentSpeedY;
                }

//                display score
                drawScore(canvas);

//                draw ball after fling
                mCurrentX = mCurrentX + currentSpeedX / 5000 * DURATION_PER_FRAME_IN_MS;
                mCurrentY = mCurrentY + currentSpeedY / 5000 * DURATION_PER_FRAME_IN_MS;
                drawBall(canvas);
                postInvalidateDelayed(DURATION_PER_FRAME_IN_MS);

            }
        }

        private void drawScore(Canvas canvas) {
            canvas.drawText(Integer.toString(score), SCORE_LOCATION_X, SCORE_LOCATION_Y, paint);
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
