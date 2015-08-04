package com.moyang.api.YuCeZhe;

import java.io.File;
import java.util.List;

import com.moyang.common.Constants;
import com.moyang.common.FileUtil;
import com.moyang.hibernate.StockDaily;
import org.springframework.util.StringUtils;

/**
 * Created by moyang on 15/8/3.
 */
public class YuchezheFeeder {

	static String getStockFileName(String dateString) {
		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6);
		return year + "-" + month + "-" + day + " data.csv";
	}
	
	/**
	 * dateString in format like 20150804
	 * @param dateString
	 * @return
	 */
	public static String getStockFeedPath(String dateString) {
		return YucezheConstants.DAILY_FEED_BASE + dateString + "/" + getStockFileName(dateString);
	}
	
	static void feedStock(String stockId, String content) {
		StockDaily feed = YucezheCommonUtil.getStockDailyFromLine(content);
		List<StockDaily> stockDailies = YucezheAPI.getStockDailies(stockId);
		StockDaily mostRecent = stockDailies.get(stockDailies.size() - 1);
		if(!feed.getDate().after(mostRecent.getDate())) {
			throw new RuntimeException("Already Updated!");
		}
		
		String stockDataPath = YucezheCommonUtil.getDataPath(stockId);
		FileUtil.append(stockDataPath, "\n" + content);
		System.out.println("Successfully Feeded " + stockId + " " + Constants.DATE_FORMAT.format(feed.getDate()));
	}
	static void removeOnelineForAll() throws Exception{
		File[] files = FileUtil.getFilesUnderDir(YucezheConstants.DATA_BASE);
		for(File file : files) {
			String path = file.getAbsolutePath();
			String contents = FileUtil.getContent(path);
			String[] lines = contents.split("\n");
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < lines.length - 1; i++) {
				sb.append(lines[i] + "\n");
			}
			FileUtil.writeToFile(path, sb.toString());
		}
	}
    static void removeEntersInFile(String path) throws Exception{
        String contents = FileUtil.getContent(path);
        String[] lines = contents.split("\n");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < lines.length; i++) {
            if(!StringUtils.isEmpty(lines[i])) {
                sb.append(lines[i] + "\n");
            }
        }
        FileUtil.writeToFile(path, sb.toString());
    }
	static void feedAll(String date) {
		String contents = FileUtil.getContent(getStockFeedPath(date));
		String[] lines = contents.split("\n");
		
		for(int i = 1; i < lines.length; i++) {
			try {
			    String stockId = YucezheCommonUtil.getStockIdFromLine(lines[i]);
			    String currentData = FileUtil.getContent(YucezheCommonUtil.getDataPath(stockId));
			    boolean endWithEnter = currentData.endsWith("\n");
			
			    StockDaily feed = YucezheCommonUtil.getStockDailyFromLine(lines[i]);
			    List<StockDaily> stockDailies = YucezheAPI.getStockDailies(stockId);
			    StockDaily mostRecent = stockDailies.get(stockDailies.size() - 1);
			    if(!feed.getDate().after(mostRecent.getDate())) {
				    throw new RuntimeException("Already Updated!");
			    }
			
		     	String stockDataPath = YucezheCommonUtil.getDataPath(stockId);
			    FileUtil.append(stockDataPath, "\n" + lines[i]);
                removeEntersInFile(stockDataPath);
			    System.out.println("Successfully Feeded " + stockId + " " + Constants.DATE_FORMAT.format(feed.getDate()));
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
			}
		}
		
	}
	
	public static void main(String[] args) {
		feedAll("20150804");
	}
}
