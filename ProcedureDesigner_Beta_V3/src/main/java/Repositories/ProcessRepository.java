package Repositories;

import Entities.Process;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class ProcessRepository implements Repository<Process ,Integer> {
    Configuration config = new Configuration();
    SessionFactory factory= config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();
    public void Insert(Process T) {
        try{
        session = factory.getCurrentSession();
        config.addAnnotatedClass(Process.class);
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

    public Process Read(Integer integer) {
        return null;
    }

    public List<Process> ReadAll() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        org.hibernate.Query queryResult = session.createQuery("from Process");
        List<Entities.Process> allActivities = queryResult.list();
        session.close();
        return allActivities;


      /*  session = factory.getCurrentSession();
        config.addAnnotatedClass(Process.class);
        session.beginTransaction();
        session.getTransaction().commit();
        org.hibernate.Query queryResult = session.createQuery("from Process");
      //  session.beginTransaction();

        List<Process> allActivities = queryResult.list();
        //session.close();
        return allActivities;*/
    }

    public void Update(Process t) {
        session = factory.getCurrentSession();
        config.addAnnotatedClass(Process.class);
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();

    }
}
