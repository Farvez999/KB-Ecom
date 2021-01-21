package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.MessageAdapter;
import com.frz.korearbazar.adapter.VendorOrderAdapter;
import com.frz.korearbazar.adapter.VendorProductAdapter;
import com.frz.korearbazar.model.MessageModel;
import com.frz.korearbazar.model.VendorOrderModel;
import com.frz.korearbazar.model.VendorProductModel;
import com.frz.korearbazar.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VendorActivity extends AppCompatActivity {

    SessionManager sessionManager;

    private RecyclerView meRecyclerView;
    private VendorOrderAdapter meExampleAdapter;
    private ArrayList<VendorOrderModel> meExampleList;

    private RecyclerView vcRecyclerView;
    private VendorOrderAdapter vcExampleAdapter;
    private ArrayList<VendorOrderModel> vcExampleList;

    private RecyclerView vpRecyclerView;
    private VendorOrderAdapter vpExampleAdapter;
    private ArrayList<VendorOrderModel> vpExampleList;

    private RecyclerView vproductRecyclerView;
    private VendorProductAdapter vproductExampleAdapter;
    private ArrayList<VendorProductModel> vproductExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        setTitle("Vendor Profile");

        sessionManager = new SessionManager(this);

        meRecyclerView = findViewById(R.id.vOrderRecyclerView);
        meRecyclerView.setHasFixedSize(true);
        meRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        meExampleList = new ArrayList<>();

        vcRecyclerView = findViewById(R.id.vCompleteRecyclerView);
        vcRecyclerView.setHasFixedSize(true);
        vcRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        vcExampleList = new ArrayList<>();

        vpRecyclerView = findViewById(R.id.vProcessingRecyclerView);
        vpRecyclerView.setHasFixedSize(true);
        vpRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        vpExampleList = new ArrayList<>();

        vproductRecyclerView = findViewById(R.id.totalProductRecyclerView);
        vproductRecyclerView.setHasFixedSize(true);
        vproductRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        vproductExampleList = new ArrayList<>();

        vOrderData();
        vProcessingData();
        vCompleteData();
        totalProductData();

    }

    private void totalProductData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getVendorproducts(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        Vendorproducts_rv(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(VendorActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Vendorproducts_rv(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);

            JSONArray jsonArray = obj.getJSONArray("products");
            //Toast.makeText(this, "affiliate_products"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String type = jsonObject.getString("type");
                String price = jsonObject.getString("price");
                String status = jsonObject.getString("status");
                //String status = jsonObject.getString("status");


                vproductExampleList.add(new VendorProductModel(id,name,type,price,status));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            vproductExampleAdapter = new VendorProductAdapter(VendorActivity.this, vproductExampleList);
            vproductRecyclerView.setAdapter(vproductExampleAdapter);

        }catch (Exception e){

        }
    }

    private void vProcessingData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getVendorDashboard(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        vProcessingData_rv(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(VendorActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void vProcessingData_rv(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);

            JSONArray jsonArray = obj.getJSONArray("processing");
           // Toast.makeText(this, "affiliate_completed"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String order_number = jsonObject.getString("order_number");
                String qty = jsonObject.getString("qty");
                String price = jsonObject.getString("price");
                String status = jsonObject.getString("status");


                vpExampleList.add(new VendorOrderModel(id,order_number,qty,price,status));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            vpExampleAdapter = new VendorOrderAdapter(VendorActivity.this, vpExampleList);
            vpRecyclerView.setAdapter(vpExampleAdapter);

        }catch (Exception e){

        }
    }

    private void vCompleteData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getVendorDashboard(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        vCompleteData_rv(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(VendorActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void vCompleteData_rv(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);

            JSONArray jsonArray = obj.getJSONArray("completed");
            //Toast.makeText(this, "affiliate_completed"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String order_number = jsonObject.getString("order_number");
                String qty = jsonObject.getString("qty");
                String price = jsonObject.getString("price");
                String status = jsonObject.getString("status");


                vcExampleList.add(new VendorOrderModel(id,order_number,qty,price,status));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            vcExampleAdapter = new VendorOrderAdapter(VendorActivity.this, vcExampleList);
            vcRecyclerView.setAdapter(vcExampleAdapter);

        }catch (Exception e){

        }
    }

    private void vOrderData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getVendorDashboard(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        vOrderData_rv(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(VendorActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void vOrderData_rv(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);

            JSONArray jsonArray = obj.getJSONArray("pending");
            //Toast.makeText(this, "affiliate_pending"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String order_number = jsonObject.getString("order_number");
                String qty = jsonObject.getString("qty");
                String price = jsonObject.getString("price");
                String status = jsonObject.getString("status");


                meExampleList.add(new VendorOrderModel(id,order_number,qty,price,status));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            meExampleAdapter = new VendorOrderAdapter(VendorActivity.this, meExampleList);
            meRecyclerView.setAdapter(meExampleAdapter);

        }catch (Exception e){

        }
    }
}