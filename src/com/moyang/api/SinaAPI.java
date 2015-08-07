package com.moyang.api;

import java.util.ArrayList;
import java.util.Scanner;

import com.moyang.model.StockDaily;
import com.moyang.common.Constants;
import com.moyang.common.MarketplaceUtil;
import com.moyang.common.RegUtil;
import com.moyang.common.downloader.SimpleWebDownloader;

public class SinaAPI {
	public static String  requestBase = "http://hq.sinajs.cn/list=";

	public static String getRequest(String stockId){
		return requestBase + MarketplaceUtil.getMarketplace(stockId) + stockId;
	}
	public static String getPriceFromMsg(String msg) throws Exception{
		ArrayList<String> prices = RegUtil.getPrices(msg);
		return prices.get(prices.size() - 1);
	}
	
	public static double getPriceForStockId(String stockId) throws Exception{
		String rawInput = SimpleWebDownloader.getAsString(getRequest(stockId));
        return Double.valueOf(getPriceFromMsg(rawInput));
	}

	public static StockDaily getLastestYahooDatum(String stockId) throws Exception{
		String rawInput = SimpleWebDownloader.getAsString(getRequest(stockId));
		rawInput.replaceAll("\"", "");

		String[] contents = rawInput.split(",");
		StockDaily datum = new StockDaily();

		datum.setDate(Constants.DATE_FORMAT.parse(Constants.MOST_RECENT_TRADING_DAY));
        datum.setAdjClose(Double.valueOf(contents[3]));
        datum.setClose(Double.valueOf(contents[3]));
		datum.setHigh(Double.valueOf(contents[4]));
		datum.setVolume(Long.valueOf(contents[8]));
		datum.setLow(Double.valueOf(contents[5]));
		datum.setOpen(Double.valueOf(contents[1]));
		return datum;
	}

	public static void main(String[] args) throws Exception{
		Scanner in=new Scanner(System.in);
        String input = "";
        while((input = in.nextLine())!=null){
        	try {
        		String rawInput = SimpleWebDownloader.getAsString(getRequest(input));
				System.out.println(getLastestYahooDatum(input).toString());
        	} catch (Exception e){
        	    System.out.println(e.getStackTrace());
        	}
        }
	}
}
