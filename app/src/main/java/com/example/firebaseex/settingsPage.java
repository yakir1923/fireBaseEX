package com.example.firebaseex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class settingsPage extends AppCompatActivity {
    private Button backHomeButton;
    private Intent goTOHome;
    public Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        backHomeButton=findViewById(R.id.backButton);
        goTOHome=new Intent(this,User_profile.class);
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(settingsPage.this, "back",Toast.LENGTH_LONG).show();
                startActivity(goTOHome);
            }
        });
    }
    //דך הגדרות
}