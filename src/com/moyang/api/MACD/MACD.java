package com.moyang.api.MACD;

import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.model.AverageDatum;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-21.
 */
public class MACD {
    private static final int SHORT = 12;
    private static final int LONG = 26;
    private static final int M = 9;
    private static final  DecimalFormat df = new DecimalFormat("#.000");

    private static double trim(double original){
        return Double.valueOf(df.format(original));
    }

    public static ArrayList<AverageDatum> getMACD(String stockId, String startDate, String endDate){
        YahooHistory history = new YahooHistory(stockId);
        ArrayList<YahooDatum> data = history.getYahooHistory();
        ArrayList<AverageDatum> result = new ArrayList<AverageDatum>();

        double dea = 0.0;
        double diff = 0.0;
        double macd = 0.0;

        if(AverageDatum.compareDateStr(data.get(0).getDateStr(), startDate) == 0){
            result.add(new AverageDatum(0.0, startDate));
        }

        for(int i = 1; i < data.size(); i++){

            double price_change = data.get(i).getClose() - data.get(i - 1).getClose();
            double emaShort = data.get(i - 1).getClose() + price_change * 2 / (SHORT + 1);
            double emaLong = data.get(i -1 ).getClose() + price_change * 2 / (LONG + 1);

            diff = trim(emaShort - emaLong);
            dea = trim(2.0 / (M + 1) * diff + dea * (M - 1) / (M + 1));
            macd =trim(2 * (diff - dea));


            String dateString = data.get(i).getDateStr();

            if(AverageDatum.compareDateStr(dateString, startDate) >= 0
                    && AverageDatum.compareDateStr(dateString, endDate)<= 0){
                System.out.println(dateString + "\t" + data.get(i).getClose() + "\t" + diff + "\t" + dea + "\t" +macd);
                result.add(new AverageDatum(macd, dateString));
            }
            if(AverageDatum.compareDateStr(dateString, endDate) > 0){
                return result;
            }
        }
        return result;
    }


    public static void main(String[] args){
        System.out.println(getMACD("600050", "2002-10-03", "2002-12-22"));
    }
}
