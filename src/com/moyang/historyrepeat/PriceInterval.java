package com.moyang.historyrepeat;

import java.util.List;

import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.api.Yahoo.YahooHistory;

public class PriceInterval {

	public static final double SLOPE_RANGE = 0.2;
	
	public static double findDistance(List<YahooDatum> expected, String stockId) throws Exception{
		List<YahooDatum> whole = new YahooHistory(stockId).getYahooHistory();
	    List<YahooDatum> actual = PriceInterval.findMostSimilar(expected, whole);


	    return getDiff(expected, actual);
	}

	public static List<YahooDatum> findMostSimilar(List<YahooDatum> expected, List<YahooDatum> whole)
		throws InterruptedException{
		double mostSimilarVal = 200;
		int pos = 0;
		
		int length = expected.size();
		List<YahooDatum> actual;
		/**
		 * TODO
		 */
		if(whole.size() < expected.size()){
			
		}
		
		for(int i = 0; i < whole.size() - length - 1; i++){
			actual = whole.subList(i, i + length);
			double diff = getDiff(expected, actual);
			if(diff < mostSimilarVal){
				pos = i;
				mostSimilarVal = diff;
			}
		}
		return whole.subList(pos, pos + length);
	}
	
	public static double getDiff(List<YahooDatum> expected, List<YahooDatum> actual){
		if(!isValid(expected, actual) || !isWithinSlopeRange(expected, actual)){
			return Double.MAX_VALUE;
		}
		
		double result = 0;
		double slope1, slope2;
		for(int i = 0; i < expected.size() - 1; i++){
			slope1 = expected.get(i + 1).getAdjClose() / expected.get(i).getAdjClose();
			slope2 = actual.get(i + 1).getAdjClose() / actual.get(i).getAdjClose();
			result += Math.abs(slope1 - slope2);
		}
		
		return result;
	}
	
	public static boolean isValid(List<YahooDatum> expected, List<YahooDatum> actual){
		if(expected == null || actual == null){
			return false;
		}
		
		if(expected.size() != actual.size() || expected.size() == 0){
			return false;
		}
		
		return true;
	}
	
	public static boolean isWithinSlopeRange(List<YahooDatum> expected, List<YahooDatum> actual){
		int length = expected.size() - 1;
		double slope1 = expected.get(length).getAdjClose() / expected.get(0).getAdjClose();
		double slope2 = actual.get(length).getAdjClose() / actual.get(0).getAdjClose();
	
		if(Math.abs(slope1 - slope2) <= SLOPE_RANGE ){
			return true;
		}
		return false;
	}
}
