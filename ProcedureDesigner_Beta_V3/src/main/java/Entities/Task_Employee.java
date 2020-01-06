package Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static sample.Test_Data.db;

public class Task_Employee {
    int TaskID;
    int EmployeeID;

    public int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int taskID) {
        TaskID = taskID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public List<Task_Employee> findAllStudentsWithJpql() {
        try {
            List<Task_Employee> TaskEmployeeDBList = new ArrayList<Task_Employee>();
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/proceduredb";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");
            String query = "SELECT * FROM  task_employee";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id1 = rs.getInt("TaskID");
                int id2 = rs.getInt("EmployeeID");
                Task_Employee TE = new Task_Employee();
                TE.setTaskID(id1);
                TE.setEmployeeID(id2);
                TaskEmployeeDBList.add(TE);
//                System.out.format("%s, %s\n", id1,id2);
            }
            String out=CheckIfDifferentEmployeesDoTheSameTask(TaskEmployeeDBList);
            System.out.println(out);
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String CheckIfDifferentEmployeesDoTheSameTask(List<Task_Employee> taskemployeelist) {
        class obj {
            Integer TaskID;
            List<Integer> EmployeesIDs = new ArrayList<Integer>();
        }
        List<obj> ObjList = new ArrayList<obj>();
        String Output = "";
        for (int i = 0; i < taskemployeelist.size(); i++) {
            obj obj1 = new obj();
            obj1.TaskID = taskemployeelist.get(i).getTaskID();
            obj1.EmployeesIDs = GetTheIDsOfDifferentEmployeesDoTheSameTask(obj1.TaskID, taskemployeelist);
            ObjList.add(obj1);
            Output = Output + PrintDifferentEmployeesDoTheSameTask(obj1.TaskID, obj1.EmployeesIDs);
        }
        return Output;
    }

    public String PrintDifferentEmployeesDoTheSameTask(Integer TaskID, List<Integer> EmployeesIDs) {
        Task task = db.Task(TaskID);
        String FirstLine = "The Task: " + task.getShortDesc() + " is done by ";
        System.out.print(FirstLine);
        System.out.println("• " + db.Employee(EmployeesIDs.get(0)).getRole() + "\n");
        String Output = FirstLine + "• " + db.Employee(EmployeesIDs.get(0)).getRole() + "\n";
        int SpaceSize = FirstLine.length();
        String Spaces = "";
        for (int i = 0; i < SpaceSize; i++) {
            Spaces = Spaces + " ";
        }
        for (int i = 1; i < EmployeesIDs.size(); i++) {
            Employee employee = db.Employee(EmployeesIDs.get(i));
            System.out.println(Spaces + "• " + db.Employee(EmployeesIDs.get(i)).getRole() + "\n");
            Output = Output + Spaces + "• " + db.Employee(EmployeesIDs.get(i)).getRole() + "\n";
        }
        return Output;
    }

    public List<Integer> GetTheIDsOfDifferentEmployeesDoTheSameTask(int taskid, List<Task_Employee> taskemployeelist) {
        List<Integer> EmployeesIDs = new ArrayList<Integer>();
        for (int i = 0; i < taskemployeelist.size(); i++) {
            if (taskemployeelist.get(i).getTaskID() == taskid) {
                if (!EmployeesIDs.contains(taskemployeelist.get(i).EmployeeID)) {
                    EmployeesIDs.add(taskemployeelist.get(i).EmployeeID);
                }
            }
        }
        return EmployeesIDs;
    }
}
