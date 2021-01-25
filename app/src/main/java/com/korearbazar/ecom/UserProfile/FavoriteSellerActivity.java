package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.FavoriteSellerAdapter;
import com.korearbazar.ecom.model.FavoriteSellerModel;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FavoriteSellerActivity extends AppCompatActivity {

    SessionManager sessionManager;

    private RecyclerView fsRecyclerView;
    private FavoriteSellerAdapter fsExampleAdapter;
    private ArrayList<FavoriteSellerModel> fsExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_seller);

        setTitle("Favorite Seller");

        sessionManager = new SessionManager(this);

        fsRecyclerView = findViewById(R.id.fsRecyclerView);
        fsRecyclerView.setHasFixedSize(true);
        fsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        fsExampleList = new ArrayList<>();

        favoriteSeller();
    }

    private void favoriteSeller() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getFavoriteSelle(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        FavoriteSelle_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(FavoriteSellerActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void FavoriteSelle_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONArray jsonArray = obj.getJSONArray("favorites");
            //Toast.makeText(this, "affiliate_favorites"+jsonArray, Toast.LENGTH_SHORT).show();
//            JSONObject Vvendor = jsonArray.getJSONObject(Integer.parseInt("vendor"));
//            Log.e("Vvendor", String.valueOf(Vvendor));
//            Toast.makeText(this, "Vvendor"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String shop_name = jsonObject.getString("shop_name");
                String owner_name = jsonObject.getString("owner_name");
                String shop_address = jsonObject.getString("shop_address");

                fsExampleList.add(new FavoriteSellerModel(id,shop_name,owner_name,shop_address));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            fsExampleAdapter = new FavoriteSellerAdapter(FavoriteSellerActivity.this, fsExampleList);
            fsRecyclerView.setAdapter(fsExampleAdapter);



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