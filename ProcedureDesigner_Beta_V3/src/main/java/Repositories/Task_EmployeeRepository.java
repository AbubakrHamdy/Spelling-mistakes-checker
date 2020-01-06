package Repositories;

import Entities.Task_Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Task_EmployeeRepository implements Repository<Task_Employee, Integer> {

    Configuration config = new Configuration();
    SessionFactory factory = config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();

    @Override
    public void Insert(Task_Employee T) {

    }

    @Override
    public Task_Employee Read(Integer integer) {
        return null;
    }

    @Override
    public void Update(Task_Employee t) {

    }

    public List<Task_Employee> ReadAll() {
        session = factory.openSession();
        config.addAnnotatedClass(Task_Employee.class);
        org.hibernate.Query queryResult = session.createQuery("from task_employee");
        session.beginTransaction();

        List<Task_Employee> allTask_Employee = queryResult.list();
        session.close();
        return allTask_Employee;
    }
}
