package Roots.Root_1;

import Controller.DB;
import Entities.ProcedureFile;
import Entities.Process;
import Roots.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static Roots.Root_1.Main_Root_1.M_R_1;
import static Roots.Root_1.Root_1_Forms.*;
import static sample.GUI_Comp.*;
import static sample.Main.*;
import static sample.Main_Menus.*;
import static sample.Test_Data.db;

public class Modi_Group_Functions_Root_1 extends Modi_Group {

    public void Get_Roor_1_Scene() {
        Clear_All();
        EmployeeScene = false;
        fleMenue4.getItems().removeAll(fleMenue4.getItems());
        fleMenue4.getItems().add(GenerateOP);
        fleMenue4.getItems().add(GenerateDOCXCheckMenuItem);
        fleMenue4.getItems().add(GeneratePDFCheckMenuItem);
        FormsMenu.getItems().clear();
        Current_Root.getChildren().clear();
        Current_Root.getChildren().add(M_R_1);
        FormsMenu.getItems().add(Operating_Procedure_Form_Menu_Item);
        FormsMenu.getItems().add(New_SubProcess_Menu_Item);
        FormsMenu.getItems().add(ImageAndDuration_Menu_Item);

        WorkSpace.WorkSpace_With_Root_1();
        TablesMenu.getItems().clear();
        TablesMenu.getItems().add(ProcessTableMenu);
        TablesMenu.getItems().add(EmployeeTableMenu);
        TablesMenu.getItems().add(DurationTableMenu);
        System.out.println("you pressed new OP");
    }

    @Override
    public void close_Forms() {
        CloseVBoxForm(Process_Current_Form_Root);
        WorkSpace.WorkSpace_With_Closed_Form(Process_Current_Form_Root);
    }

