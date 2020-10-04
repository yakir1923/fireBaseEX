package com.example.firebaseex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.animation.ObjectAnimator;

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

        woodBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(woodBox);
                Toast.makeText(boxesPage.this,"נקנה בהצלחה",Toast.LENGTH_LONG).show();
            }
        });

        silverBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(silverBox);
                Toast.makeText(boxesPage.this,"נקנה בהצלחה",Toast.LENGTH_LONG).show();
            }
        });

        goldBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anima(goldBox);
                Toast.makeText(boxesPage.this,"נקנה בהצלחה",Toast.LENGTH_LONG).show();
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