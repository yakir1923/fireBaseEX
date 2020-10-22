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
private List letterList;
private ArrayList<Button> buttonList;
private ArrayList<Letter> letterArrayList;
private TableRow playerHand;
private TableRow opponentHand;
private static String tempLetter;
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
        tempLetter=null;
        playerPoints.setText("0");
        opponentPoints.setText("0");
        userDitale=getSharedPreferences("login",MODE_PRIVATE);
        playerName.setText(userDitale.getString("name",null));
        idNum=0;
        opponentName.setText("שני מוזס זמני");
        buttonList=new ArrayList<Button>();
        letterArrayList=new ArrayList<Letter>();
        letterArrayList.add(new Alef());
        for (i=0;i<10;i++) {
            tableRow=new TableRow(this);
            tableLayout.addView(tableRow);
            for (j=0;j<10;j++){
                  button=new MyButton(this,i,j);
                  button.setId(idNum);
                  idNum++;
                  button.setBackground(getDrawable(R.drawable.my_button));
                  button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyButton myButton=(MyButton)view;
                    myButton.setText(tempLetter);
                    if (tempLetter==null)
                    Toast.makeText(GamePage.this, "not ok", Toast.LENGTH_LONG).show();
                }
            });
                tableRow.addView(button);
                buttonList.add(button);
        }
        }
        for (i=0;i<10;i++){
            playerHand.addView(button=new MyButton(this,0,0));
            button.setBackground(getDrawable(R.drawable.my_button));
            button.setText(new Alef().getLett());
            button.setLetter(new Alef().getLett());
            button.setBackground(getDrawable(R.drawable.active_button_color));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyButton myButton=(MyButton)view;
                    tempLetter=myButton.getLetter();
                    Toast.makeText(GamePage.this, tempLetter, Toast.LENGTH_LONG).show();

                }
            });

        }
        for (i=0;i<10;i++){
            opponentHand.addView(button=new MyButton(this));
            button.setBackground(getDrawable(R.drawable.active_button_color));
        }

    }
}