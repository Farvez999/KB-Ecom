package com.korearbazar.ecom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.korearbazar.ecom.Database.CartDB;
import com.korearbazar.ecom.MainActivity;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.ProdDetailsAdapter;
import com.korearbazar.ecom.adapter.RelatedProdAdapter;
import com.korearbazar.ecom.model.CartModel;
import com.korearbazar.ecom.model.ProdDetailsModel;
import com.korearbazar.ecom.model.ProdModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static com.korearbazar.ecom.ApiInterface.JSONURL;
import static com.korearbazar.ecom.ApiInterface.PDetailsImgUrl;
import static com.korearbazar.ecom.ApiInterface.ProdDetailsUrl;


public class ItemDetailsActivity extends AppCompatActivity  {

    //Related Products
    private ArrayList<ProdDetailsModel> mExampleList;
//    private ArrayList<RelatedProdModel> rExampleList;
    private ArrayList<ProdModel> rExampleList;
    private RelatedProdAdapter mExampleAdapter;
    private RecyclerView mRecyclerView;

    private RequestQueue mRequestQueue;
    private RequestQueue rRequestQueue;
    ProdDetailsAdapter prodDetailsAdapter;
    LinearLayout linearLayout;


    ProdDetailsModel prodDetailsModel;
    String totalPrice;


   ProdModel prodModel;
   CartDB cartDB;
   String imgUrl = "";


    ImageView imgBack;
    ImageView imgCart;
    TextView txtTcount;
    RelativeLayout lvlCart;
    TextView txtTitle;
    TextView txtDesc;
    TextView btnAddtocart,buyNow,continue_shopping;
    //String Uri="http://ecom.hrventure.xyz/carts";

    TextView txtPrice;
    TextView txt_previous_price;
    TextView txt_percentage;
    TextView txtItemOffer;
    TextView txt_stoke,txt_size,txt_color;
    LinearLayout count;
    TextView outOfStoke;
    ImageView plusquantity, minusquantity;
    int quantity = 1;
    TextView quantitynumber;
    String slug;
    String price;

    String pdp="Farvez";
    String Size_qty = "";
    String pStock = "One";

    String pproduct_id = "";



    //Slider
    ImageView imgDtails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);


        cartDB = new CartDB(this);

        imgBack = findViewById(R.id.img_back);
        imgCart = findViewById(R.id.img_cart);
        //txtTcount = findViewById(R.id.txt_tcount);
        lvlCart = findViewById(R.id.lvl_cart);
        txtTitle = findViewById(R.id.txt_title);
        txtDesc = findViewById(R.id.txt_desc);
        txt_size = findViewById(R.id.txt_size);
        txt_color = findViewById(R.id.txt_color);
        txtPrice = findViewById(R.id.txt_price);
        txt_previous_price = findViewById(R.id.txt_previous_price);
        txt_percentage = findViewById(R.id.txt_percentage);
        btnAddtocart = findViewById(R.id.addToCart);
        buyNow = findViewById(R.id.buyNow);
        continue_shopping = findViewById(R.id.continue_shopping);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity  = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);

        txtPrice = findViewById(R.id.txt_price);
        txtItemOffer = findViewById(R.id.txt_item_offer);
        txt_stoke = findViewById(R.id.txt_stoke);
        count = findViewById(R.id.count);
        outOfStoke = findViewById(R.id.outOfStoke);



///Related Product
        mRecyclerView = findViewById(R.id.recycler_releted);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        mRequestQueue = Volley.newRequestQueue(this);
        mExampleList = new ArrayList<>();

        rExampleList = new ArrayList<>();
        rRequestQueue = Volley.newRequestQueue(this);

        //All product receive
        Bundle intent = getIntent().getExtras();
        if (intent!=null){
            slug=intent.getString("prodctModel");
            price = intent.getString("price");
        }
//        Toast.makeText(this, ""+slug, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Total"+price, Toast.LENGTH_SHORT).show();
        //Product Details
        getdata();

        ///Related Product
        getRelatedProduct();


        imgDtails =findViewById(R.id.imgDtails);



        linearLayout = findViewById(R.id.linearLayout1);


