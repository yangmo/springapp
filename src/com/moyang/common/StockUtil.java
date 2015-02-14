package com.moyang.common;
import com.moyang.api.YahooHistory;

/**
 * Created by yangmo on 15-1-27.
 */
public class StockUtil {
    public static boolean isAlreadyUpdated(String stockId) {
        YahooHistory history = new YahooHistory(stockId);
        if(history == null || history.getYahooHistory().size() < 2){
            return false;
        }
        String dateString = history.getYahooHistory().get(history.getYahooHistory().size()-1).getDateStr();

        if(!Constants.LATEST_DAY.equals(dateString)){
            return false;
        }
        return true;
    }
}
