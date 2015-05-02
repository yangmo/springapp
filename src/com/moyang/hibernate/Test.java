package com.moyang.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by yangmo on 15-5-1.
 */
public class Test {

    public static void main(String[] args){
        Configuration config = new Configuration();
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url"
                , "jdbc:mysql://localhost:3306/money");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "123456");

        config.addClass(StockInfo.class);
        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        StockInfo info = new StockInfo("000001", "浦发银行", "sz");
        session.delete(info);
        session.save(info);
        tx.commit();
        session.close();
    }
}
