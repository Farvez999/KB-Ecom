package com.korearbazar.ecom;

import com.korearbazar.ecom.UserProfile.MessageResponse;
import com.korearbazar.ecom.model.CheckOutResponse;
import com.korearbazar.ecom.model.SignInResponse;
import com.korearbazar.ecom.model.SignUpResponse;
import com.korearbazar.ecom.model.VendorUpResponse;
import com.korearbazar.ecom.model.WithdrawResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

     String JSONURL = "http://ecom.hrventure.xyz/public/";
    //  String JSONURL = "https://korearbazar.com/public/";
    String CategoryImgUrl = "assets/images/categories/";
    String ProdImgUrl = "assets/images/thumbnails/";
    String SlidersImgUrl = "assets/images/sliders/";
    String BannerImgUrl = "assets/images/banners/";
    String BrandsImgUrl = "assets/images/partner/";
    String ReviewsImgUrl = "assets/images/reviews/";
    String BlogImgUrl = "assets/images/blogs/";
    String PDetailsImgUrl = "assets/images/products/";
    String RelatedProductImgUrl = "assets/images/thumbnails/";
    String CategoryDetailsProds = "assets/images/thumbnails/";
    String ProdDetailsUrl = "api/item/";
    String CategoryDetails = "api/category/";


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

    @GET("api/user/dashboard")
    Call<String> getOrder(
            @Header("Authorization") String Bearer
    );

    @GET("api/user/affilate/code")
    Call<String> getAffilateCode(
            @Header("Authorization") String Bearer
    );

    @GET("api/user/affilate/withdraw")
    Call<String> getAffilateWithdraw(
            @Header("Authorization") String Bearer
    );

    @GET("api/user/messages")
    Call<String> getMessages(
            @Header("Authorization") String Bearer
    );

    @GET("api/user/favorite/seller")
    Call<String> getFavoriteSelle(
            @Header("Authorization") String Bearer
    );


    //Vendor Order
    @GET("api/vendor/dashboard")
    Call<String> getVendorDashboard(
            @Header("Authorization") String Bearer
    );

    //Vendor Total Product
    @GET("api/vendor/products")
    Call<String> getVendorproducts(
            @Header("Authorization") String Bearer
    );

    //Vendor All Order
    @GET("api/vendor/orders")
    Call<String> getVendorOrder(
            @Header("Authorization") String Bearer
    );

    @GET("api/vendor/products")
    Call<String> getVendorProduct(
            @Header("Authorization") String Bearer
    );

    @GET("api/vendor/withdraw")
    Call<String> getVendorWithdraw(
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
    @retrofit.http.POST("/user/register")// API's endpoints
    public void vendorRegistration(@Field("name") String name,
                                   @Field("email") String email,
                                   @Field("phone") String phone,
                                   @Field("address") String address,
                                   @Field("password") String password,
                                   @Field("password_confirmation") String password_confirmation,
                                   @Field("shop_name") String shop_name,
                                   @Field("owner_name") String owner_name,
                                   @Field("shop_number") String shop_number,
                                   @Field("shop_address") String shop_address,
                                   @Field("reg_number") String reg_number,
                                   @Field("shop_message") String shop_message,
                                   @Field("vendor") String vendor,
                                   @Field("logintype") String logintype,
                                   Callback<VendorUpResponse> callback);


    @FormUrlEncoded
    @retrofit.http.POST("/cashondelivery")// API's endpoints
    public void checkout(@Field("personal_name") String personal_name,
                         @Field("personal_email") String personal_email,
                         @Field("shipping") String shipping,
                         @Field("pickup_location") String pickup_location,
                         @Field("name") String name,
                         @Field("phone") String phone,
                         @Field("email") String email,
                         @Field("address") String address,
                         @Field("customer_country") String customer_country,
                         @Field("city") String city,
                         @Field("zip") String zip,
                         @Field("shipping_name") String shipping_name,
                         @Field("shipping_email") String shipping_email,
                         @Field("shipping_phone") String shipping_phone,
                         @Field("shipping_address") String shipping_address,
                         @Field("shipping_country") String shipping_country,
                         @Field("shipping_city") String shipping_city,
                         @Field("shipping_zip") String shipping_zip,
                         @Field("method") String method,
                         @Field("shipping_cost") String shipping_cost,
                         @Field("packing_cost") String packing_cost,
                         @Field("dp") String dp,
                         @Field("tax") String tax,
                         @Field("totalQty") String totalQty,
                         @Field("vendor_shipping_id") String vendor_shipping_id,
                         @Field("vendor_packing_id") String vendor_packing_id,
                         @Field("total") String total,
                         @Field("user_id") Integer user_id,
                         @FieldMap Map<String, String> cartInfo,
                         @Field("logintype") String logintype,
                         Callback<CheckOutResponse> callback);

//    @FormUrlEncoded
//    @retrofit.http.POST("/user/affilate/withdraw/create")
//    Call<WithdrawResponse>withdraw(@Field("method") EditText name,
//                                   @Field("amount") EditText email,
//                                   @Field("acc_email") EditText phone,
//                                   @Field("reference") EditText address);

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/user/affilate/withdraw/create")     // API's endpoints
    public void withdraw(@Field("method") String method,
                         @Field("amount") String amount,
                         @Field("acc_name") String acc_name,
                         @Field("reference") String reference,
                         @Field("logintype") String logintype,
                         Callback<WithdrawResponse> callback);

    @FormUrlEncoded // annotation used in POST type requests

   // @Headers({"Authorization"})
    @POST("user/user/contact")     // API's endpoints
    public void UserPmeaasgeSend(@Header("Authorization") String Bearer,
                                 @Field("to") String to,
                                 @Field("subject") String subject,
                                 @Field("body") String body,
                                 @Field("logintype") String logintype,
                                 Callback<MessageResponse> callback);

    //


}
