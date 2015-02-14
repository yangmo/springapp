package com.moyang.api.MACD;

import com.moyang.api.YahooHistory;
import com.moyang.model.AverageDatum;

import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-14.
 */
public class MACDUtil {
    public static final int LONG = 26;
    public static final int SHORT = 12;
    public static final int M = 9;

    public static ArrayList<AverageDatum> getMACD(YahooHistory history, String startDate
            , String endDate) {
        String openStr = history.getYahooHistory().get(0).getDateStr();
        String endStr = history.getYahooHistory().get(history.getYahooHistory().size()-1).getDateStr();

        ArrayList<AverageDatum> deaList = DEAUtil.getDea(history, openStr, endStr);
        ArrayList<AverageDatum> diffList = DIFFUtil.getDiff(history, openStr, endStr);

        ArrayList<AverageDatum> macdList = new ArrayList<AverageDatum>();

        for(int i = 0; i < history.getYahooHistory().size(); i++){
            double val = 2 * (diffList.get(i).getVal() - deaList.get(i).getVal());
            macdList.add(new AverageDatum(val, history.getYahooHistory().get(i).getDateStr()));
        }

        ArrayList<AverageDatum> result  = new ArrayList<AverageDatum>();
        for(int i = 0; i < history.getYahooHistory().size();i++){
            String dateString = macdList.get(i).getDateStr();
            if(AverageDatum.compareDateStr(dateString, startDate) >= 0
                    && AverageDatum.compareDateStr(dateString, endDate)<= 0){
                result.add(macdList.get(i));
            }
        }
        return result;
    }


    public static void main(String[] args) throws Exception{
        ArrayList<AverageDatum> averageDatumArrayList = getMACD(new YahooHistory("000750")
                , "2014-01-01", "2015-02-13");
        for(AverageDatum datum: averageDatumArrayList){
            System.out.println(datum.getDateStr() + "\t" + datum.getVal());
        }
    }
}
