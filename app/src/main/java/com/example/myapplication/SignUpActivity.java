package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText emailed, passworded;
    String email, password;
    Button Signup;
    SharedPreferences sp;
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState2) {
        super.onCreate(savedInstanceState2);
        setContentView(R.layout.sign_up_laypout);

        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        Signup = (Button)findViewById(R.id.btn_Signup);
        emailed = (EditText)findViewById(R.id.editText3);
        passworded = (EditText)findViewById(R.id.editText4);
        mAuth = FirebaseAuth.getInstance();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailed.getText().toString();
                password = passworded.getText().toString();
                if((!email.equals(" ")) && (!password.equals(" "))) {
                    mAuth.createUserWithEmailAndPassword(email, password);
                    Toast.makeText(getApplicationContext(),
                            "User Created now sign in with the registered email and password", Toast.LENGTH_LONG).show();
                    goToMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ERROR : Empty Field", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void goToMainActivity(){
        Intent g = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(g);
        sp.edit().putBoolean("logged",false).apply();
    }
}

