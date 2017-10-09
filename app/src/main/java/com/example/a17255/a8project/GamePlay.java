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


    float mCurrentX, mCurrentY;
    float firstX, firstY, currentSpeedX, currentSpeedY;
    int score = 0;
    float obstacleX1 = 200, obstacleY1 = 700;
    float obstacleX2 = 550, obstacleY2 = 400;
    float obstacleX3 = 280, obstacleY3 = 400;
    float obstacleX4 = 550, obstacleY4 = 700;

    MyCanvas myCanvas;

    GestureDetectorCompat gestureDetectorCompat;


//    The method needed for implementing gesture listener
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        if (motionEvent.getY() > 950) {
            firstX = motionEvent.getX();
            firstY = motionEvent.getY();
        }


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
        private Paint obstaclePaint1;
        private Paint obstaclePaint2;
        private Paint obstaclePaint3;
        private Paint obstaclePaint4;


        private final int BALL_RADIUS = 50;
        private final int OBSTACLE_RADIUS_1 = 80;
        private final int OBSTACLE_RADIUS_2 = 40;

        public MyCanvas(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.RED);

            obstaclePaint1 = new Paint();
            obstaclePaint1.setColor(Color.LTGRAY);

            obstaclePaint2 = new Paint();
            obstaclePaint2.setColor(Color.LTGRAY);

            obstaclePaint3 = new Paint();
            obstaclePaint3.setColor(Color.DKGRAY);

            obstaclePaint4 = new Paint();
            obstaclePaint3.setColor(Color.DKGRAY);

        }



//        Draw a red ball at the location of that touch
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

//            draw four obstacles
            drawObstacle1(canvas, obstacleX1, obstacleY1, obstaclePaint1);
            drawObstacle1(canvas, obstacleX2, obstacleY2, obstaclePaint2);
            drawObstacle2(canvas, obstacleX3, obstacleY3, obstaclePaint3);
            drawObstacle2(canvas, obstacleX4, obstacleY4, obstaclePaint4);

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

//                detect the collision between the ball and the obstacle light gray
                if (Math.pow(mCurrentX - obstacleX1, 2) + Math.pow(mCurrentY - obstacleY1, 2) <=
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_1, 2)) {
//                    change score when collide
                    score -= 1;
//                    change direction when collide
                    currentSpeedX += 2000;
                    currentSpeedY -= 2000;
                    obstaclePaint1.setColor(Color.CYAN);
                }
                if (Math.pow(mCurrentX - obstacleX2, 2) + Math.pow(mCurrentY - obstacleY2, 2) <=
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_1, 2)) {
//                    change score when collide
                    score -= 1;
//                    change direction when collide
                    currentSpeedX -= 2000;
                    currentSpeedY += 2000;
                    obstaclePaint2.setColor(Color.CYAN);
                }
                if (Math.pow(mCurrentX - obstacleX1, 2) + Math.pow(mCurrentY - obstacleY1, 2) >
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_1, 2)) {
                    obstaclePaint1.setColor(Color.LTGRAY);
                }
                if (Math.pow(mCurrentX - obstacleX2, 2) + Math.pow(mCurrentY - obstacleY2, 2) >
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_1, 2)) {
                    obstaclePaint2.setColor(Color.LTGRAY);
                }

//                detect the collision between the ball and the obstacle dark gray

                if (Math.pow(mCurrentX - obstacleX3, 2) + Math.pow(mCurrentY - obstacleY3, 2) <=
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_2, 2)) {
//                    change score when collide
                    score -= 3;
//                    slow down when collide
                    currentSpeedX = Math.abs(currentSpeedX) - 1000;
                    currentSpeedY = Math.abs(currentSpeedY) - 1000;
//                    stop compelete
                    if (currentSpeedX < 1000) {
                        currentSpeedX = 0;
                    }
                    if (currentSpeedY < 1000) {
                        currentSpeedY = 0;
                    }
                    obstaclePaint3.setColor(Color.CYAN);
                }
                if (Math.pow(mCurrentX - obstacleX4, 2) + Math.pow(mCurrentY - obstacleY4, 2) <=
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_2, 2)) {
//                    change score when collide
                    score -= 3;
//                    slow down when collide
                    currentSpeedX = Math.abs(currentSpeedX) - 1000;
                    currentSpeedY = Math.abs(currentSpeedY) - 1000;
//                    stop compelete
                    if (currentSpeedX < 1000) {
                        currentSpeedX = 0;
                    }
                    if (currentSpeedY < 1000) {
                        currentSpeedY = 0;
                    }
                    obstaclePaint4.setColor(Color.CYAN);
                }
                if (Math.pow(mCurrentX - obstacleX3, 2) + Math.pow(mCurrentY - obstacleY3, 2) >
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_2, 2)) {
                    obstaclePaint3.setColor(Color.DKGRAY);
                }
                if (Math.pow(mCurrentX - obstacleX4, 2) + Math.pow(mCurrentY - obstacleY4, 2) >
                        Math.pow(BALL_RADIUS + OBSTACLE_RADIUS_2, 2)) {
                    obstaclePaint4.setColor(Color.DKGRAY);
                }

//                display score
                drawScore(canvas);


//                draw ball after fling
                mCurrentX = mCurrentX + currentSpeedX / 7000 * DURATION_PER_FRAME_IN_MS;
                mCurrentY = mCurrentY + currentSpeedY / 7000 * DURATION_PER_FRAME_IN_MS;
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

        private void drawObstacle1(Canvas canvas, float x, float y, Paint paint) {
            canvas.drawCircle(x, y, OBSTACLE_RADIUS_1, paint);
        }

        private void drawObstacle2(Canvas canvas, float x, float y, Paint paint) {
            canvas.drawCircle(x, y, OBSTACLE_RADIUS_2, paint);
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
