package com.moyang.stockfilter;

import com.moyang.api.Yahoo.YahooDatum;
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
    public boolean meetCriteria(YahooHistory history) {
        int size = history.getYahooHistory().size();

        long recentVolume = history.getYahooHistory().get(size - 1).getVolume();

        if(size - recentDays <= 1){
            return false;
        }

        List<YahooDatum> list = history.getYahooHistory().subList(size - recentDays - 1, size - 1);

        for(YahooDatum datum : list){
            if(datum.getVolume() > recentVolume){
                return false;
            }
        }
        return true;
    }

    public String getDetail(YahooHistory history){
        return "";
    }
}
