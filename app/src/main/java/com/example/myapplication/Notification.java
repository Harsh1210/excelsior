package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.google.firebase.database.Exclude;

public class Notification {

    String Id;
    String AppName;
    String Notifi;
    int flag = 1;

    public Notification(String Id, String AppName, String Notifi) {
        this.AppName = AppName;
        this.Id = Id;
        this.Notifi = Notifi;
    }

    @Exclude
    public String getAppName() {
        return AppName;
    }

    @Exclude
    public String getNotifi() {
        return Notifi;
    }

    @Exclude
    public String getId(){ return Id; }
    public Notification(){}
}
