package com.example.firebaseex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity<mCallbackManager> extends AppCompatActivity {
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String EMAIL = "email";
    public static UserInfo user;
    public static String nameOfCollection = "Users";
    private EditText name;
    private EditText password;
    private EditText email;
    private StringBuilder text = new StringBuilder();
    private SharedPreferences.Editor editor;
    private SharedPreferences userDitale;
    private FirebaseAuth mAuth;



    public static final int GOOGLE_SIGN_IN = 1;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private HashMap<String, String> texts = new HashMap();
    public Intent goToHomePage;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToHomePage=new Intent(this,User_profile.class);
        mAuth = FirebaseAuth.getInstance();
        userDitale=getSharedPreferences("login",MODE_PRIVATE);
        //TODO need
        //deleteSharedPreferences1();
       if (userDitale.getString("name",null)!=null&&
        userDitale.getString("email",null)!=null) {
            goToHomePage = new Intent(this, HomePage.class);

            startActivity(goToHomePage);
        }



       /* LoginButton  loginButton=findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                userFacebookID=loginResult.getAccessToken().getUserId();

            }

            @Override
            public void onCancel() {
                // App code
            }
            @Override
            public void onError(FacebookException exception) {
                // App code

            }
        });
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("ssssssss", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("error", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("error", "printHashKey()", e);
        }
        */
        Button button = findViewById(R.id.send_to_firebase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireBaseOnClick();
            }
        });
    }

    private void deleteSharedPreferences1() {
        editor=userDitale.edit();
        editor.clear();
        editor.apply();
    }

    public void addLetters() {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("text.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            int i = 0;
            while ((mLine = reader.readLine()) != null) {

                texts.put(mLine.split(":")[0], mLine.split(":")[1]);


            }
            db.collection("translation").document("translation").set(texts);
            db.collection("translation").document("translation").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    HashMap<String, String> translation = new HashMap<>();
                    Log.i("TAG", "onComplete: " + task.getResult().get("test"));
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

    public void fireBaseOnClick() {
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        final UserInfo user = new UserInfo(name.getText().toString(), password.getText().toString(), email.getText().toString(),1,0,0);
        DocumentReference docRef = db.collection(nameOfCollection).document(user.getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String testDB= document.get("name").toString();
                        Log.i("result", "DocumentSnapshot data: " + document.getData());

                    } else {
                        Log.i("result", "No such document");
                    }
                } else {
                    Log.i("result", "get failed with ", task.getException());
                }
            }
        });

        userDitale=getSharedPreferences("login",MODE_PRIVATE);
        editor=userDitale.edit();
        editor.putString("name",user.getName());
        editor.putString("email",user.getEmail());
        editor.putString("password",user.getPassword());
        editor.putInt("level",user.getLevel());
        editor.putInt("userWin",user.getUserWin());
        editor.putInt("userLose",user.getUserLose());
        editor.putInt("userPoints",user.getPlayerPoints());
        editor.commit();
        editor.apply();
        Log.i("SharedPreferences", user.getEmail()+" "+user.getName()+" "+user.getPassword());
        goToHomePage=new Intent(this,HomePage.class);
         db.collection(nameOfCollection).document(user.getEmail()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "ok "+user.getEmail().toString(), Toast.LENGTH_LONG).show();
                goToHomePage.putExtra("name",user.getName().toString());
                goToHomePage.putExtra("password",user.getPassword().toString());
                goToHomePage.putExtra("email",user.getEmail().toString());

                startActivity(goToHomePage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "not ok", Toast.LENGTH_LONG).show();
            }
        });

       /*  db.collection(nameOfCollection).document(user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 if(task.isSuccessful())
                 {
                   DocumentSnapshot  d=task.getResult();
                   if (d.exists()){
                      UserInfo userTest= d.toObject(UserInfo.class);
                       Log.i("testD", userTest.getEmail());
                   }
                 }
             }
         });*/

    }


    private String[] getSelectedProviders() {
        ArrayList<String> selectedProviders = new ArrayList<>();
        selectedProviders.add(AuthUI.EMAIL_LINK_PROVIDER);
        return selectedProviders.toArray(new String[selectedProviders.size()]);
    }



}

