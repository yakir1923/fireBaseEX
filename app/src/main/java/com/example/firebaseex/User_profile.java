package com.example.firebaseex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.NumberFormat;


public class User_profile extends AppCompatActivity {
    private ImageView userPic;
    private TextView userName;
    private TextView userEmail;
    protected TextView level;
    private Button backHomeButton;
    private Intent goTOHome;
    public Bundle bundle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        bundle=getIntent().getExtras();
        userPic=findViewById(R.id.user_image);

        userName=findViewById(R.id.user_name);

        userEmail=findViewById(R.id.user_email);

        level=findViewById(R.id.user_level);

        backHomeButton=findViewById(R.id.backButton);
        goTOHome=new Intent(this,HomePage.class);



      //   userEmail.setText(profileUser.getEmail());

        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(User_profile.this, "back",Toast.LENGTH_LONG).show();
                startActivity(goTOHome);
            }
        });
        }
    }

