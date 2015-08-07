package com.moyang.stockfilter;

import com.moyang.model.StockDaily;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.Constants;

import java.util.List;

/**
 * Created by yangmo on 15-2-5.
 *
 */
public class HighVolumeCriteria extends Criteria{

    private int recentDays = 0;
    private long times = 0;

    /**
     * new HighVolumeCriteria("13,2");
     * @param context
     */
    public  HighVolumeCriteria(String context){
        super(context);
        String[] contents = context.split(",");
        recentDays = Integer.valueOf(contents[0]);
        times = Long.valueOf(contents[1]);
    }

    private long computeRecentAverageVol(List<StockDaily> stockDailies){
        int size = stockDailies.size();
        if(!Constants.DATE_FORMAT.format(stockDailies.get(size - 1).getDate()).equals(Constants.MOST_RECENT_TRADING_DAY)){
            return 0;
        }
        long recentVolume = stockDailies.get(size - 1).getVolume();

        List<StockDaily> list = stockDailies.subList(size - recentDays - 1, size - 1);

        long total = 0;
        for(StockDaily datum : list){
            total += datum.getVolume();
        }

        long average = total/recentDays;

        return recentVolume/average;
    }


    @Override
    public boolean meetCriteria(List<StockDaily> stockDailies) {
        long actual = computeRecentAverageVol(stockDailies);
        if(actual >= times){
            return true;
        }
        return false;
    }


    public String getDetail(List<StockDaily> stockDailies){
        return "\t" + computeRecentAverageVol(stockDailies) + "\t";
    }

    public static void main(String[] args){
        HighVolumeCriteria criteria = new HighVolumeCriteria("13,2");
        System.out.println(criteria.computeRecentAverageVol(new YahooHistory("000975").getYahooHistory()));
    }
}
