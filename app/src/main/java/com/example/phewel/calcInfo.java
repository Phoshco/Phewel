package com.example.phewel;

import java.util.List;

public class calcInfo {

    List<Entry> list;

    calcInfo (List<Entry> list){
        this.list = list;
    }

    String avgFuelEff(){
        double sum = 0;
        for (int i=0; i<list.size(); i++){
            sum += list.get(i).getEconomy();
        }
        sum = sum/list.size();
        sum = Math.round(sum * 1000d)/1000d;
        return String.valueOf(sum) + " km/L";
    }

    String avgCostEff(){
        double sum = 0;
        int iter = 0;
        for (int i=0; i<list.size(); i++){
            if (!list.get(i).getCost().equals("")){
                iter += 1;
                sum += list.get(i).getCostEff();
            }
        }
        sum = sum/iter;
        sum = Math.round(sum * 1000d)/1000d;
        return "$ " + String.valueOf(sum) + " /km";
    }

}
