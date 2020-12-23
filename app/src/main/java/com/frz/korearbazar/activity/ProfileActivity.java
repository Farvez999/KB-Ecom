package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.frz.korearbazar.MainActivity;
import com.frz.korearbazar.R;
import com.frz.korearbazar.utils.SessionManager;

public class ProfileActivity extends AppCompatActivity {

//    WebView webView;
    TextView txt_save,ed_username,ed_email,ed_alternatmob,ed_address,ed_city,ed_zip;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");

        sessionManager = new SessionManager(this);

        //txt_save = findViewById(R.id.txt_save);
        ed_username = findViewById(R.id.ed_username);
        ed_email = findViewById(R.id.ed_email);
        ed_alternatmob = findViewById(R.id.ed_alternatmob);
        ed_city = findViewById(R.id.city);
        ed_zip = findViewById(R.id.zip);
        ed_address = findViewById(R.id.address);

        SharedPreferences prefs = getSharedPreferences("KOREAR_BAZAR", MODE_PRIVATE);
        String name=prefs.getString("name",null);
        String email=prefs.getString("email",null);
        String phone=prefs.getString("phone",null);
        String city=prefs.getString("city",null);
        String zip=prefs.getString("zip",null);
        String address=prefs.getString("address",null);
        ed_username.setText(name);
        ed_email.setText(email);
        ed_alternatmob.setText(phone);
        ed_city.setText(city);
        ed_zip.setText(zip);
        ed_address.setText(address);

//        String userName = "Hello "+ sessionManager.getUser().getName();
//        ed_username.setText(userName);


//        txt_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
//            }
//        });

//        webView = (WebView) findViewById(R.id.webview);
//        webView.loadUrl("http://ecom.hrventure.xyz/user/dashboard");
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
//            this.webView.goBack();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
}