package com.frz.korearbazar.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.frz.korearbazar.R;
import com.frz.korearbazar.model.BrandsModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import static com.frz.korearbazar.ApiInterface.BrandsImgUrl;
import static com.frz.korearbazar.ApiInterface.JSONURL;


public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<BrandsModel> bestModelArrayList;
    Context context;
    public BrandsAdapter(Context ctx, ArrayList<BrandsModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;

    }



    @Override
    public BrandsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_brands, parent, false);
        BrandsAdapter.MyViewHolder holder = new BrandsAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final BrandsAdapter.MyViewHolder holder, final int position) {
        //http://ecom.hrventure.xyz/assets/images/partner/1604063152b (1).png" alt="">
        Picasso.get().load(JSONURL+BrandsImgUrl+bestModelArrayList.get(position).getPhoto()).into(holder.iv);


        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "OK link Click here", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView iv;




        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.pro_img);

        }


    }

}
