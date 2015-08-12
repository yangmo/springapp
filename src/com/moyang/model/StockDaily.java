package com.moyang.model;

import com.moyang.common.Constants;

import java.io.Serializable;
import java.util.Date;

public class StockDaily extends Daily implements Serializable {

	private double adjClose;
	private double turnover;
	private long tradedMarketValue;
	private long marketValue;
    private double pe;
    private double pb;
	public StockDaily(){

	}

	public StockDaily(String id, String msg) {
		this.id = id;

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
		return  id + ","
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



	public double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
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
