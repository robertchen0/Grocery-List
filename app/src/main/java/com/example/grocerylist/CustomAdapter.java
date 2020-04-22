package com.example.grocerylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerylist.model.Item;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<Item> dataSet;

    public void setDataSet(List<Item> data){
        this.dataSet = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.name.setText(dataSet.get(position).getName());
        holder.count.setText("Amount: " + dataSet.get(position).getCount().toString());
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }

    public Item getPosition(int position){
        return dataSet.get(position);
    }
}

class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView name, count;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        count = itemView.findViewById(R.id.tv_amount);
    }
}

