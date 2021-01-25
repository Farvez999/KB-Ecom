package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.CategoryDetailsProdAdapter;
import com.korearbazar.ecom.model.CategoryDetailsProdModel;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.korearbazar.ecom.ApiInterface.CategoryDetails;
import static com.korearbazar.ecom.ApiInterface.JSONURL;

public class CategoryDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CategoryDetailsProdAdapter mExampleAdapter;
    private ArrayList<CategoryDetailsProdModel> mExampleList;
    private RequestQueue mRequestQueue;

    String slug;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        mRecyclerView = findViewById(R.id.category_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);


        Intent intent = getIntent();
        slug = intent.getStringExtra("mySlug");

        ProdDetailsfetchJSON();



    }



    //Product Details
    private void ProdDetailsfetchJSON() {
        String url = JSONURL+CategoryDetails+slug;

//        String call =(sessionManager.getToken());
        //Toast.makeText(this, "URL "+url, Toast.LENGTH_SHORT).show();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject data = response.getJSONObject("data");

                            JSONArray jsonArray=data.getJSONArray("prods");
                            for (int i=0; i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);



                                String id =jsonObject1.getString("id");
                                String name =jsonObject1.getString("name");
                                String slug =jsonObject1.getString("slug");
                                String thumbnail = jsonObject1.getString("thumbnail");
                                String photo = jsonObject1.getString("photo");
                                String price = jsonObject1.getString("price");
                                String previous_price = jsonObject1.getString("previous_price");

                                mExampleList.add(new CategoryDetailsProdModel(id,name,slug,thumbnail,photo,price,previous_price));
                            }

                            mExampleAdapter = new CategoryDetailsProdAdapter(CategoryDetailsActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }




}