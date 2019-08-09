package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class custom_notification extends AppCompatActivity {

    EditText editTextAppname;
    EditText editTextNotification;
    Button buttonAdd;

    DatabaseReference databaseNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_notification);

        editTextAppname = (EditText) findViewById(R.id.editText6);
        editTextNotification = (EditText) findViewById(R.id.editText7);

        databaseNotification = FirebaseDatabase.getInstance().getReference("Notification");

        buttonAdd = (Button) findViewById(R.id.Send_noti); //Notification button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });
    }

    public void addNotification(){
        String Appname = editTextAppname.getText().toString().trim();
        String Notiname = editTextNotification.getText().toString().trim();
        if(!TextUtils.isEmpty(Appname)){
            String id = databaseNotification.push().getKey();
            Notification notification = new Notification(id, Appname ,Notiname);
            databaseNotification.child(id).setValue(notification);
            Toast.makeText(this, "Notification Added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "You should enter an App name", Toast.LENGTH_LONG).show();
        }
    }
}
