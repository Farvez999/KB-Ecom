package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.UserProfile.AffiliateCodeActivity;
import com.korearbazar.ecom.UserProfile.EditProfileActivity;
import com.korearbazar.ecom.UserProfile.FavoriteSellerActivity;
import com.korearbazar.ecom.UserProfile.MessageActivity;
import com.korearbazar.ecom.UserProfile.ResetPasswordActivity;
import com.korearbazar.ecom.UserProfile.WithdrawActivity;
import com.korearbazar.ecom.adapter.DasboardOrderAdapter;
import com.korearbazar.ecom.model.DasboardOrderModel;
import com.korearbazar.ecom.model.ProdModel;
import com.korearbazar.ecom.utils.SessionManager;

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

    private TextView up_Withdraw,up_Message,upFSeller,upAffiliate,upEditProfile,upResetPassword;

    TextView txt_save,ed_username,ed_email,ed_alternatmob,ed_address,ed_city,ed_zip,affiliateBonus;
    SessionManager sessionManager;

    private RecyclerView mRecyclerView;
    private DasboardOrderAdapter mExampleAdapter;
    private ArrayList<DasboardOrderModel> mExampleList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");

        sessionManager = new SessionManager(this);

        up_Withdraw=findViewById(R.id.upWithdraw);
        up_Withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, WithdrawActivity.class);
                startActivity(intent);
            }
        });

        up_Message=findViewById(R.id.upMessage);
        up_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });

        upFSeller=findViewById(R.id.upFSeller);
        upFSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FavoriteSellerActivity.class);
                startActivity(intent);
            }
        });

        upAffiliate = findViewById(R.id.upAffiliate);
        upAffiliate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AffiliateCodeActivity.class);
                startActivity(intent);
            }
        });

        upEditProfile = findViewById(R.id.upEditProfile);
        upEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        upResetPassword = findViewById(R.id.upResetPassword);
        upResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });


        mRecyclerView = findViewById(R.id.recentOrderRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);


        mRequestQueue = Volley.newRequestQueue(this);
        Userdata();
        OrderJSON();


        ed_username = findViewById(R.id.ed_username);
        ed_email = findViewById(R.id.ed_email);
        ed_alternatmob = findViewById(R.id.ed_alternatmob);
        ed_city = findViewById(R.id.city);
        ed_zip = findViewById(R.id.zip);
        ed_address = findViewById(R.id.address);
        affiliateBonus = findViewById(R.id.affiliateBonus);


    }

    //Order Recent
    private void OrderJSON() {
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
//                Log.i("Responsestring", response.body().toString());
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



    //Dasborad
    private void Userdata() {
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