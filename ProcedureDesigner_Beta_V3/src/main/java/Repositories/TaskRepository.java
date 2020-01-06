package Repositories;

import Entities.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TaskRepository implements Repository<Task,Integer> {
    Configuration config = new Configuration();
    SessionFactory factory= config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();
    @Override
    public void Insert(Task T) {
        session = factory.getCurrentSession();
        config.addAnnotatedClass(Task.class);
        session.beginTransaction();
        session.save(T);
        session.getTransaction().commit();
    }

    @Override
    public Task Read(Integer integer) {
        return null;
    }

    @Override
    public List<Task> ReadAll() {
        session = factory.openSession();
        config.addAnnotatedClass(Task.class);
        org.hibernate.Query queryResult = session.createQuery("from Task");
        session.beginTransaction();

        List<Task> tasks = queryResult.list();
        session.close();
        return tasks;
    }
    public Task getTask(int id) {
        Transaction tx = session.beginTransaction();
        Task x = (Task) session.get(Task.class,id);
        tx.commit();
        return x;
    }
    @Override
    public void Update(Task t) {

    }
}
