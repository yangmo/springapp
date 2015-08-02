package com.moyang.stockfilter;

import com.moyang.hibernate.StockDaily;
import com.moyang.api.Yahoo.YahooHistory;

import java.util.List;

/**
 * Created by yangmo on 15-2-5.
 */
public class RecentMaxVolCriteria extends Criteria {

    private int recentDays = 0;

    public RecentMaxVolCriteria(String context){
        super(context);
        recentDays = Integer.valueOf(context);
    }

    @Override
    public boolean meetCriteria(List<StockDaily> stockDailies) {

        int size = stockDailies.size();

        long recentVolume = stockDailies.get(size - 1).getVolume();

        if(size - recentDays <= 1){
            return false;
        }

        List<StockDaily> list = stockDailies.subList(size - recentDays - 1, size - 1);

        for(StockDaily datum : list){
            if(datum.getVolume() > recentVolume){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDetail(List<StockDaily> stockDailies){
        return "";
    }
}
