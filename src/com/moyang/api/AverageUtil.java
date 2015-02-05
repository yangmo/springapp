package com.moyang.api;

import com.moyang.model.AverageDatum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmo on 15-1-21.
 */
public class AverageUtil {

    public static ArrayList<AverageDatum> getKAverage(String stockId, int k, String startDate, String endDate)
    {
        return getKAverage(new YahooHistory(stockId), k, startDate, endDate);
    }

    public static ArrayList<AverageDatum> getKAverage(YahooHistory history, int k, String startDate, String endDate)
    {

        ArrayList<YahooDatum> data = history.getYahooHistory();
        ArrayList<AverageDatum> kAverage = new ArrayList<AverageDatum>();

        double sum = 0;

        for(int i = 0; i < k; i++){
            YahooDatum datum = data.get(i);
            kAverage.add(new AverageDatum(datum.getClose(), datum.getDateStr()));
            sum += datum.getClose();
        }

        for(int i = k; i < data.size(); i++){
            YahooDatum datum = data.get(i);
            sum += datum.getClose() - data.get(i-k).getClose();
            kAverage.add(new AverageDatum(sum/k, datum.getDateStr()));
        }

        ArrayList<AverageDatum> result  = new ArrayList<AverageDatum>();
        for(int i = 0; i < data.size();i++){
            String dateString = kAverage.get(i).getDateStr();
            if(AverageDatum.compareDateStr(dateString, startDate) >= 0
                    && AverageDatum.compareDateStr(dateString, endDate)<= 0){
                result.add(kAverage.get(i));
            }
        }
        return result;
    }
    public static ArrayList<AverageDatum> getKAverage(int k, List<YahooDatum> data){
        ArrayList<AverageDatum> kAverage = new ArrayList<AverageDatum>();

        double sum = 0;

        for(int i = 0; i < k; i++){
            YahooDatum datum = data.get(i);
            kAverage.add(new AverageDatum(datum.getClose(), datum.getDateStr()));
            sum += datum.getClose();
        }

        for(int i = k; i < data.size(); i++){
            YahooDatum datum = data.get(i);
            sum += datum.getClose() - data.get(i-k).getClose();
            kAverage.add(new AverageDatum(sum/k, datum.getDateStr()));
        }

        return kAverage;
    }
}
