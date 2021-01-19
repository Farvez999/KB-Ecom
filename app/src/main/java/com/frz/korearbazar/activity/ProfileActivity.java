package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CategoryDetailsProdAdapter;
import com.frz.korearbazar.adapter.DasboardOrderAdapter;
import com.frz.korearbazar.model.CategoryDetailsProdModel;
import com.frz.korearbazar.model.DasboardOrderModel;
import com.frz.korearbazar.model.ProdModel;
import com.frz.korearbazar.model.User;
import com.frz.korearbazar.utils.SessionManager;
import com.google.gson.JsonObject;

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

    //private ArrayList<User> mExampleList;
    private RecyclerView mRecyclerView;
    private DasboardOrderAdapter mExampleAdapter;
    private ArrayList<DasboardOrderModel> mExampleList;
    private RequestQueue mRequestQueue;

    TextView txt_save,ed_username,ed_email,ed_alternatmob,ed_address,ed_city,ed_zip,affiliateBonus;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");

        sessionManager = new SessionManager(this);


        mRecyclerView = findViewById(R.id.recentOrderRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        mRequestQueue = Volley.newRequestQueue(this);
        getdata();
        ProdDetailsfetchJSON();

        ed_username = findViewById(R.id.ed_username);
        ed_email = findViewById(R.id.ed_email);
        ed_alternatmob = findViewById(R.id.ed_alternatmob);
        ed_city = findViewById(R.id.city);
        ed_zip = findViewById(R.id.zip);
        ed_address = findViewById(R.id.address);
        affiliateBonus = findViewById(R.id.affiliateBonus);


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
            //Toast.makeText(this, "userA"+data, Toast.LENGTH_SHORT).show();

//            JSONArray dataA=obj.getJSONArray("orders");
//            Toast.makeText(this, "userB"+dataA, Toast.LENGTH_SHORT).show();

            JSONArray jsonArray=obj.getJSONArray("orders");
            for (int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                Log.e("products_id",jsonObject1.getString("id"));
                String id =jsonObject1.getString("id");
                String order_number =jsonObject1.getString("order_number");
                //Log.e("orderNumber",jsonObject1.getString(order_number));
                String created_at =jsonObject1.getString("created_at");
                String pay_amount =jsonObject1.getString("pay_amount");
                String payment_status =jsonObject1.getString("payment_status");

                mExampleList.add(new DasboardOrderModel(id,order_number,created_at,pay_amount,payment_status));
            }

            mExampleAdapter = new DasboardOrderAdapter(ProfileActivity.this, mExampleList);
            mRecyclerView.setAdapter(mExampleAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



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