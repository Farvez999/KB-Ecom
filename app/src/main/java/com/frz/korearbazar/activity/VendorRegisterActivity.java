package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frz.korearbazar.R;
import com.frz.korearbazar.model.SignUpResponse;
import com.frz.korearbazar.utils.SessionManager;

public class VendorRegisterActivity extends AppCompatActivity {

    SignUpResponse signUpResponsesData;
    EditText email, password, name ,phone , address, password_confirmation, shop_name, owner_name, shop_num, shop_address, register_num, message;
    TextView btSignIn,btnLogin;

    Integer user_id;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_register);

        // init the EditText and Button
        name = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        password = (EditText) findViewById(R.id.password);
        password_confirmation = (EditText) findViewById(R.id.confirmation_password);
        shop_name = (EditText) findViewById(R.id.shopName);
        owner_name = (EditText) findViewById(R.id.ownerName);
        shop_num = (EditText) findViewById(R.id.shopNumber);
        shop_address = (EditText) findViewById(R.id.shopAddress);
        register_num = (EditText) findViewById(R.id.registerNumber);
        message = (EditText) findViewById(R.id.message);

        sessionManager = new SessionManager(this);

        btSignIn = findViewById(R.id.vendorRegister);
        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorRegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendorRegister();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ecom.hrventure.xyz/user/dashboard"));
                startActivity(intent);
            }
        });
    }

    private void vendorRegister() {
        Toast.makeText(this, "Work is Ongoing", Toast.LENGTH_SHORT).show();
    }
}