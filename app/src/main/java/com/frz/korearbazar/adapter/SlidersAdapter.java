package com.frz.korearbazar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import com.frz.korearbazar.R;
import com.frz.korearbazar.model.SlidersModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import static com.frz.korearbazar.ApiInterface.JSONURL;
import static com.frz.korearbazar.ApiInterface.SlidersImgUrl;

public class SlidersAdapter extends RecyclerView.Adapter<SlidersAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<SlidersModel> sliderModelArrayList;
    Context context;
    public SlidersAdapter(Context ctx, ArrayList<SlidersModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.sliderModelArrayList = dataModelArrayList;

    }



    @Override
    public SlidersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_image, parent, false);
        SlidersAdapter.MyViewHolder holder = new SlidersAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final SlidersAdapter.MyViewHolder holder, final int position) {
        //ecom.hrventure.xyz/assets/images/sliders/1605092531fish.png)
        Picasso.get().load(JSONURL+SlidersImgUrl+sliderModelArrayList.get(position).getPhoto()).into(holder.imageView);

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

