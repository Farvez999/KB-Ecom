package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.Interface.ProdInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.DasboardOrderModel;

import java.util.ArrayList;

public class DasboardOrderAdapter extends RecyclerView.Adapter<DasboardOrderAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DasboardOrderModel> bestModelArrayList;
    Context context;
    ProdInterface prodInterface;

    public DasboardOrderAdapter(Context ctx, ArrayList<DasboardOrderModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
        this.prodInterface = prodInterface;
    }




    @NonNull
    @Override
    public DasboardOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.item_order, parent, false);
        DasboardOrderAdapter.MyViewHolder holder = new DasboardOrderAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DasboardOrderAdapter.MyViewHolder holder, int position) {
//http://ecom.hrventure.xyz/assets/images/thumbnails/1607271709rcE4R1aG.jpg JSONURL+CategoryDetailsProds
       // Picasso.get().load("http://ecom.hrventure.xyz/assets/images/thumbnails/"+bestModelArrayList.get(position).getPhoto()).into(holder.iv);
        holder.order_number.setText(bestModelArrayList.get(position).getOrder_number());
        holder.date.setText(bestModelArrayList.get(position).getCreated_at());
        holder.order_status.setText(bestModelArrayList.get(position).getPayment_status());

    }

    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView order_number;
        TextView date;
        TextView order_status;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            order_number = (TextView) itemView.findViewById(R.id.order_number);
            date = (TextView) itemView.findViewById(R.id.date);
            order_status = (TextView) itemView.findViewById(R.id.order_status);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

//            int postion = this.getLayoutPosition();
//            DasboardOrderModel cdpm = bestModelArrayList.get(postion);
//            Intent intent = new Intent(context, ItemDetailsActivity.class);
//            intent.putExtra("prodctModel", cdpm.getSlug());
//
//            view.getContext().startActivity(intent);

        }
    }
}
