package com.moyang.backtesting;

import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-28.
 */
public class TradingRecordUtil {
    public static double overallProfit(ArrayList<TradingRecord> records){
        double profit = 0;

        for(TradingRecord record : records){
            profit += record.getProfitPercent();
        }

        return profit;
    }
}
