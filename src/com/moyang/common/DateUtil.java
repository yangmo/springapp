package com.moyang.common;

import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.api.Yahoo.YahooHistory;

import java.text.ParseException;
import java.util.Date;

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
    public static int calcIntervalDays(Date start, Date end){
        long interval = end.getTime() - start.getTime();
        return (int) interval / 86400000;
    }

    public static void main(String[] args) throws Exception{
        Date date ;

        System.out.println(Constants.DATE_FORMAT.parse("2005-08-11").getTime()/86400000);
    }
}
