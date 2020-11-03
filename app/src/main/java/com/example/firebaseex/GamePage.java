package com.example.firebaseex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.DiskLruCache;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
private ArrayList<MyButton> buttonList;
private ArrayList<Letter> letterArrayList;
 private ArrayList<Button> playerArrayList;
private TableRow playerHand;
private TableRow opponentHand;
private static String tempLetter;
private int buttonId;
private TextView timer;
private Button nextTurn;
private Letter letter;
private Boolean myTurn;
private Intent showActivity;
private int turns=0;
private String gameId;
private ArrayList<MyButton> wordButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        userDitale=getSharedPreferences("login",MODE_PRIVATE);
        Thread tg=new Thread(new Runnable() {
            @Override
            public void run() {
                int check=CheckWord("אבא");
            }
        });
      tg.start();

        myTurn=true;
        joinGame();


        //startGame("kE18TB2qTAsmpKc2dnGT");
        db = FirebaseFirestore.getInstance();
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

        playerName.setText(userDitale.getString("name",null));
        idNum=0;
       opponentName.setText(setUserName());
            timer = findViewById(R.id.timer_round);
            setTimer();
        nextTurn=findViewById(R.id.next_turn);
        nextTurn.setBackground(getDrawable(R.drawable.active_button_color));

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
        buttonList=new ArrayList<MyButton>();
        wordButton=new ArrayList<MyButton>();
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
                    //check location of button
                    Toast.makeText(getApplicationContext(),myButton.getX()+","+myButton.getY(),Toast.LENGTH_SHORT).show();
                    if (myButton.getLetter()!=null) {
                        //the player puts letters on the board
                        wordButton.remove(myButton);
                        tempLetter=myButton.getLetter();
                        myButton.setBackground(getDrawable(R.drawable.my_button));
                        myButton.setLetter(null);
                    }else{

                        for (Letter l:letterArrayList){
                            if (l.getLett()==tempLetter)
                            myButton.setBackgroundDrawable(getDrawable(l.getIcon()));
                            myButton.setLetter(tempLetter);
                            wordButton.add(myButton);
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
        //   playerArrayList.add(button);
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
                String s="";
                int firstLocation;
                ArrayList<MyButton> arrayList=new ArrayList<MyButton>();
                for (i=0;i<10;i++) {
                    for (MyButton mb : wordButton) {
                        if (mb.getY()==wordButton.get(i).getY()){
                            if (mb.getX()<wordButton.get(i).getX()){
                                s+=mb.getLetter();
                                CheckWord(s);
                            }
                        }
                    }
                }
            }
        });
    }

    private String setUserName() {
        if (userDitale.getString("name",null)=="yakir moses")
        {
            return "שני";
        }else{
           return "yakir moses";
        }
    }


   //טיימר
    public void setTimer(){
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
                    try {
                        if (turns<10) {
                            Thread.sleep(2000);
                            setTimer();
                            turns++;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }.start();

    }
    private void sendScore(){
        showActivity = new Intent(GamePage.this, winnerScreen.class);
        showActivity.putExtra("score",Integer.toString(playerCurrentPoints));
    }


    private void joinGame() {
        db.collection("games")
                .whereEqualTo("user2", "").limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult().size()==1){
                                DocumentSnapshot documentSnapshot= task.getResult().getDocuments().get(0);
                             //   Toast.makeText(getApplicationContext(),documentSnapshot.getId(),Toast.LENGTH_LONG).show();
                                Game game=documentSnapshot.toObject(Game.class);
                                if(!game.getUser1().equals(userDitale.getString("email",null))) {
                                    update(documentSnapshot.getId());
                                }else {
                                    startGame(documentSnapshot.getId());
                                }
                            }else{
                                creatGame();
                            }
                        } else {
                            Log.d("notJoined", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

public void creatGame(){
        final Game game=new Game(userDitale.getString("email",null),"","hello");
    db.collection("games")
            .add(game)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("sucsesDB", "DocumentSnapshot written with ID: " + documentReference.getId());
                startGame(documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("fDb", "Error adding document", e);
                }
            });

}
    public void  startGame(String gameId){
        final DocumentReference docRef = db.collection("games").document(gameId);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("result", "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.d("result", "Current data: " + snapshot.getData());
                    Toast.makeText(getApplicationContext(),snapshot.getString("data"),Toast.LENGTH_LONG).show();
                    if (snapshot.getString("user1").toString()!="yakir1923@gmail.com"){
                        playerName.setText("yakir moses");
                        opponentName.setText("shani moses");
                    }else {
                        playerName.setText("shani moses");
                        opponentName.setText("yakir moses");


                    }
                } else {
                    Log.d("result", "Current data: null");
                }
            }
        });

    }
    public void update(final String gameId){
        DocumentReference washingtonRef = db.collection("games").document(gameId);

// Set the "isCapital" field of the city 'DC'
        washingtonRef
                .update("user2", userDitale.getString("email",null))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("sucsesUpDate", "DocumentSnapshot successfully updated!");
                        startGame(gameId);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("fail", "Error updating document", e);
                    }
                });

    }
    //TODO find game id
    public String searchForGameId(){
        final String gameId="gameId";
  return gameId;
    }
    public int CheckWord(String str) {
        char c = str.charAt(0);
        ArrayList<InputStream> filesList = new ArrayList<>();
        InputStream input1 = getResources().openRawResource(R.raw.words1);
        InputStream input2 = getResources().openRawResource(R.raw.words2);
        InputStream input3 = getResources().openRawResource(R.raw.words3);
        InputStream input4 = getResources().openRawResource(R.raw.words4);
        InputStream input5 = getResources().openRawResource(R.raw.words5);
        InputStream input6 = getResources().openRawResource(R.raw.words6);
        filesList.add(input1);
        filesList.add(input2);
        filesList.add(input3);
        filesList.add(input4);
        filesList.add(input5);
        filesList.add(input6);
        Scanner scanner1;
        String line1;
        int val = 0;
        for (InputStream temp : filesList) {
            scanner1 = new Scanner(temp);
            while (scanner1.hasNext()) {
                line1 = scanner1.nextLine();
                if (line1.equals(str)) {
                    val = 1;
                }
            }
        }
        return val;
    }
