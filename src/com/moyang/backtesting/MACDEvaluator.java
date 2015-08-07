package com.moyang.backtesting;

import com.moyang.api.MACD;
import com.moyang.common.Constants;
import com.moyang.model.StockDaily;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.DateUtil;
import com.moyang.model.AverageDatum;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by yangmo on 15-2-27.
 */
public class MACDEvaluator {
    private static final double STOP_LOSS = -10.05;
    private static final DecimalFormat dfDisplay = new DecimalFormat("#.00");

    public static void main(String[] args)throws Exception{

        String stockId = "600755";
        String start = "2000-01-01";
        String end  = "2015-02-27";

        ArrayList<TradingRecord> records = generateTradingRecord(stockId, Constants.DATE_FORMAT.parse(start),
                Constants.DATE_FORMAT.parse(end));

        Collections.sort(records);

        int count = 0;
        int totalHoldingDays = 0;
        for(TradingRecord record : records){
            System.out.println(count ++ + "\t" +record);
            totalHoldingDays += record.getHoldingDays();
        }

        System.out.println(TradingRecordUtil.overallProfit(records) + "\t" + totalHoldingDays);

        System.out.println(DateUtil.calcIntervalDays(start, end));
    }

    public static ArrayList<TradingRecord> generateTradingRecord(String stockId){
        YahooHistory history = new YahooHistory(stockId);
        return generateTradingRecord(stockId, history.getYahooHistory().get(0).getDate()
                , history.getYahooHistory().get(history.getYahooHistory().size() - 1).getDate());
    }

    public static ArrayList<TradingRecord> generateTradingRecord(String stockId, Date startDate, Date endDate){

        ArrayList<AverageDatum> macdList = MACD.getMACD(stockId, startDate, endDate);
        ArrayList<TradingRecord> recordList = new ArrayList<TradingRecord>();

        YahooHistory history = new YahooHistory(stockId);

        for(int i = 2; i < macdList.size() - 1; i++){
            if(macdList.get(i-2).getVal() <= 0 && macdList.get(i - 1).getVal() > 0){
                Date buyDate = macdList.get(i).getDate();
                StockDaily datum = history.findDatumAt(buyDate);
                double buyPrice =  datum.getOpen() * datum.getAdjClose() / datum.getClose();
                buyPrice = Double.valueOf(dfDisplay.format(buyPrice));
                Date sellDate = null;

                for(int j = i ; j < macdList.size(); j ++){
                    sellDate = macdList.get(j).getDate();
                    if (macdList.get(j - 1).getVal() >= 0&& !isStopLoss(buyPrice, history.findDatumAt(macdList.get(j-1).getDate()).getAdjClose())
                            ){
                        i++;
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

    public static boolean isStopLoss(double buyPrice, double curPrice){
        double diff = curPrice - buyPrice;
        return diff / buyPrice < STOP_LOSS;
    }
}
