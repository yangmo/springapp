package com.moyang.task;

import com.moyang.api.Yahoo.YahooAPI;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.Constants;
import com.moyang.common.DateUtil;
import com.moyang.common.StockNameUtil;
import com.moyang.hibernate.StockDaily;

import java.util.*;

/**
 * Created by yangmo on 15-1-25.
 */
public class DailyUpdateTask {

    public static String getStartDateStr(String lastUpdate){
        Calendar calendar = new GregorianCalendar();
        String[] arr = lastUpdate.split("-");
        calendar.set(Integer.valueOf(arr[0]),Integer.valueOf(DateUtil.toMonthInt(arr[1])),Integer.valueOf(arr[2]));
        calendar.add(calendar.DATE, 1);
        Date date=calendar.getTime();

        return Constants.DATE_FORMAT.format(date);
    }

    public static void updateStockToDate(String stockId, String dateStr) throws Exception{
        YahooHistory history = new YahooHistory(stockId);

        String lastUpdate = Constants.DATE_FORMAT.format(history.getYahooHistory().get(history.getYahooHistory().size() - 1).getDate());
        if(lastUpdate.equals(dateStr)){
            return;
        }

        List<StockDaily> list = YahooAPI.getHistoryBetween(stockId,getStartDateStr(lastUpdate), dateStr);


        history.getYahooHistory().addAll(list);

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

        int omit = 2220;

        for(String stockId: StockNameUtil.getAllStockIds()){
            System.out.print(passed++ +"\t" + stockId + "\n");
            boolean success = true;

            if(omit>passed){
                continue;
            }


            try{
                updateStockToDate(stockId, Constants.MOST_RECENT_TRADING_DAY);
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
