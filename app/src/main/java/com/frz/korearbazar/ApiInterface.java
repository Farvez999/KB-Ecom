package com.frz.korearbazar;

import com.frz.korearbazar.model.CheckOutResponse;
import com.frz.korearbazar.model.SignInResponse;
import com.frz.korearbazar.model.SignUpResponse;
import com.frz.korearbazar.model.TokenResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiInterface {

       String JSONURL = "http://ecom.hrventure.xyz/public/";
    //  String JSONURL = "http://192.168.0.108/project/hrv-ecom/public/";
    String CategoryImgUrl = "assets/images/categories/";
    String ProdImgUrl = "assets/images/thumbnails/";
    String SlidersImgUrl = "assets/images/sliders/";
    String BannerImgUrl = "assets/images/banners/";
    String BrandsImgUrl = "assets/images/partner/";
    String ReviewsImgUrl = "assets/images/reviews/";
    String BlogImgUrl = "assets/images/blogs/";
    String PDetailsImgUrl = "assets/images/products/";
    String RelatedProductImgUrl = "assets/images/thumbnails/";
    String ProdDetailsUrl= "api/item/";
    String CategoryDetails= "api/category/";


    //set false if you want price to be displayed in decimal
    public static final boolean ENABLE_DECIMAL_ROUNDING = true;

    @GET("api/setting")
    Call<String> getString();

    @GET("api/setting")
    Call<String> getCategory();

    @GET("api/category")
    Call<String> getCategoryDetails();


    @GET("api")
    Call<String> getProducts();

    @GET("api/extra")
    Call<String> getBestProducts();

    @GET("api/user/profile")
    Call<String> getProfile(
            @Header("Authorization") String Bearer
    );

//    @GET("api/profile")
//    Call<String> getProfile(
//            @Header("Authorization") String Bearer
//    );


    @GET("api/item/")
    Call<String> getRelatedProductsDetails();

    @GET("api/products")
    Call<String> getProductsDetails();





    @FormUrlEncoded
    @retrofit.http.POST("/login")// API's endpoints
    public void login(@Field("email") String email,
                      @Field("password") String password,
                      @Field("logintype") String logintype,
                      //@Header("Authorization") String Bearer,
                      Callback<SignInResponse> callback);

//    @FormUrlEncoded
//    @retrofit.http.GET("api/profile")// API's endpoints
//    public void profile(@Header("Authorization") String Bearer,
//                      Callback<SignInResponse> callback);



    @FormUrlEncoded
    @retrofit.http.POST("/user/register")// API's endpoints
    public void registration(@retrofit.http.Field("name") String name,
                             @retrofit.http.Field("email") String email,
                             @retrofit.http.Field("phone") String phone,
                             @retrofit.http.Field("address") String address,
                             @retrofit.http.Field("password") String password,
                             @retrofit.http.Field("password_confirmation") String password_confirmation,
                             @Field("logintype") String logintype,
                             Callback<SignUpResponse> callback);



    @FormUrlEncoded
    @retrofit.http.POST("/cashondelivery")// API's endpoints
    public void checkout(@retrofit.http.Field("personal_name") String personal_name,
                         @retrofit.http.Field("personal_email") String personal_email,

                         @retrofit.http.Field("shipping") String shipping,
                         @retrofit.http.Field("pickup_location") String pickup_location,

                         @retrofit.http.Field("name") String name,
                         @retrofit.http.Field("phone") String phone,
                         @retrofit.http.Field("email") String email,
                         @retrofit.http.Field("address") String address,
                         @retrofit.http.Field("customer_country") String customer_country,
                         @retrofit.http.Field("city") String city,
                         @retrofit.http.Field("zip") String zip,



                         @retrofit.http.Field("shipping_name") String shipping_name,
                         @retrofit.http.Field("shipping_email") String shipping_email,
                         @retrofit.http.Field("shipping_phone") String shipping_phone,
                         @retrofit.http.Field("shipping_address") String shipping_address,
                         @retrofit.http.Field("shipping_country") String shipping_country,
                         @retrofit.http.Field("shipping_city") String shipping_city,
                         @retrofit.http.Field("shipping_zip") String shipping_zip,


    @retrofit.http.Field("method") String method,
    @retrofit.http.Field("shipping_cost") String shipping_cost,
    @retrofit.http.Field("packing_cost") String packing_cost,
    @retrofit.http.Field("dp") String dp,
    @retrofit.http.Field("tax") String tax,
    @retrofit.http.Field("totalQty") String totalQty,
    @retrofit.http.Field("vendor_shipping_id") String vendor_shipping_id,
    @retrofit.http.Field("vendor_packing_id") String vendor_packing_id,
    @retrofit.http.Field("total") String total,

                         @retrofit.http.Field("user_id") String user_id,
                         @retrofit.http.FieldMap Map<String, String> cartInfo,
                         //@Field("logintype") String logintype,
                         Callback<CheckOutResponse> callback);


    //void checkout(String trim, String trim1, String shipping, String pickup_location, String trim2, String trim3, String trim4, String trim5, String trim6, String trim7, String trim8, String shipping_name, String shipping_email, String shipping_phone, String shipping_address, String shipping_country, String shipping_city, String shipping_zip, String s, String shipping_cost, String packing_cost, String s1, String s2, String s3, String s4, String s5, String trim9, Integer integer, Map<String, String> map, String email, Callback<CheckOutResponse> checkOutResponseCallback);
}
//    @retrofit.http.Field("personal_name") String personal_name,
//    @retrofit.http.Field("personal_email") String personal_email,
//    @retrofit.http.Field("shipping") String shipping,
//    @retrofit.http.Field("pickup_location") String pickup_location,
//    @retrofit.http.Field("name") String name,
//    @retrofit.http.Field("phone") String phone,
//
//    @retrofit.http.Field("email") String email,
//    @retrofit.http.Field("address") String address,
//    @retrofit.http.Field("customer_country") String customer_country,
//    @retrofit.http.Field("city") String city,
//    @retrofit.http.Field("zip") String zip,
//
//    @retrofit.http.Field("shipping_name") String shipping_name,
//    @retrofit.http.Field("shipping_email") String shipping_email,
//    @retrofit.http.Field("shipping_phone") String shipping_phone,
//    @retrofit.http.Field("shipping_address") String shipping_address,
//    @retrofit.http.Field("shipping_country") String shipping_country,
//
//    @retrofit.http.Field("shipping_city") String shipping_city,
//    @retrofit.http.Field("shipping_zip") String shipping_zip,
//    @retrofit.http.Field("method") String method,
//    @retrofit.http.Field("shipping_cost") String shipping_cost,
//    @retrofit.http.Field("shipping_cost") String packing_cost,
//
//    @retrofit.http.Field("dp") String dp,
//    @retrofit.http.Field("tax") String tax,
//    @retrofit.http.Field("totalQty") String totalQty,
//    @retrofit.http.Field("vendor_shipping_id") String vendor_shipping_id,
//    @retrofit.http.Field("vendor_packing_id") String vendor_packing_id,
//    @retrofit.http.Field("total") String total,
//    @FieldMap Map<String, String> cartInfo,