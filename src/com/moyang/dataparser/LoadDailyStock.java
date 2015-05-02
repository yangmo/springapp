package com.moyang.dataparser;

import com.moyang.api.Yahoo.YahooHistory;
import com.moyang.common.FileUtil;
import com.moyang.common.StockNameUtil;
import com.moyang.hibernate.StockDaily;
import com.moyang.hibernate.StockInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Created by yangmo on 15-5-2.
 */
public class LoadDailyStock {
    public static void main(String[] args){
        Configuration config = new Configuration();
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url"
                , "jdbc:mysql://localhost:3306/money");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "123456");

        config.addClass(StockInfo.class);
        config.addClass(StockDaily.class);
        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();



        int count = 0;
        boolean start = false;
        for(String stockId : StockNameUtil.getAllStockIds()){

            if(stockId.equals("600702")){
                start = true;
                continue;
            }

            if(!start){
                continue;
            }


            YahooHistory history = null;
            try {
                history = new YahooHistory(stockId);
            } catch (Exception e) {

             }
            if(history!=null&&history.getYahooHistory()!=null&&history.getYahooHistory().size()!=0){
                count ++;
                System.out.println(count + "\t" + stockId);
                Transaction tx = session.beginTransaction();

                for(StockDaily stockDaily : history.getYahooHistory()){
                    session.save(stockDaily);
                }

                tx.commit();
            }
        }

        System.out.println(count);
        session.close();
    }
}
