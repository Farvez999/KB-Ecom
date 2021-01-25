package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.VendorProductModel;

import java.util.ArrayList;

public class VendorProductAdapter extends RecyclerView.Adapter<VendorProductAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<VendorProductModel> bestModelArrayList;
    Context context;

    public VendorProductAdapter(Context ctx, ArrayList<VendorProductModel> dataModelArrayList) {
        this.context = ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public VendorProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.vendor_product_item, parent, false);
        VendorProductAdapter.MyViewHolder holder = new VendorProductAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorProductAdapter.MyViewHolder holder, int position) {
        holder.name.setText(bestModelArrayList.get(position).getName());
        holder.type.setText(bestModelArrayList.get(position).getType());
        holder.price.setText(bestModelArrayList.get(position).getPrice());
        holder.status.setText(bestModelArrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView type;
        TextView price;
        TextView status;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.pro_name_dash);
            type = (TextView) itemView.findViewById(R.id.pro_type_dash);
            price = (TextView) itemView.findViewById(R.id.pro_price_dash);
            status = (TextView) itemView.findViewById(R.id.pro_status_dash);

        }
    }

}

