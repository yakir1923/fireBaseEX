package com.example.firebaseex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class winnerScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN=3000;

    Animation topAnim, bottomAnim;
    ImageView image;
    TextView text1, text2, score, text3;
    private Intent showActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image=findViewById(R.id.imageView);
        text1=findViewById(R.id.textView);
        text2=findViewById(R.id.textView2);
        score=findViewById(R.id.playerScore);
        text3=findViewById(R.id.textView3);

        //ADD THE SCORE OF THE PLAYER

        image.setAnimation(topAnim);
        text1.setAnimation(topAnim);
        text2.setAnimation(bottomAnim);
        score.setAnimation(bottomAnim);
        text3.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(winnerScreen.this,HomePage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }

}