//        prodDetailsModel.getShowPrice();
//        Toast.makeText(this, "Get Show Price"+prodDetailsModel.getShowPrice(), Toast.LENGTH_SHORT).show();

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Price= totalPrice;

                String pricWithStr = Price;
                pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
                float pp = Float.parseFloat(pricWithStr);


                double basePrice = pp;
                quantity++;
                displayQuantity();
                double productPrice = basePrice * quantity;
                String setnewPrice = String.valueOf(productPrice);
                txtPrice.setText(setnewPrice);

                Log.e("new price",setnewPrice);
               // Toast.makeText(ItemDetailsActivity.this, ""+setnewPrice, Toast.LENGTH_SHORT).show();
            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String pricWithStr = prodModel.getShowPrice();
                String Price= totalPrice;
                String pricWithStr = Price;
                pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
                float pp = Float.parseFloat(pricWithStr);

                double basePrice = pp;
                // because we dont want the quantity go less than 0
                if (quantity == 1) {
                    Toast.makeText(ItemDetailsActivity.this, "Cant Cart quantity < 1", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    double productPrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(productPrice);
                    txtPrice.setText(setnewPrice);
                }
            }
        });

        continue_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemDetailsActivity.this, MainActivity.class));
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemDetailsActivity.this, MainActivity.class));
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ItemDetailsActivity.this, "Buy Now", Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://ecom.hrventure.xyz/carts"));
                Intent intent1 = new Intent(ItemDetailsActivity.this,CartActivity.class);
                startActivity(intent1);

                SaveCart();
            }
        });

        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ItemDetailsActivity.this, CartActivity.class));

                SaveCart();
            }
        });

        outOfStoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ItemDetailsActivity.this, "The product is not audible in stock.", Toast.LENGTH_SHORT).show();
            }
        });

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ItemDetailsActivity.this,CartActivity.class);
                startActivity(intent1);
            }
        });


    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    //Cart data save
    private void SaveCart() {

        // getting the values from our views
        String name = txtTitle.getText().toString();
        String price = txtPrice.getText().toString();
        String quantity = quantitynumber.getText().toString();
        String size_qty = Size_qty;
        String dp = pdp;
        String stock = pStock;
        String product_id = pproduct_id;
        Log.e("PProduct_id",product_id);
        Log.e("PSO",stock);
        Log.e("PD",dp);
        Log.e("QUAAN",quantity);


        CartModel cartModel = new CartModel(name,price,quantity,size_qty,imgUrl,dp,stock,product_id);
        long insertData=  cartDB.addInsert(cartModel);
        if (insertData>0){
            List<CartModel>list= new ArrayList<>();
            for (int i=0; i<cartDB.getAllData().size();i++){
                cartDB.getAllData();
                Log.e("getAllData",cartDB.getAllData().get(i).toString());
            }
            Toast t = Toast.makeText( getApplicationContext(), "Successfully Added to Cart!" + insertData, Toast.LENGTH_LONG );
            t.show();
        }else {
            Toast t = Toast.makeText( getApplicationContext(), "Not Added to Cart!", Toast.LENGTH_LONG );
            t.show();
        }

    }




    //Product Details
    public void getdata(){
        //String url = "http://ecom.hrventure.xyz/public/api/item/"+slug;
        String url = JSONURL+ProdDetailsUrl+slug;


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jsonArray = response.getJSONObject("productt");

                            //JSONObject ja = response.getJSONObject("user");
                           // Toast.makeText(ItemDetailsActivity.this, "Hello User"+jsonArray, Toast.LENGTH_SHORT).show();
                            String id = jsonArray.getString("id");
                            pproduct_id=id;
                            String name = jsonArray.getString("name");
                            String details = jsonArray.getString("details");
                            String stock = jsonArray.getString("stock");
                           // pStock = stock;
                           // Log.e("PST",pStock);
                            String photo = jsonArray.getString("photo");
                            String slug = jsonArray.getString("slug");
                            String size = jsonArray.getString("size");
                            String size_qty = jsonArray.getString("size_qty");
                            String size_price = jsonArray.getString("size_price");

                            String color = jsonArray.getString("color");
                            String myColor = color;

                            String galleries = jsonArray.getString("galleries");
                            String showPrice = jsonArray.getString("showPrice");
                            String setCurrency = jsonArray.getString("setCurrency");
                            String showPreviousPrice = jsonArray.getString("showPreviousPrice");
                            String user = jsonArray.getString("user");


                            //int price = jsonArray.getInt("price");

                            mExampleList.add(new ProdDetailsModel(id,name,details,stock,photo,slug,size,size_qty,size_price,color,galleries,showPrice,setCurrency,showPreviousPrice,user));

                            txtTitle.setText(jsonArray.getString("name"));
                            txtDesc.setText(jsonArray.getString("details"));


                            ////////////Price

                            totalPrice = jsonArray.getString("showPrice");

                            String totalPreviousPrice = jsonArray.getString("showPreviousPrice");

                            if (totalPreviousPrice.equals(""))
                            {
                                txtPrice.setText(totalPrice);
                            }
                            else {
                                txtPrice.setText(totalPrice);

                                SpannableString ss = new SpannableString(totalPreviousPrice);
                                StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                                ss.setSpan(strikethroughSpan,0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


                                txt_previous_price.setText(ss);

                                String pricWithStr = totalPrice;
                                pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
                                float pp = Float.parseFloat(pricWithStr);

                                String pPricWithStr = totalPreviousPrice;
                                pPricWithStr = pPricWithStr.replaceAll("[^\\d.]", "");
                                float ppp = Float.parseFloat(pPricWithStr);


                                float diff = (ppp - pp);
                                if (diff > 0 ){
                                    float dif = (diff / ppp) * 100;
                                    String fff= String.valueOf(dif);

                                    int b = Math.round(dif);
                                    String fa = String.valueOf(b+" %off");

                                    txt_percentage.setText(fa);
                                }else {
                                    Toast.makeText(ItemDetailsActivity.this, "Not a Previous Price", Toast.LENGTH_SHORT).show();
                                }
                            }

                            ///////////////////Price


                            //txt_stoke.setText("stock");
                            if (jsonArray.getString("stock") == (String.valueOf(0))) {
                                txt_stoke.setText("Out Of Stock");
                                //txt_stoke.setTextColor(R.color.colorRad);
                                count.setVisibility(View.GONE);
                                outOfStoke.setVisibility(View.VISIBLE);
                                buyNow.setVisibility(View.GONE);
                                btnAddtocart.setVisibility(View.GONE);
                            } else {
                                txt_stoke.setText("In Stock");
                                //txt_stoke.setTextColor(R.color.colorAccent);
                            }




                            txtDesc.setText(jsonArray.getString("details"));
                            txt_size.setText(jsonArray.getString("size"));
                            txt_color.setText(myColor);
                            //Picasso.get().load("http://ecom.hrventure.xyz/assets/images/products/1606560895gDvz8eUj.png").into(imgDtails);
                            imgUrl = JSONURL+PDetailsImgUrl+photo;
                            Picasso.get().load(imgUrl).into(imgDtails);
                            pStock = stock;
                            //product_id = id;


                            prodDetailsAdapter = new ProdDetailsAdapter(ItemDetailsActivity.this, mExampleList);

                            // mRecyclerView.setAdapter(mExampleAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    //Related Product
    private void getRelatedProduct() {
        String url = JSONURL+ProdDetailsUrl+slug;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //ArrayList<ProdModel> prodmodelRecyclerArrayList = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("trending");
                           // Toast.makeText(ItemDetailsActivity.this, "Trending Done "+jsonArray, Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String id = hit.getString("id");
                                String user_id = hit.getString("user_id");
                                String name = hit.getString("name");
                                String slug = hit.getString("slug");
                                String features = hit.getString("features");
                                String colors = hit.getString("colors");
                                String thumbnail = hit.getString("thumbnail");
                                String price = hit.getString("price");
                                String previous_price = hit.getString("previous_price");
                                String attributes = hit.getString("attributes");
                                String size = hit.getString("size");
                                String size_price = hit.getString("size_price");
                                String discount_date = hit.getString("discount_date");

                                String showPrice = hit.getString("showPrice");
                                String setCurrency = hit.getString("setCurrency");
                                String showPreviousPrice = hit.getString("showPreviousPrice");

//rExampleList
                                rExampleList.add(new ProdModel(id,user_id,name, slug,features,colors, thumbnail,price,previous_price,attributes,size,size_price,discount_date,showPrice,setCurrency,showPreviousPrice));
                            }
                            mExampleAdapter = new RelatedProdAdapter(ItemDetailsActivity.this, rExampleList);
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


//    @Override
//    public void setProd(ProdModel prodModel) {
//        if (prodModel != null) {
//            Toast.makeText(this, "" + prodModel.getSlug(), Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this, ItemDetailsActivity.class);
//            i.putExtra("prodctModel", prodModel.getSlug());
//            startActivity(i);
//        }
//    }
}
