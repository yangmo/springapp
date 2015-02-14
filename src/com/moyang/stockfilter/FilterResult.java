package com.moyang.stockfilter;

/**
 * Created by yangmo on 15-2-14.
 */
public class FilterResult {
    private int serialNo;
    private String stockId;
    private String stockName;
    private String detail;
    private String link;

    public int getSerialNo(){
        return serialNo;
    }
    public void setSerialNo(int serialNo){
        this.serialNo = serialNo;
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

    public String getDetail(){
        return detail;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link = link;
    }
}