//
//        if (c == 'א' || c == 'ב' || c == 'ג' || c == 'ד') {
//            InputStream input = getResources().openRawResource(R.raw.words1);
//            Scanner scan = new Scanner(input);
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line==str) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//
//        }
//        if (c == 'ה') {
//            InputStream input=getResources().openRawResource(R.raw.words2);
//            Scanner scan = new Scanner(input);
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line.equalsIgnoreCase(str.trim())) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//        if (c == 'ח' || c == 'ט' || c == 'י' || c == 'כ' || c == 'ל') {
//            InputStream input =getResources().openRawResource(R.raw.words3);
//            Scanner scan = new Scanner(input);
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line.equalsIgnoreCase(str.trim())) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//        else if (c == 'מ' || c == 'נ') {
//            InputStream input =getResources().openRawResource(R.raw.words4);
//            Scanner scan = new Scanner(input);
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line.equalsIgnoreCase(str.trim())) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//        if (c == 'ס' || c == 'ע' || c == 'פ' || c == 'צ' || c == 'ק' || c == 'ר' || c == 'ש' || c == 'ת') {
//            InputStream input =getResources().openRawResource(R.raw.words5);
//            Scanner scan = new Scanner(input);
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line.equalsIgnoreCase(str.trim())) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//        if (c == 'ו' || c == 'ז') {
//            InputStream input =getResources().openRawResource(R.raw.words6);
//            Scanner scan = new Scanner(input);
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line.equalsIgnoreCase(str.trim())) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//        return 0;
    }

