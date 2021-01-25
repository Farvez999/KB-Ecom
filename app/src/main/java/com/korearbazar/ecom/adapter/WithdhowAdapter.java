package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.WithdhowModel;

import java.util.ArrayList;

public class WithdhowAdapter extends RecyclerView.Adapter<WithdhowAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<WithdhowModel> bestModelArrayList;
    Context context;

    public WithdhowAdapter(Context ctx, ArrayList<WithdhowModel> dataModelArrayList){
        this.context=ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.withdraw_item, parent, false);
        WithdhowAdapter.MyViewHolder holder = new WithdhowAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.wDate.setText(bestModelArrayList.get(position).getCreated_at());
        holder.method.setText(bestModelArrayList.get(position).getMethod());
        holder.account.setText(bestModelArrayList.get(position).getAcc_email());
        holder.amount.setText(bestModelArrayList.get(position).getAmount());
        holder.status.setText(bestModelArrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView wDate;
        TextView method;
        TextView account;
        TextView amount;
        TextView status;
        public MyViewHolder(View itemView) {
            super(itemView);

            wDate = (TextView) itemView.findViewById(R.id.wDate);
            method = (TextView) itemView.findViewById(R.id.method);
            account = (TextView) itemView.findViewById(R.id.account);
            amount = (TextView) itemView.findViewById(R.id.amount);
            status = (TextView) itemView.findViewById(R.id.status);

        }
    }
}
