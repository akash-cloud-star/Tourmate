package com.example.tourmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class result_adapter extends RecyclerView.Adapter<result_viewholder> {

    ArrayList<result_datamodel> data;
    Context context;

    public result_adapter(ArrayList<result_datamodel> data, Context context)
    {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public result_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_rec_cardview, parent,false);
        return new result_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull result_viewholder holder, int position) {
        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());
        holder.img.setImageResource(data.get(position).getImagename());

    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }
}
