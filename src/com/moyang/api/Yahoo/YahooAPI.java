package com.moyang.api.Yahoo;

import java.io.File;
import java.util.List;


import com.moyang.common.*;
import com.moyang.common.downloader.SimpleWebDownloader;
import com.moyang.hibernate.StockDaily;

public class YahooAPI {

	public static final String REQUEST_BASE = "http://table.finance.yahoo.com/table.csv?s=";
	
	public static String getYahooHistory(String stockId) {
		try{
     		return SimpleWebDownloader.getAsString(getBasicRequest(stockId));
		} catch (Exception e) {
			return null;
		}

	}

	public static String getBasicRequest(String stockId){
		String marketplace = MarketplaceUtil.getMarketplace(stockId);
		String request = REQUEST_BASE + stockId;

		return request += Constants.SH.equals(marketplace) ? ".ss" : ".sz";
	}
	
	public static List<StockDaily> getRecentClose(String stockId, int days) throws Exception{

		List<StockDaily> data = new YahooHistory(stockId).getYahooHistory();
		
		int startPos = (data.size() - days) >= 0 ? (data.size() - days) : 0; 
		
		return data.subList(startPos, data.size());
	}

	/**
	 *
	 * @param stockId
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<StockDaily> getHistoryBetween(String stockId, int start, int end) throws Exception{
		YahooHistory history = new YahooHistory(stockId);
		if(history == null){
			return null;
		}

		List<StockDaily> data = history.getYahooHistory();
		int length = data.size();
		if(data == null || length == 0){
            return null;
		}

		end = (end > data.size()) ? data.size() : end;
        start = (start > data.size()) ? data.size() : start;

		if(end < start){
			return null;
		}

		return data.subList(length - end, length - start);
	}


	public static void writeYahooHistory(String stockId, String content){
		String path = getLocalPath(stockId);

		File f = new File(path);
		try{
		    f.createNewFile();
		    FileUtil.writeToFile(path, content);
		} catch (Exception e) {

		}
	}



	public static String getLocalPath(String stockId){
		return Constants.LOCAL_ROOT + "stocks/" +  MarketplaceUtil.getMarketplace(stockId)
				+ "/" + stockId.trim() + StockNameUtil.getName(stockId) + ".csv";
	}



	public static void main(String[] args) throws Exception{
		String startStr = "2015-01-01";
		String endStr = "2015-01-19";

		String stockId = "600030";

		String request = getBasicRequest(stockId);
		String [] sArr = startStr.split("-");
		request+="&a="+ DateUtil.toMonthInt(sArr[1])+"&b="+sArr[2]+"&c="+sArr[0];
		String [] eArr = endStr.split("-");
		request+="&d="+DateUtil.toMonthInt(eArr[1])+"&e="+eArr[2]+"&f="+eArr[0];
		request+="&g=d";
		System.out.println(request);
		System.out.print(SimpleWebDownloader.getAsString(request));
	}


	public static List<StockDaily> getHistoryBetween(String stockId, String startStr, String endStr)
			throws Exception{

		String request = getBasicRequest(stockId);
		String [] sArr = startStr.split("-");
		request+="&a="+DateUtil.toMonthInt(sArr[1])+"&b="+sArr[2]+"&c="+sArr[0];
		String [] eArr = endStr.split("-");
		request+="&d="+DateUtil.toMonthInt(eArr[1])+"&e="+eArr[2]+"&f="+eArr[0];
		request+="&g=d";
		String rawInput = SimpleWebDownloader.getAsString(request);

		return YahooHistory.rawInputToDatumList(stockId, rawInput);
	}
}
