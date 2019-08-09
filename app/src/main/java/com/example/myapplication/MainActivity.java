package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import android.content.Intent;
import android.util.Log;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    Button b1,b2;
    EditText ed1,ed2;
    private static final String TAG = "MainActivity";
    private FirebaseAuth.AuthStateListener mAuthListner;

    TextView tx1;
    int counter = 3;
    private FirebaseAuth mAuth;
    SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState2) {
        super.onCreate(savedInstanceState2);
        setContentView(R.layout.activity_main);

        createNotificationChannels();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        b2 = (Button)findViewById(R.id.button2);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);
        if(sp.getBoolean("logged",true))
            goToSecondActivity();

        /*mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //User signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                    Toast.makeText(getApplicationContext(),
                            "Successfully signed in with: " + user.getUid(), Toast.LENGTH_LONG).show();
                } else {
                    //User signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out" );
                    Toast.makeText(getApplicationContext(),
                            "Successfully signed out", Toast.LENGTH_LONG).show();
                }
            }
        };*/

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String email = ed1.getText().toString();
                String password = ed2.getText().toString();
                if((!email.equals(" ")) && (!password.equals(" "))) {
                    mAuth.signInWithEmailAndPassword(email, password);
                    goToSecondActivity();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ERROR : EMPTY FIELD", Toast.LENGTH_LONG).show();
                }*/
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    goToSecondActivity();

                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                            tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpActivity();
            }
        });

    }
    public void goToSecondActivity(){
        Intent ganesh = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(ganesh);
        sp.edit().putBoolean("logged",true).apply();
    }
    public void goToSignUpActivity(){
        Intent in = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(in);
    }
   /* @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }
    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListner != null){
            mAuth.removeAuthStateListener(mAuthListner);
        }
    }*/
   private void createNotificationChannels() {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           NotificationChannel channel1 = new NotificationChannel(
                   CHANNEL_1_ID,
                   "Channel 1",
                   NotificationManager.IMPORTANCE_HIGH
           );
           channel1.setDescription("This is Channel 1");
           Log.d(TAG, "Channel 1 created");

           NotificationChannel channel2 = new NotificationChannel(
                   CHANNEL_2_ID,
                   "Channel 2",
                   NotificationManager.IMPORTANCE_LOW
           );
           channel2.setDescription("This is Channel 2");
           Log.d(TAG, "Channel 2 created");

           NotificationManager manager = getSystemService(NotificationManager.class);
           manager.createNotificationChannel(channel1);
           manager.createNotificationChannel(channel2);
       }
   }
}
