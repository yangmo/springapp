package com.moyang.api.MACD;

import com.moyang.api.YahooDatum;
import com.moyang.api.YahooHistory;
import com.moyang.model.AverageDatum;

import java.util.ArrayList;

/**
 * Created by yangmo on 15-2-14.
 */
public class DIFFUtil {

    public static ArrayList<AverageDatum> getDiff(YahooHistory history, String startDate
            , String endDate) {

        String openStr = history.getYahooHistory().get(0).getDateStr();
        String endStr = history.getYahooHistory().get(history.getYahooHistory().size()-1).getDateStr();

        ArrayList<AverageDatum> emaLong = EMAUtil.getEMA(history, MACDUtil.LONG, openStr, endStr);
        ArrayList<AverageDatum> emaShort = EMAUtil.getEMA(history, MACDUtil.SHORT, openStr, endStr);

        ArrayList<AverageDatum> diffList = new ArrayList<AverageDatum>();
        for(int i = 0; i < history.getYahooHistory().size(); i++){
            diffList.add(new AverageDatum(emaShort.get(i).getVal() - emaLong.get(i).getVal()
                    , history.getYahooHistory().get(i).getDateStr()));
        }

        ArrayList<AverageDatum> result  = new ArrayList<AverageDatum>();
        for(int i = 0; i < history.getYahooHistory().size();i++){
            String dateString = diffList.get(i).getDateStr();
            if(AverageDatum.compareDateStr(dateString, startDate) >= 0
                    && AverageDatum.compareDateStr(dateString, endDate)<= 0){
                result.add(diffList.get(i));
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception{
        ArrayList<AverageDatum> averageDatumArrayList = getDiff(new YahooHistory("600755")
                , "1996-10-03", "1996-12-13");
        for(AverageDatum datum: averageDatumArrayList){
            System.out.println(datum.getDateStr() + "\t" + datum.getVal());
        }
    }

}
