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

import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CateAdapter;
import com.frz.korearbazar.adapter.TopRatedPAdapter;
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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.frz.korearbazar.ApiInterface.JSONURL;
import static com.frz.korearbazar.ApiInterface.PDetailsImgUrl;

public class CategoryDetailsActivity extends AppCompatActivity {

    CateModel cateModel;

    private CateAdapter retrofitAdapter;
    private RecyclerView cateRecyclerView;

    String slug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        cateRecyclerView = findViewById(R.id.category_recycler);
        ProdDetailsfetchJSON();

//        Bundle intent = getIntent().getExtras();
//        if (intent!=null){
//            cateModel = (CateModel) intent.getSerializable("categoryModel");
//        }
        Intent intent = getIntent();
        slug = intent.getStringExtra("mySlug");
        Toast.makeText(this, ""+ slug, Toast.LENGTH_SHORT).show();
    }

    private void ProdDetailsfetchJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();

                        prod_details_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                        Toast.makeText(getApplicationContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CategoryDetailsActivity.this, "Error"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void prod_details_writeRecycler(String jsonresponse) {



        try {
            JSONObject object = new JSONObject(jsonresponse);

            ArrayList<CateModel> cateDetailsmodelRecyclerArrayList = new ArrayList<>();

            JSONArray jsonArray = object.getJSONArray("subs");
//                JSONObject jsonObject = object.getJSONObject("productt");
                Toast.makeText(this, "Done" + jsonArray, Toast.LENGTH_SHORT).show();


//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                CateModel cateModelRecycler = new CateModel();
//                JSONObject dataobj = jsonArray.getJSONObject(i);
//
//
//                cateModelRecycler.setName(dataobj.getString("name"));
//
//
//
//
//                cateDetailsmodelRecyclerArrayList.add(cateModelRecycler);
//
//            }
//
//            retrofitAdapter = new CateAdapter(this,cateDetailsmodelRecyclerArrayList);
//            cateRecyclerView.setAdapter(retrofitAdapter);
//            cateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));



            for (int i = 0; i < jsonArray.length(); i++) {

                CateModel cateDetalisModelRecycler = new CateModel();
                JSONObject dataobj = jsonArray.getJSONObject(i);


                String slugf=dataobj.getString("slug");

//                    JSONObject jsonObject = object.getJSONObject("productt");
//                    Toast.makeText(this, "productt" + jsonObject, Toast.LENGTH_SHORT).show();

                if (slugf.equals(cateModel.getSlug())){
                    cateDetalisModelRecycler.setSlug(dataobj.getString("slug"));
                    cateDetalisModelRecycler.setPhoto(dataobj.getString("photo"));
                    String photo=dataobj.getString("photo");
                    //prodDetalisModelRecycler.setGalleries(dataobj.getString("galleries"));
                    cateDetalisModelRecycler.setName(dataobj.getString("name"));


                    cateDetailsmodelRecyclerArrayList.add(cateDetalisModelRecycler);

                }

                    retrofitAdapter = new CateAdapter(this,cateDetailsmodelRecyclerArrayList);
                    cateRecyclerView.setAdapter(retrofitAdapter);
                    cateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}