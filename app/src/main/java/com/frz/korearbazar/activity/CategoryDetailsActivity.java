package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CateAdapter;
import com.frz.korearbazar.adapter.CategoryDetailsProdAdapter;
import com.frz.korearbazar.model.CateModel;
import com.frz.korearbazar.model.ProdModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.frz.korearbazar.ApiInterface.CategoryDetails;
import static com.frz.korearbazar.ApiInterface.JSONURL;

public class CategoryDetailsActivity extends AppCompatActivity {

    private ArrayList<CateModel> mExampleList;
    CateModel cateModel;

    private CateAdapter retrofitAdapter;



    String slug;

    private ArrayList<ProdModel> rExampleList;
    private RequestQueue rRequestQueue;
    private CategoryDetailsProdAdapter mExampleAdapter;
    private RecyclerView mRecyclerView;

    TextView testName,testSlug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        mRecyclerView = findViewById(R.id.category_recycler);

        rExampleList = new ArrayList<>();
        rRequestQueue = Volley.newRequestQueue(this);

        mRecyclerView = findViewById(R.id.recycler_releted);
        //mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        Intent intent = getIntent();
        slug = intent.getStringExtra("mySlug");
        //Toast.makeText(this, "Category "+ slug, Toast.LENGTH_SHORT).show();

        ProdDetailsfetchJSON();

        testName = findViewById(R.id.nameR);
        testSlug = findViewById(R.id.slugR);
    }

    //Product Details
    private void ProdDetailsfetchJSON() {
        String url = JSONURL+CategoryDetails+slug;
        //Toast.makeText(this, "URL "+url, Toast.LENGTH_SHORT).show();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {



                            JSONObject data = response.getJSONObject("data");

//                            JSONArray jsonArray= response.getJSONArray("prods");
//                            //Log.e("JSONA",jsonArray);
//                            Toast.makeText(CategoryDetailsActivity.this, "JSONA"+jsonArray, Toast.LENGTH_SHORT).show();
//
                            String categoryProduct = data.getString("prods");
                            JSONArray array = new JSONArray(categoryProduct);
                            for (int i = 0; i< array.length(); i++){
                                JSONObject jsonObject = array.getJSONObject(i);
                                String nameR = jsonObject.getString("name");
                                String  slugR = jsonObject.getString("slug");

//                                String id = jsonObject.getString("id");
//                                String user_id = jsonObject.getString("user_id");
//                                String name = jsonObject.getString("name");
//                                String slug = jsonObject.getString("slug");
//                                String features = jsonObject.getString("features");
//                                String colors = jsonObject.getString("colors");
//                                String thumbnail = jsonObject.getString("thumbnail");
//                                String price = jsonObject.getString("price");
//                                String previous_price = jsonObject.getString("previous_price");
//                                String attributes = jsonObject.getString("attributes");
//                                String size = jsonObject.getString("size");
//                                String size_price = jsonObject.getString("size_price");
//                                String discount_date = jsonObject.getString("discount_date");
//
//                                String showPrice = jsonObject.getString("showPrice");
//                                String setCurrency = jsonObject.getString("setCurrency");
//                                String showPreviousPrice = jsonObject.getString("showPreviousPrice");
//
//                                rExampleList.add(new ProdModel(id,user_id,name, slug,features,colors, thumbnail,price,previous_price,attributes,size,size_price,discount_date,showPrice,setCurrency,showPreviousPrice));

                                Log.e("name",nameR);
                                Toast.makeText(CategoryDetailsActivity.this, "name"+nameR, Toast.LENGTH_SHORT).show();
                                testName.setText(nameR);
                                testSlug.setText(slugR);

                            }
                            mExampleAdapter = new CategoryDetailsProdAdapter(CategoryDetailsActivity.this, rExampleList);
//                            mRecyclerView.setAdapter(mExampleAdapter);

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
        rRequestQueue.add(request);
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