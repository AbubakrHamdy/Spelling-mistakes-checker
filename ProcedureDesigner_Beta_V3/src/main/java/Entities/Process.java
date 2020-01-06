package Entities;

//import com.sun.istack.internal.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Process implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID =13;
    public int level_num = 0;
    @Column(columnDefinition="VARCHAR(1024)")
    private String ShortDescription = "";
    @Column(columnDefinition="VARCHAR(2048)")
    private String Description = "";
    private String Version= "";
    private String PName="Youmna Ashraf";
    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="Process_Employee" , joinColumns = @JoinColumn(name ="ProcessID")
            , inverseJoinColumns = @JoinColumn(name ="EmployeeID"))
    private Set<Employee>employees=new HashSet<Employee>();
    @Nullable
    @JoinTable(name="Process_SubProcess" , joinColumns = @JoinColumn(name ="ProcessID")
            , inverseJoinColumns = @JoinColumn(name ="SubProcessID"))
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<SubProcess>subProcesses=new HashSet<SubProcess>();
//    @Nullable
//    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name="Activity_Process" , joinColumns = @JoinColumn(name ="ProcessID"),
//            inverseJoinColumns = @JoinColumn(name ="ActivityID"))
//    private Set<Activity>activities=new HashSet<Activity>();
    private  String MainProcessOwner= "";

    private String language;


    public Process(java.lang.Process MP) {
    }
    public Process() {
        language = "EN";
    }

    public int getLevel_num() {
        return level_num;
    }

    public void setLevel_num(int level_num) {
        this.level_num = level_num;
    }

    public Process(int ID, String shortDescription, String description, String version, String mainProcessOwner) {
        this.ID = ID;
        ShortDescription = shortDescription;
        Description = description;
        Version = version;
        MainProcessOwner = mainProcessOwner;
        language = "EN";
    }

    public Process(String shortDescription, String description, String version, String mainProcessOwner) {
        ShortDescription = shortDescription;
        Description = description;
        Version = version;
        MainProcessOwner = mainProcessOwner;
        language = "EN";

    }

    public Process(String name, int id, String description, int levels_num) {
        this.ShortDescription = name;
        this.ID = id;
        this.Description = description;
        this.level_num = levels_num;
        language = "EN";

    }
    public Process(Process p) {

        //for (int i = 0; i < p.SubProcesses.size(); i++) {
        // SubProcess sp = new SubProcess(p.SubProcesses.get(i));
        //   SubProcesses.add(sp);
        // }
        this.PName=new String(p.PName);
        this.ShortDescription = new String(p.ShortDescription);
        this.level_num = new Integer(p.level_num);
        this.Description = new String(p.Description);
        this.ID = new Integer(p.ID);
        this.MainProcessOwner = new String(p.MainProcessOwner);
        this.Version = new String(p.Version);
        this.language =new String(p.language);
    }


    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<SubProcess> getSubProcesses() {
        return subProcesses;
    }

    public void setSubProcesses(Set<SubProcess> subProcesses) {
        this.subProcesses = subProcesses;
    }

    public String getMainProcessOwner() {
        return MainProcessOwner;
    }

    public void setMainProcessOwner(String mainProcessOwner) {
        MainProcessOwner = mainProcessOwner;
    }

//    public Set<Activity> getActivities() {
//        return activities;
//    }
//
//    public void setActivities(Set<Activity> activities) {
//        this.activities = activities;
//    }

    public void ClearChildern(){
        this.subProcesses.clear();
    }

    public void Get_Info() {
        System.out.println("-" + ShortDescription + "( MainProcess )");
        List<SubProcess>subs=new ArrayList<SubProcess>(subProcesses);
        for (int i = 0; i < subs.size(); i++) {
            subs.get(i).Get_Info("   ");
        }
    }

}
