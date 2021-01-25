package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.Interface.ProdInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.activity.ItemDetailsActivity;
import com.korearbazar.ecom.model.CategoryDetailsProdModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryDetailsProdAdapter extends RecyclerView.Adapter<CategoryDetailsProdAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<CategoryDetailsProdModel> bestModelArrayList;
    Context context;
    ProdInterface prodInterface;

    public CategoryDetailsProdAdapter(Context ctx, ArrayList<CategoryDetailsProdModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
        this.prodInterface = prodInterface;
    }




    @NonNull
    @Override
    public CategoryDetailsProdAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.product_item, parent, false);
        CategoryDetailsProdAdapter.MyViewHolder holder = new CategoryDetailsProdAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsProdAdapter.MyViewHolder holder, int position) {
//http://ecom.hrventure.xyz/assets/images/thumbnails/1607271709rcE4R1aG.jpg JSONURL+CategoryDetailsProds
        Picasso.get().load("http://ecom.hrventure.xyz/assets/images/thumbnails/"+bestModelArrayList.get(position).getPhoto()).into(holder.iv);
        holder.name.setText(bestModelArrayList.get(position).getName());
        holder.price.setText(bestModelArrayList.get(position).getPrice());
        holder.previous_price.setText(bestModelArrayList.get(position).getPrevious_price());

    }

    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView price;
        TextView previous_price;
        TextView txt_desc;
        ImageView iv;
        //TextView slug;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.proTitle);
            price = (TextView) itemView.findViewById(R.id.pro_price);
            previous_price = (TextView) itemView.findViewById(R.id.pro_priceoofer);
            iv = (ImageView) itemView.findViewById(R.id.pro_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int postion = this.getLayoutPosition();
            CategoryDetailsProdModel cdpm = bestModelArrayList.get(postion);
            Intent intent = new Intent(context,ItemDetailsActivity.class);
            intent.putExtra("prodctModel", cdpm.getSlug());

            view.getContext().startActivity(intent);

        }
    }
}
