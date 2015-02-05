package com.moyang.stockfilter;

import com.moyang.api.YahooHistory;

import java.util.List;

/**
 * Created by yangmo on 15-2-5.
 */
public abstract class Criteria {
    protected String context;

    public Criteria(String context){
        this.context = context;
    }

    public abstract boolean meetCriteria(YahooHistory history);
    public abstract String getDetail(YahooHistory history);

}
