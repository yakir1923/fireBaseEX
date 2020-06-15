package com.example.firebaseex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static String nameOfCollection="Users";
    private EditText name;
    private  EditText lastName;
    private EditText email;
    private StringBuilder text = new StringBuilder();
    private HashMap<String,String> texts=new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Button button=findViewById(R.id.send_to_firebase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name=findViewById(R.id.name);
                lastName=findViewById(R.id.last_name);
                email=findViewById(R.id.email);
                User user=new User(name.getText().toString(),lastName.getText().toString(),email.getText().toString());
              //  db.collection(nameOfCollection).document(user.getEmail()).set(user);
                db.collection(nameOfCollection).document(name.getText().toString()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"not ok",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });*/
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("text.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            int i=0;
            while ((mLine = reader.readLine()) != null) {

                texts.put(mLine.split(":")[0],mLine.split(":")[1]);


            }
            db.collection("translation").document("translation").set(texts);
            db.collection("translation").document("translation").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    HashMap<String,String> translation=new HashMap<>();
                    Log.i("TAG", "onComplete: "+task.getResult().get("test"));
                }
            });
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }

            }
        }
    }
}
