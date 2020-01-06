package Repositories;

import Entities.Risk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RiskRepository implements Repository<Risk, Integer> {
    Configuration config = new Configuration();
    SessionFactory factory = config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }


    @Override
    public void Insert(Risk T) {
        try {
            session = factory.getCurrentSession();
            config.addAnnotatedClass(Risk.class);
            session.beginTransaction();
            session.save(T);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error " + e);
            session.close();
        }
    }

    @Override
    public Risk Read(Integer integer) {
        return null;
    }

    @Override
    public List<Risk> ReadAll() {
        try {
            session = factory.openSession();
            config.addAnnotatedClass(Risk.class);
            session.beginTransaction();
            org.hibernate.Query queryResult = session.createQuery("from Risk");
            List<Risk> allRisks = queryResult.list();
            session.close();
            return allRisks;
        } catch (Exception e) {
            System.out.println(e);
            session.close();
            return null;
        }
    }

    @Override
    public void Update(Risk t) {
        session = factory.getCurrentSession();
        config.addAnnotatedClass(Risk.class);
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
    }

    public Risk getRisk(int id) {
        Transaction tx = session.beginTransaction();
        Risk x = (Risk) session.get(Risk.class, id);
        tx.commit();
        return x;
    }
}
