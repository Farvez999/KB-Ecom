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

import static com.korearbazar.ecom.ApiInterface.JSONURL;
import static com.korearbazar.ecom.ApiInterface.PDetailsImgUrl;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<CartModel> cartModelArrayList;
    Context context;
    CartInterface cartInterface;
    int sum= 0;


    public CheckOutAdapter(Context ctx, List<CartModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.cartModelArrayList = dataModelArrayList;
        this.cartInterface=cartInterface;
    }



    @NonNull
    @Override
    public CheckOutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_checkout, parent, false);
        CheckOutAdapter.MyViewHolder holder = new CheckOutAdapter.MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutAdapter.MyViewHolder holder, int position) {
        //http://ecom.hrventure.xyz/public/assets/images/products/1608651761AYGJiPqU.jpg
        final CartModel cartModel = cartModelArrayList.get(position);
        cartModelArrayList.get(holder.getAdapterPosition());

        Picasso.get().load(JSONURL+PDetailsImgUrl+cartModelArrayList.get(position).getImage()).into(holder.iv);
        holder.name.setText(cartModelArrayList.get(position).getTitle());
        holder.quantity.setText(cartModelArrayList.get(position).getQuantity());
        holder.price.setText(cartModelArrayList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return cartModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView price;
        TextView quantity;
        ImageView iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.txt_title);
            quantity = (TextView) itemView.findViewById(R.id.txtcount);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            iv = (ImageView) itemView.findViewById(R.id.img_icon);

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
