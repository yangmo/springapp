package com.moyang.task;

import com.moyang.api.SinaAPI;
import com.moyang.api.Yahoo.YahooAPI;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.Constants;
import com.moyang.common.DateUtil;
import com.moyang.common.StockNameUtil;

import java.util.*;

/**
 * Created by yangmo on 15-3-4.
 */
public class SinaDailyUpdateTask {

    public static String getStartDateStr(String lastUpdate){
        Calendar calendar   =   new GregorianCalendar();
        String[] arr = lastUpdate.split("-");
        calendar.set(Integer.valueOf(arr[0]),Integer.valueOf(DateUtil.toMonthInt(arr[1])),Integer.valueOf(arr[2]));
        calendar.add(calendar.DATE, 1);
        Date date=calendar.getTime();

        return Constants.DATE_FORMAT.format(date);
    }

    public static void updateStockToDate(String stockId, Date date) throws Exception{
        YahooHistory history = new YahooHistory(stockId);

        Date lastUpdate = history.getYahooHistory().get(history.getYahooHistory().size() - 1).getDate();
        if(lastUpdate.equals(date)){
            return;
        }

        history.getYahooHistory().add(SinaAPI.getLastestYahooDatum(stockId));

        StringBuffer sb = new StringBuffer();
        sb.append("Date,Open,High,Low,Close,Volume,Adj Close\n");
        for(int index = history.getYahooHistory().size() - 1; index >= 0; index--) {
            sb.append(history.getYahooHistory().get(index)).append("\n");
        }

        YahooAPI.writeYahooHistory(stockId, sb.toString());
    }


    public static void main(String[] args) throws Exception{

        int passed = 0;
        ArrayList<String> failed = new ArrayList<String>();

        int omit = 1910;

        for(String stockId: StockNameUtil.getAllStockIds()){
            System.out.print(passed++ +"\t" + stockId + "\t");
            boolean success = true;

            if(omit>passed){
                continue;
            }


            try{
                updateStockToDate(stockId,Constants.DATE_FORMAT.parse(Constants.MOST_RECENT_TRADING_DAY));
            } catch (Exception e) {
                failed.add(stockId);
                System.out.println(e.getLocalizedMessage());
                success = false;
            }
            System.out.println(stockId + "\t" + success);
        }
        for(String stockId : failed){
            System.out.println(stockId);
        }
    }
}
