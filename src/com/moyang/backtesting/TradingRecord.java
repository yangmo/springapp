package com.moyang.backtesting;

import com.moyang.common.Constants;
import com.moyang.common.DateUtil;

import java.util.Date;
/**
 * Created by yangmo on 15-2-27.
 */
public class TradingRecord {
    private String buyDate;
    private double buyPrice;
    private String sellDate;
    private double sellPrice;

    private int holdingDays;

    private double profitPercent;


    public TradingRecord(String buyDate, double buyPrice, String sellDate, double sellPrice){
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
        this.holdingDays = DateUtil.calcIntervalDays(buyDate, sellDate);
        this.profitPercent = Double.valueOf(Constants.DOUBLE_FORMAT.format(getProfitPercent()));
    }

    @Override
    public String toString(){
        return buyDate + "\t" + buyPrice + "\t" + sellDate + "\t" + sellPrice + "\t" + holdingDays + "\t"
                + profitPercent;
    }



    public int getHoldingDays(){
        return holdingDays;
    }

    public double getProfitPercent(){
        return (sellPrice - buyPrice) / buyPrice;
    }

    public String getBuyDate(){
        return buyDate;
    }

    public void setBuyDate(String buyDate){
        this.buyDate = buyDate;
    }

    public double getBuyPrice(){
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice){
        this.buyPrice = buyPrice;
    }

    public String getSellDate(){
        return sellDate;
    }

    public void setSellDate(String sellDate){
        this.sellDate = sellDate;
    }

    public double getSellPrice(){
        return sellPrice;
    }

    public void setSellPrice(double sellPrice){
        this.sellPrice = sellPrice;
    }
}
