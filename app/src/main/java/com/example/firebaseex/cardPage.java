package com.example.firebaseex;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.firebaseex.R.id;
import static com.example.firebaseex.R.id.freeze_time;
import static com.example.firebaseex.R.layout;

public class cardPage extends AppCompatActivity {

    private Button backButton;
    private Intent goTOhome;
    public Bundle bundle;
    ObjectAnimator animation;
    private ImageView freezeTime;
    private ImageView letterThief;
    private ImageView replace;
    private ImageView timeThief;
    private ImageView scoringDoubling;
    private ImageView bonus5;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_card_page);

        bundle=getIntent().getExtras();
        backButton=findViewById(R.id.back_button);
        goTOhome=new Intent(this,boxesPage.class);
        freezeTime=findViewById(freeze_time);
        letterThief=findViewById(id.letter_thief);
        replace=findViewById(id.replace);
        timeThief=findViewById(id.time_thief);
        scoringDoubling=findViewById(id.scoring_doubling);
        bonus5=findViewById(id.bonus5);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cardPage.this, "back",Toast.LENGTH_LONG).show();
                startActivity(goTOhome);
            }
        });


        freezeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(freezeTime);
                Toast.makeText(cardPage.this,"",Toast.LENGTH_LONG).show();
            }
        });

        letterThief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(letterThief);
                Toast.makeText(cardPage.this,"",Toast.LENGTH_LONG).show();

            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(replace);
                Toast.makeText(cardPage.this,"",Toast.LENGTH_LONG).show();
            }
        });

        timeThief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(timeThief);
                Toast.makeText(cardPage.this,"",Toast.LENGTH_LONG).show();
            }
        });

        scoringDoubling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(scoringDoubling);
                Toast.makeText(cardPage.this,"",Toast.LENGTH_LONG).show();
            }
        });

        bonus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(bonus5);
                Toast.makeText(cardPage.this,"",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void anima(ImageView image){
        animation = ObjectAnimator.ofFloat(image,  "rotation", 0f, 10f,0f);
        animation.setDuration(100);
        animation.setRepeatCount(2);
        animation.start();
    }
}