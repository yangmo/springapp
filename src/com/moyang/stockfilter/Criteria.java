package com.moyang.stockfilter;

import com.moyang.hibernate.StockDaily;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by yangmo on 15-2-5.
 */
public abstract class Criteria {
    protected String context;

    public Criteria(String context){
        this.context = context;
    }

    public abstract boolean meetCriteria(List<StockDaily> stockDailies);
    public abstract String getDetail(List<StockDaily> stockDailies);

}
