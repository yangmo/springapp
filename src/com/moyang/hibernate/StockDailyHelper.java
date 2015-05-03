package com.moyang.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by yangmo on 15-5-3.
 */
public class StockDailyHelper {
    public static List<StockDaily> getStockDaily(String stockId){
        Session session = HibernateHelper.getSession();
        Criteria criteria = session.createCriteria(StockDaily.class);
        criteria.add(Restrictions.eq("stockId", stockId));
        return criteria.list();
    }

    public static void main(String[] args){
        for(StockDaily daily : getStockDaily("000001")){
            System.out.println(daily );
        }
    }
}
