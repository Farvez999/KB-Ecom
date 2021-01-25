package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.Interface.ProdInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.ProdModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.korearbazar.ecom.ApiInterface.JSONURL;
import static com.korearbazar.ecom.ApiInterface.ProdImgUrl;

public class SaleProdAdapter extends RecyclerView.Adapter<SaleProdAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ProdModel> bestModelArrayList;
    Context context;
    ProdInterface prodInterface;
    public SaleProdAdapter(Context ctx, ArrayList<ProdModel> dataModelArrayList, ProdInterface prodInterface){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
        this.prodInterface = prodInterface;
    }



    @Override
    public SaleProdAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.product_item, parent, false);
        SaleProdAdapter.MyViewHolder holder = new SaleProdAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final SaleProdAdapter.MyViewHolder holder, final int position) {
        //http://ecom.hrventure.xyz/assets/images/thumbnails/
        Picasso.get().load(JSONURL+ProdImgUrl+bestModelArrayList.get(position).getThumbnail()).into(holder.iv);
        holder.name.setText(bestModelArrayList.get(position).getName());

        holder.price.setText(bestModelArrayList.get(position).getShowPrice());
        final String totalPrice = bestModelArrayList.get(position).getShowPrice();

        holder.previous_price.setText(bestModelArrayList.get(position).getShowPreviousPrice());
        final String totalPreviousPrice = bestModelArrayList.get(position).getShowPreviousPrice();

        if (totalPreviousPrice.equals("")){
            holder.price.setText(totalPrice);
        }else {
            holder.price.setText(totalPrice);

//            SpannableString ss = new SpannableString(totalPreviousPrice);
//            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
//            ss.setSpan(strikethroughSpan,0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.previous_price.setText(totalPreviousPrice);

            String pricWithStr = totalPrice;
            pricWithStr = pricWithStr.replaceAll("[^\\d.]", "");
            float pp = Float.parseFloat(pricWithStr);

            String pPricWithStr = totalPreviousPrice;
            pPricWithStr = pPricWithStr.replaceAll("[^\\d.]", "");
            float ppp = Float.parseFloat(pPricWithStr);

            float diff = (ppp - pp);
            if (diff > 0 ){
                float dif = (diff / ppp) * 100;
                String fff= String.valueOf(dif);

                int b = Math.round(dif);
                String fa = String.valueOf(b+" %off");

                holder.txt_offer.setText(fa);
                holder.lvl_offer.setVisibility(View.VISIBLE);
            }else {
                holder.lvl_offer.setVisibility(View.GONE);
            }
        }

        holder.price.setText(totalPrice);
        holder.previous_price.setText(totalPreviousPrice);

    }


    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView price;
        TextView previous_price;
        TextView txt_offer;
        LinearLayout lvl_offer;
        TextView txt_desc;
        ImageView iv;
        //TextView slug;



        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.proTitle);
            price = (TextView) itemView.findViewById(R.id.pro_price);
            previous_price = (TextView) itemView.findViewById(R.id.pro_priceoofer);
            iv = (ImageView) itemView.findViewById(R.id.pro_img);
            txt_offer = (TextView) itemView.findViewById(R.id.txt_offer);
            lvl_offer = (LinearLayout) itemView.findViewById(R.id.lvl_offer);

            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            int postion = this.getPosition();
            ProdModel pm=bestModelArrayList.get(postion);
            prodInterface.setProd(pm);
        }
    }

}
