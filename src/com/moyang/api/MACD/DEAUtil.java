package com.moyang.api.MACD;

import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.model.AverageDatum;

import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-14.
 */
public class DEAUtil {
    public static ArrayList<AverageDatum> getDea(YahooHistory history, String startDate
            , String endDate) {
        String openStr = history.getYahooHistory().get(0).getDateStr();
        String endStr = history.getYahooHistory().get(history.getYahooHistory().size()-1).getDateStr();
        ArrayList<AverageDatum> diffList = DIFFUtil.getDiff(history, openStr, endStr);

        ArrayList<AverageDatum> deaList = new ArrayList<AverageDatum>();
        deaList.add(new AverageDatum(0.0, history.getYahooHistory().get(0).getDateStr()));


        for(int i = 1; i < history.getYahooHistory().size(); i++){
            int M  = MACDUtil.M;
            double val = deaList.get(i - 1).getVal() * (M - 1) / (M + 1)
                    + diffList.get(i).getVal() * 2 / (M + 1);
            deaList.add(new AverageDatum(val, history.getYahooHistory().get(i).getDateStr()));
        }

        ArrayList<AverageDatum> result  = new ArrayList<AverageDatum>();
        for(int i = 0; i < history.getYahooHistory().size();i++){
            String dateString = deaList.get(i).getDateStr();
            if(AverageDatum.compareDateStr(dateString, startDate) >= 0
                    && AverageDatum.compareDateStr(dateString, endDate)<= 0){
                result.add(deaList.get(i));
            }
        }

        return result;
    }
}
