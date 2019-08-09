package com.example.myapplication;

import android.app.IntentService;
import android.content.Intent;

public class Notification_Background_Refresh extends IntentService {

    public Notification_Background_Refresh(){
        super("Notification Refresh");
    }
    @Override
    protected void onHandleIntent (Intent intent){ //Background Refresh function
        //Refresh fire base notification table
    }
}
