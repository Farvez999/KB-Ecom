package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.Interface.CategoryInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.activity.CategoryDetailsActivity;
import com.korearbazar.ecom.model.CateModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.korearbazar.ecom.ApiInterface.CategoryImgUrl;
import static com.korearbazar.ecom.ApiInterface.JSONURL;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<CateModel> dataModelArrayList;
    CategoryInterface categoryInterface;
    Context context;

    public CateAdapter(Context ctx, ArrayList<CateModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
        //this.categoryInterface=categoryInterface;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.category_item, parent, false);
        CateAdapter.MyViewHolder holder = new CateAdapter.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        /// http://ecom.hrventure.xyz/assets/images/categories/1605095240leg-of-lamb-500x500.png
        Picasso.get().load(JSONURL+CategoryImgUrl+dataModelArrayList.get(position).getPhoto()).into(holder.iv);
        holder.name.setText(dataModelArrayList.get(position).getName());

//        holder.iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Click category Item" + dataModelArrayList.get(position).getSlug(), Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(context, CategoryDetailsActivity.class);
//                i.putExtra("categoryModel");
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.cate_title);
            iv = (ImageView) itemView.findViewById(R.id.cate_imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int postion = this.getPosition();
            CateModel cm=dataModelArrayList.get(postion);
//            Toast.makeText(context, "postion  "+cm.getName(), Toast.LENGTH_SHORT).show();
//            categoryInterface.setCategory(cm);

            Toast.makeText(context, "postion  "+cm.getSlug(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context , CategoryDetailsActivity.class);
            intent.putExtra("mySlug" , cm.getSlug());
            context.startActivity(intent);
        }
    }
}

