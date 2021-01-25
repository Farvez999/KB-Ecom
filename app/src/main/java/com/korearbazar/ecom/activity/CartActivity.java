package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.korearbazar.ecom.Database.CartDB;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.CartAdapter;
import com.korearbazar.ecom.model.CartModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView orderRecyclerView;
    Button orderButton;

    TextView txtTotal,textClear;

    CartDB cartDB;
    List<CartModel> cartModelList;

    CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setTitle("Cart Activity");

        orderRecyclerView = findViewById(R.id.orderRecycler);
        txtTotal = findViewById(R.id.txtTotal);
        //textClear = findViewById(R.id.txtClear);
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
            // .makeText(this, ""+basePrice, Toast.LENGTH_SHORT).show();
            double dd = basePrice;
            String vv = String.valueOf(dd);
            float cc = Float.parseFloat(vv);
            String aa = String.valueOf(cc);
            txtTotal.setText(aa);
        }

//        textClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cartDB.deleteAll();
////                if (cartModelList.size() > 0)
////                {
////                    cartDB.deleteAll();
////                    //cartModelList.clear();
////                    //dialogClearAll();
////                }
//            }
//        });


        final double finalBasePrice = basePrice;

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDB.close();

                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                intent.putExtra("name", finalBasePrice);
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

    private void dialogClearAll()
    {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            builder = new AlertDialog.Builder(CartActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else
        {
            builder = new AlertDialog.Builder(CartActivity.this);
        }
        builder.setTitle(R.string.clear_all);
        builder.setMessage(R.string.delete_all_orders);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //cartDB.deleteAll();
                showMessage(true, getString(R.string.cart_clean));
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builder.show();
    }

    private void clearAll()
    {
        cartModelList.clear();

        //cartModelList= cartDB.close();
//        orderAdapter.notifyDataSetChanged();
//
//        updateBadge();
//        updateOrderTotal();
//        handleOrderDrawer();
    }

    private void showMessage(Boolean isSuccessful, String message)
    {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        if (isSuccessful)
        {
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(CartActivity.this, R.color.colorAccent));
        } else
        {
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(CartActivity.this, R.color.colorRad));
        }

        snackbar.show();
    }
}