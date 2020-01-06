package Entities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static sample.Main.TT;
import static sample.Test_Data.db;

@Entity
public class SubProcess implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID = 17;
    public int level_num = 0;
    private String Name = "";
    private String STD = "";
    private String ImageName = "";
    private String Category = "";
    @Column(columnDefinition = "VARCHAR(1024)")
    private String ShortDescription = "";
    @Column(columnDefinition = "VARCHAR(2048)")
    private String Description = "";

    private int Duration_id = -1;



    private String language = "EN";

    @Nullable
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private SubProcess parent = null;

    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Process_SubProcess", joinColumns = @JoinColumn(name = "SubProcessID"),
            inverseJoinColumns = @JoinColumn(name = "ProcessID"))
    Set<Process> processes;
    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Activity_SubProcess", joinColumns = @JoinColumn(name = "SubProcessID"),
            inverseJoinColumns = @JoinColumn(name = "ActivityID"))
    Set<Activity> activities = new HashSet<Activity>();


    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SubProcess_Employee", joinColumns = @JoinColumn(name = "SubProcessID"),
            inverseJoinColumns = @JoinColumn(name = "EmployeeID"))
    Set<Employee> employees = new HashSet<Employee>();


    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SubProcess_Risk", joinColumns = @JoinColumn(name = "SubProcessID"),
            inverseJoinColumns = @JoinColumn(name = "RiskID"))
    Set<Risk> risks = new HashSet<Risk>();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration_id() {
        return Duration_id;
    }

    public void setDuration_id(int duration_id) {
        Duration_id = duration_id;
    }

    public void setRisks(@Nullable Set<Risk> risks) {
        this.risks = risks;
    }
    public SubProcess() {
        language = "EN";
    }

    public SubProcess(String name, int id, String description, int levels_num) {
        this.Name = name;
        this.ShortDescription = name;
        this.ID = id;
        this.Description = description;
        this.level_num = levels_num;
        parent = null;
        language = "EN";

    }

    public SubProcess(int ID, String name, String STD, String category, String shortDescription, String description, SubProcess child) {
        this.ID = ID;
        Name = name;
        this.STD = STD;
        Category = category;
        ShortDescription = shortDescription;
        Description = description;
        this.parent = child;
        language = "EN";

    }

    public SubProcess(String name, String STD, String category, String shortDescription, String description) {
        Name = name;
        this.STD = STD;
        Category = category;
        ShortDescription = shortDescription;
        Description = description;
        parent = null;
        language = "EN";

    }

    public SubProcess(String name, String STD, String category, String shortDescription, String description, SubProcess child) {
        Name = name;
        this.STD = STD;
        Category = category;
        ShortDescription = shortDescription;
        Description = description;
        this.parent = child;
        language = "EN";

    }

    public SubProcess(SubProcess s) {

        this.Name = new String(s.Name);
        this.Description = new String(s.Description);
        this.ID = new Integer(s.ID);
        this.ShortDescription = new String(s.ShortDescription);
        this.level_num = new Integer(s.level_num);
        this.STD = new String(s.STD);
        this.language = new String(s.language);
        this.Category = new String(s.Category);
        if (s.ImageName == null) {
            this.ImageName = "";
        } else {
            this.ImageName = new String(s.ImageName);
        }

//        this.Duration_id = new Integer(s.Duration_id);
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public int getLevel_num() {
        return level_num;
    }

    public void setLevel_num(int level_num) {
        this.level_num = level_num;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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

    public SubProcess getParent() {
        return parent;
    }

    public void setParent(SubProcess child) {
        this.parent = child;
    }

    public Set<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public Set<Risk> getRisks() {
        return risks;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String getSTD() {
        return STD;
    }

    public void setSTD(String STD) {
        this.STD = STD;
    }

    public void ClearChildern() {
        this.activities.clear();
        this.employees.clear();
        this.parent = null;
        this.risks.clear();
    }

    public void Get_Info(String x) {
        System.out.println(x + "- " + Name + "( SubProcess )");
        List<Activity> Activities = new ArrayList<Activity>(activities);
        for (int i = 0; i < Activities.size(); i++) {
            Activities.get(i).Get_Info(x + "   ");
        }
        if (parent != null) {
            parent.Get_Info(x + "   ");
        }

    }

    public String toString() {
        return getName();
    }


    public String PrintToTheDoc3(XWPFRun r, String ss) {
//        System.out.println(getName()+" "+c);
        ss = ss + "► Sub Process: " + getName() + '\n';
        ss = ss + "• Short Description: " + getShortDescription().replace("\n", "") + '\n';
        ss = ss + "•• Description: " + getDescription().replace("\n", "") + '\n';
        String Duration = "it has no duration";
        for (int i = 0; i < TT.DurationDBList.size(); i++) {
            if (getDuration_id() != -1 && getDuration_id() == TT.DurationDBList.get(i).getId()) {
                Duration = TT.DurationDBList.get(i).getValue();
            }
        }
        ss = ss + "► Duration: " + Duration + '\n';
        String Owner = "it has no owner";
        for (int i = 0; i < TT.EmployeesDBList.size(); i++) {
            List<Employee> Es = new ArrayList<Employee>(getEmployees());
            if (Es.size() > 0 && Es.get(0).getId() == TT.EmployeesDBList.get(i).getId()) {
                Owner = TT.EmployeesDBList.get(i).getRole();
            }
        }
        ss = ss + "► Owner: " + Owner + '\n';

        String risk = "it has no risks";
        List<Risk> rs = new ArrayList<Risk>(risks);
        for (int i = 0; i < rs.size(); i++) {
            if (i == 0) {
                risk = rs.get(i).getRisk();
            } else {
                risk = risk + " , " + rs.get(i).getRisk();
            }
        }
        ss = ss + "► Risks: " + risk + '\n';

        List<Activity> Acs = new ArrayList<Activity>(getActivities());
        System.out.println(" kkkkkkkkk sizr" + rs.size());


        for (int i = 0; i < Acs.size(); i++) {
            String c_temp = Acs.get(i).PrintToTheDoc3(r);
            ss = ss + c_temp;
        }
        System.out.println(ss);

        return ss;
    }

    public String PrintToTheDoc33(XWPFRun r) {
        String ss = "";
        ss = ss + "► Sub Process: " + getName() + '\n';
        ss = ss + "• Short Description: " + getShortDescription().replace("\n", "") + '\n';
        ss = ss + "•• Description:" + getDescription().replace("\n", "") + '\n';
        String Duration = "it has no duration";
        for (int i = 0; i < TT.DurationDBList.size(); i++) {
            if (getDuration_id() != -1 && getDuration_id() == TT.DurationDBList.get(i).getId()) {
                Duration = TT.DurationDBList.get(i).getValue();
            }
        }
        ss = ss + "► Duration: " + Duration + '\n';

        String risk = "it has no risks";
        List<Risk> rs = new ArrayList<Risk>(risks);
        for (int i = 0; i < rs.size(); i++) {
            if (i == 0) {
                risk = rs.get(i).getRisk();
            } else {
                risk = risk + " , " + rs.get(i).getRisk();
            }
        }
        ss = ss + "► Risks : " + risk + '\n';

        List<Activity> Acs = new ArrayList<Activity>(getActivities());
        System.out.println(" kkkkkkkkk sizr" + rs.size());

        for (int i = 0; i < Acs.size(); i++) {
            String c_temp = Acs.get(i).PrintToTheDoc33(r);
            ss = ss + c_temp;
        }
        return ss;
    }

    public SubProcess GetItselfFromDB() {
        for (int i = 0; i < TT.SubProcessDBList.size(); i++) {
            if (TT.SubProcessDBList.get(i).getID() == getID()) {
                return TT.SubProcessDBList.get(i);
            }
        }
        return null;
    }

    public List<Activity> GetItsActivitiesFromDB() {
        List<Activity> acs = new ArrayList<Activity>(getActivities());
        List<Activity> result = new ArrayList<Activity>();
        for (int j = 0; j < acs.size(); j++) {
            for (int i = 0; i < TT.ActivitiesDBList.size(); i++) {
                if (TT.ActivitiesDBList.get(i).getID() == acs.get(j).getID()) {
                    result.add(TT.ActivitiesDBList.get(i));
                    break;
                }
            }
        }
        return result;
    }

    public List<Employee> GetItsEmployeesFromDB() {
        List<Employee> acs = new ArrayList<Employee>(getEmployees());
        List<Employee> result = new ArrayList<Employee>();
        for (int j = 0; j < acs.size(); j++) {
            for (int i = 0; i < TT.EmployeesDBList.size(); i++) {
                if (TT.EmployeesDBList.get(i).getId() == acs.get(j).getId()) {
                    result.add(TT.EmployeesDBList.get(i));
                    break;
                }
            }
        }
        return result;
    }

    public List<Risk> GetItsRisksFromDB() {
        List<Risk> acs = new ArrayList<Risk>(risks);
        List<Risk> result = new ArrayList<Risk>();
        for (int j = 0; j < acs.size(); j++) {
            for (int i = 0; i < TT.RiskDBList.size(); i++) {
//                System.out.println("Risks from DB =++++" + TT.RiskDBList.get(i).getSubProcesses());
                if (TT.RiskDBList.get(i).getId() == acs.get(j).getId()) {
                    result.add(TT.RiskDBList.get(i));
                    break;
                }
            }
        }
        return result;
    }

    public void UpdateDB() {
        SubProcess sb = GetItselfFromDB();
        List<Risk> rs = GetItsRisksFromDB();
        List<Employee> ems = GetItsEmployeesFromDB();

        List<Risk> sbrs = new ArrayList<Risk>(sb.getRisks());
        List<Employee> sbems = new ArrayList<Employee>(sb.getEmployees());

        for (int j = 0; j < sbrs.size(); j++) {
            for (int i = 0; i < rs.size(); i++) {
                if (sbrs.get(j).getId() != rs.get(i).getId()) {
//                rs.get(i).getSubProcesses().add(this);
//                rs.get(i).getSubProcesses().add(this);
                    sb.getRisks().add(rs.get(i));
                }
            }
        }
        for (int j = 0; j < sbems.size(); j++) {
            for (int i = 0; i < ems.size(); i++) {
                if (sbems.get(j).getId() != ems.get(i).getId()) {
                    sb.getEmployees().add(ems.get(i));
                }
            }
        }
        db.update_subProcess(sb);

    }

    public Boolean CheckIfEmployeeAreadyInTheList(Employee em) {
        SubProcess acs = GetItselfFromDB();
        List<Employee> acsems = new ArrayList<Employee>(acs.getEmployees());

        for (int i = 0; i < acsems.size(); i++) {
            if (acsems.get(i).getId() == em.getId()) {
                return true;
            }
        }
        return false;
    }

    public Boolean CheckIfRiskAreadyInTheList(Risk ri) {
        SubProcess acs = GetItselfFromDB();
        List<Risk> acsri = new ArrayList<Risk>(acs.getRisks());

        for (int i = 0; i < acsri.size(); i++) {
            if (acsri.get(i).getId() == ri.getId()) {
                return true;
            }
        }
        return false;
    }

    public void UpdateDB1() throws ClassNotFoundException, SQLException {

        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/proceduredb";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "root");
        Statement st = conn.createStatement();

        List<Risk> rs = GetItsRisksFromDB();
        List<Employee> ems = GetItsEmployeesFromDB();
        for (int i = 0; i < rs.size(); i++) {
            if (!CheckIfRiskAreadyInTheList(rs.get(i))) {
                st.executeUpdate("INSERT INTO subprocess_risk (RiskID, SubProcessID) "
                        + "VALUES (" + rs.get(i).getId() + "," + getID() + " )");
            }
        }
        for (int i = 0; i < ems.size(); i++) {
            if (!CheckIfEmployeeAreadyInTheList(ems.get(i))) {
                st.executeUpdate("INSERT INTO subprocess_employee (SubProcessID, EmployeeID) "
                        + "VALUES (" + getID() + "," + ems.get(i).getId() + " )");
            }
        }
        System.out.println("ddddddddddddd="+getImageName());
//        st.executeUpdate("UPDATE 'subprocess' set 'ImageName' = "+getImageName()+" WHERE ID ="+getID());
        PreparedStatement updateEXP = conn.prepareStatement("update subprocess set ImageName = ? where ID = ?");
        updateEXP.setString(1, getImageName());
        updateEXP.setInt(2,getID());
        updateEXP.execute();
        if(getDuration_id()!=-1){
//            st.executeUpdate("UPDATE subprocess SET Duration_id = "+getDuration_id()+" WHERE ID ="+getID());
            PreparedStatement updateEXP1 = conn.prepareStatement("update subprocess set Duration_id = ? where ID = ?");
            updateEXP1.setInt(1,getDuration_id());
            updateEXP1.setInt(2,getID());
            updateEXP1.execute();
        }

    }

    public void PrintToTheDoc31(XWPFParagraph px) throws IOException, InvalidFormatException {
        XWPFRun SubProcessL = px.createRun();
        SubProcessL.setBold(true);
        SubProcessL.setColor("005828");
        SubProcessL.setText("► Sub Process Name: ");
//        SubProcessL.addBreak();

        XWPFRun SubProcessName = px.createRun();
        SubProcessName.setBold(true);
        SubProcessName.setText(getName());
        SubProcessName.addBreak();


        XWPFRun SubProcessDescShortL = px.createRun();
//        SubProcessDescShortL.setBold(true);
        SubProcessDescShortL.setColor("00d419");
        SubProcessDescShortL.setText("• Short Description: ");
//        SubProcessDescShortL.addBreak();

        XWPFRun SubProcessDescShort = px.createRun();
//        SubProcessDescShort.setBold(true);
        SubProcessDescShort.setText(getShortDescription().replace("\n", ""));
        SubProcessDescShort.addBreak();

//        ss = ss  +  + '\n';

        XWPFRun SubProcessDescL = px.createRun();
//        SubProcessDescL.setBold(true);
        SubProcessDescL.setColor("00d419");
        SubProcessDescL.setText("•• Description: ");
//        SubProcessDescL.addBreak();

        XWPFRun SubProcessDesc = px.createRun();
//        SubProcessDesc.setBold(true);
        SubProcessDesc.setText(getDescription().replace("\n", ""));
        SubProcessDesc.addBreak();

        String Duration = "it has no duration";
        for (int i = 0; i < TT.DurationDBList.size(); i++) {
            if (getDuration_id() != -1 && getDuration_id() == TT.DurationDBList.get(i).getId()) {
                Duration = TT.DurationDBList.get(i).getValue();
            }
        }
//        ss = ss  + Duration + '\n';

        XWPFRun SubProcessDurationL = px.createRun();
//        SubProcessDurationL.setBold(true);
        SubProcessDurationL.setColor("548ed4");
        SubProcessDurationL.setText("► Duration: ");
//        SubProcessDurationL.addBreak();

        XWPFRun SubProcessDuration = px.createRun();
//        SubProcessDuration.setBold(true);
        SubProcessDuration.setText(Duration);
        SubProcessDuration.addBreak();


        String Owner = "it has no owner";
        for (int i = 0; i < TT.EmployeesDBList.size(); i++) {
            List<Employee> Es = new ArrayList<Employee>(getEmployees());
            if (Es.size() > 0 && Es.get(0).getId() == TT.EmployeesDBList.get(i).getId()) {
                Owner = TT.EmployeesDBList.get(i).getRole();
            }
        }

//        ss = ss + Owner + '\n';

        XWPFRun SubProcessOwnerL = px.createRun();
//        SubProcessOwnerL.setBold(true);
        SubProcessOwnerL.setColor("1f497d");
        SubProcessOwnerL.setText("► Owner: ");
//        SubProcessOwnerL.addBreak();

        XWPFRun SubProcessOwner = px.createRun();
//        SubProcessOwner.setBold(true);
        SubProcessOwner.setText(Owner);
        SubProcessOwner.addBreak();


        String risk = "it has no risks";
        List<Risk> rs = new ArrayList<Risk>(risks);
        for (int i = 0; i < rs.size(); i++) {
            if (i == 0) {
                risk = rs.get(i).getRisk();
            } else {
                risk = risk + " , " + rs.get(i).getRisk();
            }
        }
//        ss = ss + "► Risks: " + risk + '\n';
        XWPFRun SubProcessRiskL = px.createRun();
//        SubProcessRiskL.setBold(true);
        SubProcessRiskL.setColor("c00000");
        SubProcessRiskL.setText("► Risks: ");
//        SubProcessRiskL.addBreak();

        XWPFRun SubProcessRisk = px.createRun();
//        SubProcessRisk.setBold(true);
        SubProcessRisk.setText(risk);
        SubProcessRisk.addBreak();

        if (getImageName() != null || !getImageName().matches("") || !getImageName().matches("None")) {
            XWPFRun SubProcessImageL = px.createRun();
//        SubProcessImageL.setColor("c00000");
            SubProcessImageL.setText("► Image: ");
            SubProcessImageL.addBreak();

            XWPFRun SubProcessImage = px.createRun();
            InputStream pic = new FileInputStream("D:\\PD\\Images\\" + getImageName());
            SubProcessImage.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, getImageName(), Units.toEMU(200), Units.toEMU(200));
            SubProcessImage.addBreak();
        }


        List<Activity> Acs = new ArrayList<Activity>(getActivities());

        for (int i = 0; i < Acs.size(); i++) {
            Acs.get(i).PrintToTheDoc31(px);
        }
    }

    public void PrintToTheDoc331(XWPFParagraph px) throws IOException, InvalidFormatException {
        XWPFRun SubProcessL = px.createRun();
        SubProcessL.setBold(true);
        SubProcessL.setColor("005828");
        SubProcessL.setText("► Sub Process Name: ");
//        SubProcessL.addBreak();

        XWPFRun SubProcessName = px.createRun();
        SubProcessName.setBold(true);
        SubProcessName.setText(getName());
        SubProcessName.addBreak();


        XWPFRun SubProcessDescShortL = px.createRun();
//        SubProcessDescShortL.setBold(true);
        SubProcessDescShortL.setColor("00d419");
        SubProcessDescShortL.setText("• Short Description: ");
//        SubProcessDescShortL.addBreak();

        XWPFRun SubProcessDescShort = px.createRun();
//        SubProcessDescShort.setBold(true);
        SubProcessDescShort.setText(getShortDescription().replace("\n", ""));
        SubProcessDescShort.addBreak();


        XWPFRun SubProcessDescL = px.createRun();
//        SubProcessDescL.setBold(true);
        SubProcessDescL.setColor("00d419");
        SubProcessDescL.setText("•• Description: ");
//        SubProcessDescL.addBreak();

        XWPFRun SubProcessDesc = px.createRun();
//        SubProcessDesc.setBold(true);
        SubProcessDesc.setText(getDescription().replace("\n", ""));
        SubProcessDesc.addBreak();


        String Duration = "it has no duration";
        for (int i = 0; i < TT.DurationDBList.size(); i++) {
            if (getDuration_id() != -1 && getDuration_id() == TT.DurationDBList.get(i).getId()) {
                Duration = TT.DurationDBList.get(i).getValue();
            }
        }

        XWPFRun SubProcessDurationL = px.createRun();
//        SubProcessDurationL.setBold(true);
        SubProcessDurationL.setColor("548ed4");
        SubProcessDurationL.setText("► Duration: ");
//        SubProcessDurationL.addBreak();

        XWPFRun SubProcessDuration = px.createRun();
//        SubProcessDuration.setBold(true);
        SubProcessDuration.setText(Duration);
        SubProcessDuration.addBreak();


        String risk = "it has no risks";
        List<Risk> rs = new ArrayList<Risk>(risks);
        for (int i = 0; i < rs.size(); i++) {
            if (i == 0) {
                risk = rs.get(i).getRisk();
            } else {
                risk = risk + " , " + rs.get(i).getRisk();
            }
        }

        XWPFRun SubProcessRiskL = px.createRun();
//        SubProcessRiskL.setBold(true);
        SubProcessRiskL.setColor("c00000");
        SubProcessRiskL.setText("► Risks: ");
//        SubProcessRiskL.addBreak();

        XWPFRun SubProcessRisk = px.createRun();
//        SubProcessRisk.setBold(true);
        SubProcessRisk.setText(risk);
        SubProcessRisk.addBreak();

        if (getImageName() != null || !getImageName().matches("") || !getImageName().matches("None")) {
            XWPFRun SubProcessImageL = px.createRun();
//        SubProcessImageL.setColor("c00000");
            SubProcessImageL.setText("► Image: ");
            SubProcessImageL.addBreak();

            XWPFRun SubProcessImage = px.createRun();
            InputStream pic = new FileInputStream("D:\\PD\\Images\\" + getImageName());
            SubProcessImage.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, getImageName(), Units.toEMU(200), Units.toEMU(200));
            SubProcessImage.addBreak();
        }

        List<Activity> Acs = new ArrayList<Activity>(getActivities());

        for (int i = 0; i < Acs.size(); i++) {
            Acs.get(i).PrintToTheDoc331(px);
        }

    }

}
