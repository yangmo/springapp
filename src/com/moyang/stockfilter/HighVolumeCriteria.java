package com.moyang.stockfilter;

import com.moyang.api.YahooDatum;
import com.moyang.api.YahooHistory;
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

    private long computeRecentAverageVol(YahooHistory history){
        int size = history.getYahooHistory().size();
        if(!history.getYahooHistory().get(size - 1).getDateStr().equals(Constants.LATEST_DAY)){
            return 0;
        }
        long recentVolume = history.getYahooHistory().get(size - 1).getVolume();

        List<YahooDatum> list = history.getYahooHistory().subList(size-recentDays-1, size - 1);

        long total = 0;
        for(YahooDatum datum : list){
            total += datum.getVolume();
        }

        long average = total/recentDays;

        return recentVolume/average;
    }


    @Override
    public boolean meetCriteria(YahooHistory history) {
        long actual = computeRecentAverageVol(history);
        if(actual >= times){
            return true;
        }
        return false;
    }


    public String getDetail(YahooHistory history){
        return "\t" + computeRecentAverageVol(history) + "\t";
    }

    public static void main(String[] args){
        HighVolumeCriteria criteria = new HighVolumeCriteria("13,2");
        System.out.println(criteria.computeRecentAverageVol(new YahooHistory("000975")));
    }
}
