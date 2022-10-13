package com.example.phewel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private void gradientColor(TextView textView){
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(String.valueOf(textView.getText()));
        Shader textShader = new LinearGradient(0,0,width,textView.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
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
        gradientColor(holder.date);
        String toMileage = String.valueOf(list.get(position).getOdoAfter());
        holder.mileage.setText(toMileage);
        gradientColor(holder.mileage);
        String toDist = String.valueOf(list.get(position).getDist());
        holder.distance.setText(toDist);
        gradientColor(holder.distance);
        String toFuel = String.valueOf(list.get(position).getInFuel());
        holder.fuel.setText(toFuel);
        gradientColor(holder.fuel);

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
