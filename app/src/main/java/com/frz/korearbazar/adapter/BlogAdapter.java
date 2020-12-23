package com.frz.korearbazar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.frz.korearbazar.R;
import com.frz.korearbazar.model.BlogModel;
import com.frz.korearbazar.model.ReviewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.frz.korearbazar.ApiInterface.BlogImgUrl;
import static com.frz.korearbazar.ApiInterface.JSONURL;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<BlogModel> bestModelArrayList;
    Context context;
    public BlogAdapter(Context ctx, ArrayList<BlogModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;

    }



    @Override
    public BlogAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_blog, parent, false);
        BlogAdapter.MyViewHolder holder = new BlogAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final BlogAdapter.MyViewHolder holder, final int position) {
        //http://ecom.hrventure.xyz/assets/images/blogs/15542698954-min.jpg
        Picasso.get().load(JSONURL+BlogImgUrl+bestModelArrayList.get(position).getPhoto()).into(holder.photo);
        holder.title.setText(bestModelArrayList.get(position).getTitle());
        holder.details.setText(bestModelArrayList.get(position).getDetails());
        //holder.details.setText(bestModelArrayList.get(position).getDetails());

    }


    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView details;
        ImageView photo;



        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.blog_title);
            details = (TextView) itemView.findViewById(R.id.blog_details);
            photo = (ImageView) itemView.findViewById(R.id.blog_img);





        }

    }

}
