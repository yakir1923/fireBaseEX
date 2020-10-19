package com.example.firebaseex;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class GamePage extends AppCompatActivity {
private TableRow tableRow;
private Button button;
private    int i,j;
private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        relativeLayout=findViewById(R.id.game_layout1);

        for (i=0;i<100;i++) {
            relativeLayout.addView(new Button(this));

        }


    }
}