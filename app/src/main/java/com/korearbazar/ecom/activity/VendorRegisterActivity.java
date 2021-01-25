package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.korearbazar.ecom.Api;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.VendorUpResponse;
import com.korearbazar.ecom.utils.SessionManager;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class VendorRegisterActivity extends AppCompatActivity {

    VendorUpResponse vendorUpResponsesData;
    EditText email, password, name ,phone , address, password_confirmation, shop_name, owner_name, shop_number, shop_address, reg_number, shop_message;
    TextView btSignIn,btnLogin;
    String vendor = "1";

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
        shop_number = (EditText) findViewById(R.id.shopNumber);
        shop_address = (EditText) findViewById(R.id.shopAddress);
        reg_number = (EditText) findViewById(R.id.registerNumber);
        shop_message = (EditText) findViewById(R.id.message);

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
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ecom.hrventure.xyz/user/dashboard"));
                //startActivity(intent);
            }
        });
    }

    private void vendorRegister(){
    // display a progress dialog
    final ProgressDialog progressDialog = new ProgressDialog(VendorRegisterActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog


        Api.getClient().vendorRegistration(
                name.getText().toString().trim(),
                email.getText().toString().trim(),
                phone.getText().toString().trim(),
                address.getText().toString().trim(),
                password.getText().toString().trim(),
                password_confirmation.getText().toString().trim(),
                shop_name.getText().toString().trim(),
                owner_name.getText().toString().trim(),
                shop_number.getText().toString().trim(),
                shop_address.getText().toString().trim(),
                reg_number.getText().toString().trim(),
                shop_message.getText().toString().trim(),
                vendor,
                "email", new Callback<VendorUpResponse>() {
        @Override
        public void success(VendorUpResponse vendorUpResponse, Response response) {
            // in this method we will get the response from API
            progressDialog.dismiss(); //dismiss progress dialog
            vendorUpResponsesData = vendorUpResponse;
            user_id = vendorUpResponse.getUserid();
            Toast.makeText(VendorRegisterActivity.this, vendorUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(VendorRegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        @Override
        public void failure(RetrofitError error) {
            // if error occurs in network transaction then we can get the error in this method.
            Toast.makeText(VendorRegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            progressDialog.dismiss(); //dismiss progress dialog

        }
    });

}
}