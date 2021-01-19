package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.R;
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


        ed_username = findViewById(R.id.ed_username);
        ed_email = findViewById(R.id.ed_email);
        ed_alternatmob = findViewById(R.id.ed_alternatmob);
        ed_city = findViewById(R.id.city);
        ed_zip = findViewById(R.id.zip);
        ed_address = findViewById(R.id.address);
        affiliateBonus = findViewById(R.id.affiliateBonus);

        ProdDetailsfetchJSON();
    }

    private void ProdDetailsfetchJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getOrder(sessionManager.getToken());
        //Toast.makeText(this, "Check session Manager"+sessionManager.getToken(), Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        Order_writeRecycler(jsonresponse);

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

    private void Order_writeRecycler(String jsonresponse) {
        try {

            JSONObject obj = new JSONObject(jsonresponse);
            JSONObject data=obj.getJSONObject("user");
            Log.e("ORDEARS rrr",data.getString("id"));


            JSONArray jsonArray=data.getJSONArray("orders");
            Log.e("ORDEARS e",jsonArray.getString(Integer.parseInt("id")));
            for (int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);


                Log.e("ORDEARS",jsonObject.getString("id"));

//                                String id =jsonObject1.getString("id");
//                                String name =jsonObject1.getString("name");
//                                String slug =jsonObject1.getString("slug");
//                                String thumbnail = jsonObject1.getString("thumbnail");
//                                String photo = jsonObject1.getString("photo");
//                                String price = jsonObject1.getString("price");
//                                String previous_price = jsonObject1.getString("previous_price");

                //mExampleList.add(new CategoryDetailsProdModel(id,name,slug,thumbnail,photo,price,previous_price));
            }

//                            mExampleAdapter = new CategoryDetailsProdAdapter(CategoryDetailsActivity.this, mExampleList);
//                            mRecyclerView.setAdapter(mExampleAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private void ProdDetailsfetchJSON() {
//        String url = "http://ecom.hrventure.xyz/public/api/user/dashboard";
//
//
//        //Toast.makeText(this, "URL "+url, Toast.LENGTH_SHORT).show();
//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new com.android.volley.Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//
//                        try {
//                            JSONObject data = response.getJSONObject("user");
//                            Toast.makeText(ProfileActivity.this, "ORDEARS ee DEAR"+data, Toast.LENGTH_SHORT).show();
//
//                            JSONArray jsonArray=data.getJSONArray("orders");
//                            Toast.makeText(ProfileActivity.this, "ORDEARS DEAR"+jsonArray, Toast.LENGTH_SHORT).show();
//
//                            for (int i=0; i<jsonArray.length();i++){
//                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
//
//
//                                Log.e("ORDEARS",jsonObject1.getString("id"));
//
////                                String id =jsonObject1.getString("id");
////                                String name =jsonObject1.getString("name");
////                                String slug =jsonObject1.getString("slug");
////                                String thumbnail = jsonObject1.getString("thumbnail");
////                                String photo = jsonObject1.getString("photo");
////                                String price = jsonObject1.getString("price");
////                                String previous_price = jsonObject1.getString("previous_price");
//
//                                //mExampleList.add(new CategoryDetailsProdModel(id,name,slug,thumbnail,photo,price,previous_price));
//                            }
//
////                            mExampleAdapter = new CategoryDetailsProdAdapter(CategoryDetailsActivity.this, mExampleList);
////                            mRecyclerView.setAdapter(mExampleAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mRequestQueue.add(request);
//    }

    //Big Save Products
    private void getdata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getProfile(sessionManager.getToken());
        //Toast.makeText(this, "Check session Manager"+sessionManager.getToken(), Toast.LENGTH_SHORT).show();
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
            JSONObject obj = new JSONObject(jsonresponse);

            ArrayList<ProdModel> bsModelRecyclerArrayList = new ArrayList<>();
            JSONObject jsonObject=obj.getJSONObject("user");

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


}