    @Override
    public void SaveLabelToDB(Stage stage) {
        Vector<Process> New_Employees_With_Tasks = new Vector<Process>();
        DB db = new DB();
        String shortDesc;
        boolean AlreadyInDB = false;
        boolean error = false;
        for (int i = 0; i < Labels_vector.size(); i++) {
            if (Labels_vector.get(i) != null && Labels_vector.get(i).Type_id == 0) {
                shortDesc = PName.getText();
                ((ProcessLabel) Labels_vector.get(i)).MP.setMainProcessOwner(POwner.getText());
                ((ProcessLabel) Labels_vector.get(i)).MP.setDescription(PDescription.getText());
                ((ProcessLabel) Labels_vector.get(i)).MP.setShortDescription(PShortD.getText());
                ((ProcessLabel) Labels_vector.get(i)).MP.setVersion(PVersion.getText());
                ((ProcessLabel) Labels_vector.get(i)).MP.setPName(PName.getText());
                New_Employees_With_Tasks.add(((ProcessLabel) Labels_vector.get(i)).MP);
                List<Process> processes = new ArrayList<Process>(db.getALlProcess());

                for (int j = 0; j < processes.size(); j++) {
                    System.out.println(processes.get(j).getShortDescription() + " " + shortDesc);
                    if (processes.get(j).getPName().equals(shortDesc)) {
                        AlreadyInDB = true;
                        processes.get(j).setDescription(((ProcessLabel) Labels_vector.get(i)).MP.getDescription());
                        processes.get(j).setShortDescription(((ProcessLabel) Labels_vector.get(i)).MP.getShortDescription());
                        processes.get(j).setMainProcessOwner(((ProcessLabel) Labels_vector.get(i)).MP.getMainProcessOwner());
                        processes.get(j).setVersion(((ProcessLabel) Labels_vector.get(i)).MP.getVersion());
                        processes.get(j).setPName(((ProcessLabel) Labels_vector.get(i)).MP.getPName());
                        error = ((ProcessLabel) Labels_vector.get(i)).MP.getShortDescription().isEmpty();
                        CBF.SaveLabelToDBUpdateConfirmBoxMP(error);
                        if (!error) {
                            Labels_vector.get(i).setText(PName.getText());
                            checkButton.setVisible(false);
                            db.update_process(processes.get(j));
                        }
                    }
                }
                if (AlreadyInDB == false) {
                    error = ((ProcessLabel) Labels_vector.get(i)).MP.getShortDescription().isEmpty();
                    CBF.SaveLabelToDBADDConfirmBoxMP(error);
                    if (!error) {
                        checkButton.setVisible(false);
                        Labels_vector.get(i).setText(PName.getText());
                        db.addProcess(((ProcessLabel) Labels_vector.get(i)).MP);
                    }
//                    break;
                }
            } else if (Labels_vector.get(i) != null && Labels_vector.get(i) instanceof SubProcessLabel) {
                System.out.println("subprocess is updated id = "+((SubProcessLabel) Labels_vector.get(i)).SP.getID());
                try {
                    ((SubProcessLabel) Labels_vector.get(i)).SP.UpdateDB1();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                db.update_subProcess(((SubProcessLabel) Labels_vector.get(i)).SP);
            } else if (Labels_vector.get(i) != null && Labels_vector.get(i) instanceof ActivityLabel) {
                try {
                    ((ActivityLabel) Labels_vector.get(i)).AC.UpdateDB1();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //                System.out.println("Activity is updated id = "+((ActivityLabel) Labels_vector.get(i)).AC.getID()+"and "
//                +((ActivityLabel) Labels_vector.get(i)).AC.getCategory()+"sus size= "+((ActivityLabel) Labels_vector.get(i)).AC.getSubProcesses());
//                for(int j=0;j<TT.ActivitiesDBList.size();j++){
//                    if(TT.ActivitiesDBList.get(j).getID()==((ActivityLabel) Labels_vector.get(i)).AC.getID()){
//                        List<SubProcess> subs=new ArrayList<SubProcess>(((ActivityLabel) Labels_vector.get(i)).AC.getSubProcesses());
//                        for(int c=0;c<subs.size();c++){
//                            System.out.println("subprocess is add id = "+(subs.get(c).getID()));
//                            if(!TT.ActivitiesDBList.get(j).getSubProcesses().contains(subs.get(c))){
//                                TT.ActivitiesDBList.get(j).getSubProcesses().add(TT.SubProcessDBList.get(1));
//                            }
//                        }
//                        db.update_activity(TT.ActivitiesDBList.get(j));
//                        break;
//                    }
//                }
            }
        }
        if (error == false) {
            if (!addressoben.equalsIgnoreCase("")) {
                SaveToFile(stage, addressoben);
                Save_File_To_DB(addressoben);
            } else if (!addresssave.equalsIgnoreCase("")) {
                SaveToFile(stage, addresssave);
                Save_File_To_DB(addresssave);
            } else {
                String path = "";
                FileChooser fc1 = new FileChooser();
                fc1.setTitle("Save");
                fc1.getExtensionFilters().add(new FileChooser.ExtensionFilter("OPD File", "*.opd"));
                fc1.setInitialFileName(PName.getText());
                File file = fc1.showSaveDialog(stage);
                path = file.getPath();

                addresssave = SaveToFile(stage, path);
                Save_File_To_DB(addresssave);
            }
            picName = GenerateScreenShot();
        }
        Reconnect();
    }

    @Override
    public String SaveToFile(Stage primaryStage, String path) {
        char flag = 'p';
        try {
            FileOutputStream f = new FileOutputStream(path);
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
                        Labels_vector.get(i).Name = PName.getText();
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
            o.writeObject(PDescription.getText());
            o.writeObject(PShortD.getText());
            o.writeObject(POwner.getText());
            o.writeObject(PVersion.getText());
            o.writeObject(PName.getText());
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
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

        ProcedureFile f = new ProcedureFile();
        f.setFiles(bFile);
        f.setName(name);
        f.setPath(path);
//        f.setEmployee(emp);
        int y = 0;
        List<ProcedureFile> allFiles = new ArrayList<>(db.getAllFiles0());
        for (int i = 0; i < allFiles.size(); i++) {
            if (allFiles.get(i).getName().equals(f.getName())) {
                System.out.println("filee" + f.getName());
                f.setIdfiles(allFiles.get(i).getIdfiles());
                db.update_file0(f);
                y = 0;
                break;
            } else {
                y = 1;
            }
        }

        if (y == 1 || allFiles.size() == 0) {
            db.AddFile0(f);
        }
    }

    @Override
    public String OpenFile(Stage primaryStage, FileInputStream fi, ObjectInputStream oi) {
        String bath = "";
        try {
            Get_Roor_1_Scene();
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
                    } else if (LS.Type.matches("Duration")) {
                        label = new DurationLabel(LS);
                        Labels_vector.add(label);
                    } else if (LS.Type.matches("MainProcess")) {
                        label = new ProcessLabel(LS);
                        Labels_vector.add(label);
                    } else if (LS.Type.matches("SubProcess")) {
                        label = new SubProcessLabel(LS);
                        Labels_vector.add(label);
                    } else if (LS.Type.matches("Activity")) {
                        label = new ActivityLabel(LS);
                        Labels_vector.add(label);
                    } else if (LS.Type.matches("Risk")) {
                        label = new RiskLabel(LS);
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
                    Labels_vector.get(line.E_Index).CheckIFThe2LabelsCanBeConnectedAndConncetThem();
                    start = -1;
                    line.Add_Style(Labels_vector, Lines_vector);
                } else {
                    General_Line Nul = null;
                    Lines_vector.add(Nul);
                }
            }
            PDescription.replaceText(oi.readObject().toString());
            PShortD.setText((oi.readObject().toString()));
            POwner.setText((oi.readObject().toString()));
            PVersion.setText(oi.readObject().toString());
            PName.setText(oi.readObject().toString());
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