package com.project.hibernate;

import com.project.information.*;

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

    public static void closeSessionFactory() {
        session.close();
        factory.close();
    }

    public static void beginTransactionAgainUsingCurrentSession(){
        session = factory.getCurrentSession();
        session.beginTransaction();
    }

    public static void sessionCommit(){
        session.getTransaction().commit();
    }

    public static void addNewObject(Object object){
        initSessionFactory(object);
        session.save(object);
        sessionCommit();
        closeSessionFactory();
    }

    public static void addNewObjectWithoutChangingCurrentSession(Object object){
        session.save(object);
    }


    public static void updateObject(Object object){ // Coming object should be updated.
        initSessionFactory(object);
        sessionCommit();
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
        sessionCommit();
        closeSessionFactory();
    }

    public static void deleteObjectWithoutClosingFactory(Object toRemove){
        session.delete(toRemove);
        sessionCommit();
    }
    public static List<?> getAll(Object object) {
        initSessionFactory(object);
        List<?> data = null;
        if(object.getClass() == Company.class){
            data = session.createQuery("from Company").list();
        }
        else if(object.getClass() == CertificateRequest.class){
            data = session.createQuery("from CertificateRequest").list();
        }
        closeSessionFactory();
        return data;
    }

    public static void deleteBankAccount(FakeBank fakeBank, Integer id) {
        initSessionFactory(fakeBank);
        FakeBank toRemove = (FakeBank) session.createQuery(String.format("from FakeBank s where s.ownerId = %s",id))
                .uniqueResult();
        session.delete(toRemove);
        sessionCommit();
        closeSessionFactory();
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

    public static long getLastFinishId(Certificate certificate){
        initSessionFactory(certificate);
        Certificate temp = (Certificate) session.createQuery("from Certificate ORDER BY tuple_finish_id DESC").setMaxResults(1).uniqueResult();
        closeSessionFactory();
        return temp.getTupleFinishId();
    }

    public static Certificate getCertificate(int from, Long tupleStartId){
        // TODO NOTICE THAT SESSION IS OPENED BEFORE CALLING THIS METHOD
        Certificate toReturn = (Certificate) session.createQuery(String.format("from Certificate c where c.ownerId = %s " +
                "and c.tupleStartId = %s",from, tupleStartId)).uniqueResult();
        return toReturn;
    }

    public static Company getCompanyByName(String name){
        initSessionFactory(new Company());
        Company company = (Company) session.createQuery(String.format("from Company s where s.organizationName = %s",name)).
                setMaxResults(1).uniqueResult();
        closeSessionFactory();
        return company;
    }

    public static Company getCompanyByTaxNumber(Long taxNumber){
        initSessionFactory(new Company());
        Company company = (Company) session.createQuery(String.format("from Company s where s.taxNumber = %s",taxNumber.longValue())).
                setMaxResults(1).uniqueResult();
        closeSessionFactory();
        return company;
    }

    public static boolean checkIfTaxNumberExists(long taxNumber){
        initSessionFactory(new Company());
        Company company = (Company) session.createQuery(String.format("from Company c where c.taxNumber = %s",taxNumber)).
                setMaxResults(1).uniqueResult();
        closeSessionFactory();
        if(company == null){
            return false;
        }
        return true;
    }

    public static Admin getAdminByUsername(String username){
        initSessionFactory(new Admin());
        Admin admin = (Admin) session.createQuery(String.format("FROM Admin A WHERE A.username = '%s'",username)).
                setMaxResults(1).uniqueResult();
        closeSessionFactory();
        return admin;
    }

    public static List<Certificate> getAllCertificatesOfCompany(Long taxNumber){
        Company company = getCompanyByTaxNumber(taxNumber);
        initSessionFactory(new Certificate());
        List<Certificate> certificates = session.createQuery(String.format("from Certificate c where c.ownerId=%d", company.getId())).list();
        closeSessionFactory();
        return certificates;
    }

    public static List<?> getAllRequestsOfCompany(Long taxNumber){
        initSessionFactory(new CertificateRequest());
        List<?> certificates = session.createQuery(String.format("from CertificateRequest c where c.ownerTaxNumber=%s", taxNumber.longValue())).list();
        closeSessionFactory();
        return certificates;
    }

}
