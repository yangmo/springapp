package com.moyang.model;

/**
 * Created by yangmo on 15-2-6.
 */
public class StockFilterParas {
    private String highVolumeCriteria;
    private String oversoldCriteria;
    private String recentMaxVolCriteria;
    private String similarKAverageCriteria;

    public String getHighVolumeCriteria(){
        return  highVolumeCriteria;
    }

    public void setHighVolumeCriteria(String highVolumeCriteria){
        this.highVolumeCriteria = highVolumeCriteria;
    }

    public String getOversoldCriteria(){
        return oversoldCriteria;
    }

    public void setOversoldCriteria(String oversoldCriteria){
        this.oversoldCriteria = oversoldCriteria;
    }

    public String getRecentMaxVolCriteria(){
        return recentMaxVolCriteria;
    }

    public void setRecentMaxVolCriteria(String recentMaxVolCriteria){
        this.recentMaxVolCriteria = recentMaxVolCriteria;
    }
}
