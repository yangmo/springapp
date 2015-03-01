package com.moyang.api.Yahoo;

import java.io.File;
import java.util.*;

import com.moyang.api.Yahoo.YahooAPI;
import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.common.FileUtil;
import com.moyang.common.MarketplaceUtil;
import com.moyang.common.Constants;
import com.moyang.common.StockNameUtil;

public class YahooHistory {

	private String stockId ;
	private String marketplace;
	private ArrayList<YahooDatum> yahooHistory = new ArrayList<YahooDatum>();

	/**
	 * Binary Search
	 * @param dateStr
	 * @param start
	 * @param end
	 * @return
	 */
	public YahooDatum findDatumAt(String dateStr, int start, int end){
		if(start > end){
			return null;
		}

		int middle = (start + end) / 2;
		String middleDateStr = yahooHistory.get(middle).getDateStr();

		if(middleDateStr.equals(dateStr)){
			return yahooHistory.get(middle);
		}

		if(middleDateStr.compareTo(dateStr) > 0){
			return findDatumAt(dateStr, start, middle);
		} else{
			return findDatumAt(dateStr, middle, end);
		}
	}

	public YahooDatum findDatumAt(String dateStr){
		return findDatumAt(dateStr, 0, getYahooHistory().size());
	}

	public static void main(String[] args){
YahooHistory history = new YahooHistory("600030");
		System.out.println(history.findDatumAt("2015-02-26", 0, history.getYahooHistory().size()).getAdjClose());
	}

	public YahooHistory(String stockId) {
		String path = getFilePath(stockId);
		if(!new File(path).exists()){
			YahooAPI.writeYahooHistory(stockId, YahooAPI.getYahooHistory(stockId));
		}
		String rawInput = FileUtil.getContent(getFilePath(stockId));
		this.stockId = stockId;
		yahooHistory.addAll(rawInputToDatumList(rawInput));
	}

	public static List<YahooDatum> rawInputToDatumList(String rawInput){
		String[] data = rawInput.split("\n");

		ArrayList<YahooDatum> list = new ArrayList<YahooDatum>();

		for(int i = 1; i < data.length; i++){
			YahooDatum datum = new YahooDatum(data[i]);
			if(datum.getVolume() < (long) 1){
				continue;
			}
			list.add(datum);
		}
		Collections.reverse(list);
		return list;
	}
	
	public String getFilePath(String stockId){
		marketplace = MarketplaceUtil.getMarketplace(stockId);
		String stockName = StockNameUtil.getName(stockId);
		return Constants.STOCK_ROOT + marketplace + "/" + stockId + stockName + ".csv";
	}

	
	public String getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(String marketplace) {
		this.marketplace = marketplace;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public ArrayList<YahooDatum> getYahooHistory() {
		return yahooHistory;
	}
	public void setYahooHistory(ArrayList<YahooDatum> yahooHistory) {
		this.yahooHistory = yahooHistory;
	}
	
	
}
