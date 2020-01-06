package Controller;

import Entities.Process;
import Entities.*;
import Repositories.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate..AnnotationConfiguration;

public class DB {
    private Repository repository;

    public static Configuration config = new Configuration().configure();
    public static SessionFactory factory = config.buildSessionFactory();
    public static Session session = factory.getCurrentSession();

    public List<Employee> getALlEmployees() {
        repository = new EmployeeRepository();
        return repository.ReadAll();
    }

    public List<JobDescriptionFile> getAllFiles1() {
        repository = new JobDescriptionFileRepository();
        return repository.ReadAll();
    }

    public List<ProcedureFile> getAllFiles0() {
        repository = new ProcedureFileRepository();
        return repository.ReadAll();
    }

    public void update_employee(Employee t) {
        repository = new EmployeeRepository();
        repository.Update(t);
    }

    public void update_process(Process t) {
        repository = new ProcessRepository();
        repository.Update(t);
    }

    public void update_subProcess(SubProcess t) {
        repository = new SubProcessRepository();
        repository.Update(t);
    }
    public void update_risk(Risk t) {
        repository = new RiskRepository();
        repository.Update(t);
    }

    public void update_activity(Activity t) {
        repository = new ActivityRepository();
        repository.Update(t);
    }

    public void update_file1(JobDescriptionFile t) {
        repository = new JobDescriptionFileRepository();
        repository.Update(t);
    }

    public void update_file0(ProcedureFile t) {
        repository = new ProcedureFileRepository();
        repository.Update(t);
    }

    public List<Activity> getALlActivity() {
        repository = new ActivityRepository();
        return repository.ReadAll();
    }
    public List<SupProcessActivity> FillSupProcessActivityList(){
         List<SupProcessActivity> SupProcessActivityDBList = new ArrayList<SupProcessActivity>();
//        for(int i=0;i<TT.SubProcessDBList.size();i++){
//            SupProcessActivity SPA=new SupProcessActivity();
//            SPA.ActivitiesDBList = new ArrayList<Activity>(TT.SubProcessDBList.get(i).getActivities());
//            SPA.SupProcessID=TT.SubProcessDBList.get(i).getID();
//            SupProcessActivityDBList.add(SPA);
//        }
        return SupProcessActivityDBList;
    }

    public List<Process> getALlProcess() {
        repository = new ProcessRepository();
        return repository.ReadAll();
    }

    public List<Activity> getALlActivities() {
        repository = new ActivityRepository();
        return repository.ReadAll();
    }

    public List<SubProcess> getALlSubProcess() {
        repository = new SubProcessRepository();
        return repository.ReadAll();
    }

    public void addEmployee(Employee employee) {
        repository = new EmployeeRepository();
        repository.Insert(employee);
    }

    public void addProcess(Process process) {
        repository = new ProcessRepository();
        repository.Insert(process);
    }

    public void addActivity(Activity activity) {
        repository = new ActivityRepository();
        repository.Insert(activity);
    }

    public void addTask(Task task) {
        repository = new TaskRepository();
        repository.Insert(task);
    }

    public void addSubProcess(SubProcess subProcess) {
        repository = new SubProcessRepository();
        repository.Insert(subProcess);
    }
    public void addRisk(Risk risk) {
        repository = new RiskRepository();
        repository.Insert(risk);
    }
    public void AddTask(Task t) {
        repository = new TaskRepository();
        repository.Insert(t);
    }

    public void AddFile1(JobDescriptionFile t) {
        repository = new JobDescriptionFileRepository();
        repository.Insert(t);
    }

    public void AddFile0(ProcedureFile t) {
        repository = new ProcedureFileRepository();
        repository.Insert(t);
    }

    public List<Task> GetAllTaskes() {
        repository = new TaskRepository();
        return repository.ReadAll();
    }

    public List<Task_Employee> GetAllTaskEmployee() {
        repository = new Task_EmployeeRepository();
        return repository.ReadAll();
    }

    public void addProcess(Process process, ArrayList<SubProcess> subProcesses) {
        for (int i = 0; i < subProcesses.size(); i++) {
            process.getSubProcesses().add(subProcesses.get(i));
        }
        addProcess(process);

    }

    public ArrayList<String> GetAllCategorisFromTasks() {
        List<Task> tasks = GetAllTaskes();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (!result.contains(tasks.get(i).getCategory())) {
                result.add(tasks.get(i).getCategory());
            }
        }
        return result;
    }

    public List<Risk> GetRisk() {
        repository = new RiskRepository();
        return repository.ReadAll();

    }

    public List<Duration> GetDuration() {
        repository = new DurationRepository();
        return repository.ReadAll();

    }

    public ArrayList<String> getJobType() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Employee.class);
        ArrayList<String> result = (ArrayList<String>) criteria.setProjection(Projections.distinct(Projections.property("JobType"))).list();
        session.close();
        return result;
    }

    public ArrayList<String> getJopLocation() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Employee.class);
        ArrayList<String> result = (ArrayList<String>) criteria.setProjection(Projections.distinct(Projections.property("JobLocation"))).list();
        session.close();
        return result;
    }

    public ArrayList<String> getExperience() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Employee.class);
        ArrayList<String> result = (ArrayList<String>) criteria.setProjection(Projections.distinct(Projections.property("Experience"))).list();
        session.close();
        return result;
    }

    public ArrayList<String> getSTDS() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SubProcess.class);
        ArrayList<String> result = (ArrayList<String>) criteria.setProjection(Projections.distinct(Projections.property("STD"))).list();
        session.close();
        return result;
    }

    public ArrayList<String> getLanguages() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Employee.class);
        ArrayList<String> result = (ArrayList<String>) criteria.setProjection(Projections.distinct(Projections.property("Languages"))).list();
        session.close();
        return result;
    }

    public ArrayList<String> GetAllCategoriesFromSubprosess() {
        List<SubProcess> processList = getALlSubProcess();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < processList.size(); i++) {
            if (!result.contains(processList.get(i).getCategory())) {
                result.add(processList.get(i).getCategory());
            }
        }
        return result;
    }

    public ArrayList<String> GetAllSTDFromSubprosess() {
        List<SubProcess> processList = getALlSubProcess();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < processList.size(); i++) {
            if (!result.contains(processList.get(i).getSTD())) {
                result.add(processList.get(i).getSTD());
            }
        }
        return result;
    }

    public ArrayList<String> GetAllLanguagesFromSubprosess() {
        List<SubProcess> processList = getALlSubProcess();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < processList.size(); i++) {
            if (!result.contains(processList.get(i).getLanguage())) {
                result.add(processList.get(i).getLanguage());
            }
        }
        return result;
    }

    public Duration duration(int id) {
        DurationRepository d = new DurationRepository();
        Duration dd = d.getDuration(id);
        return dd;
    }

    public Task Task(int id) {
        TaskRepository d = new TaskRepository();
        Task dd = d.getTask(id);
        return dd;
    }

    public Employee Employee(int id) {
        EmployeeRepository d = new EmployeeRepository();
        Employee dd = d.getEmployee(id);
        return dd;
    }

}
