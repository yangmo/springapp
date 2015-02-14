package com.moyang.historyrepeat;

import java.util.List;

import com.moyang.api.YahooDatum;
import com.moyang.api.YahooHistory;
import com.moyang.common.StockNameUtil;

public class Test {

	public static List<YahooDatum> getExpectedList(String stockId, int days) throws Exception{
		YahooHistory history = new YahooHistory(stockId);
		int length = history.getYahooHistory().size();
		return history.getYahooHistory().subList(length - days, length);
	}
	
	public static void main(String[] args) throws Exception{
		String target = "600030";
		YahooHistory history = new YahooHistory(target);
		int length = history.getYahooHistory().size();
		List<YahooDatum> expected = history.getYahooHistory().subList(length-31, length);
		
		List<YahooDatum> w = PriceInterval.findMostSimilar(expected, new YahooHistory("000737").getYahooHistory());
		for(YahooDatum datum : w){
			System.out.println(datum.getDateStr() + " " + datum.getAdjClose());
		}
	
		double diff = 10;
		String mostStock = "";
		for(String stockId : StockNameUtil.getAllStockIds()){
			double cdiff = 10;
			
			try{
		     cdiff =  PriceInterval.findDistance(expected, stockId);		
			} catch(Exception e){
				
			}
		    if(cdiff < diff){
		    	diff = cdiff;
		    	mostStock = stockId;
		    	System.out.println(stockId + "\t" + diff);
		    }
		}
        System.out.println(mostStock +"\t"+ diff);
	}
}
