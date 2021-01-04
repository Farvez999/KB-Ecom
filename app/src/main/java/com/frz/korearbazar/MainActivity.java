package com.frz.korearbazar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.frz.korearbazar.Interface.CategoryInterface;
import com.frz.korearbazar.Interface.ProdInterface;
import com.frz.korearbazar.activity.BlogActivity;
import com.frz.korearbazar.activity.CartActivity;
import com.frz.korearbazar.activity.CategoryActivity;
import com.frz.korearbazar.activity.ItemDetailsActivity;
import com.frz.korearbazar.activity.LoginActivity;
import com.frz.korearbazar.activity.ProfileActivity;
import com.frz.korearbazar.activity.VendorRegisterActivity;
import com.frz.korearbazar.adapter.BSBannerAdapter;
import com.frz.korearbazar.adapter.BannerTopSmallAdapter;
import com.frz.korearbazar.adapter.BestSellerAdapter;
import com.frz.korearbazar.adapter.BigSavePAdapter;
import com.frz.korearbazar.adapter.BlogAdapter;
import com.frz.korearbazar.adapter.BrandsAdapter;
import com.frz.korearbazar.adapter.CateAdapter;
import com.frz.korearbazar.adapter.FlashDealAdapter;
import com.frz.korearbazar.adapter.HotProdAdapter;
import com.frz.korearbazar.adapter.LargeBannerAdapter;
import com.frz.korearbazar.adapter.NewProdAdapter;
import com.frz.korearbazar.adapter.ProdAdapter;
import com.frz.korearbazar.adapter.ReviewsAdapter;
import com.frz.korearbazar.adapter.SaleProdAdapter;
import com.frz.korearbazar.adapter.SlidersAdapter;
import com.frz.korearbazar.adapter.TopRatedPAdapter;
import com.frz.korearbazar.adapter.TrendingProdAdapter;
import com.frz.korearbazar.model.BSBannerModel;
import com.frz.korearbazar.model.BannerTopSmallModel;
import com.frz.korearbazar.model.BlogModel;
import com.frz.korearbazar.model.BrandsModel;
import com.frz.korearbazar.model.CateModel;
import com.frz.korearbazar.model.LargeBannerModel;
import com.frz.korearbazar.model.ProdModel;
import com.frz.korearbazar.model.ReviewsModel;
import com.frz.korearbazar.model.SlidersModel;
import com.frz.korearbazar.utils.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements ProdInterface {

    SessionManager sessionManager;


    //Slider
    private SlidersAdapter sliderAdapter;
    private RecyclerView sliderRecyclerView;
    List<SlidersModel> sliderDatumList;

    List<ProdModel> pm;
    //Category
    private CateAdapter retrofitAdapter;
    private RecyclerView cateRecyclerView;

    //Products
    private ProdAdapter prodAdapter;
    private RecyclerView prodRecyclerView;

    //Banner Small Top
    private BannerTopSmallAdapter bannerSTAdapter;
    private RecyclerView bannerSTRecyclerView;
    List<SlidersModel> bannerSTDatumList;

    //best Products
    private BestSellerAdapter bestProdAdapter;
    private RecyclerView bestProdRV;

    //Flash Deal
    private FlashDealAdapter fdProdAdapter;
    private RecyclerView fdProdRV;


    //Large Banner
    private LargeBannerAdapter lBannerAdapter;
    private RecyclerView lBannerRecyclerView;
    List<SlidersModel> lBannerDatumList;


    //Top Rated Products
    private TopRatedPAdapter TRProdAdapter;
    private RecyclerView TRProdRV;


    //Button Small Banner
    private BSBannerAdapter bsBannerAdapter;
    private RecyclerView bsBannerRecyclerView;
    List<SlidersModel> bsBannerDatumList;


    //Big Save Products
    private BigSavePAdapter BSProdAdapter;
    private RecyclerView BSProdRV;

    //Hot Products
    private com.frz.korearbazar.adapter.HotProdAdapter HotProdAdapter;
    private RecyclerView HotProdRV;


    //New Products
    private com.frz.korearbazar.adapter.NewProdAdapter NewProdAdapter;
    private RecyclerView NewProdRV;
    ProdInterface prodInterface;


    //Trending Products
    private com.frz.korearbazar.adapter.TrendingProdAdapter TrendingProdAdapter;
    private RecyclerView TrendingProdRV;


    //Sale Products
    private com.frz.korearbazar.adapter.SaleProdAdapter SaleProdAdapter;
    private RecyclerView SaleProdRV;


    //Reviews
    private ReviewsAdapter reviewsAdapter;
    private RecyclerView reviewsRV;


    //Blog
    private BlogAdapter blogAdapter;
    private RecyclerView blogRV;


    //Brands
    private BrandsAdapter BrandsProdAdapter;
    private RecyclerView BrandsProdRV;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pm = new ArrayList<>();
        sessionManager = new SessionManager(this);
        Log.e("token",sessionManager.getToken());

        //Slider
        sliderRecyclerView = findViewById(R.id.recyclerSlider);
        sliderfetchJSON();


        //Category
        cateRecyclerView = findViewById(R.id.cate_recycler);
        fetchJSON();

        //Products
        prodRecyclerView = findViewById(R.id.product_recycler);
        prodfetchJSON();

        //Banner Small Top
        bannerSTRecyclerView = findViewById(R.id.topSmallBanner);
        BannerSTfetchJSON();

        // Best Products
        bestProdRV = findViewById(R.id.best_product_recycler);
        bestProdfetchJSON();

        // Flash Deal
        fdProdRV = findViewById(R.id.fd_product_recycler);
        fdProdfetchJSON();

        // Large Banner
        lBannerRecyclerView = findViewById(R.id.largeBanner);
        lBannerfetchJSON();

        // Top Rated Products
        TRProdRV = findViewById(R.id.topRate_product_recycler);
        TRProdfetchJSON();


        //Button Small Banner
        bsBannerRecyclerView = findViewById(R.id.bsBanner);
        bsBannerfetchJSON();


        // Big Save Products
        BSProdRV = findViewById(R.id.bs_product_recycler);
        BSProdfetchJSON();

        // Hot Products
        HotProdRV = findViewById(R.id.hot_product_recycler);
        HotProdfetchJSON();

        // Hot Products
        NewProdRV = findViewById(R.id.new_product_recycler);
        NewProdfetchJSON();


        //Trending Products
        TrendingProdRV = findViewById(R.id.trending_product_recycler);
        TrendingProdfetchJSON();


        //Trending Products
        SaleProdRV = findViewById(R.id.sale_product_recycler);
        SaleProdfetchJSON();


        //Reviews
        reviewsRV = findViewById(R.id.reviews_recycler);
        reviewsfetchJSON();


        //Blog
        blogRV = findViewById(R.id.blog_recycler);
        blogfetchJSON();


        //Brands
        BrandsProdRV = findViewById(R.id.brands_product_recycler);
        BrandsProdfetchJSON();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.getMenu().findItem(R.id.nav_category).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                drawer.close();
                Intent i = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(i);
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_blog).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                drawer.close();
                Intent i = new Intent(MainActivity.this, BlogActivity.class);
                startActivity(i);
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_login).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                drawer.close();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_vendorRe).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                drawer.close();
                Intent intent = new Intent(MainActivity.this, VendorRegisterActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                drawer.close();
                sessionManager.logOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Log Out Success", Toast.LENGTH_SHORT).show();
                finish();
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_profile).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                drawer.close();
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
                return false;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_messenger).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                drawer.close();

                Intent i1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/hrventureaiacademy"));
                i1.setPackage("com.facebook.orca");https:
                startActivity(i1);
                return false;
            }
        });

