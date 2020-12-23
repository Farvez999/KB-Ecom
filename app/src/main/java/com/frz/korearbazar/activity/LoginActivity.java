package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frz.korearbazar.Api;
import com.frz.korearbazar.MainActivity;
import com.frz.korearbazar.R;
import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.model.SignInResponse;
import com.frz.korearbazar.model.SignUpResponse;
import com.frz.korearbazar.model.TokenResponse;
import com.frz.korearbazar.model.User;
import com.frz.korearbazar.utils.SessionManager;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    TokenResponse tokenResponse;
    SignInResponse signInResponse;
    EditText email;
    EditText password;
    TextView Login,btn_sign;
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
    }

    private void logIn() {

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        Api.getClient().login(
                email.getText().toString().trim(),
                password.getText().toString().trim(),
                "email", new Callback<SignInResponse>() {
                    @Override
                    public void success(SignInResponse signInResponse, Response response) {
                        // in this method we will get the response from API
                        progressDialog.dismiss(); //dismiss progress dialog
                        signInResponse = signInResponse;
                        user_id = signInResponse.getUserid();
                        sessionManager.saveUser(signInResponse.getUser());
                        Toast.makeText(LoginActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        SharedPreferences.Editor editor = getSharedPreferences("USER_LOGIN", MODE_PRIVATE).edit();
//                        editor.putString("email",user.getEmail());
//                        editor.putString("name",user.getName());
//                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
//                        if (signInResponse.equals("200")){
//                            sessionManager.saveUser(signInResponse.getUser());
//                            // display the message getting from web api
//                            Toast.makeText(LoginActivity.this, signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
