package com.example.firebaseex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//4
public class MainActivity<mCallbackManager> extends AppCompatActivity {
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String EMAIL = "email";
    public static UserInfo user;
    public static String nameOfCollection = "Users";
    private EditText name;
    private Button googleButton;
    private EditText lastName;
    private EditText email;
    private StringBuilder text = new StringBuilder();
    private SharedPreferences.Editor editor;


    public static final int GOOGLE_SIGN_IN = 1;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private HashMap<String, String> texts = new HashMap();
    public Intent goToHomePage;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToHomePage=new Intent(this,User_profile.class);
         callbackManager = CallbackManager.Factory.create();
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
        googleButton = findViewById(R.id.google_button);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                googleOnClick();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("requestCode", "onActivityResult: "+requestCode);
        if (requestCode == GOOGLE_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
        //facebook login
      /*  else if(requestCode==64206)
        {
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
                // ...
            } else {
                Toast.makeText(this,"cant sign in",Toast.LENGTH_SHORT).show();
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
       */
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
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        final UserInfo user = new UserInfo(name.getText().toString(), lastName.getText().toString(), email.getText().toString(),0);
        goToHomePage=new Intent(this,HomePage.class);

         db.collection(nameOfCollection).document(user.getEmail()).set(user);
        db.collection(nameOfCollection).document(name.getText().toString()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "ok "+user.getEmail().toString(), Toast.LENGTH_LONG).show();
                goToHomePage.putExtra("name",user.getName().toString());
                goToHomePage.putExtra("last_name",user.getLastName().toString());
                goToHomePage.putExtra("email",user.getEmail().toString());

                startActivity(goToHomePage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "not ok", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void googleOnClick() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                GOOGLE_SIGN_IN);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            Toast.makeText(this,"null",Toast.LENGTH_LONG).show();
        }
        else{
        Toast.makeText(this,user.getEmail().toString(),Toast.LENGTH_SHORT).show();
          }
        goToHomePage=new Intent(this,HomePage.class);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            goToHomePage.putExtra("name",acct.getDisplayName());
            goToHomePage.putExtra("last_Name",acct.getFamilyName());
            goToHomePage.putExtra("email", acct.getEmail());
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }
    }
    private String[] getSelectedProviders() {
        ArrayList<String> selectedProviders = new ArrayList<>();
        selectedProviders.add(AuthUI.EMAIL_LINK_PROVIDER);
        return selectedProviders.toArray(new String[selectedProviders.size()]);
    }
}

