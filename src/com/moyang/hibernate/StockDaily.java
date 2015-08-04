package com.moyang.hibernate;

import com.moyang.common.Constants;

import java.io.Serializable;
import java.util.Date;

public class StockDaily implements Serializable{
	private String stockId;
	private Date date;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjClose;
    private double change;
	private long money;
	private double turnover;
	private long tradedMarketValue;
	private long marketValue;
    private double pe;
    private double pb;
	public StockDaily(){

	}

	public StockDaily(String stockId, String msg) {
		this.stockId = stockId;

		String[] components = msg.split(",");
		try {
			date = Constants.DATE_FORMAT.parse(components[0]);
		} catch (Exception e){
			throw  new RuntimeException("Invalid Date " + components[0]);
		}
		open = Double.valueOf(components[1]);
		high = Double.valueOf(components[2]);
		low = Double.valueOf(components[3]);
		close = Double.valueOf(components[4]);
		volume = Long.valueOf(components[5]);
		adjClose = Double.valueOf(components[6]);

	}

	@Override
	public String toString(){
		return  stockId + ","
				+ Constants.DATE_FORMAT.format(date) +","+ Constants.DOUBLE_FORMAT.format(open)+","
				+ Constants.DOUBLE_FORMAT.format(high)+","
				+ Constants.DOUBLE_FORMAT.format(low)+","
		        + Constants.DOUBLE_FORMAT.format(close)+","
				+ volume +","+ Constants.DOUBLE_FORMAT.format(adjClose) + ","
				+ getChange() + ","
				+ getMoney() + ","
				+ getTradedMarketValue() + ","
				+ getMarketValue() + ",";
	}

	public String getStockId(){
		return stockId;
	}

	public void setStockId(String stockId){
		this.stockId = stockId;
	}

	public double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
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
	public long getTradedMarketValue() {
		return tradedMarketValue;
	}
	public void setTradedMarketValue(long tradedMarketValue) {
		this.tradedMarketValue = tradedMarketValue;
	}
	public long getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(long marketValue) {
		this.marketValue = marketValue;
	}
    public double getPE() {
    	return pe;
    }
    public void setPE(double pe) {
    	this.pe = pe;
    }
	public double getPB() {
		return pb;
	}
	public void setPB(double pb) {
		this.pb = pb;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
}
