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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.DiskLruCache;

import java.io.InputStream;
import java.lang.reflect.Array;
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

import static java.util.Collections.checkedMap;
import static java.util.Collections.swap;

public class GamePage extends AppCompatActivity {
    private MyButton button;
    private int i, j;
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
    private ArrayList<MyButton> playerArrayList;
    private TableRow playerHand;
    private TableRow opponentHand;
    private static String tempLetter;
    private int buttonId;
    private TextView timer;
    private Button nextTurn;
    private Letter letter;
    private static Boolean myTurn;
    private Intent showActivity;
    private int turns = 0;
    private static String gameId;
    private ArrayList<MyButton> wordButton;
    private static String s = "";
    private int score=0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        userDitale = getSharedPreferences("login", MODE_PRIVATE);
        editor = userDitale.edit();


        joinGame();
        myTurn=false;
        userDitale = getSharedPreferences("login", MODE_PRIVATE);
       gameId=userDitale.getString("game_id",null);
       // gameId= getSharedPreferences("game_id", "");

        db = FirebaseFirestore.getInstance();
        tableLayout = findViewById(R.id.game_layout);
        playerPoints = findViewById(R.id.player_points);
        playerName = findViewById(R.id.player_name);
        opponentName = findViewById(R.id.opponent_name);
        opponentPoints = findViewById(R.id.opponent_points);
        playerHand = findViewById(R.id.player_hand);
        opponentHand = findViewById(R.id.opponent_hand);
        playerCurrentPoints = 0;
        opponentCurrentPoints = 0;
        playerPoints.setText(String.valueOf(score));
        opponentPoints.setText("0");
        idNum = 0;
        timer = findViewById(R.id.timer_round);
        setTimer();
        nextTurn = findViewById(R.id.next_turn);
        nextTurn.setBackground(getDrawable(R.drawable.active_button_color));
        playerName.setText(userDitale.getString("name", null));


        //יצירת רשימה של אותיות
        letterArrayList = new ArrayList<Letter>();
        letterArrayList.add(letter = new Letter("א", 10, R.drawable.alef));
        letterArrayList.add(letter = new Letter("ב", 10, R.drawable.beit));
        letterArrayList.add(letter = new Letter("ג", 20, R.drawable.gimel));
        letterArrayList.add(letter = new Letter("ד", 30, R.drawable.daled));
        letterArrayList.add(letter = new Letter("ה", 10, R.drawable.hei));
        letterArrayList.add(letter = new Letter("ו", 5, R.drawable.vav));
        letterArrayList.add(letter = new Letter("ז", 80, R.drawable.zain));
        letterArrayList.add(letter = new Letter("ח", 30, R.drawable.chet));
        letterArrayList.add(letter = new Letter("ט", 40, R.drawable.tet));
        letterArrayList.add(letter = new Letter("י", 30, R.drawable.yud));
        letterArrayList.add(letter = new Letter("כ", 70, R.drawable.chaf));
        letterArrayList.add(letter = new Letter("ל", 40, R.drawable.lamed));
        letterArrayList.add(letter = new Letter("מ", 60, R.drawable.mem));
        letterArrayList.add(letter = new Letter("נ", 30, R.drawable.non));
        letterArrayList.add(letter = new Letter("ס", 50, R.drawable.samech));
        letterArrayList.add(letter = new Letter("ע", 80, R.drawable.aein));
        letterArrayList.add(letter = new Letter("פ", 90, R.drawable.pey));
        letterArrayList.add(letter = new Letter("צ", 100, R.drawable.tzadik));
        letterArrayList.add(letter = new Letter("ק", 60, R.drawable.kof));
        letterArrayList.add(letter = new Letter("ר", 40, R.drawable.resha));
        letterArrayList.add(letter = new Letter("ש", 50, R.drawable.shin));
        letterArrayList.add(letter = new Letter("ת", 40, R.drawable.taf));


        playerArrayList = new ArrayList<MyButton>();

        //יצירת מסך וגם שמירה של כל הכפתורים ברשימה
        buttonList = new ArrayList<MyButton>();
        wordButton = new ArrayList<MyButton>();

