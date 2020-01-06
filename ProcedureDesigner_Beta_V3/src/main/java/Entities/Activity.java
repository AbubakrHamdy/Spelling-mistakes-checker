package Entities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static sample.Main.TT;
import static sample.Test_Data.db;

@Entity
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID = 58;
    @Column(columnDefinition = "VARCHAR(1024)")
    private String ShortDescription = "";
    @Column(columnDefinition = "VARCHAR(2048)")
    private String Description = "";
    private String Category = "";
    private String STD = "";
    public int level_num = 0;
    private int Duration_id = -1;
    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    private String ImageName = "";
//    @Nullable
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "Activity_Process", joinColumns = @JoinColumn(name = "ActivityID"),
//            inverseJoinColumns = @JoinColumn(name = "ProcessID"))
//    Set<Process> processes = new HashSet<Process>();


    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Activity_SubProcess", joinColumns = @JoinColumn(name = "ActivityID"),
            inverseJoinColumns = @JoinColumn(name = "SubProcessID"))
    Set<SubProcess> subProcesses = new HashSet<SubProcess>();
    private String language;

    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "Activity_Employee", joinColumns = @JoinColumn(name = "ActivityID"),
            inverseJoinColumns = @JoinColumn(name = "EmployeeID"))
    Set<Employee> Employees = new HashSet<Employee>();


    @Nullable
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "Activity_Risk", joinColumns = @JoinColumn(name = "ActivityID"),
            inverseJoinColumns = @JoinColumn(name = "RiskID"))
    Set<Risk> risks = new HashSet<Risk>();



    public Activity(int ID, String shortDescription, String description, String category, String STD) {
        this.ID = ID;
        ShortDescription = shortDescription;
        Description = description;
        Category = category;
        this.STD = STD;
        language = "EN";

    }

    public Activity(String shortDescription, String description, String category, String STD) {
        ShortDescription = shortDescription;
        Description = description;
        Category = category;
        this.STD = STD;
        language = "EN";

    }

    public Activity(Activity a) {
//        for (int i = 0; i < a.Responsible.size(); i++) {
//        Employee em = new Employee(a.Responsible.get(i));
//          this.Responsible.add(em);
//        }
//        this.Duration_id=new Integer(a.Duration_id);
        this.language=new String(a.language);
        this.ShortDescription = new String(a.ShortDescription);
        this.Description = new String(a.Description);
        this.ID = new Integer(a.ID);
        this.Category= new String(a.Category);
        this.level_num = new Integer(a.level_num);
        this.STD = new String(a.STD);
        if (a.ImageName==null){
            this.ImageName="";
        }
        else {
            this.ImageName=new String(a.ImageName);
        }
        //        this.OutputName = new String(a.OutputName);
    }
    public Activity(String name, int id, String description, int levelnum) {
        this.ShortDescription = name;
        this.ID = id;
        this.Description = description;
        this.level_num = levelnum;
        language = "EN";

    }
    public Activity() {
        language = "EN";
    }
    public int getDuration_id() {
        return Duration_id;
    }

    public void setDuration_id(int duration_id) {
        Duration_id = duration_id;
    }
    public Set<Employee> getEmployees() {
        return Employees;
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSTD() {
        return STD;
    }

    public void setSTD(String STD) {
        this.STD = STD;
    }

//    public Set<Process> getProcesses() {
//        return processes;
//    }
//
//    public void setProcesses(Set<Process> processes) {
//        this.processes = processes;
//    }

    public Set<SubProcess> getSubProcesses() {

        return subProcesses;
//    return null;
    }

    public Set<Risk> getRisks() {
        return risks;
    }

    public void setSubProcesses(Set<SubProcess> subProcesses) {
        this.subProcesses = subProcesses;
    }



    public void ClearChildern() {
        this.subProcesses.clear();
//        this.processes.clear();
        this.risks.clear();
    }

    public void Get_Info(String x) {
        if (subProcesses.size() > 0) {
            List<SubProcess> subs = new ArrayList<SubProcess>(subProcesses);
//            System.out.print(x + "- " + Name + "( Activity ) and assigned to [");
            for (int i = 0; i < subs.size(); i++) {
                if (i == subs.size() - 1) {
                    System.out.println(subs.get(i).getShortDescription() + " ]");
                } else {
                    System.out.print(subs.get(i).getShortDescription() + " - ");
                }
            }
        } else {
//            System.out.println(x + "- " + Name + "( Activity )");
        }
    }

    public int PrintToTheDoc(XWPFRun r, int c) {
        System.out.println("- " + getShortDescription() + " Ac sizeee" + c);
        r.setText("- " + getShortDescription(), ++c);
        r.addCarriageReturn();
        r.setText("•• " + getDescription(), ++c);
        r.addCarriageReturn();
        r.setText("Duration: " + getDuration_id(), ++c);
        r.addCarriageReturn();
        return c;
    }

    public String PrintToTheDoc3(XWPFRun r) {
        String ss = "";
        ss = ss + "► Activity: " + getShortDescription().replace("\n", "") + '\n';
        ss = ss + "• Description: " + getDescription().replace("\n", "") + '\n';
        String Owner = "it has no owner";
        for (int i = 0; i < getEmployees().size(); i++) {
            List<Employee> Es = new ArrayList<Employee>(getEmployees());
            if (Es.size() > 0) {
                Owner = Es.get(i).getRole();
            }
        }
        ss = ss + "► Owner: " + Owner + '\n';
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
        ss = ss + "► Risks: " + risk + '\n';

        return ss;
    }

    public String PrintToTheDoc33(XWPFRun r) {
        String ss = "";
        ss = ss + "► Activity: " + getShortDescription().replace("\n", "") + '\n';
        ss = ss + "• Description: " + getDescription().replace("\n", "") + '\n';
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
        ss = ss + "► Risks: " + risk + '\n';

        return ss;
    }

    public Activity GetItselfFromDB() {
        for (int i = 0; i < TT.ActivitiesDBList.size(); i++) {
            if (TT.ActivitiesDBList.get(i).getID() == getID()) {
                return TT.ActivitiesDBList.get(i);
            }
        }
        return null;
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
                if (TT.RiskDBList.get(i).getId() == acs.get(j).getId()) {
                    result.add(TT.RiskDBList.get(i));
                    break;
                }
            }
        }
        return result;
    }

    public void UpdateDB() {
        Activity acs = GetItselfFromDB();
        List<Risk> rs = GetItsRisksFromDB();
        List<Employee> ems = GetItsEmployeesFromDB();
        for (int i = 0; i < rs.size(); i++) {
            if (!acs.getRisks().contains(rs.get(i))) {
                acs.getRisks().add(rs.get(i));
            }
        }
        for (int i = 0; i < ems.size(); i++) {
            if (!acs.getEmployees().contains(ems.get(i))) {
                acs.getEmployees().add(ems.get(i));
            }
        }
        db.update_activity(acs);
    }

    public Boolean CheckIfEmployeeAreadyInTheList(Employee em) {
        Activity acs = GetItselfFromDB();
        List<Employee> acsems = new ArrayList<Employee>(acs.getEmployees());

        for (int i = 0; i < acsems.size(); i++) {
            if (acsems.get(i).getId() == em.getId()) {
                return true;
            }
        }
        return false;
    }

    public Boolean CheckIfRiskAreadyInTheList(Risk ri) {
        Activity acs = GetItselfFromDB();
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

        Activity acs = GetItselfFromDB();
        List<Risk> rs = GetItsRisksFromDB();
        List<Employee> ems = GetItsEmployeesFromDB();
        for (int i = 0; i < rs.size(); i++) {
            if (!CheckIfRiskAreadyInTheList(rs.get(i))) {
                st.executeUpdate("INSERT INTO activity_risk (RiskID, ActivityID) "
                        + "VALUES (" + rs.get(i).getId() + "," + getID() + " )");
            }
        }
        for (int i = 0; i < ems.size(); i++) {
            if (!CheckIfEmployeeAreadyInTheList(ems.get(i))) {
                st.executeUpdate("INSERT INTO activity_employee (EmployeeID, ActivityID) "
                        + "VALUES (" + ems.get(i).getId() + "," + getID() + " )");
            }
        }
        PreparedStatement updateEXP = conn.prepareStatement("update activity set ImageName = ? where ID = ?");
        updateEXP.setString(1, getImageName());
        updateEXP.setInt(2,getID());
        updateEXP.execute();
        if(getDuration_id()!=-1){
//            st.executeUpdate("UPDATE subprocess SET Duration_id = "+getDuration_id()+" WHERE ID ="+getID());
            PreparedStatement updateEXP1 = conn.prepareStatement("update activity set Duration_id = ? where ID = ?");
            updateEXP1.setInt(1,getDuration_id());
            updateEXP1.setInt(2,getID());
            updateEXP1.execute();
        }
    }


    public void PrintToTheDoc31(XWPFParagraph px) throws IOException, InvalidFormatException {
        XWPFRun SubProcessL = px.createRun();
        SubProcessL.setBold(true);
        SubProcessL.setColor("2af7ba");
        SubProcessL.setText("► Activity: ");
//        SubProcessL.addBreak();

        XWPFRun SubProcessDescShort = px.createRun();
        SubProcessDescShort.setBold(true);
        SubProcessDescShort.setText(getShortDescription().replace("\n", ""));
        SubProcessDescShort.addBreak();

//        ss = ss  +  + '\n';

        XWPFRun SubProcessDescL = px.createRun();
//        SubProcessDescL.setBold(true);
        SubProcessDescL.setColor("2af7ba");
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

    }
    public void PrintToTheDoc331(XWPFParagraph px) throws IOException, InvalidFormatException {
        XWPFRun SubProcessL = px.createRun();
        SubProcessL.setBold(true);
        SubProcessL.setColor("2af7ba");
        SubProcessL.setText("► Activity: ");
//        SubProcessL.addBreak();

        XWPFRun SubProcessDescShort = px.createRun();
        SubProcessDescShort.setBold(true);
        SubProcessDescShort.setText(getShortDescription().replace("\n", ""));
        SubProcessDescShort.addBreak();

//        ss = ss  +  + '\n';

        XWPFRun SubProcessDescL = px.createRun();
//        SubProcessDescL.setBold(true);
        SubProcessDescL.setColor("2af7ba");
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
    }
}