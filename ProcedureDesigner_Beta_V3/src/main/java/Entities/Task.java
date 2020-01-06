package Entities;

//import com.sun.istack.internal.Nullable;
import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID=40;
    @Column (columnDefinition="VARCHAR(1024)")
    private String ShortDesc= "";
    @Column (columnDefinition="VARCHAR(2048)")
    private String Description= "";
    private String category= "";
    private String Std= "";
    private int level_num=0;
    private String language= "EN";

    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(name="Task_Employee" , joinColumns = @JoinColumn(name ="TaskID"), inverseJoinColumns = @JoinColumn(name ="EmployeeID"))
    private Set <Employee> employees=new HashSet<Employee>();
    public Task(int ID, String shortDesc, String description, String category, String std) {
        this.ID = ID;
        ShortDesc = shortDesc;
        Description = description;
        this.category = category;
        Std = std;
    }

    public Task(String shortDesc, String description, String category, String language) {
        ShortDesc = shortDesc;
        Description = description;
        this.category = category;
        this.language = language;
    }

    public Task(String name, int ID, String description) {
        this.ID = ID;
        ShortDesc = name;
        Description = description;
    }


    public Task(Task a) {
        //for (int i = 0; i < a.Responsible.size(); i++) {
        //Employee em = new Employee(a.Responsible.get(i));
        //  this.Responsible.add(em);
        //}
        this.ShortDesc = new String(a.ShortDesc);
        this.Description = new String(a.Description);
        this.ID = new Integer(a.ID);
        this.level_num = new Integer(a.level_num);
        this.Std = new String(a.Std);
        this.category = new String(a.category);
        this.language=new String(a.language);
    }

    public Task() {
        language = "EN";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getLevel_num() {
        return level_num;
    }

    public void setLevel_num(int level_num) {
        this.level_num = level_num;
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

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStd() {
        return Std;
    }

    public void setStd(String std) {
        Std = std;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void ClearChildern(){
        this.employees.clear();
    }

    public void Get_Info(String x) {
        if (employees.size() > 0) {
            System.out.print(x + "- " + ShortDesc + "( Task_ ) and assigned to [");
            List<Employee> emps=new ArrayList<Employee>(employees);
            for (int i = 0; i < emps.size(); i++) {
                if (i == emps.size() - 1) {

                    System.out.println(emps.get(i).getName());
                } else {
                    System.out.print(emps.get(i).getName() + " - ");
                }
            }
        } else {
            System.out.println(x + "- " + ShortDesc +"   and Duration id = ");
        }
    }

    public void Assign(Employee e) {
        if(!this.employees.contains(e))
        e.tasks.add(this);
        System.out.println("size e = "+e.tasks.size()+" ");
        this.employees.add(e);
    }

}
