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

import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.VendorProfile.VendorBulkProductUploadActivity;
import com.korearbazar.ecom.VendorProfile.VendorDashboadActivity;
import com.korearbazar.ecom.VendorProfile.VendorOrderActivity;
import com.korearbazar.ecom.VendorProfile.VendorProductsActivity;
import com.korearbazar.ecom.VendorProfile.VendorSettingsActivity;
import com.korearbazar.ecom.VendorProfile.VendorWithdrawsActivity;
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

public class VendorActivity extends AppCompatActivity {

    SessionManager sessionManager;

    TextView vDashboard,vOrder,vProducts,vWithdraws,vBulkProductUpload,vSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        setTitle("Vendor Profile");

        sessionManager = new SessionManager(this);

        vDashboard = findViewById(R.id.vDashboard);
        vOrder = findViewById(R.id.vOrder);
        vProducts = findViewById(R.id.vProducts);
        vWithdraws = findViewById(R.id.vWithdraws);
        vBulkProductUpload = findViewById(R.id.vBulkProductUpload);
        vSettings = findViewById(R.id.vSettings);

        vDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorActivity.this, VendorDashboadActivity.class);
                startActivity(intent);
            }
        });

        vOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorActivity.this, VendorOrderActivity.class);
                startActivity(intent);
            }
        });

        vProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorActivity.this, VendorProductsActivity.class);
                startActivity(intent);
            }
        });

        vWithdraws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorActivity.this, VendorWithdrawsActivity.class);
                startActivity(intent);
            }
        });

        vBulkProductUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorActivity.this, VendorBulkProductUploadActivity.class);
                startActivity(intent);
            }
        });

        vSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorActivity.this, VendorSettingsActivity.class);
                startActivity(intent);
            }
        });



    }

}