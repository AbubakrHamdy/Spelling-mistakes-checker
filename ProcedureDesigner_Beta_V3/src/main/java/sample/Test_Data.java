package sample;

import Controller.DB;
import Entities.Process;
import Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static Entities.Images.GetImages;

public class Test_Data implements Serializable {


    public static DB db;
    public List<Employee> EmployeesDBList = new ArrayList<Employee>();
    public List<Process> ProceduresDBList = new ArrayList<Process>();
    public List<JobDescriptionFile> JobDescriptionFilesDBList = new ArrayList<>();
    public List<ProcedureFile> ProcedureFilesDBList = new ArrayList<>();

    public List<String> SubProcessCategoriesDBList = new ArrayList<String>();
    public List<SubProcess> SubProcessDBList = new ArrayList<SubProcess>();
    public List<Task> TasksDBList = new ArrayList<Task>();
    public ArrayList<String> TasksCategoriesDBList = new ArrayList<String>();
    public ArrayList<String> SubProcessesSTDsDBList = new ArrayList<String>();
    public ArrayList<String> SubProcessLanguagesDBList = new ArrayList<String>();
    public List<Duration> DurationDBList = new ArrayList<Duration>();
    public List<Risk> RiskDBList = new ArrayList<Risk>();
    public List<SupProcessActivity> SupProcessActivityDBList = new ArrayList<SupProcessActivity>();
    public List<Activity> ActivitiesDBList = new ArrayList<Activity>();
    public ArrayList<String> ImagesList = new ArrayList<String>();


//    public List<Task_Employee> TaskEmployeeDBList = new ArrayList<Task_Employee>();

//    Duration D_1 = new Duration(1, "2015-12-21");
//    Duration D_2 = new Duration(2, "2013-10-20");
//    Duration D_3 = new Duration(3, "2011-11-22");
//    Duration D_4 = new Duration(4, "2009-09-12");
//    Duration D_5 = new Duration(5, "2017-07-04");
//    Duration D_6 = new Duration(6, "2019-12-29");


    public Test_Data() {
        db = new DB();
        ImagesList=GetImages();
        EmployeesDBList = db.getALlEmployees();
        JobDescriptionFilesDBList = db.getAllFiles1();
        ProcedureFilesDBList = db.getAllFiles0();
        ProceduresDBList = db.getALlProcess();
        DurationDBList = db.GetDuration();
        RiskDBList = db.GetRisk();

        SubProcessDBList = db.getALlSubProcess();
        SubProcessesSTDsDBList = db.GetAllSTDFromSubprosess();
        SubProcessLanguagesDBList = db.GetAllLanguagesFromSubprosess();
        SubProcessCategoriesDBList = db.GetAllCategoriesFromSubprosess();
        SupProcessActivityDBList = db.FillSupProcessActivityList();
        ActivitiesDBList = db.getALlActivity();
        TasksDBList = db.GetAllTaskes();
//        for (int i = 0; i < RiskDBList.size(); i++) {
//            System.out.println("++Risks from DB =++++"+RiskDBList.get(i).getActivity().size());
//        }
//        for (int i = 0; i < ActivitiesDBList.size(); i++) {
//            System.out.println(ActivitiesDBList.get(i).getShortDescription()+" Risks from DB =++++"+ActivitiesDBList.get(i).getRisks());
//        }
//        for (int i = 0; i < TasksDBList.size(); i++) {
////            List<SubProcess> SPA = new ArrayList<SubProcess>(ActivitiesDBList.get(i).getSubProcesses());
//            //            Employee employee = db.Employee(EmployeesIDs.get(i));
////            System.out.println(ActivitiesDBList.get(i).getShortDescription() + "• "
////                    +SPA.size() + "\n"
////            );
//            if(TT.TasksDBList.get(i)!=null&&TT.TasksDBList.get(i).getEmployees()!=null){
//                List<Employee> TEs = new ArrayList<Employee>(TT.TasksDBList.get(i).getEmployees());
//
//                System.out.println(TasksDBList.get(i).getShortDesc() + "•+ "
//                        +TEs.size() + "\n"
//                );
//            }
//            else {
//                System.out.println(TasksDBList.get(i).getShortDesc() + "•+ "+"0"
////                    +TEs.size() + "\n"
//                );
//            }
////            List<Employee> TEs = new ArrayList<Employee>(TT.TasksDBList.get(i).getEmployees());
//
//            System.out.println(TasksDBList.get(i).getShortDesc() + "•+ "
////                    +TEs.size() + "\n"
//            );
//        }

        TasksCategoriesDBList = db.GetAllCategorisFromTasks();
//        TaskEmployeeDBList=db.GetAllTaskEmployee();
//        Collections.sort(TaskEmployeeDBList, new Comparator<Task_Employee>() {
//            @Override
//            public int compare(Task_Employee o1, Task_Employee o2) {
//                return o1.getTaskID().compareTo(o2.getTaskID());
//            }
//        });
        Collections.sort(EmployeesDBList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getRole().compareTo(o2.getRole());
            }
        });
        Collections.sort(SubProcessDBList, new Comparator<SubProcess>() {
            @Override
            public int compare(SubProcess o1, SubProcess o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Collections.sort(JobDescriptionFilesDBList, new Comparator<JobDescriptionFile>() {
            @Override
            public int compare(JobDescriptionFile o1, JobDescriptionFile o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Collections.sort(ProcedureFilesDBList, new Comparator<ProcedureFile>() {
            @Override
            public int compare(ProcedureFile o1, ProcedureFile o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Collections.sort(TasksDBList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getShortDesc().compareTo(o2.getShortDesc());
            }
        });
        Collections.sort(TasksCategoriesDBList);
        Collections.sort(SubProcessesSTDsDBList);
        Collections.sort(SubProcessLanguagesDBList);
        Collections.sort(SubProcessCategoriesDBList);
        Collections.sort(DurationDBList, new Comparator<Duration>() {
            @Override
            public int compare(Duration o1, Duration o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });


//        for (int i = 0; i < TasksDBList.size(); i++) {
//
//            if (TasksDBList.get(i) != null
//                    && TasksDBList.get(i).getEmployees().size() != 0) {
//                List<Employee> TEs = new ArrayList<Employee>(TasksDBList.get(i).getEmployees());
//
//                System.out.println(TasksDBList.get(i).getShortDesc() + "•+ "
//                        + TEs.get(0).getRole() + "\n"
//                );
//            }
//        }
//Risk r1=new Risk();
//r1.setRisk("Get fined");
//db.addRisk(r1);
//        Duration_List.add(D_1);
//        Duration_List.add(D_2);
//        Duration_List.add(D_3);
//        Duration_List.add(D_4);
//        Duration_List.add(D_5);
//        Duration_List.add(D_6);

    }


}

