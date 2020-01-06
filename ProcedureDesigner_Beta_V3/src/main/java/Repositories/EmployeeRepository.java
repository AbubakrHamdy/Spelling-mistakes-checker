package Repositories;

import Entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeRepository implements Repository<Employee,Integer> {
    Configuration config = new Configuration();
    SessionFactory factory= config.configure().buildSessionFactory();
    Session session = factory.getCurrentSession();
    public void Insert(Employee T) {
        try {
            session = factory.getCurrentSession();
            config.addAnnotatedClass(Employee.class);
            session.beginTransaction();
            session.save(T);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error :: " + e.toString());
            System.out.println("Error :: " + e.getLocalizedMessage());
            System.out.println("Error :: " + e.getMessage());
//            System.out.println("Error :: " + e.toString());
            Insert(T);
            session.close();
        }
    }

    public Employee Read(Integer integer) {
        return null;
    }

    public List<Employee> ReadAll() {
        try {
            session = factory.openSession();
            config.addAnnotatedClass(Employee.class);
            session.beginTransaction();
            org.hibernate.Query queryResult = session.createQuery("from Employee");

            List<Employee> allEmployees = queryResult.list();
            session.close();
            return allEmployees;
        }catch (Exception e){
            System.out.println(e);
            session.close();
            return null;
        }
    }

    public void Update(Employee t) {
        session = factory.getCurrentSession();
        config.addAnnotatedClass(Employee.class);
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
//        session.close();

    }

    public Employee getEmployee(int id) {
        Transaction tx = session.beginTransaction();
        Employee x = (Employee) session.get(Employee.class,id);
        tx.commit();
        return x;
    }
}
