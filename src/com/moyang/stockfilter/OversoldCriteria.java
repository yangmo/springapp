package com.moyang.stockfilter;

import com.moyang.api.AverageUtil;
import com.moyang.common.Constants;
import com.moyang.model.StockDaily;
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
    public boolean meetCriteria(List<StockDaily> stockDailies) {
        return getDiff(stockDailies) >= range;
    }

    public double getDiff(List<StockDaily> stockDailies){
        List<AverageDatum> list = null;
        try {
            list = AverageUtil.getKAverage(stockDailies, kAverage, Constants.DATE_FORMAT.parse(Constants.MOST_RECENT_TRADING_DAY),
                    Constants.DATE_FORMAT.parse(Constants.MOST_RECENT_TRADING_DAY));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        double average = list.get(0).getVal();

        double cur = stockDailies.get(stockDailies.size()-1).getAdjClose();

        double diff= average - cur;
        return diff/cur;
    }

    public String getDetail(List<StockDaily> stockDailies){
        return "\t" + kAverage + ":" + Constants.DOUBLE_FORMAT.format(getDiff(stockDailies)) + "\t";
    }
}
