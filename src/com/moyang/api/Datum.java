package com.moyang.api;

import java.util.Date;

/**
 * Created by yangmo on 15-1-24.
 */
public class Datum {
    private Date date;
    private double val;

    public Datum(double val, Date date){
        this.val = val;
        this.date = date;
    }

    public double getVal(){
        return val;
    }
    public void setVal(double val){
        this.val = val;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