//        SharedPreferences prefs = getSharedPreferences("KOREAR_BAZAR", MODE_PRIVATE);
//        String dd=prefs.getString("name",null);
//        Toast.makeText(this, ""+dd, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void blog_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<BlogModel> blogModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("blogs");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                BlogModel blogModelRecycler = new BlogModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                blogModelRecycler.setPhoto(dataobj.getString("photo"));
                blogModelRecycler.setTitle(dataobj.getString("title"));
                blogModelRecycler.setDetails(dataobj.getString("details"));

                blogModelRecyclerArrayList.add(blogModelRecycler);

            }

            blogAdapter = new BlogAdapter(this, blogModelRecyclerArrayList);
            blogRV.setAdapter(blogAdapter);
            blogRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Reviews
    private void reviewsfetchJSON() {
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
                        reviews_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reviews_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ReviewsModel> reviewsModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("reviews");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ReviewsModel reviewsModelRecycler = new ReviewsModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                reviewsModelRecycler.setPhoto(dataobj.getString("photo"));
                reviewsModelRecycler.setTitle(dataobj.getString("title"));
                reviewsModelRecycler.setSubtitle(dataobj.getString("subtitle"));
                reviewsModelRecycler.setDetails(dataobj.getString("details"));

                reviewsModelRecyclerArrayList.add(reviewsModelRecycler);

            }

            reviewsAdapter = new ReviewsAdapter(this, reviewsModelRecyclerArrayList);
            reviewsRV.setAdapter(reviewsAdapter);
            reviewsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Sale
    private void SaleProdfetchJSON() {
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
                        Sale_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Sale_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> saleModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("sale_products");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel saleModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                saleModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                saleModelRecycler.setName(dataobj.getString("name"));

                saleModelRecycler.setSlug(dataobj.getString("slug"));


                saleModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                saleModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));

                saleModelRecyclerArrayList.add(saleModelRecycler);

            }

            SaleProdAdapter = new SaleProdAdapter(this, saleModelRecyclerArrayList,this);
            SaleProdRV.setAdapter(SaleProdAdapter);
            SaleProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Trending Products
    private void TrendingProdfetchJSON() {
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
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        Trending_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Trending_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> trendingModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("trending_products");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel trendingModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                trendingModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                trendingModelRecycler.setName(dataobj.getString("name"));

                trendingModelRecycler.setSlug(dataobj.getString("slug"));


                trendingModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                trendingModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));

                trendingModelRecyclerArrayList.add(trendingModelRecycler);

            }

            TrendingProdAdapter = new TrendingProdAdapter(this, trendingModelRecyclerArrayList, this);
            TrendingProdRV.setAdapter(TrendingProdAdapter);
            TrendingProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //New
    private void NewProdfetchJSON() {
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
                        New_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void New_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> newModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("latest_products");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel newModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                newModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                newModelRecycler.setName(dataobj.getString("name"));

                newModelRecycler.setSlug(dataobj.getString("slug"));


                newModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                newModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));

                newModelRecyclerArrayList.add(newModelRecycler);

            }

            NewProdAdapter = new NewProdAdapter(this, newModelRecyclerArrayList,this);
            NewProdRV.setAdapter(NewProdAdapter);
            NewProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Brands
    private void BrandsProdfetchJSON() {
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
                        Brands_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Brands_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<BrandsModel> brandsModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("partners");

            // Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                BrandsModel brandsModelRecycler = new BrandsModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                brandsModelRecycler.setPhoto(dataobj.getString("photo"));
                // brandsModelRecycler.setLink(dataobj.getString("link"));


                brandsModelRecyclerArrayList.add(brandsModelRecycler);

            }

            BrandsProdAdapter = new BrandsAdapter(this, brandsModelRecyclerArrayList);
            BrandsProdRV.setAdapter(BrandsProdAdapter);
            BrandsProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Hot Product
    private void HotProdfetchJSON() {
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
                        Hot_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Hot_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> hotModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("hot_products");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel hotModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                hotModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                hotModelRecycler.setName(dataobj.getString("name"));

                hotModelRecycler.setSlug(dataobj.getString("slug"));


                hotModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                hotModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));

                hotModelRecyclerArrayList.add(hotModelRecycler);

            }

            HotProdAdapter = new HotProdAdapter(this, hotModelRecyclerArrayList,this);
            HotProdRV.setAdapter(HotProdAdapter);
            HotProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Big Save Products
    private void BSProdfetchJSON() {
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
                        BS_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void BS_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> bsModelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("big_products");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel bsModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                bsModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                bsModelRecycler.setName(dataobj.getString("name"));
                bsModelRecycler.setSlug(dataobj.getString("slug"));

                bsModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                bsModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));

                bsModelRecyclerArrayList.add(bsModelRecycler);

            }

            BSProdAdapter = new BigSavePAdapter(this, bsModelRecyclerArrayList,this);
            BSProdRV.setAdapter(BSProdAdapter);
            BSProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Button Small Banner
    private void bsBannerfetchJSON() {
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
                        bsBannerWriteRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bsBannerWriteRecycler(String jsonresponse) {
        try {
            JSONObject object = new JSONObject(jsonresponse);

            ArrayList<BSBannerModel> bsBannerModelRecyclerArrayList = new ArrayList<>();
            JSONArray jsonArray = object.getJSONArray("bottom_small_banners");

            // Toast.makeText(this, ""+jsonArray, Toast.LENGTH_SHORT).show();


            for (int i = 0; i < jsonArray.length(); i++) {
                BSBannerModel bsBannerModelRecycler = new BSBannerModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                bsBannerModelRecycler.setPhoto(jsonObject.getString("photo"));

                bsBannerModelRecyclerArrayList.add(bsBannerModelRecycler);
            }

            bsBannerAdapter = new BSBannerAdapter(this, bsBannerModelRecyclerArrayList);
            bsBannerRecyclerView.setAdapter(bsBannerAdapter);
            bsBannerRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //Top Rated Products

    private void TRProdfetchJSON() {
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
                        TR_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void TR_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> trmodelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("top_products");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel trModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                trModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                trModelRecycler.setName(dataobj.getString("name"));
                trModelRecycler.setSlug(dataobj.getString("slug"));
                trModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                trModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));



                trmodelRecyclerArrayList.add(trModelRecycler);

            }

            TRProdAdapter = new TopRatedPAdapter(this, trmodelRecyclerArrayList,this);
            TRProdRV.setAdapter(TRProdAdapter);
            TRProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

//            }else {
//                Toast.makeText(TestRMain.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }



    // Large Banner
    private void lBannerfetchJSON() {
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
                        lBannerWriteRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void lBannerWriteRecycler(String jsonresponse) {
        try {
            JSONObject object = new JSONObject(jsonresponse);

            ArrayList<LargeBannerModel> lBannerModelRecyclerArrayList = new ArrayList<>();
            JSONArray jsonArray = object.getJSONArray("large_banners");

            // Toast.makeText(this, ""+jsonArray, Toast.LENGTH_SHORT).show();


            for (int i = 0; i < jsonArray.length(); i++) {
                LargeBannerModel lBannerModelRecycler = new LargeBannerModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                lBannerModelRecycler.setPhoto(jsonObject.getString("photo"));

                lBannerModelRecyclerArrayList.add(lBannerModelRecycler);
            }

            lBannerAdapter = new LargeBannerAdapter(this, lBannerModelRecyclerArrayList);
            lBannerRecyclerView.setAdapter(lBannerAdapter);
            lBannerRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //Flash Deal
    private void fdProdfetchJSON() {
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
                        fd_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fd_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> fdtmodelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("discount_products");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel fdModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                fdModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                fdModelRecycler.setName(dataobj.getString("name"));
                fdModelRecycler.setSlug(dataobj.getString("slug"));
                fdModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                fdModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));



                fdtmodelRecyclerArrayList.add(fdModelRecycler);

            }

            fdProdAdapter = new FlashDealAdapter(this, fdtmodelRecyclerArrayList,this);
            fdProdRV.setAdapter(fdProdAdapter);
            fdProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

//            }else {
//                Toast.makeText(TestRMain.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //best Products
    private void bestProdfetchJSON() {
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
                        best_prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void best_prod_writeRecycler(String jsonresponse) {
        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(jsonresponse);
//            if(obj.optString("status").equals("true")){

            ArrayList<ProdModel> bestmodelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("best_products");

            for (int i = 0; i < dataArray.length(); i++) {

                ProdModel bestModelRecycler = new ProdModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                bestModelRecycler.setThumbnail(dataobj.getString("thumbnail"));
                bestModelRecycler.setName(dataobj.getString("name"));
                bestModelRecycler.setShowPrice(dataobj.getString("showPrice"));
                bestModelRecycler.setShowPreviousPrice(dataobj.getString("showPreviousPrice"));
                bestModelRecycler.setSlug(dataobj.getString("slug"));

                bestmodelRecyclerArrayList.add(bestModelRecycler);

            }

            bestProdAdapter = new BestSellerAdapter(this, bestmodelRecyclerArrayList, this);
            bestProdRV.setAdapter(bestProdAdapter);
            bestProdRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


//            }else {
//                Toast.makeText(TestRMain.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    //Banner Small Top
    private void BannerSTfetchJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<String> call = api.getProducts();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        bannerSTWriteRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void bannerSTWriteRecycler(String jsonresponse) {
        try {
            JSONObject object = new JSONObject(jsonresponse);

            ArrayList<BannerTopSmallModel> bannerTSmodelRecyclerArrayList = new ArrayList<>();
//            JSONArray jsonArray = object.getJSONArray("feature_products");
            JSONArray jsonArray = object.getJSONArray("top_small_banners");

            //Toast.makeText(this, ""+jsonArray, Toast.LENGTH_SHORT).show();


            for (int i = 0; i < jsonArray.length(); i++) {
                BannerTopSmallModel bannerTSrModelRecycler = new BannerTopSmallModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                bannerTSrModelRecycler.setPhoto(jsonObject.getString("photo"));
                bannerTSrModelRecycler.setLink(jsonObject.getString("link"));

                bannerTSmodelRecyclerArrayList.add(bannerTSrModelRecycler);
            }

            bannerSTAdapter = new BannerTopSmallAdapter(this, bannerTSmodelRecyclerArrayList);
            bannerSTRecyclerView.setAdapter(bannerSTAdapter);
            bannerSTRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //Products
    private void prodfetchJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getProducts();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        prod_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prod_writeRecycler(String jsonresponse) {
        try {
            JSONObject object = new JSONObject(jsonresponse);

            ArrayList<ProdModel> prodmodelRecyclerArrayList = new ArrayList<>();
            JSONArray jsonArray = object.getJSONArray("feature_products");
            JSONArray contacts = object.getJSONArray("sliders");


            for (int i = 0; i < jsonArray.length(); i++) {
                ProdModel prodModelRecycler = new ProdModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                prodModelRecycler.setThumbnail(jsonObject.getString("thumbnail"));
                prodModelRecycler.setName(jsonObject.getString("name"));

                prodModelRecycler.setShowPrice(jsonObject.getString("showPrice"));
//                String s = jsonObject.getString("showPrice");
//                float ss = Float.parseFloat(s);
//                String sss = String.valueOf(ss);
//                prodModelRecycler.setPrice(s);
                //txtPrice.setText(sss);

                prodModelRecycler.setShowPreviousPrice(jsonObject.getString("showPreviousPrice"));


                String totalPrice = jsonObject.getString("showPrice");

                String totalPreviousPrice = jsonObject.getString("showPreviousPrice");



                prodModelRecycler.setSlug(jsonObject.getString("slug"));

                prodmodelRecyclerArrayList.add(prodModelRecycler);
            }

            prodAdapter = new ProdAdapter(this, prodmodelRecyclerArrayList, this);
            prodRecyclerView.setAdapter(prodAdapter);
            prodRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //////Category///////

    private void fetchJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<String> call = api.getCategory();

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
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeRecycler(String response) {

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
//            if(obj.optString("status").equals("true")){

            ArrayList<CateModel> modelRecyclerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("categories");

            //Toast.makeText(this, ""+dataArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < dataArray.length(); i++) {

                CateModel modelRecycler = new CateModel();
                JSONObject dataobj = dataArray.getJSONObject(i);


                modelRecycler.setPhoto(dataobj.getString("photo"));
                modelRecycler.setName(dataobj.getString("name"));
                modelRecycler.setSlug(dataobj.getString("slug"));

                modelRecyclerArrayList.add(modelRecycler);

            }

            retrofitAdapter = new CateAdapter(this, modelRecyclerArrayList);
            cateRecyclerView.setAdapter(retrofitAdapter);
            cateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

//            }else {
//                Toast.makeText(TestRMain.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }


    }


    //Slider
    private void sliderfetchJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<String> call = api.getProducts();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        sliderWriteRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sliderWriteRecycler(String jsonresponse) {
        try {
            JSONObject object = new JSONObject(jsonresponse);

            ArrayList<SlidersModel> slidermodelRecyclerArrayList = new ArrayList<>();
//            JSONArray jsonArray = object.getJSONArray("feature_products");
            JSONArray jsonArray = object.getJSONArray("sliders");

            // Toast.makeText(this, ""+jsonArray, Toast.LENGTH_SHORT).show();


            for (int i = 0; i < jsonArray.length(); i++) {
                SlidersModel sliderModelRecycler = new SlidersModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                sliderModelRecycler.setPhoto(jsonObject.getString("photo"));

                slidermodelRecyclerArrayList.add(sliderModelRecycler);
            }

            sliderAdapter = new SlidersAdapter(this, slidermodelRecyclerArrayList);
            sliderRecyclerView.setAdapter(sliderAdapter);
            sliderRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }




    @Override
    public void setProd(ProdModel newProdModel) {
        if (newProdModel != null) {
            Toast.makeText(this, "" + newProdModel.getSlug(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ItemDetailsActivity.class);
            i.putExtra("prodctModel", newProdModel.getSlug());
            i.putExtra("price",newProdModel.getShowPrice());
            startActivity(i);
        }
    }

//    @Override
//    public void setCategory(CateModel cateModel) {
//        if (cateModel != null) {
//            Toast.makeText(this, "Click category Item" + cateModel.getSlug(), Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this, ItemDetailsActivity.class);
//            i.putExtra("categoryModel", cateModel);
//            startActivity(i);
//        }
//    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (sessionManager.isLogin())
//        {
//            Toast.makeText(this, "Session Done", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(MainActivity.this,MainActivity.class);
//            startActivity(i);
//            finish();
//        }
//        else {
//
//        }
//    }

    }
