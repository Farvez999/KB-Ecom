package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.korearbazar.ecom.R;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        setTitle("Reset Password");
    }
}