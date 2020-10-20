package com.example.firebaseex;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GamePage extends AppCompatActivity {
private Button button;
private int i,j;
private TableLayout tableLayout;
private TableRow tableRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        tableLayout=findViewById(R.id.tableLayout);
        for (i=0;i<10;i++) {
            tableRow=new TableRow(this);
            tableLayout.addView(tableRow);
            for (j=1;j<11;j++){
                  button=new Button(this);
                  button.setId(i);
                  button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
                tableRow.addView(button);
        }
        }


    }
}