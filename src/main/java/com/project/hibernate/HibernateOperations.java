package com.project.hibernate;

import com.project.information.Certificate;
import com.project.information.Company;

import com.project.information.FakeBank;
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

    public static Object getObjectById(Object object, Integer primaryKey){
        initSessionFactory(object);
        Object result = session.get(object.getClass(), primaryKey.intValue());
        closeSessionFactory();
        return result;
    }

    public static void deleteObjectById(Object object, Integer primaryKey){
        initSessionFactory(object);
        Object toRemove = session.get(object.getClass(), primaryKey.intValue());
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
        closeSessionFactory();
        return data;
    }

    public static void deleteBankAccount(FakeBank fakeBank, Integer id) {
        initSessionFactory(fakeBank);
        FakeBank toRemove = (FakeBank) session.createQuery(String.format("from FakeBank s where s.ownerId = %s",id))
                .uniqueResult();
        session.delete(toRemove);
        session.getTransaction().commit();
        closeSessionFactory();
    }

    public static long getLastFinishId(Certificate certificate){
        initSessionFactory(certificate);
        Certificate temp = (Certificate) session.createQuery("from Certificate ORDER BY tuple_finish_id DESC").setMaxResults(1).uniqueResult();
        closeSessionFactory();
        return temp.getTuppleFinishId();
    }

    public static String isTableEmpty(Object object){
        List<?> list = null;
        String toReturn = "ERROR";
        initSessionFactory(object);
        if(object.getClass() == Certificate.class){
            list = session.createQuery("from Certificate").list();
            toReturn = list.size() == 0 ? "EMPTY" : "NOT_EMPTY";
        }
        closeSessionFactory();
        return toReturn;
    }

    /* Deprecated
    public static int getLastId(Object object){
        initSessionFactory(object);
        Object temp = null;
        int last = 0;
        if(object.getClass() == Company.class){
            try{
                temp = session.createQuery("from Company ORDER BY id DESC").setMaxResults(1).uniqueResult();
                last = ((Company)temp).getId();
            }catch (NullPointerException error){
                last = 0; // !!! Last remains zero.
            }
        }
        closeSessionFactory();
        return last;
    }
    */
}
