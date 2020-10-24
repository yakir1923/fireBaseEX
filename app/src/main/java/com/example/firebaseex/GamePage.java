package com.example.firebaseex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
private Letter letter;

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

        //יצירת רשימה של אותיות
        letterArrayList=new ArrayList<Letter>();
        letterArrayList.add(letter=new Letter("א",10,R.drawable.alef));
        letterArrayList.add(letter=new Letter("ב",10,R.drawable.beit));
        letterArrayList.add(letter=new Letter("ג",20,R.drawable.gimel));
        letterArrayList.add(letter=new Letter("ד",30,R.drawable.daled));
        letterArrayList.add(letter=new Letter("ה",10,R.drawable.hei));
        letterArrayList.add(letter=new Letter("ו",5,R.drawable.vav));
        letterArrayList.add(letter=new Letter("ז",80,R.drawable.zain));
        letterArrayList.add(letter=new Letter("ח",30,R.drawable.chet));
        letterArrayList.add(letter=new Letter("ט",40,R.drawable.tet));
        letterArrayList.add(letter=new Letter("י",30,R.drawable.yud));
        letterArrayList.add(letter=new Letter("כ",70,R.drawable.chaf));
        letterArrayList.add(letter=new Letter("ל",40,R.drawable.lamed));
        letterArrayList.add(letter=new Letter("מ",60,R.drawable.mem));
        letterArrayList.add(letter=new Letter("נ",30,R.drawable.non));
        letterArrayList.add(letter=new Letter("ס",50,R.drawable.samech));
        letterArrayList.add(letter=new Letter("ע",80,R.drawable.aein));
        letterArrayList.add(letter=new Letter("פ",90,R.drawable.pey));
        letterArrayList.add(letter=new Letter("צ",100,R.drawable.tzadik));
        letterArrayList.add(letter=new Letter("ק",60,R.drawable.kof));
        letterArrayList.add(letter=new Letter("ר",40,R.drawable.resha));
        letterArrayList.add(letter=new Letter("ש",50,R.drawable.shin));
        letterArrayList.add(letter=new Letter("ת",40,R.drawable.taf));

        //יצירת מסך וגם שמירה של כל הכפתורים ברשימה
        buttonList=new ArrayList<Button>();
        for (i=0;i<10;i++) {
            tableRow=new TableRow(this);
            TableRow.LayoutParams params=new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tableLayout.addView(tableRow);
            tableRow.setGravity(Gravity.CENTER);
            tableRow.setLayoutParams(params);
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
                        myButton.setBackground(getDrawable(R.drawable.my_button));
                        myButton.setLetter(null);
                    }else{

                        for (Letter l:letterArrayList){
                            if (l.getLett()==tempLetter)
                            myButton.setBackgroundDrawable(getDrawable(l.getIcon()));
                            myButton.setLetter(tempLetter);
                            TableRow.LayoutParams buttonParams=new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            myButton.setLayoutParams(buttonParams);
                        }
                        tempLetter = null;

                    }
                }
            });
                tableRow.addView(button);
                buttonList.add(button);
        }

            //יצרה של היד של השחקן
        }
        for (i=0;i<10;i++){
            Random random=new Random();
            int rndNum=random.nextInt(22);
            playerHand.addView(button=new MyButton(this,0,0));
            button.setBackground(getDrawable(R.drawable.my_button));
            //button.setText(letterArrayList.get(rndNum).getLett());
            button.setBackgroundDrawable(getDrawable(letterArrayList.get(rndNum).getIcon()));
            button.setLetter(letterArrayList.get(rndNum).getLett());
            TableRow.LayoutParams buttonParamsPlayer=new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            button.setLayoutParams(buttonParamsPlayer);
          //  button.setBackground(getDrawable(R.drawable.active_button_color));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyButton myButton=(MyButton)view;
                    if (myButton.getLetter()!=null) {
                        tempLetter = myButton.getLetter();
                        myButton.setLetter(null);
                        myButton.setBackground(getDrawable(R.drawable.my_button));
                    }
                    else{
                        for (Letter l:letterArrayList){
                            if (l.getLett()==tempLetter){
                                myButton.setBackgroundDrawable(getDrawable(l.getIcon()));
                                myButton.setBackground(getDrawable(l.getIcon()));
                                myButton.setLetter(tempLetter);
                                TableRow.LayoutParams buttonParamsPlayer=new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                myButton.setLayoutParams(buttonParamsPlayer);
                         }
                        }
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
    //טיימר
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