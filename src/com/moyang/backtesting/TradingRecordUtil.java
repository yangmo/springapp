package com.moyang.backtesting;

import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-28.
 */
public class TradingRecordUtil {
    public static double overallProfit(ArrayList<TradingRecord> records){
        double profit = 1;

        for(TradingRecord record : records){
            profit *= (record.getProfitPercent() + 1);
        }

        return profit;
    }
}
