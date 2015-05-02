package com.moyang.api;

import com.moyang.hibernate.StockDaily;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.Constants;
import com.moyang.model.AverageDatum;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

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
    public static ArrayList<AverageDatum> getMACD(String stockId, Date startDate, Date endDate){
        YahooHistory history = new YahooHistory(stockId);
        ArrayList<StockDaily> data = history.getYahooHistory();
        ArrayList<AverageDatum> result = new ArrayList<AverageDatum>();

        double shortRatio = (SHORT - 1) / (SHORT + 1);
        double longRatio =  (LONG - 1) / (LONG + 1);

        if(data.get(0).getDate().equals(startDate)){
            result.add(new AverageDatum(0, startDate));
        }

        double emaShort = data.get(0).getAdjClose() +(1 - shortRatio) * (data.get(1).getAdjClose() - data.get(0).getAdjClose());
        double emaLong =  data.get(0).getAdjClose() +(1 - longRatio)* (data.get(1).getAdjClose() - data.get(0).getAdjClose());

        double diff = emaShort - emaLong;
        double dea = 2 / (M + 1) * diff ;
        double macd = trimDisplay(2 * (diff - dea));

        if(data.get(1).getDate().compareTo(startDate) >= 0){
            result.add(new AverageDatum(macd, data.get(1).getDate()));
        }


        for(int i = 2; i < data.size(); i++){

            emaShort = trim(emaShort * shortRatio+ data.get(i).getAdjClose() * (1 - shortRatio));
            emaLong = trim(emaLong * longRatio + data.get(i).getAdjClose() * (1 - longRatio));

            diff = emaShort - emaLong;
            dea = 2 / (M + 1) * diff + dea * (M - 1) / (M + 1);
            macd =trimDisplay(2 * (diff - dea));

            StockDaily datum = data.get(i);

            Date date = data.get(i).getDate();

            if(date.compareTo(startDate) >= 0
                    && date.compareTo(endDate)<= 0){
                result.add(new AverageDatum(macd, date));
            }
            if(date.compareTo(endDate) > 0){
                return result;
            }

        }
        return result;
    }


    public static void main(String[] args)throws Exception{

     //   ArrayList<AverageDatum> list = getMACD("600030", "2003-01-06", "2003-02-22");
        ArrayList<AverageDatum> list = getMACD("600489", Constants.DATE_FORMAT.parse("2008-11-11"), Constants.DATE_FORMAT.parse(Constants.LATEST_DAY));

        for(AverageDatum item : list){
            System.out.println(item);
        }
    }
}
