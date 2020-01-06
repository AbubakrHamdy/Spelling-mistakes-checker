package Repositories;

import Entities.Activity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ActivityRepository implements Repository<Activity, Integer> {
    Configuration config = new Configuration();
    SessionFactory factory= config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();
    public void Insert(Activity T) {
        try{

            session = factory.getCurrentSession();
            config.addAnnotatedClass(Activity.class);
            session.beginTransaction();
            session.save(T);
            session.getTransaction().commit();}
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("...........//////////////"+e);
        }
//        session = factory.getCurrentSession();
//        config.addAnnotatedClass(Activity.class);
//        session.beginTransaction();
//        session.save(T);
//        session.getTransaction().commit();

    }

    public Activity Read(Integer integer) {
        return null;
    }

    public List<Activity> ReadAll() {

        session = factory.openSession();
        config.addAnnotatedClass(Activity.class);
        org.hibernate.Query queryResult = session.createQuery("from Activity");
        session.beginTransaction();

        List<Activity> allActivities = queryResult.list();
        session.close();
        return allActivities;
    }

    public void Update(Activity t) {
        session = factory.getCurrentSession();
        config.addAnnotatedClass(Activity.class);
        session.beginTransaction();
        session.merge(t);
        session.getTransaction().commit();
    }
}
