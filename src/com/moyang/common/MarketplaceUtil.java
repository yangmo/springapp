package com.moyang.common;

public class MarketplaceUtil {

	public static String getMarketplace(String stockId){
		if(stockId == null || stockId.length() != 6){
			throw new RuntimeException("Invalid length of stockId " + stockId);
		}
		
		if(stockId.startsWith("0") || stockId.startsWith("3")){
		    return Constants.SZ;	
		}
		
		if(stockId.startsWith("6")){
			return Constants.SH;
		}
		
		return Constants.SZ;
	}
}
