package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.VendorOrderModel;

import java.util.ArrayList;

public class VendorOrderAdapter extends RecyclerView.Adapter<VendorOrderAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<VendorOrderModel> bestModelArrayList;
    Context context;

    public VendorOrderAdapter(Context ctx, ArrayList<VendorOrderModel> dataModelArrayList) {
        this.context = ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public VendorOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.order_dash_item, parent, false);
        VendorOrderAdapter.MyViewHolder holder = new VendorOrderAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorOrderAdapter.MyViewHolder holder, int position) {
        holder.order_number.setText(bestModelArrayList.get(position).getOrder_number());
        holder.qty.setText(bestModelArrayList.get(position).getQty());
        holder.price.setText(bestModelArrayList.get(position).getPrice());
        holder.status.setText(bestModelArrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView order_number;
        TextView qty;
        TextView price;
        TextView status;


        public MyViewHolder(View itemView) {
            super(itemView);

            order_number = (TextView) itemView.findViewById(R.id.order_number_dash);
            qty = (TextView) itemView.findViewById(R.id.qty);
            price = (TextView) itemView.findViewById(R.id.price);
            status = (TextView) itemView.findViewById(R.id.status_dash);

        }
    }

}

