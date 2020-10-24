package com.example.firebaseex;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class boxesPage extends AppCompatActivity {

    private Button backHomeButton;
    private Intent goTOHome;
    private Button showCards;
    private Intent goTOCards;
    public Bundle bundle;
   public ObjectAnimator animation;
    private ImageButton woodBox;
    private ImageButton silverBox;
    private ImageButton goldBox;
    private ArrayList <cards> cardsArrayList;
    private cards card;
    private UserInfo user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxes_page);

        bundle=getIntent().getExtras();
        backHomeButton=findViewById(R.id.back_button);
        goTOHome=new Intent(this,HomePage.class);
        showCards=findViewById(R.id.show_cards);
        goTOCards=new Intent(this,cardPage.class);
        woodBox=findViewById(R.id.wood_box);
        silverBox=findViewById(R.id.silver_box);
        goldBox=findViewById(R.id.gold_box);


        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(boxesPage.this, "back",Toast.LENGTH_LONG).show();
                startActivity(goTOHome);
            }
        });

        showCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(boxesPage.this, "cards",Toast.LENGTH_LONG).show();
                startActivity(goTOCards);
            }
        });


        cardsArrayList=new ArrayList<cards>();
        cardsArrayList.add(card=new cards("Freeze_time",R.drawable.card1));
        cardsArrayList.add(card=new cards("Letter_theft",R.drawable.card2));
        cardsArrayList.add(card=new cards("Exchange_letters",R.drawable.card3));
        cardsArrayList.add(card=new cards("Stealing_time",R.drawable.card4));
        cardsArrayList.add(card=new cards("Doubling_the_score",R.drawable.card5));
        cardsArrayList.add(card=new cards("5_bonuses",R.drawable.card6));


        woodBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(woodBox);
                Toast.makeText(boxesPage.this,"כרטיס 1 התווסף",Toast.LENGTH_LONG).show();
                Random random = new Random();
                int rndNum = random.nextInt(6);
                card.setCardName(cardsArrayList.get(rndNum).getCardName());
                MainActivity.user.addCard(card);
            }
        });

        silverBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(silverBox);
                Toast.makeText(boxesPage.this,"שני כרטיסים התווספו",Toast.LENGTH_LONG).show();

                for (int i=0;i<2;i++) {
                    Random random = new Random();
                    int rndNum = random.nextInt(6);
                    card.setCardName(cardsArrayList.get(rndNum).getCardName());
                    MainActivity.user.addCard(card);
                }
            }
        });

        goldBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(goldBox);
                Toast.makeText(boxesPage.this,"ארבעה כרטיסים התווספו",Toast.LENGTH_LONG).show();
                for (int i=0;i<4;i++) {
                    Random random = new Random();
                    int rndNum = random.nextInt(6);
                    card.setCardName(cardsArrayList.get(rndNum).getCardName());
                    MainActivity.user.addCard(card);
                }
            }
        });




    }
    public void anima(ImageButton image){
        animation = ObjectAnimator.ofFloat(image,  "rotation", 0f, 10f,0f);
        animation.setDuration(100);
        animation.setRepeatCount(2);
        animation.start();
    }
}