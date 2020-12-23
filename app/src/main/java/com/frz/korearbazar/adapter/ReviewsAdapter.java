package com.frz.korearbazar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.frz.korearbazar.R;
import com.frz.korearbazar.activity.ItemDetailsActivity;
import com.frz.korearbazar.model.NewProdModel;
import com.frz.korearbazar.model.ReviewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.frz.korearbazar.ApiInterface.JSONURL;
import static com.frz.korearbazar.ApiInterface.ReviewsImgUrl;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ReviewsModel> bestModelArrayList;
    Context context;
    public ReviewsAdapter(Context ctx, ArrayList<ReviewsModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;

    }



    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_reviews, parent, false);
        ReviewsAdapter.MyViewHolder holder = new ReviewsAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ReviewsAdapter.MyViewHolder holder, final int position) {
        //http://ecom.hrventure.xyz/assets/images/reviews/1557343012img.jpg
        Picasso.get().load(JSONURL+ReviewsImgUrl+bestModelArrayList.get(position).getPhoto()).into(holder.photo);
        holder.title.setText(bestModelArrayList.get(position).getTitle());
        holder.subtitle.setText(bestModelArrayList.get(position).getSubtitle());
        holder.details.setText(bestModelArrayList.get(position).getDetails());

    }


    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;
        TextView details;
        ImageView photo;



        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.profile_title);
            subtitle = (TextView) itemView.findViewById(R.id.profile_subtitle);
            details = (TextView) itemView.findViewById(R.id.profile_comment);
            photo = (ImageView) itemView.findViewById(R.id.profile_image);





        }

    }

}
