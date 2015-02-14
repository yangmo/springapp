package com.moyang.stockfilter;

import com.moyang.api.AverageUtil;
import com.moyang.api.YahooHistory;
import com.moyang.common.Constants;
import com.moyang.model.AverageDatum;

import java.util.List;

/**
 * Created by yangmo on 15-2-5.
 */
public class OversoldCriteria extends Criteria{

    private int kAverage;
    private double range;
    public OversoldCriteria(String context){
        super(context);
        String[] contents = context.split(",");

        kAverage = Integer.valueOf(contents[0]);
        range = Double.valueOf(contents[1]);
    }

    @Override
    public boolean meetCriteria(YahooHistory history) {
        return getDiff(history) >= range;
    }

    public double getDiff(YahooHistory history){
        List<AverageDatum> list = AverageUtil.getKAverage(history, kAverage, Constants.LATEST_DAY, Constants.LATEST_DAY);
        double average = list.get(0).getVal();

        double cur = history.getYahooHistory().get(history.getYahooHistory().size()-1).getAdjClose();

        double diff= average - cur;
        return diff/cur;
    }

    public String getDetail(YahooHistory history){
        return "\t" + kAverage + ":" + Constants.DOUBLE_FORMAT.format(getDiff(history)) + "\t";
    }
}
