package com.example.firebaseex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private ImageButton goToSettings;
    private Intent goSettings;
    public Bundle bundle;
    private TextView userWin;
    private TextView userLose;
    private SharedPreferences.Editor editor;
    private SharedPreferences userDitale;
    private  String i;
    private UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userDitale=this.getSharedPreferences("login",MODE_PRIVATE);
        Log.i("email", userDitale.getString("email",null));
        Log.i("password", userDitale.getString("password",null));
        Log.i("name", userDitale.getString("name",null));

        userInfo=new UserInfo(userDitale.getString("name",null),
                userDitale.getString("password",null),
                userDitale.getString("email",null),
                userDitale.getInt("level",-1),
                userDitale.getInt("userWin",-1),
                userDitale.getInt("userLose",-1)
                );
        bundle=getIntent().getExtras();
        userPic=findViewById(R.id.user_image);

        userName=findViewById(R.id.user_name);
        userName.setText(userDitale.getString("name",null));

        userEmail=findViewById(R.id.user_email);
        userEmail.setText(userDitale.getString("email",null));

        level=findViewById(R.id.user_level);
        level.setText(String.valueOf(userInfo.getLevel()));

        userWin=findViewById(R.id.user_wins);
        userWin.setText(String.valueOf(userInfo.getUserWin()));

        userLose=findViewById(R.id.user_lose);
        userLose.setText(String.valueOf(userInfo.getUserLose()));

        backHomeButton=findViewById(R.id.backButton);
        goTOHome=new Intent(this,HomePage.class);

        goToSettings=findViewById(R.id.settings_Button);
        goSettings=new Intent(this,settingsPage.class);

      //   userEmail.setText(profileUser.getEmail());

        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(User_profile.this, "back",Toast.LENGTH_LONG).show();
                startActivity(goTOHome);
            }
        });
        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(User_profile.this, "settings",Toast.LENGTH_LONG).show();
                startActivity(goSettings);
            }
        });
        }
    }

