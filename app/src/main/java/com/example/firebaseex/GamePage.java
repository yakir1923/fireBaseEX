package com.example.firebaseex;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePage extends AppCompatActivity {
private MyButton button;
private int i,j;
private TableLayout tableLayout;
private TableRow tableRow;
private TextView playerPoints;
private TextView playerName;
private TextView opponentName;
private TextView opponentPoints;
private int playerCurrentPoints;
private int opponentCurrentPoints;
private SharedPreferences.Editor editor;
private SharedPreferences userDitale;
private Drawable drawable;
private int idNum;
private ArrayList<Button> buttonList;
private ArrayList<Letter> letterArrayList;
private TableRow playerHand;
private TableRow opponentHand;
private static String tempLetter;
private int buttonId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        tableLayout=findViewById(R.id.game_layout);
        playerPoints=findViewById(R.id.player_points);
        playerName=findViewById(R.id.player_name);
        opponentName=findViewById(R.id.opponent_name);
        opponentPoints=findViewById(R.id.opponent_points);
        playerHand=findViewById(R.id.player_hand);
        opponentHand=findViewById(R.id.opponent_hand);
        playerCurrentPoints=0;
        opponentCurrentPoints=0;
        playerPoints.setText("0");
        opponentPoints.setText("0");
        userDitale=getSharedPreferences("login",MODE_PRIVATE);
        playerName.setText(userDitale.getString("name",null));
        idNum=0;
        opponentName.setText("שני מוזס זמני");
        buttonList=new ArrayList<Button>();
        letterArrayList=new ArrayList<Letter>();
        letterArrayList.add(new Letter("א",10,"---"));
        letterArrayList.add(new Letter("ב",10,"---"));
        letterArrayList.add(new Letter("ג",20,"---"));
        letterArrayList.add(new Letter("ד",30,"---"));
        letterArrayList.add(new Letter("ה",10,"---"));
        letterArrayList.add(new Letter("ו",5,"---"));
        letterArrayList.add(new Letter("ז",80,"---"));
        letterArrayList.add(new Letter("ח",30,"---"));
        letterArrayList.add(new Letter("ט",40,"---"));
        letterArrayList.add(new Letter("י",30,"---"));
        letterArrayList.add(new Letter("כ",70,"---"));
        letterArrayList.add(new Letter("ל",40,"---"));
        letterArrayList.add(new Letter("מ",60,"---"));
        letterArrayList.add(new Letter("נ",30,"---"));
        letterArrayList.add(new Letter("ס",50,"---"));
        letterArrayList.add(new Letter("ע",80,"---"));
        letterArrayList.add(new Letter("פ",90,"---"));
        letterArrayList.add(new Letter("צ",100,"---"));
        letterArrayList.add(new Letter("ק",60,"---"));
        letterArrayList.add(new Letter("ר",40,"---"));
        letterArrayList.add(new Letter("ש",50,"---"));
        letterArrayList.add(new Letter("ת",40,"---"));
        for (i=0;i<10;i++) {
            tableRow=new TableRow(this);
            tableLayout.addView(tableRow);
            for (j=0;j<10;j++){
                  button=new MyButton(this,i,j);
                  button.setId(idNum);
                  idNum++;
                  button.setBackground(getDrawable(R.drawable.my_button));
                  button.setText(null);
                  button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyButton myButton=(MyButton)view;
                    if (myButton.getLetter()!=null) {
                        tempLetter=myButton.getLetter();
                        myButton.setText(null);
                    }else{
                        myButton.setText(tempLetter);
                        tempLetter = null;

                    }
                }
            });
                tableRow.addView(button);
                buttonList.add(button);
        }
        }
        for (i=0;i<10;i++){
            Random random=new Random();
            int rndNum=random.nextInt(22);
            playerHand.addView(button=new MyButton(this,0,0));
            button.setBackground(getDrawable(R.drawable.my_button));
            button.setText(letterArrayList.get(rndNum).getLett());
            button.setLetter(letterArrayList.get(rndNum).getLett());
            button.setBackground(getDrawable(R.drawable.active_button_color));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyButton myButton=(MyButton)view;
                    if (myButton.getLetter()!=null) {
                        tempLetter = myButton.getLetter();
                        myButton.setText(null);
                    }
                    else{
                        myButton.setText(tempLetter);
                        tempLetter=null;
                    }
                }
            });

        }
        for (i=0;i<10;i++){
            opponentHand.addView(button=new MyButton(this));
            button.setBackground(getDrawable(R.drawable.active_button_color));
        }

    }
}