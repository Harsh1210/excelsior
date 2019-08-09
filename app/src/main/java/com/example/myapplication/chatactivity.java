package com.example.myapplication;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.app.NotificationManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import static com.example.myapplication.CreateChannel.CHANNEL_1_ID;
import static com.example.myapplication.CreateChannel.CHANNEL_2_ID;


public class chatactivity extends AppCompatActivity {
    ListView listViewNotification;
    DatabaseReference databaseNotification;
    List<Notification> notificationList;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatactivity_layout);

    listViewNotification=(ListView) findViewById(R.id.messages_view);
    databaseNotification = FirebaseDatabase.getInstance().getReference("Notification");
    notificationList = new ArrayList<>();
    notificationManager = NotificationManagerCompat.from(this);
    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseNotification.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                notificationList.clear();
                for(DataSnapshot notiSnapshot : dataSnapshot.getChildren()){
                    Notification notification = notiSnapshot.getValue(Notification.class);
                    notificationList.add(notification);
                }
                NotificationList adapter = new NotificationList(chatactivity.this, notificationList);
                listViewNotification.setAdapter(adapter);
                //sendOnChannel1();
            }

            @Override
            public void onCancelled(DatabaseError databaseerror){

            }
        });
    }
   /* public void sendOnChannel1(){

        String s = "Notification";

        android.app.Notification notification = new NotificationCompat.Builder( this, CHANNEL_1_ID )
                .setContentTitle(s)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_STATUS)
                .build();
        notificationManager.notify(1, notification);

    }*/
}
