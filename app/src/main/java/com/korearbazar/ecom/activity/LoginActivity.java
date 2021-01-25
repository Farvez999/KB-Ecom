package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.korearbazar.ecom.Api;
import com.korearbazar.ecom.MainActivity;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.SignInResponse;
import com.korearbazar.ecom.model.TokenResponse;
import com.korearbazar.ecom.model.User;
import com.korearbazar.ecom.utils.SessionManager;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    TokenResponse tokenResponse;
    SignInResponse signInResponse;
    EditText email;
    EditText password;
    TextView Login,btn_sign,btn_vendorR;
    Integer user_id;
    SessionManager sessionManager;

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOG IN");

        sessionManager = new SessionManager(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        Login = (TextView) findViewById(R.id.Login);
        btn_sign = (TextView) findViewById(R.id.btn_sign);
        //btn_vendorR = (TextView) findViewById(R.id.btn_vendorR);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// validate the fields and call sign method to implement the api
                if (validate(email) && validate(password)) {
                    logIn();
                }
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        //Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();

//        btn_vendorR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, VendorRegisterActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void logIn() {

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        Api.getClient().login(
                email.getText().toString().trim(),
                password.getText().toString().trim(),
                "email", new Callback<SignInResponse>(){
                    @Override
                    public void success(SignInResponse signInResponse, Response response) {
                        progressDialog.dismiss(); //dismiss progress dialog
                        signInResponse = signInResponse;
                        user_id = signInResponse.getUserid();
                        sessionManager.saveUser(signInResponse.getUser());
                        sessionManager.setToken("Bearer "+signInResponse.getToken());
                        //Toast.makeText(LoginActivity.this, ""+signInResponse.getToken(), Toast.LENGTH_SHORT).show();
                        //Log.e("login",""+signInResponse.toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("fgcfgdhf", error.getBody().toString());
                        progressDialog.dismiss(); //dismiss progress dialog

                    }
                });

//        Api.getClient().getToken(
//                email.getText().toString().trim(),
//                password.getText().toString().trim(),
//                "email", new Callback<TokenResponse>() {
//                    @Override
//                    public void success(TokenResponse tokenResponse, Response response) {
//                        progressDialog.dismiss(); //dismiss progress dialog
//                        Gson gson = new Gson();
//                        TokenResponse response1 = gson.fromJson(t.toString(), TokenResponse.class);
//                        Toast.makeText(LoginActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        // if error occurs in network transaction then we can get the error in this method.
//                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss(); //dismiss progress dialog
//
//                    }
//                });
    }

    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returs true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sessionManager.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }
}
