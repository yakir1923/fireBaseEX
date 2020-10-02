package com.example.firebaseex;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;

//import static com.example.firebaseex.MainActivity.profileUser;

public class User_profile extends AppCompatActivity {
    private ImageView userPic;
    private TextView userName;
    private TextView userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPic=findViewById(R.id.user_image);
        userName=findViewById(R.id.user_name);
        userEmail=findViewById(R.id.user_email);
     //   userName.setText(profileUser.getName()+" "+profileUser.getLastName());
    //   userEmail.setText(profileUser.getEmail());
        setContentView(R.layout.activity_user_profile);
    }
    }