        for (i = 0; i < 9; i++) {
            tableRow = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tableLayout.addView(tableRow);
            tableRow.setGravity(Gravity.CENTER);
            tableRow.setLayoutParams(params);
            for (j = 0; j < 9; j++) {
                button = new MyButton(this, i, j);
                button.setId(idNum);
                idNum++;
                button.setBackground(getDrawable(R.drawable.my_button));
              //  button.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyButton myButton = (MyButton) view;
                        if (!myButton.getSetted()) {
                            //check location of button
                           // Toast.makeText(getApplicationContext(), myButton.getX() + "," + myButton.getY(), Toast.LENGTH_SHORT).show();
                            if (myButton.getLetter() !=null) {
                                //the player puts letters on the board
                                wordButton.remove(myButton);
                                tempLetter = myButton.getLetter();
                                myButton.setBackground(getDrawable(R.drawable.my_button));
                                myButton.setLetter(null);

                            } else {

                                for (Letter l : letterArrayList) {
                                    if (l.getLett() == tempLetter)
                                        myButton.setBackgroundDrawable(getDrawable(l.getIcon()));
                                    myButton.setLetter(tempLetter);
                                    TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    myButton.setLayoutParams(buttonParams);
                                }
                                //       לעשות רשימה של האותיות שנכנסות לבדוק מיקום שהן נכנסו ולהכניס לפי הסדר לרשימה

                                //     s+=tempLetter;
                                wordButton.add(myButton);
                                tempLetter =null;

                            }
                        }
                    }
                });
                tableRow.addView(button);
                buttonList.add(button);
            }

            //יצרה של היד של השחקן
        }

        getPlayerCards();

        //TODO
        nextTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkC();
                getAWord();
                CheckWord(s);

                // Toast.makeText(GamePage.this, s+"", Toast.LENGTH_SHORT).show();
                for (MyButton myButton:wordButton){
                    myButton.setSetted(true);
                    for (Letter letter:letterArrayList){
                        if (myButton.getLetter()==letter.getLett()){
                            score+=letter.getScoring();
                        }
                    }
                }
                String a="";
                for (MyButton button:buttonList)
                    if (button.getLetter()!=null)
                        a+=button.getLetter();
                    else {
                        a+=" ";
                    }
                chh(a);
                playerPoints.setText(String.valueOf(score));
                wordButton.removeAll(wordButton);
                s="";


               if(!myTurn) {
                   for (MyButton b : playerArrayList) {
                       b.setEnabled(false);
                   }
               }
                resetPlayerHand();
                tempLetter=null;
            }

        });


    }

    public void getPlayerCards() {
        for (i = 0; i < 10; i++) {
            Random random = new Random();
            int rndNum = random.nextInt(22);
            playerHand.addView(button = new MyButton(this, 0, 0));
            button.setBackground(getDrawable(R.drawable.my_button));
            //button.setText(letterArrayList.get(rndNum).getLett());
            button.setBackgroundDrawable(getDrawable(letterArrayList.get(rndNum).getIcon()));
            button.setLetter(letterArrayList.get(rndNum).getLett());
            TableRow.LayoutParams buttonParamsPlayer = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            button.setLayoutParams(buttonParamsPlayer);
            //   playerArrayList.add(button);
            //  button.setBackground(getDrawable(R.drawable.active_button_color));

            playerArrayList.add(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (myTurn) {
                        MyButton myButton = (MyButton) view;


                        if (myButton.getLetter() != null) {
                            tempLetter = myButton.getLetter();
                            myButton.setLetter(null);
                            myButton.setBackground(getDrawable(R.drawable.my_button));
                        } else {
                            for (Letter l : letterArrayList) {
                                if (l.getLett() == tempLetter) {
                                    myButton.setBackgroundDrawable(getDrawable(l.getIcon()));
                                    myButton.setBackground(getDrawable(l.getIcon()));
                                    myButton.setLetter(tempLetter);
                                    TableRow.LayoutParams buttonParamsPlayer = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    myButton.setLayoutParams(buttonParamsPlayer);
                                }
                            }
                            tempLetter = null;
                        }
                    }
                }
            });


        }

    }

    public void chh(String a){
        DocumentReference DR=db.collection("games").document(userDitale.getString("game_id",null));
        db.collection("games").document(userDitale.getString("game_id",null)).update("data2",a).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("fail", "onFailure:"+e.getMessage() );
            }
        });
        db.collection("games").document(userDitale.getString("game_id",null)).update("user1turn",!myTurn);
        db.collection("games").document(userDitale.getString("game_id",null)).update("opponentPoints",score);
    }


    //טיימר
    public void setTimer() {
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
                try {
                    if (turns < 10) {
                        Thread.sleep(2000);
                        setTimer();
                        turns++;
                        db.collection("games").document(userDitale.getString("game_id",null)).update("user1turn",!myTurn);
                        resetPlayerHand();
                        tempLetter=null;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    public void resetPlayerHand(){
        for (MyButton b:playerArrayList){
            Random random = new Random();
            int rndNum = random.nextInt(22);
            if (b.getLetter()==null) {
                b.setBackground(getDrawable(R.drawable.my_button));
                b.setBackgroundDrawable(getDrawable(letterArrayList.get(rndNum).getIcon()));
                b.setLetter(letterArrayList.get(rndNum).getLett());
                TableRow.LayoutParams buttonParamsPlayer = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                b.setLayoutParams(buttonParamsPlayer);
            }
        }
    }

    private void sendScore() {
        showActivity = new Intent(GamePage.this, winnerScreen.class);
        showActivity.putExtra("score", Integer.toString(playerCurrentPoints));
    }


    private void joinGame() {
        db.collection("games")
                .whereEqualTo("user2", "").limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() == 1) {
                                DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                              //   Toast.makeText(getApplicationContext(),documentSnapshot.getId(),Toast.LENGTH_LONG).show();
                                editor.putString("game_id", documentSnapshot.getId());
                                editor.commit();
                                editor.apply();
                                Game game = documentSnapshot.toObject(Game.class);

                                if (!game.getUser1().equals(userDitale.getString("email", null))) {
                                    update(documentSnapshot.getId());
                                } else {
                                    startGame(documentSnapshot.getId());
                                }
                            } else {
                                creatGame();
                            }
                        } else {
                            Log.d("notJoined", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void creatGame() {
        final Game game = new Game(userDitale.getString("email", null), "", "hello");
        db.collection("games")
                .add(game)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("sucsesDB", "DocumentSnapshot written with ID: " + documentReference.getId());
                        editor.putString("game_id", documentReference.getId());
                        editor.commit();
                        editor.apply();
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

    public void startGame(final String gameId) {

        final DocumentReference docRef = db.collection("games").document(gameId);
        editor.putString("game_id", gameId);
        editor.commit();
        editor.apply();
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable final DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("result", "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.d("result", "Current data: " + snapshot.getData());
                   // Toast.makeText(getApplicationContext(), snapshot.getString("data"), Toast.LENGTH_LONG).show();
                    if (!snapshot.getString("user1").equals(userDitale.getString("email", null))) {
                        opponentName.setText(snapshot.getString("user1Name").toString());
                       myTurn=snapshot.getBoolean("user1turn");
                       String user2name=userDitale.getString("name",null);
                        db.collection("games").document(userDitale.getString("game_id",null)).update("user2Name",userDitale.getString("name",null));
//                       opponentCurrentPoints=Integer.parseInt(snapshot.getString("opponentPoints"));
                    } else {
                        opponentName.setText(snapshot.getString("user2Name").toString());
                        myTurn=snapshot.getBoolean("user1turn");
                        db.collection("games").document(userDitale.getString("game_id",null)).update("user1Name",userDitale.getString("name",null));
                    }
                    String data2 = (String) snapshot.get("data2");
                    if(data2!=null)
                    {
                        Log.d("length_data2", "onEvent: "+data2.length()+" "+data2);
                     //  getRESS(data2);
                        getRESS2(data2);
                        int x;
                    }
                } else {
                    Log.d("result", "Current data: null");
                }
//55
            }
        });
    }

    public void getRESS2(String data2){
        //Toast.makeText(getApplicationContext(),data2,Toast.LENGTH_LONG).show();
        char[] c=data2.toCharArray() ;
                String ch = "";
                MyButton button ;
        for (int a=0;a<80;a++){
            ch = String.valueOf(c[a]);
            button =buttonList.get(a);
         //   buttonList.get(a).setLetter(String.valueOf(c[a]));
            if (!ch.equalsIgnoreCase(" "))
              button.setLetter(ch);
       //     if (!(String.valueOf(c[a]).equalsIgnoreCase(" "))){
            if(!ch.equalsIgnoreCase(" ")){
                for (Letter l:letterArrayList){
                    if (ch.equalsIgnoreCase(l.getLett())){
                        buttonList.get(a).setBackground(getDrawable(l.getIcon()));
                        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        buttonList.get(a).setLayoutParams(buttonParams);
                        buttonList.get(a).setSetted(true);
                    }
                }
          //  }
                //aa
            }
        }
    }

    public void  getRESS(ArrayList<String> data2){
        Toast.makeText(getApplicationContext(),data2.get(data2.size()-1),Toast.LENGTH_LONG).show();
        String[] res = data2.get(data2.size()-1).split("\\$");
        if(!userDitale.getString("email",null).equalsIgnoreCase(res[0]))
        {
            String letters = res[4];
            ArrayList<MyButton> newButtonList=new ArrayList<MyButton>();
            Toast.makeText(getApplicationContext(),res[4],Toast.LENGTH_LONG).show();
            int row=Integer.parseInt(res[1]);
            int col=Integer.parseInt(res[2]);
            for (int i=0;i<letters.length();i++) {
                String s1 = letters.charAt(i) + "";
                int pos = row * 9 + col;
            MyButton button =  buttonList.get(pos);
            button.setLetter(s1);
                for (Letter l : letterArrayList) {
                    if (l.getLett().equalsIgnoreCase(button.getLetter())) {
                        button.setBackground(getDrawable(l.getIcon()));
                        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        button.setLayoutParams(buttonParams);
                    }
                }
                if (res[3].equalsIgnoreCase("R")) {
                    col--;
                } else {
                    row++;
                }
            }
        }
    }

    public void update(final String gameId) {
        DocumentReference washingtonRef = db.collection("games").document(gameId);
        editor.putString("game_id", gameId);
        editor.commit();
        editor.apply();
        // Set the "isCapital" field of the city 'DC'
        washingtonRef
                .update("user2", userDitale.getString("email", null))
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

    public void CheckWord(final String str) {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                final DocumentReference docRefW = db.collection("translation").document(s);


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
                test(val);
            }
        });

    }
    public void test(int val){
        Toast.makeText(getApplicationContext(),String.valueOf(val),Toast.LENGTH_LONG).show();
    }

    public void getAWord() {


        try {
            ArrayList<MyButton> arrayList = new ArrayList();
            if (wordButton.get(0).getX()==wordButton.get(1).getX()) {
                boolean flag = true;
                scanLine('y',(int)wordButton.get(0).getX());
                for (int i = 0; i < wordButton.size() && flag; i++) {
                    flag = false;
                    for (int j = 0; j < wordButton.size() - i - 1; j++) {
                        if (wordButton.get(j).getY() < wordButton.get(j + 1).getY()) {
                            flag = true;
                            swap(wordButton, j, j + 1);
                        }
                    }
                }

            }else {
                boolean flag = true;
                scanLine('x',(int)wordButton.get(0).getY());
                for (int i = 0; i < wordButton.size() && flag; i++) {
                    flag = false;
                    for (int j = 0; j < wordButton.size() - i - 1; j++) {
                        if (wordButton.get(j).getX() > wordButton.get(j + 1).getX()) {
                            flag = true;
                            swap(wordButton, j, j + 1);
                        }
                    }
                }

            }

        }catch (Exception e){
            Log.e("eror_thread", "getAWord: "+e.getMessage());
        }
        for (MyButton mb:wordButton){
            s=s+mb.getLetter();
        }
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }



    private void scanLine(char charXOrY,int cordinateEq) {
        if (charXOrY=='x') {
            for (MyButton button : buttonList) {
                if (button.getY()==cordinateEq&&button.getLetter()!=null){
                    if (button.getSetted()) {
                        wordButton.add(button);
                    }
                }
            }
        } else{
                for (MyButton button : buttonList) {
                    if (button.getX()==cordinateEq&&button.getLetter()!=null){
                        if (button.getSetted()) {
                            wordButton.add(button);
                        }
                    }
                }
        }
    }
}
