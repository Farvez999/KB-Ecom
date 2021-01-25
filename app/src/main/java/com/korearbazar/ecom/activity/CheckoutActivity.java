package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.korearbazar.ecom.Api;
import com.korearbazar.ecom.Database.CartDB;
import com.korearbazar.ecom.MainActivity;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.CheckOutAdapter;
import com.korearbazar.ecom.model.CartModel;
import com.korearbazar.ecom.model.CheckOutResponse;
import com.korearbazar.ecom.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CheckoutActivity extends AppCompatActivity {

    CheckOutResponse checkOutResponseData;
    Integer userId=13;
    SessionManager sessionManager;
    CartDB cartDB;
    ProgressDialog progressDialog;
    List<CartModel> data;
    String data_order_list = "";
    String str_currency_code;

    TextView personal_name, personal_email;
    TextView txt_continus;
    EditText name, phone, email, address, customer_country, city, zip;
    EditText edt_order_list;
    EditText total;


    String shipping = "shipto";
    String pickup_location = "Bangladesh";

//    String shipping_name="jubayer Api";
//    String shipping_email="jubayerk7@gmail.com";
//    String shipping_phone= "01710029052";
//    String shipping_address="DHaka, Bangladesh";
//    String shipping_country="Bangladesh";
//    String shipping_city="Dhaka";
//    String shipping_zip="3456";

    EditText shipping_name, shipping_email, shipping_phone, shipping_address, shipping_country, shipping_city, shipping_zip;

    String shipping_cost = String.valueOf(0);
    String packing_cost = String.valueOf(0);

    String method = "cash on delivery";
    String dp = "0";
    String tax = "0";
    String totalQty = "2";
    String vendor_shipping_id = "0";
    String vendor_packing_id = "0";

//    String personal_name="user";
//    String personal_email="user@gmail.com";

//    String name="USER";
//            String phone="34534534534";
//                    String email="user@gmail.com";
//                            String address="Test Address";
//                                    String customer_country="Bangladesh";
//                                            String city="Test City";
//                                                    String zip="1231";
//
//                                                    String total="1000";
    //String user_id="22";


    Map<String, String> map;

    List<CartModel> cartModelList;
    // List<CheckOutModel> checkoutModelList;

    CheckOutAdapter cartAdapter;

    Double totalPrice;

    RecyclerView orderRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setTitle("Check Out");

        sessionManager = new SessionManager(this);

        cartDB = new CartDB(this);
        cartModelList = new ArrayList<>();

        orderRecyclerView = findViewById(R.id.checkRecyclerView);

        personal_name = findViewById(R.id.username);
        personal_email = findViewById(R.id.user_email);

        name = findViewById(R.id.fullName);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        customer_country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        zip = findViewById(R.id.postCode);

        //EditText shipping_name,shipping_email,shipping_phone,shipping_address,shipping_country,shipping_city,shipping_zip;

        shipping_name = findViewById(R.id.shipping_name);
        shipping_email = findViewById(R.id.shipping_email);
        shipping_phone = findViewById(R.id.shipping_phone);
        shipping_address = findViewById(R.id.shipping_address);
        shipping_country = findViewById(R.id.shipping_country);
        shipping_city = findViewById(R.id.shipping_city);
        shipping_zip = findViewById(R.id.shipping_zip);

        edt_order_list = findViewById(R.id.edt_order_list);
        total = findViewById(R.id.edt_order_total);

        txt_continus = findViewById(R.id.txt_continus);
        cartModelList=cartDB.getAllData();


        map = new HashMap<>();
        for (int i = 0; i < cartModelList.size(); i++) {
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][qty]", cartModelList.get(i).getQuantity());
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][size_key]","0");
//            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][size_qty]", cartModelList.get(i).getSize_qty());
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][size_qty]", "1");
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][size_price]","");
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][size]","");
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][color]","");
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][stock]", cartModelList.get(i).getStock());
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][price]", cartModelList.get(i).getPrice());
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][license]","");
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][dp]", cartModelList.get(i).getDp());//dp product e nai but dp input na dile checkout hobe na..kichu na hole 0 dite hobe
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][keys]","");
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][values]","");
            map.put("cart[items][" + cartModelList.get(i).getProduct_id() + "][product_id]", String.valueOf(cartModelList.get(i).getProduct_id()));
        }
        //Log.e("mappsize_qty",map.get("cart[items][" + cartModelList.get(0).getProduct_id() + "][size_qty]"));


        if (cartDB.getAllData().size() > 0) {
            cartModelList = cartDB.getAllData();
            cartAdapter = new CheckOutAdapter(this, cartModelList);

            orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            orderRecyclerView.setAdapter(cartAdapter);


            Toast.makeText(this, "Cart " + cartModelList, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cart is Empty", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences prefs = getSharedPreferences("KOREAR_BAZAR", MODE_PRIVATE);
        String userName = prefs.getString("name", null);
        String userEmail = prefs.getString("email", null);

        int user_id = prefs.getInt("id", -1);
        Log.e("USERID", String.valueOf(user_id));
        userId = user_id;

        String fullName = prefs.getString("name", null);
        String Phone = prefs.getString("phone", null);
        String Email = prefs.getString("email", null);
        String Address = prefs.getString("address", null);
        String country = prefs.getString("country", null);
//        Log.e("USERCountry", country);
        String City = prefs.getString("city", null);
        String postCode = prefs.getString("zip", null);

        personal_name.setText(userName);
        personal_email.setText(userEmail);

        name.setText(fullName);
        phone.setText(Phone);
        email.setText(Email);
        address.setText(Address);
        customer_country.setText(country);

        city.setText(City);
        zip.setText(postCode);

        txt_continus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkoutComplete();
                //Toast.makeText(CheckoutActivity.this, "Check Out Successful", Toast.LENGTH_SHORT).show();

            }
        });

