package com.moyang.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by yangmo on 15-5-3.
 */
public class HibernateHelper {
    private static final ThreadLocal session = new ThreadLocal();
    private static final SessionFactory sessionFactory = new Configuration().configure()
            .buildSessionFactory();

    private HibernateHelper(){

    }

    public static Session getSession(){
        Session session = (Session) HibernateHelper.session.get();
        if(session == null){
            session = sessionFactory.openSession();
            HibernateHelper.session.set(session);
        }
        return session;
    }
}
