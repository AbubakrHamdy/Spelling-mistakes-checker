package Repositories;

import Entities.ProcedureFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProcedureFileRepository implements Repository<ProcedureFile,Integer> {
    Configuration config = new Configuration();
    SessionFactory factory= config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
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
    public void Insert(ProcedureFile T) {
        try {
            session = factory.getCurrentSession();
            config.addAnnotatedClass(ProcedureFile.class);
            session.beginTransaction();
            session.save(T);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error " + e);
            session.close();
        }
    }

    @Override
    public ProcedureFile Read(Integer integer) {
        return null;
    }

    @Override
    public List<ProcedureFile> ReadAll() {
        try {
            session = factory.openSession();
            config.addAnnotatedClass(ProcedureFile.class);
            session.beginTransaction();
            org.hibernate.Query queryResult = session.createQuery("from ProcedureFile");

            List<ProcedureFile> allFiles = queryResult.list();
            session.close();
            return allFiles;
        }catch (Exception e){
            System.out.println(e);
            session.close();
            return null;
        }
    }

    @Override
    public void Update(ProcedureFile t) {
        session = factory.getCurrentSession();
        config.addAnnotatedClass(ProcedureFile.class);
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
    }
}
