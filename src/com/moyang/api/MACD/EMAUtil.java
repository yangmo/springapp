package com.moyang.api.MACD;

import com.moyang.api.YahooDatum;
import com.moyang.api.YahooHistory;
import com.moyang.model.AverageDatum;

import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-14.
 */
public class EMAUtil {

    public static ArrayList<AverageDatum> getEMA(YahooHistory history, int k, String startDate
            , String endDate){
        ArrayList<YahooDatum> data = history.getYahooHistory();
        ArrayList<AverageDatum> EMAList = new ArrayList<AverageDatum>();

        EMAList.add(new AverageDatum(0, data.get(0).getDateStr()));

        double ratioPre = (double)(k - 1) / (double) (k + 1);
        double ratioCur = 2.0 / (double)(k + 1);

        for(int i = 1; i < data.size(); i++){
            double curEMA = EMAList.get(i - 1).getVal() * ratioPre + data.get(i).getAdjClose() * ratioCur;
            EMAList.add(new AverageDatum(curEMA, data.get(i).getDateStr()));
        }

        ArrayList<AverageDatum> result  = new ArrayList<AverageDatum>();
        for(int i = 0; i < data.size();i++){
            String dateString = EMAList.get(i).getDateStr();
            if(AverageDatum.compareDateStr(dateString, startDate) >= 0
                    && AverageDatum.compareDateStr(dateString, endDate)<= 0){
                result.add(EMAList.get(i));
            }
        }

        return result;
    }


}
