package com.example.firebaseex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class settingsPage extends AppCompatActivity {
    private Button backHomeButton;
    private Intent goTOHome;
    public Bundle bundle;

    private Switch music_switch;

    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        music_switch= (Switch) findViewById(R.id.music_switch);
        backHomeButton=findViewById(R.id.backButton);
        goTOHome=new Intent(this,User_profile.class);
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(goTOHome);
            }
        });

        music=MediaPlayer.create(settingsPage.this,R.raw.background_music);
        music_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    playIt();
                }
                else {
                    onPause();
                }
            }
            });

    }
    public void playIt(){

            music.start();

    }

    protected void onPause(){
        super.onPause();
        music.release();
    }
}