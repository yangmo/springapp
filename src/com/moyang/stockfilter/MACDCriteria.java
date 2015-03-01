package com.moyang.stockfilter;

import com.moyang.api.AverageUtil;
import com.moyang.api.MACD;
import com.moyang.api.Yahoo.YahooDatum;
import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.Constants;
import com.moyang.model.AverageDatum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmo on 15-3-1.
 */
public class MACDCriteria extends Criteria{
    private int hasBeenPositiveFor;

    public MACDCriteria(String context) {
        super(context);
        hasBeenPositiveFor = Integer.valueOf(context);
    }


    @Override
    public boolean meetCriteria(YahooHistory history) {
        ArrayList<YahooDatum> yahooList = history.getYahooHistory();
        ArrayList<AverageDatum> macdList = MACD.getMACD(history.getStockId(), yahooList.get(0).getDateStr()
                , yahooList.get(yahooList.size() - 1).getDateStr());
        if (macdList.get(macdList.size() - 1).getVal() <= 0) {
            return false;
        }

        for (int i = macdList.size() - 1 - hasBeenPositiveFor; i < macdList.size(); i++) {
            if (macdList.get(i).getVal() <= 0) {
                return true;
            }
        }

        return false;
    }

    public String getDetail(YahooHistory history){
        return "" + history.getStockId();
    }
}
