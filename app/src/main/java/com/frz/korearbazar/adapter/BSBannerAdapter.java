package com.frz.korearbazar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.frz.korearbazar.R;
import com.frz.korearbazar.model.BSBannerModel;
import com.frz.korearbazar.model.LargeBannerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.frz.korearbazar.ApiInterface.BannerImgUrl;
import static com.frz.korearbazar.ApiInterface.JSONURL;


public class BSBannerAdapter extends RecyclerView.Adapter<BSBannerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<BSBannerModel> largeModelArrayList;
    Context context;
    public BSBannerAdapter(Context ctx, ArrayList<BSBannerModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.largeModelArrayList = dataModelArrayList;

    }



    @Override
    public BSBannerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_bs_banner, parent, false);
        BSBannerAdapter.MyViewHolder holder = new BSBannerAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final BSBannerAdapter.MyViewHolder holder, final int position) {
        //http://ecom.hrventure.xyz/assets/images/banners/16050929741.png
        Picasso.get().load(JSONURL+BannerImgUrl+largeModelArrayList.get(position).getPhoto()).into(holder.imageView);
        // Picasso.get().load("http://ecom.hrventure.xyz/assets/images/banners/16050929741.png").into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return largeModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }

    }

}

