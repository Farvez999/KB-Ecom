package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.BannerTopSmallModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.korearbazar.ecom.ApiInterface.BannerImgUrl;
import static com.korearbazar.ecom.ApiInterface.JSONURL;

public class BannerTopSmallAdapter extends RecyclerView.Adapter<BannerTopSmallAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<BannerTopSmallModel> sliderModelArrayList;
    Context context;
    public BannerTopSmallAdapter(Context ctx, ArrayList<BannerTopSmallModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.sliderModelArrayList = dataModelArrayList;

    }



    @Override
    public BannerTopSmallAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_image, parent, false);
        BannerTopSmallAdapter.MyViewHolder holder = new BannerTopSmallAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final BannerTopSmallAdapter.MyViewHolder holder, final int position) {
        //ecom.hrventure.xyz/assets/images/sliders/1605092531fish.png)
        Picasso.get().load(JSONURL+BannerImgUrl+sliderModelArrayList.get(position).getPhoto()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Banner Click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(sliderModelArrayList.get(position).getLink()));
                context.startActivity(intent);
//                BannerTopSmallModel pm = sliderModelArrayList.get(position);
//            Intent intent = new Intent(context , ItemDetailsActivity.class);
//            intent.putExtra("name" , pm.getLink());
            }
        });
    }


    @Override
    public int getItemCount() {
        return sliderModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;



        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }

    }

}

