package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CategoryDetailsProdAdapter;
import com.frz.korearbazar.adapter.DasboardOrderAdapter;
import com.frz.korearbazar.adapter.FavoriteSellerAdapter;
import com.frz.korearbazar.adapter.MessageAdapter;
import com.frz.korearbazar.adapter.WithdhowAdapter;
import com.frz.korearbazar.model.CategoryDetailsProdModel;
import com.frz.korearbazar.model.DasboardOrderModel;
import com.frz.korearbazar.model.FavoriteSellerModel;
import com.frz.korearbazar.model.MessageModel;
import com.frz.korearbazar.model.ProdModel;
import com.frz.korearbazar.model.SlidersModel;
import com.frz.korearbazar.model.User;
import com.frz.korearbazar.model.WithdhowModel;
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

    private RecyclerView wRecyclerView;
    private WithdhowAdapter wExampleAdapter;
    private ArrayList<WithdhowModel> wExampleList;

    private RecyclerView fsRecyclerView;
    private FavoriteSellerAdapter fsExampleAdapter;
    private ArrayList<FavoriteSellerModel> fsExampleList;

    private RecyclerView meRecyclerView;
    private MessageAdapter meExampleAdapter;
    private ArrayList<MessageModel> meExampleList;

    TextView txt_save,ed_username,ed_email,ed_alternatmob,ed_address,ed_city,ed_zip,affiliateBonus;
    TextView affilate_Link,copy_affilateLink,affilate_html,Copy_affilateHtml;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");

        sessionManager = new SessionManager(this);


        mRecyclerView = findViewById(R.id.recentOrderRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        wRecyclerView = findViewById(R.id.wRecyclerView);
        wRecyclerView.setHasFixedSize(true);
        wRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        wExampleList = new ArrayList<>();

        fsRecyclerView = findViewById(R.id.fsRecyclerView);
        fsRecyclerView.setHasFixedSize(true);
        fsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        fsExampleList = new ArrayList<>();

        meRecyclerView = findViewById(R.id.meRecyclerView);
        meRecyclerView.setHasFixedSize(true);
        meRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        meExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        Userdata();
        OrderJSON();
        affilateCodeData();
        WithdrawData();
        favoriteSeller();
        MessagesData();

        ed_username = findViewById(R.id.ed_username);
        ed_email = findViewById(R.id.ed_email);
        ed_alternatmob = findViewById(R.id.ed_alternatmob);
        ed_city = findViewById(R.id.city);
        ed_zip = findViewById(R.id.zip);
        ed_address = findViewById(R.id.address);
        affiliateBonus = findViewById(R.id.affiliateBonus);
        affilate_Link = findViewById(R.id.affilateLink);
        copy_affilateLink = findViewById(R.id.copyaffilateLink);
        affilate_html = findViewById(R.id.affilatehtml);
        Copy_affilateHtml = findViewById(R.id.copyAffilatehtml);

        copy_affilateLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(affilate_Link.getText());
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        Copy_affilateHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(affilate_html.getText());
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

    }



    //Message User
    private void MessagesData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getMessages(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        affilateMessages_writeRecycler(jsonresponse);

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

    private void affilateMessages_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONArray jsonArray = obj.getJSONArray("convs");
            //Toast.makeText(this, "affiliate_sign"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String subject = jsonObject.getString("subject");
                String message = jsonObject.getString("message");

                meExampleList.add(new MessageModel(id,subject,message));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            meExampleAdapter = new MessageAdapter(ProfileActivity.this, meExampleList);
            meRecyclerView.setAdapter(meExampleAdapter);



            JSONObject affiliate_sign=obj.getJSONObject("sign");
            //Toast.makeText(this, "affiliate_sign"+affiliate_sign, Toast.LENGTH_SHORT).show();

//        String affiliate_link = obj.getString("link");
//        String affiliate_html = obj.getString("html");
//        affilate_Link.setText(affiliate_link);
//        affilate_html.setText(affiliate_html);

        }catch (Exception e){

        }
    }

    //favoriteSeller
    private void favoriteSeller() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getFavoriteSelle(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        FavoriteSelle_writeRecycler(jsonresponse);

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

    private void FavoriteSelle_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONArray jsonArray = obj.getJSONArray("favorites");
            //Toast.makeText(this, "affiliate_favorites"+jsonArray, Toast.LENGTH_SHORT).show();
//            JSONObject Vvendor = jsonArray.getJSONObject(Integer.parseInt("vendor"));
//            Log.e("Vvendor", String.valueOf(Vvendor));
//            Toast.makeText(this, "Vvendor"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String shop_name = jsonObject.getString("shop_name");
                String owner_name = jsonObject.getString("owner_name");
                String shop_address = jsonObject.getString("shop_address");

                fsExampleList.add(new FavoriteSellerModel(id,shop_name,owner_name,shop_address));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            fsExampleAdapter = new FavoriteSellerAdapter(ProfileActivity.this, fsExampleList);
            fsRecyclerView.setAdapter(fsExampleAdapter);



            JSONObject affiliate_sign=obj.getJSONObject("sign");
            //Toast.makeText(this, "affiliate_sign"+affiliate_sign, Toast.LENGTH_SHORT).show();

//        String affiliate_link = obj.getString("link");
//        String affiliate_html = obj.getString("html");
//        affilate_Link.setText(affiliate_link);
//        affilate_html.setText(affiliate_html);

        }catch (Exception e){

        }
    }

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
                Toast.makeText(ProfileActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
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

            wExampleAdapter = new WithdhowAdapter(ProfileActivity.this, wExampleList);
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

    //Affilate Code
    private void affilateCodeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getAffilateCode(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        affilateCodeData_writeRecycler(jsonresponse);

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

    private void affilateCodeData_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONObject affiliate_code=obj.getJSONObject("user");

            String affiliate_link = obj.getString("link");
            String affiliate_html = obj.getString("html");
            affilate_Link.setText(affiliate_link);
            affilate_html.setText(affiliate_html);

        }catch (Exception e){

        }
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