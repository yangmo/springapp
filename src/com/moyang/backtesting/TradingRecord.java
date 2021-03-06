package com.moyang.backtesting;

import com.moyang.common.Constants;
import com.moyang.common.DateUtil;

import java.util.Date;
/**
 * Created by yangmo on 15-2-27.
 */
public class TradingRecord implements Comparable<TradingRecord>{
    private Date buyDate;
    private double buyPrice;
    private Date sellDate;
    private double sellPrice;

    private int holdingDays;

    private double profitPercent;


    public TradingRecord(Date buyDate, double buyPrice, Date sellDate, double sellPrice){
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
        this.holdingDays = DateUtil.calcIntervalDays(buyDate, sellDate);
        this.profitPercent = Double.valueOf(Constants.DOUBLE_FORMAT.format(getProfitPercent()));
    }

    @Override
    public int compareTo(TradingRecord obj){
        if(profitPercent != obj.getProfitPercent()){
            if(profitPercent > obj.getProfitPercent()){
                return 1;
            } else {
                return -1;
            }
        }
        return obj.getHoldingDays() - holdingDays;
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

    public Date getBuyDate(){
        return buyDate;
    }

    public void setBuyDate(Date buyDate){
        this.buyDate = buyDate;
    }

    public double getBuyPrice(){
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice){
        this.buyPrice = buyPrice;
    }

    public Date getSellDate(){
        return sellDate;
    }

    public void setSellDate(Date sellDate){
        this.sellDate = sellDate;
    }

    public double getSellPrice(){
        return sellPrice;
    }

    public void setSellPrice(double sellPrice){
        this.sellPrice = sellPrice;
    }
}
