package com.korearbazar.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.korearbazar.ecom.R;
import com.korearbazar.ecom.model.MessageModel;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<MessageModel> bestModelArrayList;
    Context context;

    public MessageAdapter(Context ctx, ArrayList<MessageModel> dataModelArrayList) {
        this.context = ctx;
        inflater = LayoutInflater.from(ctx);
        this.bestModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.message_item, parent, false);
        MessageAdapter.MyViewHolder holder = new MessageAdapter.MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        holder.subjects.setText(bestModelArrayList.get(position).getSubject());
        holder.message.setText(bestModelArrayList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return bestModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subjects;
        TextView message;


        public MyViewHolder(View itemView) {
            super(itemView);

            subjects = (TextView) itemView.findViewById(R.id.subjectUser);
            message = (TextView) itemView.findViewById(R.id.messageUser);


        }
    }

}