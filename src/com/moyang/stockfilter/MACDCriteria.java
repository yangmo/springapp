package com.moyang.stockfilter;

import com.moyang.api.MACD;
import com.moyang.hibernate.StockDaily;
import com.moyang.api.Yahoo.YahooHistory;
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
    public boolean meetCriteria(List<StockDaily> stockDailies) {
        ArrayList<AverageDatum> macdList = MACD.getMACD(stockDailies);

        if (macdList.get(macdList.size() - 1).getVal() <= 0) {
            return false;
        }

        for (int i = macdList.size() - 1 - hasBeenPositiveFor; i < macdList.size(); i++) {
            if (macdList.get(i).getVal() < 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getDetail(List<StockDaily> stockDailies){
        return "";
    }
}
