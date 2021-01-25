package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.WithdhowAdapter;
import com.korearbazar.ecom.model.WithdhowModel;
import com.korearbazar.ecom.model.WithdrawResponse;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WithdrawActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SessionManager sessionManager;
    WithdrawResponse withdrawResponsesData;
    Integer user_id;

    private RecyclerView wRecyclerView;
    private WithdhowAdapter wExampleAdapter;
    private ArrayList<WithdhowModel> wExampleList;

    private Spinner spinner;
    private EditText method,amount,acc_email,reference;
    private TextView Withdraw;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        setTitle("Withdraw");

        sessionManager = new SessionManager(this);
        progressBar = new ProgressBar(this);

        wRecyclerView = findViewById(R.id.wRecyclerView);
        wRecyclerView.setHasFixedSize(true);
        wRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        wExampleList = new ArrayList<>();

        WithdrawData();



        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        method =findViewById(R.id.Sp);
        amount = findViewById(R.id.wAmmount);
        acc_email = findViewById(R.id.eAEmail);
        reference = findViewById(R.id.wReference);
        Withdraw = findViewById(R.id.withdraw);

        Withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickWithdraw();
            }
        });
    }

//    public void ClickWithdraw(final String method, final String amount , final String acc_email){
//        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
//        String url = "http://ecom.hrventure.xyz/api/user/affilate/withdraw/create"; // <----enter your post url here
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        } ){
//            protected Map<String, String> getParams() {
//                Map<String, String> MyData = new HashMap<String, String>();
//                MyData.put("methods", method);
//                MyData.put("amount", amount);
//                MyData.put("acc_email", acc_email);
//                return MyData;
//            }
//        };
//
//
//        MyRequestQueue.add(MyStringRequest);
//    }

    private void ClickWithdraw() {
//        final ProgressDialog progressBar = new ProgressDialog(WithdrawActivity.this);
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show();

        String url = "http://ecom.hrventure.xyz/api/user/affilate/withdraw/create";
//        final String phoneNumber = method.getText().toString();
//        final String amounts = amount.getText().toString();
//        final String acc_emails = acc_email.getText().toString();
        final String phoneNumber = "cash";
        final String amounts = "200";
        final String acc_emails = "abc@gmail.com";

        StringRequest requestPostResponsen = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String getAnswer = response.toString();
                if (getAnswer.equals("RegistrationSuccessful")) {
                    Toast.makeText(WithdrawActivity.this, "Okay" + response, Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WithdrawActivity.this, "Fail" + error.getStackTrace(), Toast.LENGTH_SHORT).show();
                Log.e("FAilED", String.valueOf(error.getLocalizedMessage()));
                //progressBar.dismiss();
            }
        }) {
            //To send our parametrs
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("customer_address",method);
                params.put("methods", phoneNumber);
                params.put("amount", amounts);
                params.put("acc_email", acc_emails);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestPostResponsen);
    }
//
////        final ProgressDialog progressDialog = new ProgressDialog(WithdrawActivity.this);
////        progressDialog.setCancelable(false); // set cancelable to false
////        progressDialog.setMessage("Please Wait"); // set message
////        progressDialog.show(); // show progress dialog
////
////        // Api is a class in which we define a method getClient() that returns the API Interface class object
////        // registration is a POST request type method in which we are sending our field's data
////        Api.getClient().withdraw(method.getText().toString().trim(),
////                amount.getText().toString().trim(),
////                acc_email.getText().toString().trim(),
////                reference.getText().toString().trim(),
////                "email", new Callback<WithdrawResponse>() {
////                    @Override
////                    public void success(SignUpResponse signUpResponse, retrofit.client.Response response) {
////                        // in this method we will get the response from API
////                        progressDialog.dismiss(); //dismiss progress dialog
////                        signUpResponsesData = signUpResponse;
////                        user_id = signUpResponse.getUserid();
////                        Toast.makeText(SignUpActivity.this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
////                        startActivity(intent);
////                    }
////
////                    @Override
////                    public void failure(RetrofitError error) {
////                        // if error occurs in network transaction then we can get the error in this method.
////                        Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_LONG).show();
////                        progressDialog.dismiss(); //dismiss progress dialog
////
////                    }
////                });
//
////        Api.getClient().withdraw(
////                method.getText().toString().trim(),
////                amount.getText().toString().trim(),
////                acc_email.getText().toString().trim(),
////                reference.getText().toString().trim(),
////                new retrofit.Callback<WithdrawResponse>() {
////                    @Override
////                    public void success(WithdrawResponse withdrawResponse, retrofit.client.Response response) {
////                        // in this method we will get the response from API
////                        progressDialog.dismiss(); //dismiss progress dialog
////                        withdrawResponsesData = withdrawResponse;
////                        user_id = withdrawResponse.getUserid();
////                        Toast.makeText(WithdrawActivity.this, withdrawResponse.getMessage(), Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(WithdrawActivity.this, ProfileActivity.class);
////                        startActivity(intent);
////                    }
////
////                    @Override
////                    public void failure(RetrofitError error) {
////                        // if error occurs in network transaction then we can get the error in this method.
////                        Toast.makeText(WithdrawActivity.this, error.toString(), Toast.LENGTH_LONG).show();
////                        progressDialog.dismiss(); //dismiss progress dialog
////
////                    }
////                });
//
//    }

    //AffilateWithdrawData
    private void WithdrawData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getAffilateWithdraw(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        affilateWithdrawData_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(WithdrawActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void affilateWithdrawData_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONArray jsonArray = obj.getJSONArray("withdraws");
            //Toast.makeText(this, "affiliate_sign"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String created_at = jsonObject.getString("created_at");
                String method = jsonObject.getString("method");
                String acc_email = jsonObject.getString("acc_email");
                String amount = jsonObject.getString("amount");
                String status = jsonObject.getString("status");

                wExampleList.add(new WithdhowModel(id,created_at,method,acc_email,amount,status));
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            wExampleAdapter = new WithdhowAdapter(WithdrawActivity.this, wExampleList);
            wRecyclerView.setAdapter(wExampleAdapter);



            JSONObject affiliate_sign=obj.getJSONObject("sign");
            //Toast.makeText(this, "affiliate_sign"+affiliate_sign, Toast.LENGTH_SHORT).show();

//        String affiliate_link = obj.getString("link");
//        String affiliate_html = obj.getString("html");
//        affilate_Link.setText(affiliate_link);
//        affilate_html.setText(affiliate_html);

        }catch (Exception e){

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}