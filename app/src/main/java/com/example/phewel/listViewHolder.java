package com.example.phewel;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class listViewHolder extends RecyclerView.ViewHolder {

    TextView date;
    TextView mileage;
    TextView distance;
    TextView fuel;

    TextView fuelEff;
    TextView costEff;
    TextView cost;
    TextView type;

    View view;

    listViewHolder(View itemView){

        super(itemView);

        date = itemView.findViewById(R.id.date);
        mileage = itemView.findViewById(R.id.mileage);
        distance = itemView.findViewById(R.id.distance);
        fuel = itemView.findViewById(R.id.fuel);
        fuelEff = itemView.findViewById(R.id.fuelEff);
        costEff = itemView.findViewById(R.id.costEff);
        cost = itemView.findViewById(R.id.cost);
        type = itemView.findViewById(R.id.type);

        view = itemView;

    }
}
