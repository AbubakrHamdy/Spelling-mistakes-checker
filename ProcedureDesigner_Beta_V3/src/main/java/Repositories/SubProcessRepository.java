package Repositories;

import Entities.SubProcess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SubProcessRepository implements Repository<SubProcess, Integer> {
    Configuration config = new Configuration();
    SessionFactory factory = config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();

    public void Insert(SubProcess T) {
        try {

            session = factory.getCurrentSession();
            config.addAnnotatedClass(SubProcess.class);
            session.beginTransaction();
            session.save(T);
            session.getTransaction().commit();
        } catch (Exception e) {
//            System.out.println("Error :: " + e.toString());
//            System.out.println("Error :: " + e.getLocalizedMessage());
//            System.out.println("Error :: " + e.getMessage());
//            System.out.println("Error :: " + e.toString());
            Insert(T);
            session.close();
        }
    }

    public SubProcess Read(Integer integer) {
        return null;
    }

    public List<SubProcess> ReadAll() {
        session = factory.openSession();
        config.addAnnotatedClass(SubProcess.class);
        org.hibernate.Query queryResult = session.createQuery("from SubProcess");
        session.beginTransaction();
        List<SubProcess> allSubProcesses = queryResult.list();
        session.close();
        return allSubProcesses;
    }

    public void Update(SubProcess t) {
        session = factory.getCurrentSession();
        config.addAnnotatedClass(SubProcess.class);
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
    }
}
