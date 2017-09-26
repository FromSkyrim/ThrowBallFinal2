package com.example.a17255.a8project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start_play_button;
    Button high_score_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Link the button using its ID, and set onClick listener to make it jump to other screen once
//        the user click on the button
        start_play_button = (Button) findViewById(R.id.start_play_button);
        high_score_button = (Button) findViewById(R.id.high_score_button);

        start_play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GamePlay.class);
                startActivity(intent);
            }
        });

        high_score_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HighScore.class);
                startActivity(intent);
            }
        });
    }
}
