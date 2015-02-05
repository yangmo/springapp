package com.moyang.common;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class StockNameUtil {

	public static final String STOCK_NAME_FILE_PATH = "files/Stocks.txt";
	public static final HashMap<String, String> STOCK_NAME_MAP = new HashMap<String, String>();
	
	protected StockNameUtil(){
	}

	static {
		init();
	}
	
	public static void init(){
		File dir = new File(Constants.STOCK_ROOT + Constants.SH);

		String[] files = dir.list();
		for(String fileName : files){
			String stockId = fileName.substring(0,6);

			String stockName = fileName.substring(6, fileName.length() - 4);

			STOCK_NAME_MAP.put(stockId, stockName);
		}
		
		dir = new File(Constants.STOCK_ROOT + Constants.SZ);
	    files = dir.list();
		for(String fileName : files){
			String stockId = fileName.substring(0,6);
			String stockName = fileName.substring(6, fileName.length() - 4);
			STOCK_NAME_MAP.put(stockId, stockName);
		}

	}

	public static boolean isValid(String stockId){
		return STOCK_NAME_MAP.containsKey(stockId);
	}

	public static void main(String[] args){
		init();
	}
	public static String getName(String stockId){
		if(STOCK_NAME_MAP.size() == 0){
			init();
		}
		return STOCK_NAME_MAP.get(stockId).replace("*", "");
	}
	
	public static Set<String> getAllStockIds(){
	    if(STOCK_NAME_MAP.size() == 0){
	    	init();
	    }
	    return STOCK_NAME_MAP.keySet();
	}
}
