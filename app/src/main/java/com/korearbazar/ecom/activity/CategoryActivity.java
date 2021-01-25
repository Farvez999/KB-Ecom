package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.CateAdapter;
import com.korearbazar.ecom.adapter.RelatedProdAdapter;
import com.korearbazar.ecom.model.ProdModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private CateAdapter retrofitAdapter;
    private RecyclerView cateRecyclerView;


    private RequestQueue rRequestQueue;
    private ArrayList<ProdModel> rExampleList;
    private RelatedProdAdapter mExampleAdapter;
    private RecyclerView mRecyclerView;

    String slug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("All Category");

        cateRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView = findViewById(R.id.category_product);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rExampleList = new ArrayList<>();
        rRequestQueue = Volley.newRequestQueue(this);

        Bundle intent = getIntent().getExtras();
        if (intent!=null){
            slug=intent.getString("mySlug");
        }
        Toast.makeText(this, ""+slug, Toast.LENGTH_SHORT).show();
        //getData();
        getRelatedProduct();
        //Category
        //fetchJSON();
    }

    //Related Product
    private void getRelatedProduct() {
        //String url = JSONURL+ProdDetailsUrl+slug;
        String url = "http://ecom.hrventure.xyz/public/api/category/"+slug;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //ArrayList<ProdModel> prodmodelRecyclerArrayList = new ArrayList<>();

                            JSONObject jsonArray = response.getJSONObject("data");
                            Toast.makeText(CategoryActivity.this, "Data Done "+jsonArray, Toast.LENGTH_SHORT).show();

                            //JSONArray jsonArray = response.getJSONArray("prods");
                            //Toast.makeText(CategoryActivity.this, "Prods Done "+jsonArray, Toast.LENGTH_SHORT).show();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject hit = jsonArray.getJSONObject(i);
//                                String id = hit.getString("id");
//                                String user_id = hit.getString("user_id");
//                                String name = hit.getString("name");
//                                String slug = hit.getString("slug");
//                                String features = hit.getString("features");
//                                String colors = hit.getString("colors");
//                                String thumbnail = hit.getString("thumbnail");
//                                String price = hit.getString("price");
//                                String previous_price = hit.getString("previous_price");
//                                String attributes = hit.getString("attributes");
//                                String size = hit.getString("size");
//                                String size_price = hit.getString("size_price");
//                                String discount_date = hit.getString("discount_date");
//
//                                String showPrice = hit.getString("showPrice");
//                                String setCurrency = hit.getString("setCurrency");
//                                String showPreviousPrice = hit.getString("showPreviousPrice");
//
////rExampleList
//                                rExampleList.add(new ProdModel(id,user_id,name, slug,features,colors, thumbnail,price,previous_price,attributes,size,size_price,discount_date,showPrice,setCurrency,showPreviousPrice));
//                            }
                            mExampleAdapter = new RelatedProdAdapter(CategoryActivity.this, rExampleList);
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
        rRequestQueue.add(request);
    }

