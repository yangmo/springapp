package com.moyang.api.Yahoo;

import java.io.File;
import java.util.*;

import com.moyang.hibernate.StockDaily;
import com.moyang.common.FileUtil;
import com.moyang.common.MarketplaceUtil;
import com.moyang.common.Constants;
import com.moyang.common.StockNameUtil;

public class YahooHistory {

	private String stockId ;
	private static String marketplace;
	private ArrayList<StockDaily> yahooHistory = new ArrayList<StockDaily>();

	/**
	 * Binary Search
	 * @param date
	 * @param start
	 * @param end
	 * @return
	 */
	public StockDaily findDatumAt(Date date, int start, int end){
		if(start > end){
			return null;
		}

		int middle = (start + end) / 2;
		Date middleDate  = yahooHistory.get(middle).getDate();

		if(middleDate.equals(date)){
			return yahooHistory.get(middle);
		}

		if(middleDate.compareTo(date) > 0){
			return findDatumAt(date, start, middle);
		} else{
			return findDatumAt(date, middle, end);
		}
	}

	public StockDaily findDatumAt(Date date){
		return findDatumAt(date, 0, getYahooHistory().size());
	}

	public static void main(String[] args){
YahooHistory history = new YahooHistory("600030");
		System.out.println(history.findDatumAt(new Date("2015-02-26"), 0, history.getYahooHistory().size()).getAdjClose());
	}

	public YahooHistory(String stockId) {
		String path = getFilePath(stockId);
		if(!new File(path).exists()){
			YahooAPI.writeYahooHistory(stockId, YahooAPI.getYahooHistory(stockId));
		}
		String rawInput = FileUtil.getContent(getFilePath(stockId));
		this.stockId = stockId;
		yahooHistory.addAll(rawInputToDatumList(stockId, rawInput));
	}

	public static List<StockDaily> rawInputToDatumList(String stockId, String rawInput){
		String[] data = rawInput.split("\n");

		ArrayList<StockDaily> list = new ArrayList<StockDaily>();

		for(int i = 1; i < data.length; i++){
			StockDaily datum = new StockDaily(stockId, data[i]);
			if(datum.getVolume() < (long) 1){
				continue;
			}
			list.add(datum);
		}
		Collections.reverse(list);
		return list;
	}
	
	public static String getFilePath(String stockId){
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
	public ArrayList<StockDaily> getYahooHistory() {
		return yahooHistory;
	}
	public void setYahooHistory(ArrayList<StockDaily> yahooHistory) {
		this.yahooHistory = yahooHistory;
	}
	
	
}
