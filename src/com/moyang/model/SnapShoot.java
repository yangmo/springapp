package com.moyang.model;

import com.moyang.hibernate.StockDaily;

import java.util.List;

/**
 * Created by yangmo on 15-1-24.
 */
public class SnapShoot implements Comparable<SnapShoot>{
    private String stockId;
    private List<StockDaily> datumList;
    private double distance;

    public SnapShoot(String stockId, List<StockDaily> datumList, double distance){
        this.stockId = stockId;
        this.datumList = datumList;
        this.distance = distance;
    }

    @Override
    public String toString(){
        return stockId+","+datumList.get(0).getDate()+","+datumList.get(datumList.size()-1).getDate();
    }
    public int compareTo(SnapShoot obj){
        if(distance == obj.getDistance()){
            return 0;
        }

        return (distance > obj.getDistance()) ? 1 : -1;
    }

    public String getStockId(){
        return stockId;
    }
    public void setStockId(String stockId){
        this.stockId = stockId;
    }

    public List<StockDaily> getDatumList(){
        return datumList;
    }
    public void setDatumList(List<StockDaily> datumList){
        this.datumList = datumList;
    }

    public double getDistance(){
        return distance;
    }
    public void setDistance(double distance){
        this.distance = distance;
    }
}