//    public void getData(){
//        //http://192.168.0.108/project/hrv-ecom/public/api/category/electric
//        String url = "//192.168.0.108/project/hrv-ecom/public/api/category/"+slug;
//        //String url = JSONURL+ProdDetailsUrl+slug;
//
//
//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new com.android.volley.Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//
//                            JSONObject jsonArray = response.getJSONObject("productt");
//
//                            //JSONObject ja = response.getJSONObject("user");
//                            // Toast.makeText(ItemDetailsActivity.this, "Hello User"+jsonArray, Toast.LENGTH_SHORT).show();
//
//                            String name = jsonArray.getString("name");
//                            String details = jsonArray.getString("details");
//                            String stock = jsonArray.getString("stock");
//                            String photo = jsonArray.getString("photo");
//                            String slug = jsonArray.getString("slug");
//                            String size = jsonArray.getString("size");
//                            String size_qty = jsonArray.getString("size_qty");
//                            String size_price = jsonArray.getString("size_price");
//
//                            String color = jsonArray.getString("color");
//                            String myColor = color;
//
//                            String galleries = jsonArray.getString("galleries");
//                            String showPrice = jsonArray.getString("showPrice");
//                            String setCurrency = jsonArray.getString("setCurrency");
//                            String showPreviousPrice = jsonArray.getString("showPreviousPrice");
//                            String user = jsonArray.getString("user");
//
//
//                            //int price = jsonArray.getInt("price");
//
//                            mExampleList.add(new ProdDetailsModel(name,details,stock,photo,slug,size,size_qty,size_price,color,galleries,showPrice,setCurrency,showPreviousPrice,user));
//
//                            txtTitle.setText(jsonArray.getString("name"));
//                            txtDesc.setText(jsonArray.getString("details"));
//
//
//                            ////////////Price
//
//                            totalPrice = jsonArray.getString("showPrice");
//
//                            String totalPreviousPrice = jsonArray.getString("showPreviousPrice");
//
//                            if (totalPreviousPrice.equals(""))
//                            {
//                                txtPrice.setText(totalPrice);
//                            }
//                            else {
//                                txtPrice.setText(totalPrice);
//
//                                SpannableString ss = new SpannableString(totalPreviousPrice);
//                                StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
//                                ss.setSpan(strikethroughSpan,0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//
//                                txt_previous_price.setText(ss);
//
//                                String pricWithStr = totalPrice;
//                                pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
//                                float pp = Float.parseFloat(pricWithStr);
//
//                                String pPricWithStr = totalPreviousPrice;
//                                pPricWithStr = pPricWithStr.replaceAll("[^\\d.]", "");
//                                float ppp = Float.parseFloat(pPricWithStr);
//
//
//                                float diff = (ppp - pp);
//                                if (diff > 0 ){
//                                    float dif = (diff / ppp) * 100;
//                                    String fff= String.valueOf(dif);
//
//                                    int b = Math.round(dif);
//                                    String fa = String.valueOf(b+" %off");
//
//                                    txt_percentage.setText(fa);
//                                }else {
//                                    Toast.makeText(ItemDetailsActivity.this, "Not a Previous Price", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            ///////////////////Price
//
//
//                            //txt_stoke.setText("stock");
//                            if (jsonArray.getString("stock") == (String.valueOf(0))) {
//                                txt_stoke.setText("Out Of Stock");
//                                //txt_stoke.setTextColor(R.color.colorRad);
//                                count.setVisibility(View.GONE);
//                                outOfStoke.setVisibility(View.VISIBLE);
//                                buyNow.setVisibility(View.GONE);
//                                btnAddtocart.setVisibility(View.GONE);
//                            } else {
//                                txt_stoke.setText("In Stock");
//                                //txt_stoke.setTextColor(R.color.colorAccent);
//                            }
//
//
//
//
//                            txtDesc.setText(jsonArray.getString("details"));
//                            txt_size.setText(jsonArray.getString("size"));
//                            txt_color.setText(myColor);
//                            //Picasso.get().load("http://ecom.hrventure.xyz/assets/images/products/1606560895gDvz8eUj.png").into(imgDtails);
//                            imgUrl = JSONURL+PDetailsImgUrl+photo;
//                            Picasso.get().load(imgUrl).into(imgDtails);
//
//
//                            prodDetailsAdapter = new ProdDetailsAdapter(ItemDetailsActivity.this, mExampleList);
//
//                            // mRecyclerView.setAdapter(mExampleAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mRequestQueue.add(request);
//    }


//    private void fetchJSON() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiInterface.JSONURL)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//        ApiInterface api = retrofit.create(ApiInterface.class);
//
//        Call<String> call = api.getString();
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                //366357
//                Log.i("Responsestring", response.body().toString());
//                //Toast.makeText()
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        Log.i("onSuccess", response.body().toString());
//
//                        String jsonresponse = response.body().toString();
//                        writeRecycler(jsonresponse);
//
//                    } else {
//                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(CategoryActivity.this, "Error"+t, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//    private void writeRecycler(String response){
//
//        try {
//            //getting the whole json object from the response
//            JSONObject obj = new JSONObject(response);
////            if(obj.optString("status").equals("true")){
//
//            ArrayList<CateModel> modelRecyclerArrayList = new ArrayList<>();
//            JSONArray dataArray  = obj.getJSONArray("categories");
//
//            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();
//
//            for (int i = 0; i < dataArray.length(); i++) {
//
//                CateModel modelRecycler = new CateModel();
//                JSONObject dataobj = dataArray.getJSONObject(i);
//
//                modelRecycler.setPhoto(dataobj.getString("photo"));
//                modelRecycler.setName(dataobj.getString("name"));
//
//                modelRecyclerArrayList.add(modelRecycler);
//
//            }
//
//            retrofitAdapter = new CateAdapter(this,modelRecyclerArrayList);
//            cateRecyclerView.setAdapter(retrofitAdapter);
//            cateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
////            }else {
////                Toast.makeText(TestRMain.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
////            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
//        }
//
//
//    }


}