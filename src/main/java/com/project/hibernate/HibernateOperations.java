package com.project.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateOperations {

    private static SessionFactory factory;
    private static Session session;

    public static void initSessionFactory(Object object){
        factory = new Configuration().configure().addAnnotatedClass(object.getClass()).buildSessionFactory();
        session = factory.getCurrentSession();
        session.beginTransaction();
    }

    private static void closeSessionFactory() {
        factory.close();
    }

    public static void addNewObject(Object object){
        initSessionFactory(object);
        session.save(object);
        session.getTransaction().commit();
        closeSessionFactory();
    }

    public static Object getObjectById(Object object, int id){
        initSessionFactory(object);
        Object result = session.get(object.getClass(),id);
        closeSessionFactory();
        return result;
    }

}
