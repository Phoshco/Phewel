package com.example.phewel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<listViewHolder> {

    List<Entry> list = Collections.emptyList();
    Context context;

    public itemAdapter(List<Entry> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.card, parent, false);

        return new listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
        final int idx = holder.getAdapterPosition();
        holder.date.setText(list.get(position).getDate());
        String toMileage = String.valueOf(list.get(position).getOdoAfter());
        holder.mileage.setText(toMileage);
        String toDist = String.valueOf(list.get(position).getDist());
        holder.distance.setText(toDist);
        String toFuel = String.valueOf(list.get(position).getInFuel());
        holder.fuel.setText(toFuel);

        String toEconomy = String.valueOf(list.get(position).getEconomy());
        holder.fuelEff.setText(toEconomy);
        if (list.get(position).getEffRating() == 1){
            holder.fuelEff.setTextColor(Color.rgb(211,47,47));
        } else if (list.get(position).getEffRating() == 2){
            holder.fuelEff.setTextColor(Color.rgb(255,139,24));
        } else if (list.get(position).getEffRating() == 3){
            holder.fuelEff.setTextColor(Color.rgb(139,195,74));
        }

        String toCostEff = String.valueOf(list.get(position).getCostEff());
        holder.costEff.setText(toCostEff);
        String toCost = String.valueOf(list.get(position).getCost());
        holder.cost.setText(toCost);
        holder.type.setText(list.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
