package com.example.phewel;

import java.util.Calendar;

public class Entry {

    private int odoBefore;
    private int odoAfter;
    private double inFuel;
    private double economy;
    private int dist;
    private String cost;
    private String type;
    private double costEff;
    private String date;
    private int effRating = 0; // 1 = below avg, 2 = avg, 3 = above avg

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCostEff() {
        return costEff;
    }

    public void calcCostEff() {
        if (this.cost.equals("")){
            this.costEff = 0;
        } else{
            double temp = Double.parseDouble(this.cost)*this.inFuel/(double) this.dist;
            this.costEff = (double) Math.round(temp * 1000d)/1000d;
        }
    }

    public void setCostEff( String costEff ) {
        if (costEff.equals("")) {
            this.costEff = 0;
        } else {
            this.costEff = Double.parseDouble(costEff);
        }
    }

    public int getOdoBefore() {
        return odoBefore;
    }

    public void setOdoBefore(int odoBefore) {
        this.odoBefore = odoBefore;
    }

    public int getOdoAfter() {
        return odoAfter;
    }

    public void setOdoAfter(int odoAfter) {
        this.odoAfter = odoAfter;
    }

    public double getInFuel() {
        return inFuel;
    }

    public void setInFuel(double inFuel) {
        this.inFuel = inFuel;
    }

    public double getEconomy() {
        return economy;
    }

    public void calcEconomy() {
        double temp = (double) this.dist/this.inFuel;
        this.economy = (double) Math.round(temp * 1000d)/1000d;
    }

    public void setEconomy( String economy ) { this.economy = Double.parseDouble(economy); }

    public int getDist() {
        return dist;
    }

    public void calcDist() {
        this.dist = this.odoAfter-this.odoBefore;
    }

    public void setDist( String dist ) { this.dist = Integer.parseInt(dist); }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost){ this.cost = cost; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getString(){
        String output = getOdoAfter()+","+getInFuel()+","+getType()+","+getCost();
        output += ","+getDist()+","+getEconomy()+","+getCostEff()+","+getDate();
        return output;
    }

    public int getEffRating() {
        return effRating;
    }

    public void setEffRating(int effRating) {
        this.effRating = effRating;
    }

    Entry(String odoBefore, String odoAfter, String inFuel, String cost, String type){
        setOdoBefore(Integer.parseInt(odoBefore));
        setOdoAfter(Integer.parseInt(odoAfter));
        setInFuel(Double.parseDouble(inFuel));
        calcDist();
        setCost(cost);
        setType(type);
        calcEconomy();
        calcCostEff();
        setDate(returnDate());
    }

    Entry(String odoBefore, String odoAfter, String inFuel, String type, String cost, String dist, String economy, String costEff, String date) {
        setOdoBefore(Integer.parseInt(odoBefore));
        setOdoAfter(Integer.parseInt(odoAfter));
        setInFuel(Double.parseDouble(inFuel));
        setType(type);
        setCost(cost);
        setDist(dist);
        setEconomy(economy);
        setCostEff(costEff);
        setDate(date);
    }

    private String returnDate(){
        String[] months = new String[]{"January","February","March","April","May","June","July","August","september","October","November","December"};
        int yr = Calendar.getInstance().get(Calendar.YEAR);
        int mth = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return day + " " + months[mth] + " " + yr;
    }

}
