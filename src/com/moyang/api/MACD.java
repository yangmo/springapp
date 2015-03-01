package com.moyang.api;

import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.model.AverageDatum;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-21.
 */
public class MACD {
    private static final double SHORT = 12.0;
    private static final double LONG = 26.0;
    private static final double M = 9.0;
    private static final  DecimalFormat df = new DecimalFormat("#.0000");
    private static final  DecimalFormat dfDisplay = new DecimalFormat("#.00");

    private static double trim(double original){
        return Double.valueOf(df.format(original));
    }

    private static double trimDisplay(double original) {
        return Double.valueOf(dfDisplay.format(original));
    }
    public static ArrayList<AverageDatum> getMACD(String stockId, String startDate, String endDate){
        YahooHistory history = new YahooHistory(stockId);
        ArrayList<YahooDatum> data = history.getYahooHistory();
        ArrayList<AverageDatum> result = new ArrayList<AverageDatum>();

        double shortRatio = (SHORT - 1) / (SHORT + 1);
        double longRatio =  (LONG - 1) / (LONG + 1);

        if(AverageDatum.compareDateStr(data.get(0).getDateStr(), startDate) == 0){
            result.add(new AverageDatum(0, startDate));
        }

        double emaShort = data.get(0).getAdjClose() +(1 - shortRatio) * (data.get(1).getAdjClose() - data.get(0).getAdjClose());
        double emaLong =  data.get(0).getAdjClose() +(1 - longRatio)* (data.get(1).getAdjClose() - data.get(0).getAdjClose());

        double diff = emaShort - emaLong;
        double dea = 2 / (M + 1) * diff ;
        double macd = trimDisplay(2 * (diff - dea));

        if(AverageDatum.compareDateStr(data.get(1).getDateStr(), startDate) >= 0){
            result.add(new AverageDatum(macd, data.get(1).getDateStr()));
        }


        for(int i = 2; i < data.size(); i++){

            emaShort = trim(emaShort * shortRatio+ data.get(i).getAdjClose() * (1 - shortRatio));
            emaLong = trim(emaLong * longRatio + data.get(i).getAdjClose() * (1 - longRatio));

            diff = emaShort - emaLong;
            dea = 2 / (M + 1) * diff + dea * (M - 1) / (M + 1);
            macd =trimDisplay(2 * (diff - dea));

            YahooDatum datum = data.get(i);

            String dateString = data.get(i).getDateStr();

            if(AverageDatum.compareDateStr(dateString, startDate) >= 0
                    && AverageDatum.compareDateStr(dateString, endDate)<= 0){
                result.add(new AverageDatum(macd, dateString));
            }
            if(AverageDatum.compareDateStr(dateString, endDate) > 0){
                return result;
            }

        }
        return result;
    }


    public static void main(String[] args){

     //   ArrayList<AverageDatum> list = getMACD("600030", "2003-01-06", "2003-02-22");
        ArrayList<AverageDatum> list = getMACD("000960", "2014-11-01", "2015-02-27");

        for(AverageDatum item : list){
            System.out.println(item);
        }
    }
}
