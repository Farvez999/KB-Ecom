package com.korearbazar.ecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        int SPLASH_TIME_OUT = 2000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(FirstActivity.this,MainActivity.class));
                finish();
            }
        },SPLASH_TIME_OUT);


    }
}