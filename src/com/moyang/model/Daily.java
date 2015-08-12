package com.moyang.model;

import java.util.Date;

/**
 * Created by moyang on 15/8/8.
 */
public class Daily {
    protected String id;
    protected Date date;
    protected double open;
    protected double high;
    protected double low;
    protected double close;
    protected long volume;
    protected double change;
    protected long money;

    public String getId(){
        return id;
    }
    public void setId(String stockId){
        this.id = stockId;
    }
    public double getClose() {
        return close;
    }
    public void setClose(double close) {
        this.close = close;
    }
    public double getLow() {
        return low;
    }
    public void setLow(double low) {
        this.low = low;
    }
    public double getHigh() {
        return high;
    }
    public void setHigh(double high) {
        this.high = high;
    }
    public double getOpen() {
        return open;
    }
    public void setOpen(double open) {
        this.open = open;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public long getVolume() {
        return volume;
    }
    public void setVolume(long volume) {
        this.volume = volume;
    }
    public void setChange(double change) {
        this.change = change;
    }
    public double getChange() {
        return change;
    }
    public void setMoney(long money) {
        this.money = money;
    }
    public long getMoney() {
        return money;
    }
}
