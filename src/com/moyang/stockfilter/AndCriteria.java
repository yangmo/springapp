package com.moyang.stockfilter;

import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.hibernate.StockDaily;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmo on 15-2-5.
 */
public class AndCriteria extends Criteria{
    private List<Criteria> criteriaList = new ArrayList<Criteria>();

    public AndCriteria(String stockId) {
        super(stockId);
    }

    public AndCriteria appendCriteria(Criteria criteria){
        criteriaList.add(criteria);
        return this;
    }

    @Override
    public boolean meetCriteria(List<StockDaily> stockDailies){
        for(Criteria criteria : criteriaList){
            if(! criteria.meetCriteria(stockDailies)){
                return false;
            }
        }

        return true;
    }

    @Override
    public String getDetail(List<StockDaily> stockDailies){
        StringBuffer sb = new StringBuffer();
        for(Criteria criteria : criteriaList){
            sb.append(criteria.getDetail(stockDailies));
        }
        return sb.toString();
    }
}
