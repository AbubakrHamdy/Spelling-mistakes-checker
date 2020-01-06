package Roots.Root_2;

import Controller.DB;
import Entities.Employee;
import Entities.JobDescriptionFile;
import Entities.ProcedureFile;
import Roots.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Roots.Root_2.Main_Root_2.M_R_2;
import static Roots.Root_2.Root_2_Forms.*;
import static sample.GUI_Comp.*;
import static sample.Main.*;
import static sample.Main_Menus.*;
import static sample.Test_Data.db;

public class Modi_Group_Functions_Root_2 extends Modi_Group {
    @Override
    public void close_Forms() {
        CloseVBoxForm(Current_JD_Form_Root);
        WorkSpace.WorkSpace_With_Closed_Form(Current_JD_Form_Root);
    }

    public void Get_Roor_2_Scene() {
        Clear_All();
        EmployeeScene = true;
        fleMenue4.getItems().removeAll(fleMenue4.getItems());
        fleMenue4.getItems().add(GenerateJD);
        fleMenue4.getItems().add(GenerateDOCXCheckMenuItem);
        fleMenue4.getItems().add(GeneratePDFCheckMenuItem);
        FormsMenu.getItems().clear();
        Current_Root.getChildren().clear();
        Current_Root.getChildren().add(M_R_2);
        WorkSpace.WorkSpace_With_Root_2();
        FormsMenu.getItems().add(JobDescription_Form_Menu_Item);
        FormsMenu.getItems().add(Task_Form_Menu_Item);
        TablesMenu.getItems().clear();
        TablesMenu.getItems().add(TasksTableMenu);
        System.out.println("you pressed new JD");
    }

    @Override
    public void SaveLabelToDB(Stage stage) {
        Employee emp;
        DB db = new DB();
        String role;
        boolean error = false;

        for (int i = 0; i < Labels_vector.size(); i++) {

            boolean flag = false;
            error = false;
            if (Labels_vector.get(i) != null && Labels_vector.get(i).Type_id == 1) {
                role = JobTitle.getText();
                ((EmployeeLabel) Labels_vector.get(i)).EM.setRole(JobTitle.getText());
                if (Experience.getValue() != null)
                    ((EmployeeLabel) Labels_vector.get(i)).EM.setExperience(Experience.getValue().toString());
                if (JobLocation.getValue() != null)
                    ((EmployeeLabel) Labels_vector.get(i)).EM.setJobLocation(JobLocation.getValue().toString());
                if (Languages.getValue() != null)
                    ((EmployeeLabel) Labels_vector.get(i)).EM.setLanguages(Languages.getValue().toString());
                if (JobType.getValue() != null)
                    ((EmployeeLabel) Labels_vector.get(i)).EM.setJobType(JobType.getValue().toString());
                ((EmployeeLabel) Labels_vector.get(i)).EM.setManager(JobManager.getText());
                ((EmployeeLabel) Labels_vector.get(i)).EM.setSkills(Skills.getText());
                ((EmployeeLabel) Labels_vector.get(i)).EM.setQualification(JobQualification.getText());

                List<Employee> employees = new ArrayList<>(db.getALlEmployees());

                for (int j = 0; j < employees.size(); j++) {
                    if (employees.get(j).getRole().equals(role)) {
                        flag = true;
                        employees.get(j).setExperience(((EmployeeLabel) Labels_vector.get(i)).EM.getExperience());
                        employees.get(j).setQualification(((EmployeeLabel) Labels_vector.get(i)).EM.getQualification());
                        employees.get(j).setSkills(((EmployeeLabel) Labels_vector.get(i)).EM.getSkills());
                        employees.get(j).setManager(((EmployeeLabel) Labels_vector.get(i)).EM.getManager());
                        employees.get(j).setJobType(((EmployeeLabel) Labels_vector.get(i)).EM.getJobType());
                        employees.get(j).setLanguages(((EmployeeLabel) Labels_vector.get(i)).EM.getLanguages());
                        employees.get(j).setJobLocation(((EmployeeLabel) Labels_vector.get(i)).EM.getJobLocation());
                        employees.get(j).setRole(((EmployeeLabel) Labels_vector.get(i)).EM.getRole());
                        System.out.println(employees.get(j).getName() + " " + employees.get(j).getExperience());

                        error = ((EmployeeLabel) Labels_vector.get(i)).EM.getRole().isEmpty();
                        CBF.SaveLabelToDBUpdateConfirmBoxEM(error);
                        if (!error) {
                            db.update_employee(employees.get(j));
                            checkButton.setVisible(false);
                            Labels_vector.get(i).setText(JobTitle.getText());
                        }
                        break;
                    }
                }
                if (flag == false) {
                    error = ((EmployeeLabel) Labels_vector.get(i)).EM.getRole().isEmpty();
                    CBF.SaveLabelToDBADDConfirmBoxEM(error);
                    if (!error) {
                        db.addEmployee(((EmployeeLabel) Labels_vector.get(i)).EM);
                        checkButton.setVisible(false);
                        Labels_vector.get(i).setText(JobTitle.getText());
                    }
                    break;
                }
            }
            Reconnect();
        }
        if (error == false) {
            //if the file is open so it will save on the open address
            if (!addressoben.equalsIgnoreCase("")) {
                SaveToFile(stage, addressoben);
                Save_File_To_DB(addressoben);
            }
            //if the file is open so it will save on the open address
            else if (!addresssave.equalsIgnoreCase("")) {
                SaveToFile(stage, addresssave);
                Save_File_To_DB(addresssave);
            }
            //if the file is new so it will make address
            else {
                String path = "";
                FileChooser fc1 = new FileChooser();
                fc1.setTitle("Save");
                fc1.getExtensionFilters().add(new FileChooser.ExtensionFilter("JD File", "*.jd"));
                fc1.setInitialFileName(JobTitle.getText());
                File file = fc1.showSaveDialog(stage);
                path = file.getPath();

                addresssave = SaveToFile(stage, path);
                Save_File_To_DB(addresssave);
            }
            picName = GenerateScreenShot();
        }
    }

