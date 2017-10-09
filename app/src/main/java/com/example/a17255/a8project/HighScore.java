package com.example.a17255.a8project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScore extends AppCompatActivity {

    Button mainMenu, playAgain;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
//         set full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mainMenu = (Button) findViewById(R.id.main_menu);
        playAgain = (Button) findViewById(R.id.play_again_button);
        listView = (ListView) findViewById(R.id.listView);

        SharedPreferences sharedPref = HighScore.this.getSharedPreferences("abc", MODE_PRIVATE);

////        get the score from GamePlay activity
//        Intent intent = getIntent();
//        int score = intent.getIntExtra("score", 0);


//        add the score into an array
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int value = sharedPref.getInt(Integer.toString(i), 0);
            arrayList.add(value);
        }

        Collections.sort(arrayList);
        Collections.reverse(arrayList);

        List<String> myArray = new ArrayList<>();

//        transfer int array to string array
        for (int i : arrayList) {
            myArray.add(Integer.toString(i));
        }

//        set the listview adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, myArray);
        listView.setAdapter(adapter);



        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScore.this, MainActivity.class);
                startActivity(intent);
            }
        });

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScore.this, GamePlay.class);
                startActivity(intent);
            }
        });
    }
}
