package com.moyang.api;

/**
 * Created by yangmo on 15-1-24.
 */
public class Datum {
    private String dateStr;
    private double val;

    public Datum(double val, String dateStr){
        this.val = val;
        this.dateStr = dateStr;
    }

    public double getVal(){
        return val;
    }
    public void setVal(double val){
        this.val = val;
    }

    public String getDateStr(){
        return dateStr;
    }

    public void setDateStr(String dateStr){
        this.dateStr = dateStr;
    }
}