    @Override
    public String SaveToFile(Stage primaryStage, String Path) {
        char flag = 'j';
        try {
            FileOutputStream f = new FileOutputStream(Path);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeChar(flag);
            // Write objects to file
            int c = 0;
            o.write(Labels_vector.size());
            for (int i = 0; i < Labels_vector.size(); i++) {
                if (Labels_vector.get(i) == null) {
                    LabelStorage TemLabel = new LabelStorage(false);
                    o.writeObject(TemLabel);
                    o.writeDouble(-1);
                    o.writeDouble(-1);
                } else {
                    if (c == 0) {
                        Labels_vector.get(i).Name = JobTitle.getText();
                        c++;
                    } else {
                        Labels_vector.get(i).Name = Labels_vector.get(i).getText();
                    }
                    LabelStorage LS = new LabelStorage(Labels_vector.get(i));
                    o.writeObject(LS);
                    o.writeDouble(LS.getTranslateX() + LS.layoutXProperty().getValue());
                    o.writeDouble(LS.getTranslateY() + LS.layoutYProperty().getValue());
                }
            }
            o.write(Lines_vector.size());
            for (int i = 0; i < Lines_vector.size(); i++) {
                if (Lines_vector.get(i) == null) {
                    General_Line TemLine = new General_Line(false);
                    o.writeObject(TemLine);
                } else {
                    o.writeObject(Lines_vector.get(i));
                }
            }
            o.writeObject(JobTitle.getText());
            o.writeObject(JobType.getEditor().getText());
            o.writeObject(JobLocation.getEditor().getText());
            o.writeObject(JobManager.getText());
            o.writeObject(JobQualification.getText());
            o.writeObject(Experience.getEditor().getText());
            o.writeObject(Skills.getText());
            o.writeObject(Languages.getEditor().getText());
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Path;
    }

    @Override
    public void Save_File_To_DB(String p) {
        String path = p;
        File f1 = new File(path);
        String name = f1.getName();
        byte[] bFile = new byte[(int) f1.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(f1);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception error) {
            error.printStackTrace();
        }

        JobDescriptionFile f = new JobDescriptionFile();
        f.setFiles(bFile);
        f.setName(name);
        f.setPath(path);
        int y = 0;
        List<ProcedureFile> allFiles = new ArrayList<>(db.getAllFiles0());
        for (int i = 0; i < allFiles.size(); i++) {
            if (allFiles.get(i).getName().equals(f.getName())) {
                System.out.println("filee" + f.getName());
                f.setIdfiles(allFiles.get(i).getIdfiles());
                db.update_file1(f);
                y = 0;
                break;
            } else {
                y = 1;
            }
        }

        if (y == 1 || allFiles.size() == 0) {
            db.AddFile1(f);
        }

    }

    @Override
    public String OpenFile(Stage primaryStage, FileInputStream fi, ObjectInputStream oi) {
        String bath = "";
        try {
            Get_Roor_2_Scene();
            int LabelsSize = oi.read();
            while (Labels_vector.size() != LabelsSize) {
                LabelStorage LS = (LabelStorage) oi.readObject();
                LS.setTranslateX(oi.readDouble());
                LS.setTranslateY(oi.readDouble());
                LS.setText(LS.Name);
                if (LS.Exist == true) {
                    General_Label label;
                    if (LS.Type.matches("Employee")) {
                        label = new EmployeeLabel(LS);
                        Labels_vector.add(label);
                    } else if (LS.Type.matches("Task")) {
                        label = new TaskLabel(LS);
                        Labels_vector.add(label);
                    }
                } else {
                    General_Label Nul = null;
                    Labels_vector.add(Nul);
                }
            }
            System.out.println("Lb: " + Labels_vector.size());
            int LinesSize = oi.read();
            while (Lines_vector.size() != LinesSize) {
                General_Line line = (General_Line) oi.readObject();
                if (line.CanBeConnceted == true) {
                    start = line.S_Index;
                    line.Add_Style(Labels_vector, Lines_vector);
                    Labels_vector.get(line.E_Index).CheckIFThe2LabelsCanBeConnectedAndConncetThem();
                    start = -1;
                } else {
                    General_Line Nul = null;
                    Lines_vector.add(Nul);
                }
            }

            JobTitle.setText(oi.readObject().toString());
            JobType.getEditor().setText(oi.readObject().toString());
            JobLocation.getEditor().setText(oi.readObject().toString());
            JobManager.setText(oi.readObject().toString());
            JobQualification.setText(oi.readObject().toString());
            Experience.getEditor().setText(oi.readObject().toString());
            Skills.setText(oi.readObject().toString());
            Languages.getEditor().setText(oi.readObject().toString());

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bath;
    }

}