//        edt_order_list.setEnabled(false);

        //getDataFromDatabase();
        submitOrder();
        getTaxCurrency();
    }

    private void checkoutComplete() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(CheckoutActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog


        Api.getClient().checkout(
                personal_name.getText().toString().trim(),
                personal_email.getText().toString().trim(),
                shipping,
                pickup_location,
                name.getText().toString().trim(),
                phone.getText().toString().trim(),
                email.getText().toString().trim(),
                address.getText().toString().trim(),
                customer_country.getText().toString().trim(),
                city.getText().toString().trim(),
                zip.getText().toString().trim(),
                shipping_name.getText().toString().trim(),
                shipping_email.getText().toString().trim(),
                shipping_phone.getText().toString().trim(),
                shipping_address.getText().toString().trim(),
                shipping_country.getText().toString().trim(),
                shipping_city.getText().toString().trim(),
                shipping_zip.getText().toString().trim(),
                method = method,
                shipping_cost,
                packing_cost,
                dp = dp,
                tax = tax,
                totalQty = totalQty,
                vendor_shipping_id = vendor_shipping_id,
                vendor_packing_id = vendor_packing_id,
                total.getText().toString().trim(),
                userId,
                map,
                "email", new Callback<CheckOutResponse>() {
                    @Override
                    public void success(CheckOutResponse checkOutResponse, Response response) {
                        progressDialog.dismiss();
                        checkOutResponseData = checkOutResponse;
                        userId = checkOutResponse.getUserid();
                        Log.e("ERROR", checkOutResponse.getMessage());
                        Toast.makeText(CheckoutActivity.this, checkOutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        Toast.makeText(CheckoutActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("fdsgjsdh", error.getBody().toString());
                        progressDialog.dismiss(); //dismiss progress dialog

                    }
                });


    }

    public void getTaxCurrency() {
        Intent intent = getIntent();
        totalPrice = intent.getDoubleExtra("name", 0);
        String total = String.valueOf(totalPrice);
        Toast.makeText(this, "TOtal"+total, Toast.LENGTH_SHORT).show();
        edt_order_list.setText(total);
    }


    private void submitOrder() {
    }

//    private void getDataFromDatabase() {
//
//        data = cartDB.getAllData();
//
//        double Order_price = 0;
//        double Total_price = 0;
//        //double tax = 0;
//
//        for (int i = 0; i < data.size(); i++) {
//            CartModel row = data.get(i);
//
////            String Menu_name = row.get(1).toString();
////            String Quantity = row.get(2).toString();
//            String Menu_name = row.getTitle().toString();
//            String Quantity = row.getQuantity().toString();
//
//            double Sub_total_price = Double.parseDouble(row.getPrice().toString());
//
//            String _Sub_total_price = String.format(Locale.GERMAN, "%1$,.0f", Sub_total_price);
//
//            Order_price += Sub_total_price;
//
//            if (ApiInterface.ENABLE_DECIMAL_ROUNDING) {
//                data_order_list += (Quantity + " " + Menu_name + " " + _Sub_total_price + " " + str_currency_code + ",\n");
//            } else {
//                data_order_list += (Quantity + " " + Menu_name + " " + Sub_total_price + " " + str_currency_code + ",\n");
//            }
//        }
//
//        if (data_order_list.equalsIgnoreCase("")) {
//            data_order_list += getString(R.string.no_order_menu);
//        }
//
////        tax = Order_price * (str_tax / 100);
////        Total_price = Order_price + tax;
//
//        String price_tax = String.format(Locale.GERMAN, "%1$,.0f");
//        String _Order_price = String.format(Locale.GERMAN, "%1$,.0f", Order_price);
//        String _tax = String.format(Locale.GERMAN, "%1$,.0f");
//        String _Total_price = String.format(Locale.GERMAN, "%1$,.0f", Total_price);
//
//        if (ApiInterface.ENABLE_DECIMAL_ROUNDING) {
//            data_order_list += "\n" + getResources().getString(R.string.txt_order) + " " + _Order_price + " " + str_currency_code +
//                    "\n" + getResources().getString(R.string.txt_tax) + " " + price_tax + " % : " + _tax + " " + str_currency_code +
//                    "\n" + getResources().getString(R.string.txt_total) + " " + _Total_price + " " + str_currency_code;
//
//
//            edt_order_total.setText(_Total_price + " " + str_currency_code);
//
//        } else {
//            data_order_list += "\n" + getResources().getString(R.string.txt_order) + " " + Order_price + " " + str_currency_code +
//                    "\n" + getResources().getString(R.string.txt_tax) + " "  + " % : "  + " " + str_currency_code +
//                    "\n" + getResources().getString(R.string.txt_total) + " " + Total_price + " " + str_currency_code;
//
//            edt_order_total.setText(Total_price + " " + str_currency_code);
//        }
//
//        edt_order_list.setText(data_order_list);
//    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sessionManager.isLoggedIn()) {
            // Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(CheckoutActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

}