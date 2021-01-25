package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.Interface.CartInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.CartModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<CartModel> cartModelArrayList;
    Context context;
    CartInterface cartInterface;
    int sum= 0;


    public CartAdapter(Context ctx, List<CartModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.cartModelArrayList = dataModelArrayList;
        this.cartInterface=cartInterface;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_cart, parent, false);
        CartAdapter.MyViewHolder holder = new CartAdapter.MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //http://ecom.hrventure.xyz/public/assets/images/products/1608651761AYGJiPqU.jpg
        final CartModel cartModel = cartModelArrayList.get(position);
        cartModelArrayList.get(holder.getAdapterPosition());
//        String cff=cartModelArrayList.get(position).getPrice();
//        int ff=Integer.parseInt(cff);
//        sum = sum +ff ;
        //int totalPrice = 0;
//        for (int i = 0; i<cartModelArrayList.size(); i++)
//        {
//            sum += cartModelArrayList.get(i).getPrice();
//        }
//        sum= Integer.parseInt(sum+cartModelArrayList.get(position).getPrice());
//        sum = Float.parseFloat(sum + cartModelArrayList.get(position).getPrice());

//        Picasso.get().load(JSONURL+PDetailsImgUrl+cartModelArrayList.get(position).getImage()).into(holder.iv);
        Picasso.get().load("http://ecom.hrventure.xyz/public/assets/images/products/"+cartModelArrayList.get(position).getImage()).into(holder.iv);
        holder.name.setText(cartModelArrayList.get(position).getTitle());
        holder.quantity.setText(cartModelArrayList.get(position).getQuantity());
        holder.price.setText(cartModelArrayList.get(position).getPrice());
//        holder.img_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  Toast.makeText(context, "Total"+sum, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.minImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        holder.plusImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return cartModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView price;
        TextView quantity;
        ImageView iv,img_delete,minImage,plusImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.txt_title);
            quantity = (TextView) itemView.findViewById(R.id.txtcount);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            iv = (ImageView) itemView.findViewById(R.id.img_icon);
//            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
//            minImage = (ImageView) itemView.findViewById(R.id.minImage);
//            plusImage = (ImageView) itemView.findViewById(R.id.plusImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            int postion = this.getPosition();
//            ProdModel pm=cartModelArrayList.get(postion);
//            Toast.makeText(context, "postion  "+pm.getName(), Toast.LENGTH_SHORT).show();
//            cartInterface.setCart(pm);
        }
    }
}
