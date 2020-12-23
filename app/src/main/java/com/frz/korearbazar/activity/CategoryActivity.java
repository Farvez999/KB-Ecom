package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.frz.korearbazar.ApiInterface;
import com.frz.korearbazar.Interface.CategoryInterface;
import com.frz.korearbazar.MainActivity;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CateAdapter;
import com.frz.korearbazar.model.CateModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    private CateAdapter retrofitAdapter;
    private RecyclerView cateRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("All Category");

        cateRecyclerView = findViewById(R.id.recycler_view);

        //Category
        fetchJSON();
    }


    private void fetchJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
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
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Error"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void writeRecycler(String response){

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
//            if(obj.optString("status").equals("true")){

            ArrayList<CateModel> modelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray  = obj.getJSONArray("categories");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                CateModel modelRecycler = new CateModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                modelRecycler.setPhoto(dataobj.getString("photo"));
                modelRecycler.setName(dataobj.getString("name"));

                modelRecyclerArrayList.add(modelRecycler);

            }

            retrofitAdapter = new CateAdapter(this,modelRecyclerArrayList);
            cateRecyclerView.setAdapter(retrofitAdapter);
            cateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

//            }else {
//                Toast.makeText(TestRMain.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }


    }


}