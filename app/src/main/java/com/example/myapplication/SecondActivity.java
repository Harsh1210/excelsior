package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.android.gms.tasks.OnCompleteListener;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SecondActivity extends Activity {//implements Runnable {

    Button b3,b4,b5,b6,b7;
    ImageButton b8;
    SharedPreferences sp;
    String TAG;

    {
        TAG = "SecondActivity";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        b4 = (Button) findViewById(R.id.Group4_btn);//General Message
        b3 = (Button) findViewById(R.id.logout_btn);//Logout Button
        b5 = (Button) findViewById(R.id.group3_btn);//Priority 3
        b6 = (Button) findViewById(R.id.group1_btn);//Priority 1
        b7 = (Button) findViewById(R.id.group2_btn);//Priority 2
        b8 = (ImageButton) findViewById(R.id.imageButton);//New Notification/custom notification
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);


        /*FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {

                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(SecondActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }

        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChatActivity();
            }

        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customNotification();
            }

        });


    }
    public void goToMainActivity(){
            Intent g = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(g);
            sp.edit().putBoolean("logged",false).apply();
        }
    public void goToChatActivity(){
        Intent i = new Intent(SecondActivity.this, chatactivity.class);
        startActivity(i);
    }

    public void customNotification(){
        Intent m = new Intent(SecondActivity.this, custom_notification.class);
        startActivity(m);
    }

    /*Context con =SecondActivity.this.getApplicationContext();
    MessageAdapter messageAdapter = new MessageAdapter(con);
    final ObjectMapper mapper = new ObjectMapper();
    boolean belongsToCurrentUser =false;
    final MemberData data = new MemberData("Harsh", "blue" );
    final Message message = new Message("Notification 1", data, belongsToCurrentUser);
    @Override
    public void run() {
        messageAdapter.add(message);
    }*/


}
