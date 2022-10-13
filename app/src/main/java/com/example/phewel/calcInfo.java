package com.example.phewel;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class calcInfo {

    List<Entry> list;

    calcInfo (List<Entry> list){
        this.list = list;
    }

    Double avgFuelEff(){
        double sum = 0;
        for (int i=0; i<list.size(); i++){
            sum += list.get(i).getEconomy();
        }
        sum = sum/list.size();
        sum = Math.round(sum * 1000d)/1000d;

        for (int i=0; i<list.size(); i++){
            Entry entry = list.get(i);
            if (entry.getEconomy()<sum){
                entry.setEffRating(1);
            } else if (entry.getEconomy()==sum){
                entry.setEffRating(2);
            } else entry.setEffRating(3);
        }

        return sum;
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

    void generateGraphs(GraphView graphView, int noOfPoints){
        if (noOfPoints > list.size()){
            noOfPoints = list.size();
        }
        graphView.addSeries(graphAvg(noOfPoints));
        graphView.addSeries(graphSeries(noOfPoints));

        graphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graphView.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.rgb(187,134,252));
        graphView.getGridLabelRenderer().reloadStyles();
    }

    private LineGraphSeries<DataPoint> graphSeries(int noOfPoints){
        List<DataPoint> dpArrList = new ArrayList<>();
        for (int i=0;i<noOfPoints;i++){
            dpArrList.add(new DataPoint(i,list.get(noOfPoints-1-i).getEconomy()));
        }
        DataPoint[] dpArr = new DataPoint[dpArrList.size()];
        dpArrList.toArray(dpArr);
        LineGraphSeries<DataPoint> out = new LineGraphSeries<DataPoint>(dpArr);

        out.setThickness(7);
        out.setColor(Color.argb(230,3,218,197));

        return out;
    }

    private LineGraphSeries<DataPoint> graphAvg(int noOfPoints){
        List<DataPoint> dpArrList = new ArrayList<>();
        dpArrList.add(new DataPoint(0,avgFuelEff()));
        dpArrList.add(new DataPoint(noOfPoints,avgFuelEff()));
        DataPoint[] dpArr = new DataPoint[dpArrList.size()];
        dpArrList.toArray(dpArr);
        LineGraphSeries<DataPoint> out = new LineGraphSeries<DataPoint>(dpArr);
        out.setThickness(5);
        out.setColor(Color.argb(200,200,200,200));
        return out;
    }

}
