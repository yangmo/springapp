package com.moyang.model;

/**
 * Created by yangmo on 15-1-25.
 */
public class DailyUpdateParas {
    private String dateStr;

    public DailyUpdateParas(String dateStr){
        this.dateStr = dateStr;
    }

    public void setDateStr(String dateStr){
        this.dateStr = dateStr;
    }

    public String getDateStr(){
        return dateStr;
    }
}
