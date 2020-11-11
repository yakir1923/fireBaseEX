package com.example.firebaseex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Instructions extends AppCompatActivity {
    private Intent goTOHome;
    private Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        goBack=findViewById(R.id.backButton);
        goTOHome=new Intent(this,HomePage.class);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Instructions.this, "back",Toast.LENGTH_LONG).show();
                startActivity(goTOHome);
            }
        });
    }
}