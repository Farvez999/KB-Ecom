package com.frz.korearbazar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frz.korearbazar.Database.CartDB;
import com.frz.korearbazar.R;
import com.frz.korearbazar.adapter.CartAdapter;
import com.frz.korearbazar.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView orderRecyclerView;
    Button orderButton;

    TextView txtTotal;

    CartDB cartDB;
    List<CartModel> cartModelList;

    CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        orderRecyclerView = findViewById(R.id.orderRecycler);
        txtTotal = findViewById(R.id.txtTotal);
        orderButton = findViewById(R.id.order);

        cartDB=new CartDB(this);
        cartModelList=new ArrayList<>();
        if (cartDB.getAllData().size()>0){
            cartModelList= cartDB.getAllData();
            cartAdapter =  new CartAdapter(this,cartModelList);

            orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            orderRecyclerView.setAdapter(cartAdapter);

        }
        else {
            Toast.makeText(this, "Cart is Empty", Toast.LENGTH_SHORT).show();
        }

//
//        String totalPrice = String.valueOf(0);
//        for (int i = 0; i<cartModelList.size(); i++)
//        {
//            String cff=cartModelList.get(i).getPrice();
//             float ff=Float.parseFloat(cff);
//            totalPrice =totalPrice+cff;
//        }
//        txtTotal.setText(totalPrice);

//        String totalPrice = String.valueOf(0);
//        for (int i = 0; i<cartModelList.size(); i++)
//        {
//            String pricWithStr = cartModelList.get(i).getPrice();
//            pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
//            float pp = Float.parseFloat(pricWithStr);
//
////            String cff=cartModelList.get(i).getPrice();
////            float ff=Float.parseFloat(cff);
//            totalPrice =totalPrice+pp;
//            float dd = Float.parseFloat(totalPrice);
//            String ddd = String.valueOf(dd);
//            txtTotal.setText(ddd);
//        }


//        double basePrice = 0;
//
//        for (int i = 0; i<cartModelList.size(); i++)
//        {
//            String cff=cartModelList.get(i).getPrice();
//            double ff= Double.parseDouble(cff);
//            basePrice =basePrice+ff;
//            Toast.makeText(this, ""+basePrice, Toast.LENGTH_SHORT).show();
//            double dd = basePrice;
//            String vv = String.valueOf(dd);
//            float cc = Float.parseFloat(vv);
//            String aa = String.valueOf(cc);
//            txtTotal.setText(aa);
//        }


        //String pricWithStr = cartModelList.get(i).getPrice();
            //pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
            //float pp = Float.parseFloat(pricWithStr);

                double basePrice = 0;

        for (int i = 0; i<cartModelList.size(); i++)
        {
            String pricWithStr=cartModelList.get(i).getPrice();
            pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
            double pp = Float.parseFloat(pricWithStr);

            //double ff= Double.parseDouble(String.valueOf(pp));
            basePrice =basePrice+pp;
            Toast.makeText(this, ""+basePrice, Toast.LENGTH_SHORT).show();
            double dd = basePrice;
            String vv = String.valueOf(dd);
            float cc = Float.parseFloat(vv);
            String aa = String.valueOf(cc);
            txtTotal.setText(aa);
        }




        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDB.close();

                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
//                intent.putExtra("tax", str_tax);
//                intent.putExtra("currency_code", str_currency_code);
                startActivity(intent);
            }
        });

    }

    private void updateOrderTotal()
    {
        String total = getOrderTotal();
        txtTotal.setText(String.format(" Total: ", total));
    }

    private String getOrderTotal()
    {
        String total = null;

        for (CartModel cartModel : cartModelList)
        {
            total += cartModel.getPrice();
        }

        return total;
    }
}