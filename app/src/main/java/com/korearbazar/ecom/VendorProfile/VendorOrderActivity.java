package com.korearbazar.ecom.VendorProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.UserProfile.MessageActivity;
import com.korearbazar.ecom.adapter.MessageAdapter;
import com.korearbazar.ecom.adapter.VendorOrderAdapter;
import com.korearbazar.ecom.model.MessageModel;
import com.korearbazar.ecom.model.VendorOrderModel;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VendorOrderActivity extends AppCompatActivity {

    SessionManager sessionManager;

    private RecyclerView meRecyclerView;
    private VendorOrderAdapter meExampleAdapter;
    private ArrayList<VendorOrderModel> meExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order);

        setTitle("Order");

        sessionManager = new SessionManager(this);

        meRecyclerView = findViewById(R.id.VendorOrderRecyclerView);
        meRecyclerView.setHasFixedSize(true);
        meRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        meExampleList = new ArrayList<>();

        MessagesData();
    }

    private void MessagesData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getVendorOrder(sessionManager.getToken());
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
                Toast.makeText(VendorOrderActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void affilateMessages_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);


            JSONObject jsonObject=obj.getJSONObject("orders");
            Toast.makeText(this, "affiliate_user"+jsonObject, Toast.LENGTH_SHORT).show();
            Log.e("affiliate_user", String.valueOf(jsonObject));


            JSONArray jsonArray = jsonObject.getJSONArray("5vI81604132115");
            Toast.makeText(this, "affiliate_orders"+jsonArray, Toast.LENGTH_SHORT).show();
            Log.e("affiliate_orders", String.valueOf(jsonArray));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                String id = jsonObject1.getString("id");
                Toast.makeText(this, "affiliate_id"+id, Toast.LENGTH_SHORT).show();
                String order_number = jsonObject1.getString("order_number");
                String qty = jsonObject1.getString("qty");
                String price = jsonObject1.getString("price");
                String status = jsonObject1.getString("status");


                meExampleList.add(new VendorOrderModel(id,order_number,qty,price,status));

            }

            meExampleAdapter = new VendorOrderAdapter(VendorOrderActivity.this, meExampleList);
            meRecyclerView.setAdapter(meExampleAdapter);


        }catch (Exception e){

        }
    }
}