package com.moyang.common;

import com.moyang.hibernate.StockDaily;
import com.moyang.api.Yahoo.YahooHistory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangmo on 15-1-24.
 */
public class DateUtil {

    private static final YahooHistory sampleHistory = new YahooHistory("000001");

    public static int daysFromNow(String dateStr) throws ParseException{

        int index = 0;
        int length = sampleHistory.getYahooHistory().size();

        for(StockDaily datum : sampleHistory.getYahooHistory()) {
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
        long interval = end.getTime() - start.getTime() ;
        interval += (interval > 0 ? 1000 : -1000);
        return (int)(interval / 24 / 60 / 60 / 1000);
    }


    public static int calcIntervalDays(String start, String end) {
        return calcIntervalDays(getDate(start), getDate(end));
    }

    public static Date getDate(String dateStr){
        String[] components = dateStr.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(components[0]), Integer.valueOf(toMonthInt(components[1]))
                , Integer.valueOf(components[2]));

        return calendar.getTime();
    }

    public static void main(String[] args) throws Exception{
        String startStr = "2013-03-12";
        String endStr = "2013-03-11";

        System.out.println(calcIntervalDays(startStr, endStr));
    }
}
