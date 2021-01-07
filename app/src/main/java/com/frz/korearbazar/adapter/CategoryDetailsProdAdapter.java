package com.frz.korearbazar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frz.korearbazar.Interface.ProdInterface;
import com.frz.korearbazar.R;
import com.frz.korearbazar.activity.ItemDetailsActivity;
import com.frz.korearbazar.model.ProdModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.frz.korearbazar.ApiInterface.JSONURL;
import static com.frz.korearbazar.ApiInterface.RelatedProductImgUrl;

public class CategoryDetailsProdAdapter extends RecyclerView.Adapter<CategoryDetailsProdAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ProdModel> bestModelArrayList;
    Context context;
    ProdInterface prodInterface;

    public CategoryDetailsProdAdapter(Context ctx, ArrayList<ProdModel> dataModelArrayList){
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
//http://192.168.0.103/project/hrv-ecom/public/assets/images/thumbnails/1568025872thPsuRSJ.jpg
        Picasso.get().load(JSONURL+RelatedProductImgUrl+bestModelArrayList.get(position).getThumbnail()).into(holder.iv);
        holder.name.setText(bestModelArrayList.get(position).getName());
        holder.price.setText(bestModelArrayList.get(position).getShowPrice());
        holder.previous_price.setText(bestModelArrayList.get(position).getShowPreviousPrice());

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

            int postion = this.getPosition();
            ProdModel pm=bestModelArrayList.get(postion);
            Intent intent = new Intent(context , ItemDetailsActivity.class);
            intent.putExtra("prodctModel" , pm.getSlug());
            // intent.putExtra("price" ,pm.getPrice());

            view.getContext().startActivity(intent);

        }
    }
}
