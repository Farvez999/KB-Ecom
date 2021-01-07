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

import com.frz.korearbazar.Database.CartDB;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CheckOutAdapter;
import com.frz.korearbazar.model.CartModel;
import com.frz.korearbazar.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    SessionManager sessionManager;
    CartDB cartDB;
    ProgressDialog progressDialog;
    List<CartModel> data;
    String data_order_list = "";
    String str_currency_code;

    TextView ed_username,ed_user_email,txt_continus;
    EditText ed_fullName,ed_phone,ed_email,ed_address,ed_city,ed_postCode;
    EditText edt_order_list,edt_order_total;



    List<CartModel> cartModelList;

    CheckOutAdapter cartAdapter;

    Double totalPrice;

    RecyclerView orderRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setTitle("Check Out");

        sessionManager = new SessionManager(this);

        orderRecyclerView = findViewById(R.id.checkRecyclerView);

        ed_username = findViewById(R.id.username);
        ed_user_email = findViewById(R.id.user_email);

        ed_fullName = findViewById(R.id.fullName);
        ed_phone = findViewById(R.id.phone);
        ed_email = findViewById(R.id.email);
        ed_address = findViewById(R.id.address);
        ed_city = findViewById(R.id.city);
        ed_postCode = findViewById(R.id.postCode);

        edt_order_list = findViewById(R.id.edt_order_list);
        edt_order_total = findViewById(R.id.edt_order_total);

        txt_continus = findViewById(R.id.txt_continus);


        cartDB=new CartDB(this);
        cartModelList=new ArrayList<>();
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
        String city=prefs.getString("city",null);
        String postCode=prefs.getString("zip",null);
        ed_username.setText(name);
        ed_user_email.setText(userEmail);

        ed_fullName.setText(fullName);
        ed_phone.setText(phone);
        ed_email.setText(email);
        ed_address.setText(address);
        ed_city.setText(city);
        ed_postCode.setText(postCode);

        txt_continus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CheckoutActivity.this, "Check Out Successful", Toast.LENGTH_SHORT).show();

            }
        });

        edt_order_list.setEnabled(false);

        //getDataFromDatabase();
        submitOrder();
        getTaxCurrency();
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