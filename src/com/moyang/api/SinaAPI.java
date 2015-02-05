package com.moyang.api;

import java.util.ArrayList;
import java.util.Scanner;

import com.moyang.common.MarketplaceUtil;
import com.moyang.common.RegUtil;
import com.moyang.common.StockNameUtil;
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
	
	public static void main(String[] args) throws Exception{
		Scanner in=new Scanner(System.in);
        String input = "";
        while((input = in.nextLine())!=null){
        	try {
        		String rawInput = SimpleWebDownloader.getAsString(getRequest(input));
        		System.out.println(getPriceFromMsg(rawInput) + " " +StockNameUtil.getName(input));
        	} catch (Exception e){
        	    System.out.println(e.getMessage());	
        	}
        }
	}
}
