package com.korearbazar.ecom.VendorProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.VendorProductAdapter;
import com.korearbazar.ecom.adapter.WithdhowAdapter;
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

public class VendorProductsActivity extends AppCompatActivity {

    SessionManager sessionManager;

    private RecyclerView wRecyclerView;
    private VendorProductAdapter wExampleAdapter;
    private ArrayList<VendorProductModel> wExampleList;

    private TextView vAddNewProduct,vProductCatalogs,vAllProduct;
    private LinearLayout linerProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_products);

        setTitle("Products");

        sessionManager = new SessionManager(this);



        vAddNewProduct = findViewById(R.id.vAddNewProduct);
        vProductCatalogs = findViewById(R.id.vProductCatalogs);
        vAllProduct = findViewById(R.id.vAllProduct);

        linerProduct = findViewById(R.id.linerProduct);

        vAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerProduct.setVisibility(View.VISIBLE);

                wRecyclerView = findViewById(R.id.orderProductsRecyclerView);
                wRecyclerView.setHasFixedSize(true);
                wRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                wExampleList = new ArrayList<>();

                VendorProductsData();
            }
        });

        vAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorProductsActivity.this,VendorAddNewProductsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void VendorProductsData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getVendorProduct(sessionManager.getToken());
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
                Toast.makeText(VendorProductsActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void affilateWithdrawData_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONArray jsonArray = obj.getJSONArray("products");
            Toast.makeText(this, "affiliate_sign"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String product_type = jsonObject.getString("product_type");
                String price = jsonObject.getString("price");
                String status = jsonObject.getString("status");
                //String status = jsonObject.getString("status");

                wExampleList.add(new VendorProductModel(id,name,product_type,price,status));
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            wExampleAdapter = new VendorProductAdapter(VendorProductsActivity.this, wExampleList);
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
}