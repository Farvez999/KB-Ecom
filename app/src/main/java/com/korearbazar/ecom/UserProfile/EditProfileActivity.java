package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.korearbazar.ecom.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setTitle("Edit_profile");
    }
}