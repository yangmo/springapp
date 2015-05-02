package com.moyang.hibernate;

import com.moyang.common.Constants;

public class StockDaily {
	private String stockId;
	private String dateStr;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjClose;

	public StockDaily(){

	}

	public StockDaily(String stockId, String msg) {
		this.stockId = stockId;

		String[] components = msg.split(",");
		dateStr = components[0];
		open = Double.valueOf(components[1]);
		high = Double.valueOf(components[2]);
		low = Double.valueOf(components[3]);
		close = Double.valueOf(components[4]);
		volume = Long.valueOf(components[5]);
		adjClose = Double.valueOf(components[6]);

	}

	@Override
	public String toString(){
		return dateStr+","+ Constants.DOUBLE_FORMAT.format(open)+","
				+ Constants.DOUBLE_FORMAT.format(high)+","
				+ Constants.DOUBLE_FORMAT.format(low)+","
		        + Constants.DOUBLE_FORMAT.format(close)+","
				+ volume +","+ Constants.DOUBLE_FORMAT.format(adjClose);
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
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}
	
}
