package com.moyang.stockfilter;

import com.moyang.api.Yahoo.YahooHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmo on 15-2-5.
 */
public class AndCriteria {
    private List<Criteria> criteriaList = new ArrayList<Criteria>();

    public AndCriteria appendCriteria(Criteria criteria){
        criteriaList.add(criteria);
        return this;
    }

    public boolean meetCriteria(YahooHistory history){
        for(Criteria criteria : criteriaList){
            if(! criteria.meetCriteria(history)){
                return false;
            }
        }

        return true;
    }

    public String getDetail(YahooHistory history){
        StringBuffer sb = new StringBuffer();
        for(Criteria criteria : criteriaList){
            sb.append(criteria.getDetail(history));
        }
        return sb.toString();
    }
}
