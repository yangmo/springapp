package com.moyang.hibernate;

/**
 * Created by yangmo on 15-5-1.
 */
public class StockInfo {
    private String stockId;
    private String stockName;
    private String marketplace;

    public StockInfo(){

    }

    public StockInfo(String stockId, String stockName, String marketplace){
        this.stockId = stockId;
        this.stockName = stockName;
        this.marketplace = marketplace;
    }

    public String getStockId(){
        return stockId;
    }

    public void setStockId(String stockId){
        this.stockId = stockId;
    }

    public String getStockName(){
        return stockName;
    }

    public void setStockName(String stockName){
        this.stockName = stockName;
    }

    public String getMarketplace(){
        return marketplace;
    }

    public void setMarketplace(String marketplace){
        this.marketplace = marketplace;
    }

}