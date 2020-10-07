package com.example.firebaseex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
private Button showProfile;
private Button shop;
private Intent goToProfile;
private Intent goToShop;

public Bundle bundle;


    public static UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //2
        bundle=getIntent().getExtras();
        showProfile=findViewById(R.id.shoe_profile);
        goToProfile=new Intent(this,User_profile.class);
        goToShop=new Intent(this,boxesPage.class);


        showProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomePage.this, "ok",Toast.LENGTH_LONG).show();
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
    }
}