package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.FavoriteSellerModel;

import java.util.ArrayList;

public class FavoriteSellerAdapter extends RecyclerView.Adapter<FavoriteSellerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<FavoriteSellerModel> bestModelArrayList;
    Context context;

    public FavoriteSellerAdapter(Context ctx, ArrayList<FavoriteSellerModel> dataModelArrayList) {
        this.context = ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public FavoriteSellerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.favorite_seller_item, parent, false);
        FavoriteSellerAdapter.MyViewHolder holder = new FavoriteSellerAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteSellerAdapter.MyViewHolder holder, int position) {
        holder.shop_name.setText(bestModelArrayList.get(position).getShop_name());
        holder.owner_name.setText(bestModelArrayList.get(position).getOwner_name());
        holder.shop_address.setText(bestModelArrayList.get(position).getShop_address());

    }

    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView shop_name;
        TextView owner_name;
        TextView shop_address;


        public MyViewHolder(View itemView) {
            super(itemView);

            shop_name = (TextView) itemView.findViewById(R.id.shop_name);
            owner_name = (TextView) itemView.findViewById(R.id.owner_name);
            shop_address = (TextView) itemView.findViewById(R.id.shop_address);


        }
    }

}

