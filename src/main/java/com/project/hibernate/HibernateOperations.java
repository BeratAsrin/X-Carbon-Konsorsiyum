package com.project.hibernate;

import com.project.information.Company;
import com.project.information.Individual;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateOperations {

    private static SessionFactory factory;
    private static Session session;

    public static void initSessionFactory(Object object){
        factory = new Configuration().configure().addAnnotatedClass(object.getClass()).buildSessionFactory();
        session = factory.getCurrentSession();
        session.beginTransaction();
    }

    private static void closeSessionFactory() {
        session.close();
        factory.close();
    }

    public static void addNewObject(Object object){
        initSessionFactory(object);
        session.save(object);
        session.getTransaction().commit();
        closeSessionFactory();
    }

    public static Object getObjectById(Object object, Long primaryKey){
        initSessionFactory(object);
        Object result = session.get(object.getClass(), primaryKey.longValue());
        closeSessionFactory();
        return result;
    }

    public static void deleteObjectById(Object object, Long primaryKey){
        initSessionFactory(object);
        Object toRemove = session.get(object.getClass(), primaryKey.longValue());
        session.delete(toRemove);
        session.getTransaction().commit();
        closeSessionFactory();
    }

    public static List<?> getAll(Object object) {
        initSessionFactory(object);
        List<?> data = null;
        if(object.getClass() == Company.class){
            data = session.createQuery("from Company").list();
        }
        else if(object.getClass() == Individual.class){
            data = session.createQuery("from Individual").list();
        }
        closeSessionFactory();
        return data;
    }
}
