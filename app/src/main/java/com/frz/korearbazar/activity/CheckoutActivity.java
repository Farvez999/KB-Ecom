package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frz.korearbazar.Api;
import com.frz.korearbazar.Database.CartDB;
import com.frz.korearbazar.MainActivity;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CheckOutAdapter;
import com.frz.korearbazar.model.CartModel;
import com.frz.korearbazar.model.CheckOutResponse;
import com.frz.korearbazar.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CheckoutActivity extends AppCompatActivity {

    CheckOutResponse checkOutResponseData;
    Integer user_id;
    SessionManager sessionManager;
    CartDB cartDB;
    ProgressDialog progressDialog;
    List<CartModel> data;
    String data_order_list = "";
    String str_currency_code;

    TextView personal_name,ed_user_email,txt_continus;
    EditText ed_fullName,ed_phone,ed_email,ed_address,ed_country,ed_city,ed_postCode;
    EditText edt_order_list,edt_order_total;

    String method ="cash on delivery";
    String dp = "0";
    String tax = "0";
    String totalQty = "2";
    String vendor_shipping_id="0";
    String vendor_packing_id="0";

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

        cartDB=new CartDB(this);
        cartModelList=new ArrayList<>();

        orderRecyclerView = findViewById(R.id.checkRecyclerView);

        personal_name = findViewById(R.id.username);
        ed_user_email = findViewById(R.id.user_email);

        ed_fullName = findViewById(R.id.fullName);
        ed_phone = findViewById(R.id.phone);
        ed_email = findViewById(R.id.email);
        ed_address = findViewById(R.id.address);
        ed_country = findViewById(R.id.country);
        ed_city = findViewById(R.id.city);
        ed_postCode = findViewById(R.id.postCode);

        edt_order_list = findViewById(R.id.edt_order_list);
        edt_order_total = findViewById(R.id.edt_order_total);

        txt_continus = findViewById(R.id.txt_continus);



//
//        Map<String, String> map = new HashMap<>();
//        for(int i=0; i<cartModelList.size();i++){
//            map.put("cart[items]["+cartModelList.get(i).getQuantity()+"][qty]",cartModelList.get(i).getQuantity());
//            //map.put("cart[items]["+cartModelList.get(i).getStock()+"][stock]",cartModelList.get(i).getStock());
//            map.put("cart[items]["+cartModelList.get(i).getPrice()+"][price]",cartModelList.get(i).getPrice());
//            //map.put("cart[items]["+cartModelList.get(i).getDp()+"][dp]",cartModelList.get(i).getDp());//dp product e nai but dp input na dile checkout hobe na..kichu na hole 0 dite hobe
//            //map.put("cart[items]["+cartModelList.get(i).getProduct_id()+"][product_id]", String.valueOf(cartModelList.get(i).getProduct_id()));
//        }



        if (cartDB.getAllData().size()>0){
            cartModelList= cartDB.getAllData();
            cartAdapter =  new CheckOutAdapter(this,cartModelList);

            orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            orderRecyclerView.setAdapter(cartAdapter);


            Toast.makeText(this, "Cart "+cartModelList, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Cart is Empty", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences prefs = getSharedPreferences("KOREAR_BAZAR", MODE_PRIVATE);
        String name=prefs.getString("name",null);
        String userEmail=prefs.getString("email",null);

        String fullName=prefs.getString("name",null);
        String phone=prefs.getString("phone",null);
        String email=prefs.getString("email",null);
        String address=prefs.getString("address",null);
        String country=prefs.getString("country",null);
        String city=prefs.getString("city",null);
        String postCode=prefs.getString("zip",null);
        personal_name.setText(name);
        ed_user_email.setText(userEmail);

        ed_fullName.setText(fullName);
        ed_phone.setText(phone);
        ed_email.setText(email);
        ed_address.setText(address);
        ed_country.setText(country);
        ed_city.setText(city);
        ed_postCode.setText(postCode);

        txt_continus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkoutComplete();
                Toast.makeText(CheckoutActivity.this, "Check Out Successful", Toast.LENGTH_SHORT).show();

            }
        });

        edt_order_list.setEnabled(false);

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

            // Api is a class in which we define a method getClient() that returns the API Interface class object
            // registration is a POST request type method in which we are sending our field's data
            Api.getClient().checkout(
                    personal_name.getText().toString().trim(),
                    ed_user_email.getText().toString().trim(),
                    ed_fullName.getText().toString().trim(),
                    ed_email.getText().toString().trim(),
                    ed_phone.getText().toString().trim(),
                    ed_address.getText().toString().trim(),

                    ed_country.getText().toString().trim(),
                    ed_user_email.getText().toString().trim(),
                    ed_city.getText().toString().trim(),
                    ed_postCode.getText().toString().trim(),

                    method= method,
                    dp = dp,
                    tax = tax,
                    totalQty= totalQty,
                    vendor_shipping_id = vendor_shipping_id,
                    vendor_packing_id = vendor_packing_id,

                    edt_order_total.getText().toString().trim(),

                    "email", new Callback<CheckOutResponse>() {
                        @Override
                        public void success(CheckOutResponse checkOutResponse, Response response) {
                            // in this method we will get the response from API
                            progressDialog.dismiss(); //dismiss progress dialog
                            checkOutResponseData = checkOutResponse;
                            user_id = checkOutResponse.getUserid();
                            Toast.makeText(CheckoutActivity.this, checkOutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            // if error occurs in network transaction then we can get the error in this method.
                            Toast.makeText(CheckoutActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss(); //dismiss progress dialog

                        }
                    });


    }

    public void getTaxCurrency() {
        Intent intent = getIntent();
        totalPrice=intent.getDoubleExtra("name",0);
        String total= String.valueOf(totalPrice);
        //Toast.makeText(this, "TOtal"+total, Toast.LENGTH_SHORT).show();
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
        if (sessionManager.isLoggedIn()){
           // Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(CheckoutActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

}