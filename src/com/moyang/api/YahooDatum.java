package com.moyang.api;

import com.moyang.common.Constants;

public class YahooDatum {
	private String dateStr;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjClose;
	
	public YahooDatum(String msg) {
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

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
