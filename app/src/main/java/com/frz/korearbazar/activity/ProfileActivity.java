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
import com.frz.korearbazar.model.ProdModel;
import com.frz.korearbazar.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.frz.korearbazar.ApiInterface.JSONURL;
import static com.frz.korearbazar.ApiInterface.ProdDetailsUrl;

public class ProfileActivity extends AppCompatActivity {

//    WebView webView;
    TextView txt_save,ed_username,ed_email,ed_alternatmob,ed_address,ed_city,ed_zip;
    SessionManager sessionManager;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");

        sessionManager = new SessionManager(this);
        Log.e("token",sessionManager.getToken());
        Toast.makeText(this, ""+sessionManager.getToken(), Toast.LENGTH_SHORT).show();

        mRequestQueue = Volley.newRequestQueue(this);
        getdata();

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

    //Big Save Products
    private void getdata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getProfile();
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

//            JSONObject obj = new JSONObject(jsonresponse);
//            Toast.makeText(this, "Check Obj"+obj, Toast.LENGTH_SHORT).show();

            ArrayList<ProdModel> bsModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("big_products");

            Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel bsModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                bsModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                bsModelRecycler.setName(dataobj.getString("name"));
                bsModelRecycler.setSlug(dataobj.getString("slug"));

                bsModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                bsModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));

                bsModelRecyclerArrayList.add(bsModelRecycler);

            }

//            BSProdAdapter = new BigSavePAdapter(this, bsModelRecyclerArrayList,this);
//            BSProdRV.setAdapter(BSProdAdapter);
//            BSProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


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