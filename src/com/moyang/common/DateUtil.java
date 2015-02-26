package com.moyang.common;

import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.api.Yahoo.YahooHistory;

import java.text.ParseException;

/**
 * Created by yangmo on 15-1-24.
 */
public class DateUtil {

    private static final YahooHistory sampleHistory = new YahooHistory("000001");

    public static int daysFromNow(String dateStr) throws ParseException{

        int index = 0;
        int length = sampleHistory.getYahooHistory().size();

        for(YahooDatum datum : sampleHistory.getYahooHistory()) {
            if (datum.getDateStr().equals(dateStr)) {
                return length - index;
            }
            index++;
        }
        return length;
    }
    public static String toMonthInt(String month){
        int monthInt = Integer.valueOf(month);
        if(monthInt <= 10){
            return "0" + String.valueOf(monthInt - 1);
        } else{
            return String.valueOf(monthInt - 1);
        }
    }
    public static void main(String[] args) throws Exception{
        System.out.println(daysFromNow("2014-11-11"));
    }
}
