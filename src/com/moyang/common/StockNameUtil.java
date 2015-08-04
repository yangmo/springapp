package com.moyang.common;

import java.util.HashMap;
import java.util.Set;

public class StockNameUtil {

	private static final String MARKETPLACE_BASE = "data/marketplace data/";
	private static final String[] MARKETPLACES = {"sh", "sz", "zx", "cy"};
	public static final HashMap<String, String> STOCK_NAME_MAP = new HashMap<String, String>();
	
	protected StockNameUtil(){
	}

	static {
		for (String marketplace : MARKETPLACES) {
			loadMarketplace(marketplace);
		}

	}
	static void loadMarketplace(String marketplace) {
		String content =  FileUtil.getContent(MARKETPLACE_BASE + marketplace);

		String[] stocks = content.split("\n");
		for(String stock : stocks){
			String[] tmp = stock.split("\t");

			if(tmp == null || tmp.length != 2){
				continue;
			}
			STOCK_NAME_MAP.put(tmp[0], tmp[1]);
		}

	}



	public static String getName(String stockId){
		return STOCK_NAME_MAP.get(stockId).replace("*", "");
	}
	
	public static Set<String> getAllStockIds(){
	    return STOCK_NAME_MAP.keySet();
	}
}
