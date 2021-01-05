package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.MainActivity;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.BigSavePAdapter;
import com.frz.korearbazar.model.ProdDetailsModel;
import com.frz.korearbazar.model.ProdModel;
import com.frz.korearbazar.model.User;
import com.frz.korearbazar.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.frz.korearbazar.ApiInterface.JSONURL;
import static com.frz.korearbazar.ApiInterface.ProdDetailsUrl;

public class ProfileActivity extends AppCompatActivity {

    private ArrayList<User> mExampleList;

    TextView txt_save,ed_username,ed_email,ed_alternatmob,ed_address,ed_city,ed_zip,affiliateBonus;
    SessionManager sessionManager;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");

        sessionManager = new SessionManager(this);
//        Log.e("token",sessionManager.getToken());
//        Toast.makeText(this, ""+sessionManager.getToken(), Toast.LENGTH_SHORT).show();

        mRequestQueue = Volley.newRequestQueue(this);
        getdata();

        //txt_save = findViewById(R.id.txt_save);
        ed_username = findViewById(R.id.ed_username);
        ed_email = findViewById(R.id.ed_email);
        ed_alternatmob = findViewById(R.id.ed_alternatmob);
        ed_city = findViewById(R.id.city);
        ed_zip = findViewById(R.id.zip);
        ed_address = findViewById(R.id.address);
        affiliateBonus = findViewById(R.id.affiliateBonus);
    }

    //Big Save Products
    private void getdata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getProfile(sessionManager.getToken());
        Toast.makeText(this, "Check session Manager"+sessionManager.getToken(), Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        BS_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void BS_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> bsModelRecyclerArrayList = new ArrayList<>();
            JSONObject jsonObject=obj.getJSONObject("user");

            Toast.makeText(this, "user "+jsonObject, Toast.LENGTH_SHORT).show();


            String name = jsonObject.getString("name");
            String email=jsonObject.getString("email");
            String phone=jsonObject.getString("phone");
            String city = jsonObject.getString("city");
            String zip = jsonObject.getString("zip");
            String address=jsonObject.getString("address");
            String affiliate_bonus = jsonObject.getString("affilate_income");

            Float litersOfPetrol=Float.parseFloat(affiliate_bonus);
            DecimalFormat df = new DecimalFormat("0.00");
            df.setMaximumFractionDigits(2);


            //Toast.makeText(this, "Last Check"+name, Toast.LENGTH_SHORT).show();
            ed_username.setText(name);
            ed_email.setText(email);
            ed_alternatmob.setText(phone);
            ed_city.setText(city);
            ed_zip.setText(zip);
            ed_address.setText(address);
            affiliateBonus.setText(df.format(litersOfPetrol));



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

//    private void getdata() {
//        String url = "http://ecom.hrventure.xyz/api/profile";
//
//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,
//                new com.android.volley.Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONObject jsonObject = response.getJSONObject("user");
//                            Toast.makeText(ProfileActivity.this, "User "+jsonObject, Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mRequestQueue.add(request);
//    }
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