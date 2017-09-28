package com.example.a17255.a8project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class GamePlay extends AppCompatActivity {




//    Create my own view class
    class MyCanvas extends View {

        public MyCanvas(Context context) {
            super(context);
        }

        float x, y;

//        Draw a red ball
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawCircle(x, y, 50, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
//            Log.d("204", event.actionToString(event.getAction()));
            x = event.getX();
            y = event.getY();

            invalidate();
            return true;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        Set Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


//        setContentView(R.layout.activity_game_play);
        setContentView(new MyCanvas(this));
    }

}
