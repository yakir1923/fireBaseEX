package com.example.firebaseex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
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
private TextView timer;
private Button nextTurn;

private Intent showActivity;

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
        timer=findViewById(R.id.timer_round);
        setTimer();
        nextTurn=findViewById(R.id.next_turn);
        nextTurn.setBackground(getDrawable(R.drawable.active_button_color));
        nextTurn.setText("סיום");
        buttonList=new ArrayList<Button>();
        letterArrayList=new ArrayList<Letter>();
        letterArrayList.add(new Letter("א",10,"alef"));
        letterArrayList.add(new Letter("ב",10,"beit"));
        letterArrayList.add(new Letter("ג",20,"gimel"));
        letterArrayList.add(new Letter("ד",30,"daled"));
        letterArrayList.add(new Letter("ה",10,"hei"));
        letterArrayList.add(new Letter("ו",5,"vav"));
        letterArrayList.add(new Letter("ז",80,"zain"));
        letterArrayList.add(new Letter("ח",30,"chet"));
        letterArrayList.add(new Letter("ט",40,"tet"));
        letterArrayList.add(new Letter("י",30,"yud"));
        letterArrayList.add(new Letter("כ",70,"chaf"));
        letterArrayList.add(new Letter("ל",40,"lamed"));
        letterArrayList.add(new Letter("מ",60,"mem"));
        letterArrayList.add(new Letter("נ",30,"non"));
        letterArrayList.add(new Letter("ס",50,"samech"));
        letterArrayList.add(new Letter("ע",80,"aein"));
        letterArrayList.add(new Letter("פ",90,"pey"));
        letterArrayList.add(new Letter("צ",100,"tzadik"));
        letterArrayList.add(new Letter("ק",60,"kof"));
        letterArrayList.add(new Letter("ר",40,"resha"));
        letterArrayList.add(new Letter("ש",50,"shin"));
        letterArrayList.add(new Letter("ת",40,"taf"));


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
                        myButton.setLetter(null);
                    }else{
                        myButton.setText(tempLetter);
                        myButton.setLetter(tempLetter);
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


            String str="res/drawable/" +letterArrayList.get(rndNum).getIcon() + ".png";
            File file = new File(str);
            button.setBackground(getDrawable(R.drawable.);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyButton myButton=(MyButton)view;
                    if (myButton.getLetter()!=null) {
                        tempLetter = myButton.getLetter();
                        myButton.setText(null);
                        myButton.setLetter(null);
                    }
                    else{
                        myButton.setText(tempLetter);
                        myButton.setLetter(tempLetter);
                        tempLetter=null;
                    }
                }
            });

        }
        nextTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
    public void setTimer(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();
    }
    private void sendScore(){
        showActivity = new Intent(GamePage.this, winnerScreen.class);
        showActivity.putExtra("score",Integer.toString(playerCurrentPoints));
    }
}