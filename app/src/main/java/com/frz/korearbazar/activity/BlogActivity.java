package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.BlogAdapter;
import com.frz.korearbazar.model.BlogModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BlogActivity extends AppCompatActivity {

    //Blog
    private BlogAdapter blogAdapter;
    private RecyclerView blogRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        setTitle("Blog");

        //Blog
        blogRV = findViewById(R.id.blog_recycler);
        blogfetchJSON();
    }

    //Blog
    private void blogfetchJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getBestProducts();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        blog_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(BlogActivity.this, "Error"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void blog_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<BlogModel> blogModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray  = obj.getJSONArray("blogs");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                BlogModel blogModelRecycler = new BlogModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                blogModelRecycler.setPhoto(dataobj.getString("photo"));
                blogModelRecycler.setTitle(dataobj.getString("title"));
                blogModelRecycler.setDetails(dataobj.getString("details"));

                blogModelRecyclerArrayList.add(blogModelRecycler );

            }

            blogAdapter = new BlogAdapter(this,blogModelRecyclerArrayList);
            blogRV.setAdapter(blogAdapter);
            blogRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

}