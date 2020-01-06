package Entities;

//import com.sun.istack.internal.Nullable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.annotation.Nullable;

import javax.persistence.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Employee implements Serializable {
    private static final long serialVersionUID = 7463544239886137876L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 22;
    private String Name = "";
    @Column(columnDefinition = "VARCHAR(1024)")
    private String Description = "";
    @Column(columnDefinition = "text")
    private String Role = "";

    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Task_Employee", joinColumns = @JoinColumn(name = "EmployeeID"),
            inverseJoinColumns = @JoinColumn(name = "TaskID"))
    Set<Task> tasks = new HashSet<Task>();


    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SubProcess_Employee", joinColumns = @JoinColumn(name = "EmployeeID"),
            inverseJoinColumns = @JoinColumn(name = "SubProcessID"))
    Set<SubProcess> subProcesses = new HashSet<SubProcess>();

//    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER, cascade = CascadeType.ALL)


    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Activity_Employee", joinColumns = @JoinColumn(name = "EmployeeID"),
            inverseJoinColumns = @JoinColumn(name = "ActivityID"))
    Set<Activity> Activities = new HashSet<Activity>();


    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Process_Employee", joinColumns = @JoinColumn(name = "EmployeeID"),
            inverseJoinColumns = @JoinColumn(name = "ProcessID"))
    Set<Process> processes = new HashSet<Process>();

    private String JobType="";
    private String JobLocation="";
    private String Manager="";
    @Column(columnDefinition = "VARCHAR(2048)")
    private String Qualification="";
    private String Experience="";
    @Column(columnDefinition = "VARCHAR(2048)")
    private String Skills="";
    private String Languages="";

    private String language="";

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public String getJobLocation() {
        return JobLocation;
    }

    public void setJobLocation(String jobLocation) {
        JobLocation = jobLocation;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }

    public Set<Activity> getActivities() {
        return Activities;
    }

    public void setActivities(Set<Activity> activities) {
        Activities = activities;
    }

    public Employee() {
        language = "EN";
    }

    public Employee(String name, String description, String role) {
        Name = name;
        Description = description;
        Role = role;
        language = "EN";

    }

    public Employee(int id, String name, String description, String role) {
        this.id = id;
        Name = name;
        Description = description;
        Role = role;
        language = "EN";

    }

    public Employee(Employee e) {
        this.Name = new String(e.Name);
        //for (int i = 0; i < e.Activities.size(); i++) {
        //String ac = new String(e.Activities.get(i));
        //  this.Activities.add(ac);
        //}
        this.id = new Integer(e.id);
        this.Description = new String(e.Description);
        this.Experience = new String(e.Experience);
        this.JobType = new String(e.JobType);
        this.JobLocation = new String(e.JobLocation);
        this.Manager = new String(e.Manager);
        this.Qualification = new String(e.Qualification);
        this.Skills = new String(e.Skills);

        this.Role = new String(e.Role);
        this.language =new String(e.language);

    }

    public Employee(String emp_Name, int emp_ID, String emp_Job_Title, String emp_Job_Description) {
        this.Name = emp_Name;
        this.id = emp_ID;
        this.Role = emp_Job_Title;
        this.Description = emp_Job_Description;
        language = "EN";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<SubProcess> getSubProcesses() {
        return subProcesses;
    }

    public void setSubProcesses(Set<SubProcess> subProcesses) {
        this.subProcesses = subProcesses;
    }

    public Set<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }

    public void ClearChildern() {
        this.tasks.clear();
    }


    public String GetTasksString() {
        String T = Role + " " + this.Description + " and Tasks is { ";
//        System.out.println(" task num = "+Tasks.size());
        List<Task> Tasks = new ArrayList<Task>(tasks);
        for (int i = 0; i < Tasks.size(); i++) {
            T = T + Tasks.get(i).getShortDesc() + " , ";
        }
        T = T + " }";
        return T;
    }

    public void AddSub(SubProcess sp) {
        this.subProcesses.add(sp);
    }


    public String PrintToTheDoc33(XWPFRun r) {
        String c = "";
        c = "► Owner " + getRole().replace("\n", "") + '\n';
        List<SubProcess> Acs = new ArrayList<SubProcess>(getSubProcesses());
        for (int i = 0; i < Acs.size(); i++) {
            String c_temp = Acs.get(i).PrintToTheDoc33(r);
            c = c + c_temp;
        }
        return c;
    }

    public void PrintToTheDoc3(XWPFParagraph px) throws IOException, InvalidFormatException {

        XWPFRun SubProcessL = px.createRun();
        SubProcessL.setBold(true);
        SubProcessL.setColor("344cfa");
        SubProcessL.setText("► Owner ");
//        SubProcessL.addBreak();

        XWPFRun SubProcessName = px.createRun();
        SubProcessName.setBold(true);
        SubProcessName.setText(getRole().replace("\n", ""));
        SubProcessName.addBreak();

        List<SubProcess> Acs = new ArrayList<SubProcess>(getSubProcesses());
        for (int i = 0; i < Acs.size(); i++) {
            Acs.get(i).PrintToTheDoc331(px);
        }
    }
}
