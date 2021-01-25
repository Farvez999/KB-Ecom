package com.korearbazar.ecom.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.ProdDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.korearbazar.ecom.ApiInterface.JSONURL;
import static com.korearbazar.ecom.ApiInterface.PDetailsImgUrl;

public class ProdDetailsAdapter extends RecyclerView.Adapter<ProdDetailsAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<ProdDetailsModel> dataModelArrayList;

    public ProdDetailsAdapter(Context ctx, ArrayList<ProdDetailsModel> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public ProdDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.activity_item_details, parent, false);
        ProdDetailsAdapter.MyViewHolder holder = new ProdDetailsAdapter.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ProdDetailsAdapter.MyViewHolder holder, int position) {
        /// http://ecom.hrventure.xyz/assets/images/galleries/160656089571-yyAiEDiL._UL1500_.jpg
        //http://ecom.hrventure.xyz/assets/images/products/1606560895gDvz8eUj.png
        ProdDetailsModel prodDetailsModel = dataModelArrayList.get(position);
        Picasso.get().load(JSONURL+PDetailsImgUrl+dataModelArrayList.get(position).getPhoto()).into(holder.iv);
        holder.name.setText(dataModelArrayList.get(position).getName());
        holder.txt_desc.setText(dataModelArrayList.get(position).getDetails());
        holder.slug.setText(dataModelArrayList.get(position).getSlug());
        holder.stoke.setText(dataModelArrayList.get(position).getStock());
//        if (prodDetailsModel.getStock() == (String.valueOf(0))) {
//            holder.stoke.setText("Stoke Okay");
//        } else {
//            holder.stoke.setText("Stoke");
//        }

//        if (datum.getStock() == 0) {
//            holder.lvlOutofstock.setVisibility(View.VISIBLE);
//        } else {
//            holder.lvlOutofstock.setVisibility(View.GONE);
//
//        }

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView txt_desc;
        ImageView iv;
        TextView slug;
        TextView stoke;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.txt_title);
            txt_desc = (TextView) itemView.findViewById(R.id.txt_desc);
            stoke = (TextView) itemView.findViewById(R.id.txt_stoke);
            iv = (ImageView) itemView.findViewById(R.id.imgDtails);
        }


    }
}
