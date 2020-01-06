package Entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Risk implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "Risk")
    private String Risk;

    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SubProcess_Risk", joinColumns = @JoinColumn(name = "RiskID")
            , inverseJoinColumns = @JoinColumn(name = "SubProcessID"))
    private Set<SubProcess> subProcesses = new HashSet<SubProcess>();

    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Activity_Risk", joinColumns = @JoinColumn(name = "RiskID")
            , inverseJoinColumns = @JoinColumn(name = "ActivityID"))
    private Set<Activity> activity = new HashSet<Activity>();


    public Set<SubProcess> getSubProcesses() {
        return subProcesses;
    }

    public void setSubProcesses(Set<SubProcess> subProcesses) {
        this.subProcesses = subProcesses;
    }

    public Set<Activity> getActivity() {
        return activity;
    }

    public void setActivity(Set<Activity> activity) {
        this.activity = activity;
    }

    //    public SubProcess subProcesses = new SubProcess();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRisk() {
        return Risk;
    }

    public void setRisk(String risk) {
        Risk = risk;
    }
    Risk(){}
    public Risk(Risk a) {
        this.id=new Integer(a.id);
        this.Risk=new String(a.getRisk());
    }
    public void ClearChildren() {
        subProcesses.clear();
        activity.clear();
    }
}
