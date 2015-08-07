package com.moyang.api;

import com.moyang.model.StockDaily;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.model.AverageDatum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangmo on 15-1-21.
 */
public class AverageUtil {

    public static ArrayList<AverageDatum> getKAverage(String stockId, int k, Date startDate, Date endDate)
    {
        return getKAverage(new YahooHistory(stockId), k, startDate, endDate);
    }

    public static ArrayList<AverageDatum> getKAverage(YahooHistory history, int k, Date startDate, Date endDate)
    {

        return getKAverage(history.getYahooHistory(), k, startDate, endDate);
    }

    public static ArrayList<AverageDatum> getKAverage(List<StockDaily> data, int k, Date startDate, Date endDate)
    {

        ArrayList<AverageDatum> kAverage = new ArrayList<AverageDatum>();

        double sum = 0;

        for(int i = 0; i < k; i++){
            StockDaily datum = data.get(i);
            kAverage.add(new AverageDatum(datum.getAdjClose(), datum.getDate()));
            sum += datum.getAdjClose();
        }

        for(int i = k; i < data.size(); i++){
            StockDaily datum = data.get(i);
            sum += datum.getAdjClose() - data.get(i-k).getAdjClose();
            kAverage.add(new AverageDatum(sum/k, datum.getDate()));
        }

        ArrayList<AverageDatum> result  = new ArrayList<AverageDatum>();
        for(int i = 0; i < data.size();i++){
            Date date = kAverage.get(i).getDate();
            if(date.compareTo(startDate) >= 0
                    && date.compareTo(endDate)<= 0){
                result.add(kAverage.get(i));
            }
        }
        return result;
    }
    public static ArrayList<AverageDatum> getKAverage(int k, List<StockDaily> data){
        ArrayList<AverageDatum> kAverage = new ArrayList<AverageDatum>();

        double sum = 0;

        for(int i = 0; i < k; i++){
            StockDaily datum = data.get(i);
            kAverage.add(new AverageDatum(datum.getAdjClose(), datum.getDate()));
            sum += datum.getAdjClose();
        }

        for(int i = k; i < data.size(); i++){
            StockDaily datum = data.get(i);
            sum += datum.getAdjClose() - data.get(i-k).getAdjClose();
            kAverage.add(new AverageDatum(sum/k, datum.getDate()));
        }

        return kAverage;
    }
}
