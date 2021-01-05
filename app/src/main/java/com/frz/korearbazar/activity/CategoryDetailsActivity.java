package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CateAdapter;
import com.frz.korearbazar.adapter.ProdDetailsAdapter;
import com.frz.korearbazar.adapter.TopRatedPAdapter;
import com.frz.korearbazar.model.BSBannerModel;
import com.frz.korearbazar.model.CateModel;
import com.frz.korearbazar.model.ProdDetailsModel;
import com.frz.korearbazar.model.ProdModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.frz.korearbazar.ApiInterface.JSONURL;
import static com.frz.korearbazar.ApiInterface.PDetailsImgUrl;
import static com.frz.korearbazar.ApiInterface.ProdDetailsUrl;

public class CategoryDetailsActivity extends AppCompatActivity {

    private ArrayList<CateModel> mExampleList;
    CateModel cateModel;

    private CateAdapter retrofitAdapter;
    private RecyclerView cateRecyclerView;

    private RequestQueue mRequestQueue;

    String slug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        cateRecyclerView = findViewById(R.id.category_recycler);


        mRequestQueue = Volley.newRequestQueue(this);

        mExampleList = new ArrayList<>();

        Intent intent = getIntent();
        slug = intent.getStringExtra("mySlug");
        Toast.makeText(this, "Category "+ slug, Toast.LENGTH_SHORT).show();

        ProdDetailsfetchJSON();
    }

    //Product Details
    private void ProdDetailsfetchJSON() {
        String url = "http://192.168.0.108/project/hrv-ecom/public/api/category/"+slug;
        //Toast.makeText(this, "URL "+url, Toast.LENGTH_SHORT).show();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {


                            JSONObject data = response.getJSONObject("data");
//                            Toast.makeText(CategoryDetailsActivity.this, "Data" +data, Toast.LENGTH_SHORT).show();
                             //String x = data.getString("cat");
                            //JSONArray contacts = data.getJSONArray("prods");
                            String categoryProduct = data.getString("prods");
                            Toast.makeText(CategoryDetailsActivity.this, "prods "+categoryProduct, Toast.LENGTH_SHORT).show();



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

//    private void ProdDetailsfetchJSON() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(JSONURL)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//        ApiInterface api = retrofit.create(ApiInterface.class);
//        Call<String> call = api.getCategoryDetails();
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
//                //Toast.makeText()
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        Log.i("onSuccess", response.body().toString());
//
//                        String jsonresponse = response.body().toString();
//
//                        prod_details_writeRecycler(jsonresponse);
//
//                    } else {
//                        Log.i("onEmptyResponse", "Returned empty response");
//                        Toast.makeText(getApplicationContext(),"Nothing returned",Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(CategoryDetailsActivity.this, "Error"+t, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @SuppressLint("ResourceAsColor")
//    private void prod_details_writeRecycler(String jsonresponse) {
//
//
//
//        try {
//            JSONObject object = new JSONObject(jsonresponse);
//
//            ArrayList<CateModel> cateDetailsmodelRecyclerArrayList = new ArrayList<>();
//
//            JSONArray jsonArray = object.getJSONArray("prods");
////                JSONObject jsonObject = object.getJSONObject("productt");
//                Toast.makeText(this, "Done" + jsonArray, Toast.LENGTH_SHORT).show();
//
//
////            for (int i = 0; i < jsonArray.length(); i++) {
////
////                CateModel cateModelRecycler = new CateModel();
////                JSONObject dataobj = jsonArray.getJSONObject(i);
////
////
////                cateModelRecycler.setName(dataobj.getString("name"));
////
////
////
////
////                cateDetailsmodelRecyclerArrayList.add(cateModelRecycler);
////
////            }
////
////            retrofitAdapter = new CateAdapter(this,cateDetailsmodelRecyclerArrayList);
////            cateRecyclerView.setAdapter(retrofitAdapter);
////            cateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                CateModel cateDetalisModelRecycler = new CateModel();
//                JSONObject dataobj = jsonArray.getJSONObject(i);
//
//
//                String slugf=dataobj.getString("slug");
//
////                    JSONObject jsonObject = object.getJSONObject("productt");
////                    Toast.makeText(this, "productt" + jsonObject, Toast.LENGTH_SHORT).show();
//
//                if (slugf.equals(cateModel.getSlug())){
//                    cateDetalisModelRecycler.setSlug(dataobj.getString("slug"));
//                    cateDetalisModelRecycler.setPhoto(dataobj.getString("photo"));
//                    String photo=dataobj.getString("photo");
//                    //prodDetalisModelRecycler.setGalleries(dataobj.getString("galleries"));
//                    cateDetalisModelRecycler.setName(dataobj.getString("name"));
//
//
//                    cateDetailsmodelRecyclerArrayList.add(cateDetalisModelRecycler);
//
//                }
//
//                    retrofitAdapter = new CateAdapter(this,cateDetailsmodelRecyclerArrayList);
//                    cateRecyclerView.setAdapter(retrofitAdapter);
//                    cateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//
//            }
//
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}