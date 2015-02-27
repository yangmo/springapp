package com.moyang.backtesting;

import com.moyang.api.MACD;
import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.model.AverageDatum;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-27.
 */
public class MACDEvaluator {
    private static final DecimalFormat dfDisplay = new DecimalFormat("#.00");

    public static void main(String[] args){
        ArrayList<TradingRecord> records = generateTradingRecord("600030", "2003-01-09", "2015-11-15");

        int count = 0;
        for(TradingRecord record : records){
            System.out.println(count ++ + "\t" +record);
        }

        System.out.println(TradingRecordUtil.overallProfit(records));
    }

    public static ArrayList<TradingRecord> generateTradingRecord(String stockId){
        YahooHistory history = new YahooHistory(stockId);
        return generateTradingRecord(stockId, history.getYahooHistory().get(0).getDateStr()
                , history.getYahooHistory().get(history.getYahooHistory().size() - 1).getDateStr());
    }

    public static ArrayList<TradingRecord> generateTradingRecord(String stockId, String startDate, String endDate){

        ArrayList<AverageDatum> macdList = MACD.getMACD(stockId, startDate, endDate);
        ArrayList<TradingRecord> recordList = new ArrayList<TradingRecord>();

        YahooHistory history = new YahooHistory(stockId);

        for(int i = 2; i < macdList.size() - 1; i++){
            if(macdList.get(i-2).getVal() <= 0 && macdList.get(i - 1).getVal() > 0){
                String buyDate = macdList.get(i).getDateStr();
                YahooDatum datum = history.findDatumAt(buyDate);
                double buyPrice =  datum.getOpen() * datum.getAdjClose() / datum.getClose();
                buyPrice = Double.valueOf(dfDisplay.format(buyPrice));
                String sellDate = "";
                for(int j = i ; j < macdList.size(); j ++){
                    if(j == macdList.size() - 1 || macdList.get(j - 1).getVal() > 0){
                        i++;
                        sellDate = macdList.get(j).getDateStr();
                    } else {
                        break;
                    }
                }

                datum = history.findDatumAt(sellDate);
                double sellPrice = datum.getOpen() * datum.getAdjClose() / datum.getClose();
                sellPrice = Double.valueOf(dfDisplay.format(sellPrice));

                TradingRecord record = new TradingRecord(buyDate, buyPrice, sellDate, sellPrice);
                recordList.add(record);
             }
        }

        return recordList;
    }
}
