package com.example.firebaseex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
private Button showProfile;
private Button shop;
private Button startGame;
private Intent startTheGame;
private Intent goToProfile;
private Intent goToShop;

public Bundle bundle;


    public static UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bundle=getIntent().getExtras();
        showProfile=findViewById(R.id.show_profile);
        goToProfile=new Intent(this,User_profile.class);
        goToShop=new Intent(this,boxesPage.class);
        startTheGame=new Intent(this,GamePage.class);

        showProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goToProfile);
            }
        });

        shop=findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goToShop);
            }
        });

        startGame=findViewById(R.id.Start_Game);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(startTheGame);
            }
        });
    }
}