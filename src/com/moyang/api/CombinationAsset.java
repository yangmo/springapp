package com.moyang.api;

import com.moyang.common.FileUtil;
import com.moyang.common.Constants;

public class CombinationAsset {

    public static String combiRoot = Constants.LOCAL_ROOT + "combination/" ;
	public static double getTotal(String username) throws Exception{
		double total = 0;
		
		String[] combis = FileUtil.getContent(combiRoot + username).split("\n");
	
		for(String combi : combis){
			int index = combi.indexOf(" ");
			String stockId = combi.substring(0, index);
			int sum = Integer.valueOf(combi.substring(index + 1)).intValue();
            total += SinaAPI.getPriceForStockId(stockId) * Double.valueOf(sum).doubleValue();
		}
		
		
		return total;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(getTotal("mo"));
	}
}
