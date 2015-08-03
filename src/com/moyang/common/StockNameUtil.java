package com.moyang.common;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class StockNameUtil {

	public static final HashMap<String, String> STOCK_NAME_MAP = new HashMap<String, String>();
	
	protected StockNameUtil(){
	}

	static {
		init();
	}
	
	public static void init(){
		String content =  FileUtil.getContent(Constants.STOCKS_TXT);

		String[] stocks = content.split("\n");
		for(String stock : stocks){
			String[] tmp = stock.split("\t");

			if(tmp == null || tmp.length != 2){
				continue;
			}


			STOCK_NAME_MAP.put(tmp[0], tmp[1]);
		}

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
