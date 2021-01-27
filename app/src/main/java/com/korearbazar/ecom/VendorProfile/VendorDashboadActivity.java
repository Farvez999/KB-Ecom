package com.korearbazar.ecom.VendorProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.activity.VendorActivity;
import com.korearbazar.ecom.adapter.VendorOrderAdapter;
import com.korearbazar.ecom.adapter.VendorProductAdapter;
import com.korearbazar.ecom.model.VendorOrderModel;
import com.korearbazar.ecom.model.VendorProductModel;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VendorDashboadActivity extends AppCompatActivity {

    SessionManager sessionManager;

    TextView btn_order_pending,btn_order_processing,btn_order_complete,btn_order_products;
    LinearLayout order_pending,order_processing,order_complete,order_products;

    private RecyclerView meRecyclerView;
    private VendorOrderAdapter meExampleAdapter;
    private ArrayList<VendorOrderModel> meExampleList;

    private RecyclerView vpRecyclerView;
    private VendorOrderAdapter vpExampleAdapter;
    private ArrayList<VendorOrderModel> vpExampleList;

    private RecyclerView vcRecyclerView;
    private VendorOrderAdapter vcExampleAdapter;
    private ArrayList<VendorOrderModel> vcExampleList;

    private RecyclerView vproductRecyclerView;
    private VendorProductAdapter vproductExampleAdapter;
    private ArrayList<VendorProductModel> vproductExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_dashboad);

        setTitle("Vendor Dashboard");

        sessionManager = new SessionManager(this);

        btn_order_pending = findViewById(R.id.btn_orderPending);
        btn_order_processing = findViewById(R.id.btn_orderProcessing);
        btn_order_complete = findViewById(R.id.btn_orderCompleted);
        btn_order_products = findViewById(R.id.btn_orderProducts);

        order_pending = findViewById(R.id.orderPending);
        order_processing = findViewById(R.id.orderProcessing);
        order_complete = findViewById(R.id.orderComplete);
        order_products = findViewById(R.id.orderProducts);

        btn_order_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_pending.setVisibility(View.VISIBLE);

                order_processing.setVisibility(View.INVISIBLE);
                order_complete.setVisibility(View.INVISIBLE);
                order_products.setVisibility(View.INVISIBLE);

                meRecyclerView = findViewById(R.id.vOrderRecyclerView);
                meRecyclerView.setHasFixedSize(true);
                meRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                meExampleList = new ArrayList<>();

                vOrderData();
            }
        });

        btn_order_processing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_processing.setVisibility(View.VISIBLE);
                order_pending.setVisibility(View.INVISIBLE);
                order_complete.setVisibility(View.INVISIBLE);
                order_products.setVisibility(View.INVISIBLE);

                vpRecyclerView = findViewById(R.id.vProcessingRecyclerView);
                vpRecyclerView.setHasFixedSize(true);
                vpRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                vpExampleList = new ArrayList<>();

                vProcessingData();
            }
        });

        btn_order_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_complete.setVisibility(View.VISIBLE);
                order_processing.setVisibility(View.INVISIBLE);
                order_pending.setVisibility(View.INVISIBLE);
                order_products.setVisibility(View.INVISIBLE);

                vcRecyclerView = findViewById(R.id.vCompleteRecyclerView);
                vcRecyclerView.setHasFixedSize(true);
                vcRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                vcExampleList = new ArrayList<>();

                vCompleteData();
            }
        });


        btn_order_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_products.setVisibility(View.VISIBLE);
                order_processing.setVisibility(View.INVISIBLE);
                order_pending.setVisibility(View.INVISIBLE);
                order_complete.setVisibility(View.INVISIBLE);


                vproductRecyclerView = findViewById(R.id.totalProductRecyclerView);
                vproductRecyclerView.setHasFixedSize(true);
                vproductRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                vproductExampleList = new ArrayList<>();

                totalProductData();
            }
        });


    }

    //order pending
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
                Toast.makeText(VendorDashboadActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
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

            meExampleAdapter = new VendorOrderAdapter(VendorDashboadActivity.this, meExampleList);
            meRecyclerView.setAdapter(meExampleAdapter);

        }catch (Exception e){

        }
    }


    //Processing
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
                Toast.makeText(VendorDashboadActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
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

            vpExampleAdapter = new VendorOrderAdapter(VendorDashboadActivity.this, vpExampleList);
            vpRecyclerView.setAdapter(vpExampleAdapter);

        }catch (Exception e){

        }
    }


    //Complete
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
                Toast.makeText(VendorDashboadActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
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

            vcExampleAdapter = new VendorOrderAdapter(VendorDashboadActivity.this, vcExampleList);
            vcRecyclerView.setAdapter(vcExampleAdapter);

        }catch (Exception e){

        }
    }


    //Products
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
                Toast.makeText(VendorDashboadActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
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

            vproductExampleAdapter = new VendorProductAdapter(VendorDashboadActivity.this, vproductExampleList);
            vproductRecyclerView.setAdapter(vproductExampleAdapter);

        }catch (Exception e){

        }
    }
}