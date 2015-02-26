package com.moyang.evaluate;

/**
 * Created by yangmo on 15-2-14.
 */
public class TradeRecord {
    private String stockId;
    private String buyDateString;
    private String sellDateString;
    private double buyPrice;
    private double sellPrice;


    public String getStockId(){
        return stockId;
    }
    public void setStockId(String stockId){
        this.stockId = stockId;
    }
    public String getBuyDateString(){
        return buyDateString;
    }
    public void setBuyDateString(String buyDateString){
        this.buyDateString = buyDateString;
    }
    public String getSellDateString(){
        return sellDateString;
    }
    public void setSellDateString(String sellDateString){
        this.sellDateString = sellDateString;
    }
    public double getBuyPrice(){
        return buyPrice;
    }
    public void setBuyPrice(double buyPrice){
        this.buyPrice = buyPrice;
    }
    public double getSellPrice(){
        return sellPrice;
    }
    public void setSellPrice(double sellPrice){
        this.sellPrice = sellPrice;
    }
}
