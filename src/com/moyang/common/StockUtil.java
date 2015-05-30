package com.moyang.common;
import com.moyang.api.Yahoo.YahooHistory;

import java.util.Date;

/**
 * Created by yangmo on 15-1-27.
 */
public class StockUtil {
    public static boolean isAlreadyUpdated(String stockId) {
        YahooHistory history = new YahooHistory(stockId);
        if(history == null || history.getYahooHistory().size() < 2){
            return false;
        }
        Date date = history.getYahooHistory().get(history.getYahooHistory().size()-1).getDate();

        if(!Constants.MOST_RECENT_TRADING_DAY.equals(Constants.DATE_FORMAT.format(date))){
            return false;
        }
        return true;
    }
}
