package sample;

import Entities.Employee;
import Entities.SubProcess;
import Entities.Task;
import Roots.EmployeeLabel;
import Roots.General_Label;
import Roots.Modi_Group;
import Roots.Root_1.ProcessLabel;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Roots.Modi_Group.CloseVBoxTable;
import static Roots.Modi_Group.OpenVBox;
import static Roots.Root_1.Main_Root_1.F1;
import static Roots.Root_1.Main_Root_1.M_R_1;
import static Roots.Root_1.Root_1_Forms.*;
import static Roots.Root_1.Root_1_Tables.*;
import static Roots.Root_2.Main_Root_2.M_R_2;
import static Roots.Root_2.Root_2_Forms.*;
import static Roots.Root_2.Root_2_Tables.TasksTable;
import static sample.GUI_Comp.*;
import static sample.Main.*;
import static sample.Main_Shortcut_Buttons.Setup_Main_Shortcut_Buttons_To_Arabic;
import static sample.Main_Shortcut_Buttons.Setup_Main_Shortcut_Buttons_To_English;

public class Main_Menus {

    public static Menu fleMenue = new Menu("File");
    public static Menu New = new Menu("New");
    public static MenuItem newob = new MenuItem("OP");
    public static MenuItem newjb = new MenuItem("JD");
    public static Menu RecentOpenn = new Menu("RecentOpen");
    public static MenuItem savee = new MenuItem("Save");
    public static MenuItem saveeAs = new MenuItem("Save as");
    public static MenuItem openn = new MenuItem("Open");
    public static MenuItem close = new MenuItem("Close");
    public static MenuItem exit = new MenuItem("Exit");

    public static Menu fleMenue1 = new Menu("Edit");
    public static MenuItem Undo = new MenuItem("Undo Ctrl+Z");
    public static MenuItem Redo = new MenuItem("Redo Ctrl+X");


    public static MenuItem print = new MenuItem("Print");

    public static Menu fleMenue2 = new Menu("Action");
    public static MenuItem Delete = new MenuItem("Delete Alt");
    public static MenuItem Link = new MenuItem("Link Shift");

    public static Menu fleMenue3 = new Menu("View");
    public static MenuItem zoomInMenuItem = new MenuItem("Zoom In");
    public static MenuItem zoomOutMenuItem = new MenuItem("Zoom Out");

    public static Menu fleMenue4 = new Menu("Generate");
    public static MenuItem GeneratePdf = new MenuItem("Generate PDF");
    public static CheckBox GeneratePDFCheckBoxItem = new CheckBox("Generate PDF");
    public static CheckBox GenerateDOCXCheckBoxItem = new CheckBox("Generate DOCX");
    public static CustomMenuItem GeneratePDFCheckMenuItem = new CustomMenuItem(GenerateDOCXCheckBoxItem);
    public static CustomMenuItem GenerateDOCXCheckMenuItem = new CustomMenuItem(GeneratePDFCheckBoxItem);
    public static MenuItem GenerateOP = new MenuItem("Generate OP");
    public static MenuItem GenerateJD = new MenuItem("Generate JD");
    public static MenuItem Load_Template_Menu_Item = new MenuItem("Load Template");

    public static Menu fleMenue5 = new Menu("Help");
    public static MenuItem Help = new MenuItem("Open Help file");

    public static Menu language = new Menu("Language");
    public static MenuItem arabic = new MenuItem("Arabic");
    public static MenuItem english = new MenuItem("English");

    public static Menu FormsMenu = new Menu("Forms");

    public static MenuItem JobDescription_Form_Menu_Item = new MenuItem("Job Description Form");
    public static MenuItem Task_Form_Menu_Item = new MenuItem("Task Form");

    public static MenuItem Operating_Procedure_Form_Menu_Item = new MenuItem("Operating Procedure Form");
    public static MenuItem New_SubProcess_Menu_Item = new MenuItem("New SubProcess Form");
    public static MenuItem ImageAndDuration_Menu_Item = new MenuItem("Image And Duration Form");


    public static MenuItem Close_Form_Menu_Item = new MenuItem("Close The Form");

    public static Menu ReportsMenu = new Menu("Reports");
    public static MenuItem GenerateJopDescriptionReport = new MenuItem("Generate Repeated Tasks Report");
    public static MenuItem GenerateSubProcessReport = new MenuItem("Generate Repeated SubProcess Report");

    public static Menu TablesMenu = new Menu("Tables");
    public static CheckBox TasksTableMenuCheckBox = new CheckBox("TasksTable");
    public static CustomMenuItem TasksTableMenu = new CustomMenuItem(TasksTableMenuCheckBox);
    public static CheckBox ProcessTableMenuCheckBox = new CheckBox("ProcessTable");
    public static CustomMenuItem ProcessTableMenu = new CustomMenuItem(ProcessTableMenuCheckBox);
    public static CheckBox DurationTableMenuCheckBox = new CheckBox("DurationTable");
    public static CustomMenuItem DurationTableMenu = new CustomMenuItem(DurationTableMenuCheckBox);
    public static CheckBox EmployeeTableMenuCheckBox = new CheckBox("EmployeeTable");
    public static CustomMenuItem EmployeeTableMenu = new CustomMenuItem(EmployeeTableMenuCheckBox);

    public static MenuBar menuBar;

    Main_Menus(Stage stage) {
        menuBar = new MenuBar();
        fleMenue.getItems().clear();
        fleMenue1.getItems().clear();
        fleMenue2.getItems().clear();
        fleMenue3.getItems().clear();
        fleMenue4.getItems().clear();
        fleMenue5.getItems().clear();
        language.getItems().clear();
        New.getItems().clear();

        language.getItems().addAll(arabic, english);
        fleMenue.getItems().add(New);
        New.getItems().add(newjb);
        New.getItems().add(newob);
        fleMenue.getItems().add(savee);
        fleMenue.getItems().add(openn);
//        fleMenue.getItems().add(RecentOpenn);
        fleMenue.getItems().add(saveeAs);
        fleMenue.getItems().add(close);
        fleMenue.getItems().add(exit);

        fleMenue1.getItems().add(Undo);
        fleMenue1.getItems().add(Redo);

        fleMenue2.getItems().add(Delete);
        fleMenue2.getItems().add(Link);

        fleMenue3.getItems().add(zoomInMenuItem);
        fleMenue3.getItems().add(zoomOutMenuItem);
        GenerateDOCXCheckMenuItem.setHideOnClick(false);
        GeneratePDFCheckMenuItem.setHideOnClick(false);
        fleMenue4.getItems().add(GenerateDOCXCheckMenuItem);
        fleMenue4.getItems().add(GeneratePDFCheckMenuItem);

        fleMenue5.getItems().add(Help);
        fleMenue5.getItems().add(print);
        ReportsMenu.getItems().add(GenerateJopDescriptionReport);
        ReportsMenu.getItems().add(GenerateSubProcessReport);

//        GenerateDOCXCheckMenuItem.
        GenerateDOCXCheckBoxItem.setSelected(true);
        TasksTableMenuCheckBox.setSelected(true);
        ProcessTableMenuCheckBox.setSelected(true);
        EmployeeTableMenuCheckBox.setSelected(true);
        DurationTableMenuCheckBox.setSelected(true);
        menuBar.setMinWidth(3000);

        String button_web = "http://fxexperience.com/2011/12/styling-fx-buttons-with-css/";
//        52c2fa
        Set_Menu_Style();
        menuBar.getMenus().addAll(fleMenue, fleMenue1, fleMenue2, fleMenue3, fleMenue4, language, FormsMenu, TablesMenu, ReportsMenu, fleMenue5);
        menuBar.setStyle("-fx-background-color: #aadffa;-fx-border-width: 1.5;" + "-fx-border-color: #0b7eb8;");
        Set_Action_To_Menu(stage);

    }

    public void Set_Action_To_Menu(Stage stage) {

        TasksTableMenuCheckBox.setOnAction(e -> {
            if (TasksTableMenuCheckBox.isSelected()) {
                OpenVBox(TasksTable);
            } else {
                CloseVBoxTable(TasksTable);
            }
        });

        ProcessTableMenuCheckBox.setOnAction(e -> {
            if (ProcessTableMenuCheckBox.isSelected()) {
                OpenVBox(ProcessTable);
            } else {
                CloseVBoxTable(ProcessTable);
            }
        });

        EmployeeTableMenuCheckBox.setOnAction(e -> {
            if (EmployeeTableMenuCheckBox.isSelected()) {
                OpenVBox(EmployeesTable);
            } else {
                CloseVBoxTable(EmployeesTable);
            }
        });

        DurationTableMenuCheckBox.setOnAction(e -> {
            if (DurationTableMenuCheckBox.isSelected()) {
                OpenVBox(DurationTable);
            } else {
                CloseVBoxTable(DurationTable);
            }
        });

        JobDescription_Form_Menu_Item.setOnAction(e -> {
            Current_JD_Form_Root.getChildren().clear();
            Current_JD_Form_Root.getChildren().add(Employee_Form_Root);
            if (!Current_JD_Form_Root.isVisible()) {
                Timeline timeline = new Timeline();
                Current_JD_Form_Root.setVisible(true);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(Current_JD_Form_Root.prefWidthProperty(), 0)
                        ),
                        new KeyFrame(Duration.millis(500.0d),
                                new KeyValue(Current_JD_Form_Root.prefWidthProperty(), 300)
                        )
                );
                timeline.play();
                timeline.setOnFinished((evt) -> {
                    FormsMenu.getItems().add(Close_Form_Menu_Item);
                    WorkSpace.WorkSpace_With_opened_Form(Current_JD_Form_Root);
                });
            }
        });

        Task_Form_Menu_Item.setOnAction(e -> {
            Current_JD_Form_Root.getChildren().clear();
            Current_JD_Form_Root.getChildren().add(Task_Form_Root);
            if (!Current_JD_Form_Root.isVisible()) {
                Timeline timeline = new Timeline();
                Current_JD_Form_Root.setVisible(true);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(Current_JD_Form_Root.prefWidthProperty(), 0)
                        ),
                        new KeyFrame(Duration.millis(500.0d),
                                new KeyValue(Current_JD_Form_Root.prefWidthProperty(), 300)
                        )
                );
                timeline.play();
                timeline.setOnFinished((evt) -> {
                    FormsMenu.getItems().add(Close_Form_Menu_Item);
                    WorkSpace.WorkSpace_With_opened_Form(Current_JD_Form_Root);
                });
            }
        });

        New_SubProcess_Menu_Item.setOnAction(e -> {
            Process_Current_Form_Root.getChildren().clear();
            Process_Current_Form_Root.getChildren().add(SubProcess_Form_Root);
            if (!Process_Current_Form_Root.isVisible()) {
                Timeline timeline = new Timeline();
                Process_Current_Form_Root.setVisible(true);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(Process_Current_Form_Root.prefWidthProperty(), 0)
                        ),
                        new KeyFrame(Duration.millis(500.0d),
                                new KeyValue(Process_Current_Form_Root.prefWidthProperty(), 300)
                        )
                );
                timeline.play();
                timeline.setOnFinished((evt) -> {
                    FormsMenu.getItems().add(Close_Form_Menu_Item);
                    WorkSpace.WorkSpace_With_opened_Form(Process_Current_Form_Root);
                });
            }
        });

        Operating_Procedure_Form_Menu_Item.setOnAction(e -> {
            Process_Current_Form_Root.getChildren().clear();
            Process_Current_Form_Root.getChildren().add(Process_Form_Root);
            if (!Process_Current_Form_Root.isVisible()) {
                Timeline timeline = new Timeline();
                Process_Current_Form_Root.setVisible(true);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(Process_Current_Form_Root.prefWidthProperty(), 0)
                        ),
                        new KeyFrame(Duration.millis(500.0d),
                                new KeyValue(Process_Current_Form_Root.prefWidthProperty(), 300)
                        )
                );
                timeline.play();
                timeline.setOnFinished((evt) -> {
                    FormsMenu.getItems().add(Close_Form_Menu_Item);
                });
            }
            WorkSpace.WorkSpace_With_opened_Form(Process_Form_Root);
        });

        ImageAndDuration_Menu_Item.setOnAction(e -> {
            ImageAndDuration_Form_Root.getChildren().remove(1);
            F1.Setup_ImageAndDuration_Form_Body();
            Process_Current_Form_Root.getChildren().clear();
            Process_Current_Form_Root.getChildren().add(ImageAndDuration_Form_Root);
            if (!Process_Current_Form_Root.isVisible()) {
                Timeline timeline = new Timeline();
                Process_Current_Form_Root.setVisible(true);
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(Process_Current_Form_Root.prefWidthProperty(), 0)
                        ),
                        new KeyFrame(Duration.millis(500.0d),
                                new KeyValue(Process_Current_Form_Root.prefWidthProperty(), 300)
                        )
                );
                timeline.play();
                timeline.setOnFinished((evt) -> {
                    FormsMenu.getItems().add(Close_Form_Menu_Item);
                });
            }
            WorkSpace.WorkSpace_With_opened_Form(ImageAndDuration_Form_Root);
        });

        Close_Form_Menu_Item.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).close_Forms();
                }
            }
            FormsMenu.getItems().remove(Close_Form_Menu_Item);
        });

        GenerateJopDescriptionReport.setOnAction(e -> {
            FileChooser fcWord = new FileChooser();
            fcWord.getExtensionFilters().add(new FileChooser.ExtensionFilter("DOCX File", "*.docx"));
            fcWord.setTitle("Save to word");
            fcWord.setInitialFileName(java.time.LocalDate.now() + " JD Report" + ".docx");
            File file = fcWord.showSaveDialog(stage);

            try {
                XWPFDocument document = new XWPFDocument();
                FileOutputStream out = new FileOutputStream(new File(file.getAbsolutePath()));
                XWPFParagraph paragraph = document.createParagraph();

                System.out.println("Report written successfully");

                for (int i = 0; i < TT.TasksDBList.size(); i++) {
                    List<Employee> TEs = new ArrayList<Employee>(TT.TasksDBList.get(i).getEmployees());

                    if (TEs.size() > 1) {
                        String TheTask = "Task: ";
                        String TaskName = TT.TasksDBList.get(i).getShortDesc();
                        String IsDoneBy = " is done by: ";

                        XWPFRun paragraphTheTask = paragraph.createRun();
                        paragraphTheTask.setText(TheTask);
                        paragraphTheTask.setColor("bdb700");

                        XWPFRun paragraphTaskName = paragraph.createRun();
                        paragraphTaskName.setBold(true);
                        paragraphTaskName.setText(TaskName);

                        XWPFRun paragraphOneIsDoneBy = paragraph.createRun();
                        paragraphOneIsDoneBy.setText(IsDoneBy);
                        paragraphOneIsDoneBy.addBreak();
//                        String FirstLine = TheTask + TaskName + IsDoneBy;
                        String FirstLine = TheTask + IsDoneBy;
                        int SpaceSize = FirstLine.length();
                        String Spaces = "";

                        for (int c = 0; c < SpaceSize; c++) {
                            Spaces = Spaces + " ";
                        }

                        for (int j = 0; j < TEs.size(); j++) {

                            String PreString = Spaces + "• ";
                            XWPFRun paragraphEmployee = paragraph.createRun();
                            paragraphEmployee.setBold(true);
                            paragraphEmployee.setText(PreString + TEs.get(j).getRole());
                            paragraphEmployee.addBreak();

                        }
                        XWPFRun paragraphBreak = paragraph.createRun();
                        paragraphBreak.setBold(true);
                        paragraphBreak.addBreak();
                    }
                }
                document.write(out);
                out.close();
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) desktop.open(file);
                Reconnect();
            } catch (Exception E) {
            }

        });

        GenerateSubProcessReport.setOnAction(e -> {
            FileChooser fcWord = new FileChooser();
            fcWord.getExtensionFilters().add(new FileChooser.ExtensionFilter("DOCX File", "*.docx"));
            fcWord.setTitle("Save to word");
            fcWord.setInitialFileName(java.time.LocalDate.now() + " SubProcess Report" + ".docx");
            File file = fcWord.showSaveDialog(stage);

            try {
                XWPFDocument document = new XWPFDocument();
                FileOutputStream out = new FileOutputStream(new File(file.getAbsolutePath()));
                XWPFParagraph paragraph = document.createParagraph();

                System.out.println("Report written successfully");

                for (int i = 0; i < TT.SubProcessDBList.size(); i++) {
                    List<Employee> TEs = new ArrayList<Employee>(TT.SubProcessDBList.get(i).getEmployees());

                    if (TEs.size() > 1) {
                        String TheTask = "Sub Process: ";
                        String TaskName = TT.SubProcessDBList.get(i).getName();
                        String IsDoneBy = " is done by: ";

                        XWPFRun paragraphTheTask = paragraph.createRun();
                        paragraphTheTask.setText(TheTask);
                        paragraphTheTask.setColor("12db2d");

                        XWPFRun paragraphTaskName = paragraph.createRun();
                        paragraphTaskName.setBold(true);
                        paragraphTaskName.setText(TaskName);
//                        paragraphTaskName.setColor("#0fff67");

                        XWPFRun paragraphOneIsDoneBy = paragraph.createRun();
                        paragraphOneIsDoneBy.setText(IsDoneBy);
                        paragraphOneIsDoneBy.addBreak();

                        String FirstLine = TheTask + IsDoneBy;
                        int SpaceSize = FirstLine.length();
                        String Spaces = "";

                        for (int c = 0; c < SpaceSize; c++) {
                            Spaces = Spaces + " ";
                        }

                        for (int j = 0; j < TEs.size(); j++) {

                            String PreString = Spaces + "• ";
                            XWPFRun paragraphEmployee = paragraph.createRun();
                            paragraphEmployee.setBold(true);
                            paragraphEmployee.setText(PreString + TEs.get(j).getRole());
                            paragraphEmployee.addBreak();

                        }
                        XWPFRun paragraphBreak = paragraph.createRun();
                        paragraphBreak.setBold(true);
                        paragraphBreak.addBreak();

                    }
                }

                for (int i = 0; i < TT.ActivitiesDBList.size(); i++) {
                    List<Employee> TEs = new ArrayList<Employee>(TT.ActivitiesDBList.get(i).getEmployees());

                    if (TEs.size() > 1) {
                        String TheTask = "Activity: ";
                        String TaskName = TT.ActivitiesDBList.get(i).getShortDescription();
                        String IsDoneBy = " is done by: ";

                        XWPFRun paragraphTheTask = paragraph.createRun();
                        paragraphTheTask.setBold(true);
                        paragraphTheTask.setColor("68f2b6");

                        paragraphTheTask.setText(TheTask);

                        XWPFRun paragraphTaskName = paragraph.createRun();
                        paragraphTaskName.setBold(true);
//                        paragraphTaskName.setColor("#82ffdc");
                        paragraphTaskName.setText(TaskName);

                        XWPFRun paragraphOneIsDoneBy = paragraph.createRun();
                        paragraphOneIsDoneBy.setText(IsDoneBy);
                        paragraphOneIsDoneBy.addBreak();

                        String FirstLine = TheTask + IsDoneBy;
                        int SpaceSize = FirstLine.length();
                        String Spaces = "";

                        for (int c = 0; c < SpaceSize; c++) {
                            Spaces = Spaces + " ";
                        }

                        for (int j = 0; j < TEs.size(); j++) {

                            String PreString = Spaces + "• ";
                            XWPFRun paragraphEmployee = paragraph.createRun();
                            paragraphEmployee.setBold(true);
                            paragraphEmployee.setText(PreString + TEs.get(j).getRole());
                            paragraphEmployee.addBreak();

                        }
                        XWPFRun paragraphBreak = paragraph.createRun();
                        paragraphBreak.setBold(true);
                        paragraphBreak.addBreak();

                    }
                }
                document.write(out);
                out.close();
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) desktop.open(file);
                Reconnect();
            } catch (Exception E) {
            }

        });

        GenerateJD.setOnAction(ev -> {
//            FileChooser fileChooser = new FileChooser();
//            File selectedFile = fileChooser.showOpenDialog(stage);
//            fileChooser.setTitle("Load the template");
            FileChooser fcWord = new FileChooser();

            fcWord.getExtensionFilters().add(new FileChooser.ExtensionFilter("DOCX File", "*.docx"));
            fcWord.setTitle("Save to word");
            String TemplatePath = "";
            if (araabc) {
                TemplatePath = templatescreator.Path + "//" + templatescreator.JD_AR_NameFile;
                fcWord.setInitialFileName(JobTitle.getText() + "-ar.docx");
            } else {
                TemplatePath = templatescreator.Path + "//" + templatescreator.JD_EN_NameFile;
                fcWord.setInitialFileName(JobTitle.getText() + ".docx");
            }
            File file = fcWord.showSaveDialog(stage);
            if (file != null) {
                try {
                    XWPFDocument doc = new XWPFDocument(OPCPackage.open(TemplatePath));


                    String jobtitle = JobTitle.getText();
                    String jobtype = JobType.getEditor().getText();
                    String joblocation = JobLocation.getEditor().getText();
                    String manager = JobManager.getText();
                    String quali = JobQualification.getText();
                    String exp = Experience.getEditor().getText();
                    String skills = Skills.getText();
                    String languages = Languages.getEditor().getText();


                    ArrayList<String> res = new ArrayList<String>();
                    for (int i = 0; i < Labels_vector.size(); i++) {
                        if (Labels_vector.get(i) != null && Labels_vector.get(i).Type_id == 1) {
                            List<Task> tasks2 = new ArrayList<Task>(((EmployeeLabel) Labels_vector.get(i)).EM.getTasks());
                            for (int j = 0; j < tasks2.size(); j++) {
                                res.add("► " + tasks2.get(j).getShortDesc());
                                res.add("• " + tasks2.get(j).getDescription());
                            }
                        }
                    }

                    ArrayList<XWPFParagraph> T_paras = new ArrayList<XWPFParagraph>();
                    boolean RemoveParagraph = false;


                    XmlCursor cursor = null;
                    XWPFParagraph paragraph = null;

                    boolean foundTablePosition = false;
                    boolean thereWasParagraphAfter = false;
                    for (IBodyElement element : doc.getBodyElements()) {
                        if (element instanceof XWPFParagraph) {
                            paragraph = (XWPFParagraph) element;
                            StringBuilder sb = new StringBuilder();
                            for (XWPFRun irun : paragraph.getRuns()) {
                                sb.append(irun.getText(0));
                                System.out.println(sb);
                                if (sb.toString().contains("Taskcat")) {
                                    cursor = paragraph.getCTP().newCursor();
                                    thereWasParagraphAfter = cursor.toNextSibling();
                                    foundTablePosition = true;
                                }
                            }
                        }
                        if (foundTablePosition) break;
                    }

                    if (cursor != null) {
                        if (thereWasParagraphAfter) {
                            paragraph = doc.insertNewParagraph(cursor);
                        } else {
                            paragraph = doc.createParagraph();
                        }

                        cursor = paragraph.getCTP().newCursor();
                        XWPFParagraph px = doc.insertNewParagraph(cursor);
                        XWPFRun paragraphTheTask = px.createRun();
                        paragraphTheTask.setBold(true);
                        paragraphTheTask.setColor("68f2b6");

                        for (int c = 0; c < res.size(); c = c + 2) {
                            XWPFRun paragraphTheTask1 = px.createRun();
                            paragraphTheTask1.setBold(true);
                            paragraphTheTask1.setColor("68f2b6");
                            paragraphTheTask1.setText(res.get(c));
                            paragraphTheTask1.addBreak();

                            XWPFRun paragraphTheTask2 = px.createRun();
                            paragraphTheTask2.setText(res.get(c + 1));
                            paragraphTheTask2.addBreak();
                        }
                    }

                    int j = 0;
                    XWPFParagraph p1 = null;
                    for (XWPFParagraph p : doc.getParagraphs()) {
                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (XWPFRun r : runs) {
                                System.out.println("prar " + j + " " + r.getText(0));
                                String text = r.getText(0);
                                if (text != null && text.contains("EmployeeRole")) {
                                    text = text.replace("EmployeeRole", jobtitle);//your content
                                    r.setText(text, 0);
                                }
                                if (text != null && text.contains("EmployeeJobType")) {
                                    text = text.replace("EmployeeJobType", jobtype);//your content
                                    r.setText(text, 0);
                                }
                                if (text != null && text.contains("EmployeeLocation")) {
                                    text = text.replace("EmployeeLocation", joblocation);//your content
                                    r.setText(text, 0);
                                }
                                if (text != null && text.contains("EmployeeReportingLine")) {
                                    text = text.replace("EmployeeReportingLine", manager);//your content
                                    r.setText(text, 0);
                                }
                                if (text != null && text.contains("EmployeeDesirableSkills")) {
                                    text = text.replace("EmployeeDesirableSkills", skills);//your content
                                    r.addCarriageReturn();
                                    r.setText(text, 0);
                                    r.addCarriageReturn();
                                }
                                if (text != null && text.contains("EmployeeWorkexp")) {
                                    text = text.replace("EmployeeWorkexp", exp);//your content
                                    r.addCarriageReturn();
                                    r.setText(text, 0);
                                    r.addCarriageReturn();
                                }
                                if (text != null && text.contains("EmployeeMandatorySkills")) {

                                    text = text.replace("EmployeeMandatorySkills", quali);//your content
                                    r.addCarriageReturn();
                                    r.setText(text, 0);
                                    r.addCarriageReturn();
                                }
                                if (text != null && text.contains("EmployeeLangs")) {
                                    text = text.replace("EmployeeLangs", languages);//your content
                                    r.addCarriageReturn();
                                    r.setText(text, 0);
//                                    r.addCarriageReturn();
                                }
                                if (RemoveParagraph) {
                                    T_paras.add(p);
                                    break;
                                }
                                if (text != null && text.contains("Taskcat")) {
                                    text = text.replace("Taskcat", "");
                                    r.setText(text, 0);
//                                    for (int c = 0; c < res.size(); c = c + 2) {
//                                        r.setText(res.get(c), c);
//                                        r.addCarriageReturn();
//                                        r.setText(res.get(c + 1), c + 1);
//                                        r.addCarriageReturn();
//                                        r.addBreak();
//
//                                    }
                                    p1 = p;
                                    RemoveParagraph = true;
                                }
                            }
                        }
                        j++;
                    }
                    int taskspara = doc.getPosOfParagraph(p1);


                    for (int i = 0; i < T_paras.size(); i++) {
                        XWPFRun paragraphTheTask = doc.createParagraph().createRun();
                        doc.setParagraph(T_paras.get(i), taskspara + 2 + i);
                        deleteParagraph(T_paras.get(i));
                    }
//
                    XWPFRun ImageHeader = doc.createParagraph().createRun();
                    ImageHeader.setBold(true);
//                    SubProcessL.setColor("005828");
                    ImageHeader.setText("Job Description Tasks: ");
                    ImageHeader.addBreak();

                    InputStream pic = null;
                    pic = new FileInputStream(GenerateScreenShot());
                    XWPFParagraph paragraphFive = doc.createParagraph();
                    paragraphFive.setAlignment(ParagraphAlignment.CENTER);
                    XWPFRun paragraphFiveRunOne = paragraphFive.createRun();
                    paragraphFiveRunOne.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, "New_Screen.png", Units.toEMU(200), Units.toEMU(200));



                    doc.getPackage().replaceContentType(
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.template.main+xml",
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");
                    if (GenerateDOCXCheckBoxItem.isSelected()) {
//                        doc.enforceUpdateFields();
                        doc.write(new FileOutputStream(file.getAbsolutePath()));
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(file);

                    }
                    if (GeneratePDFCheckBoxItem.isSelected()) {
                        String PDFPath = file.getAbsolutePath();
                        PDFPath = PDFPath.replace(".docx", ".pdf");
                        OutputStream out = new FileOutputStream(PDFPath);
                        doc.createNumbering();
                        PdfOptions options = PdfOptions.create();
                        PdfConverter.getInstance().convert(doc, out, options);
                        out.close();
                    }
                    Reconnect();

//                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        GenerateOP.setOnAction(ev -> {

            FileChooser fcWord = new FileChooser();
            fcWord.getExtensionFilters().add(new FileChooser.ExtensionFilter("DOCX File", "*.docx"));
            fcWord.setTitle("Save to word");
            String TemplatePath = "";
            if (araabc) {
                TemplatePath = templatescreator.Path + "//" + templatescreator.P_AR_NameFile;
                fcWord.setInitialFileName(PName.getText() + "-ar.docx");
            } else {
                TemplatePath = templatescreator.Path + "//" + templatescreator.P_EN_NameFile;
                fcWord.setInitialFileName(PName.getText() + ".docx");
            }
            File file = fcWord.showSaveDialog(stage);
            if (file != null) {
                String str = file.getAbsolutePath();
                try {
                    XWPFDocument doc = new XWPFDocument(OPCPackage.open(TemplatePath));
                    String mainProcessName = PName.getText();
                    String procedureOwner = POwner.getText();
                    String procedureVersion = PVersion.getText();
                    String processShort = PShortD.getText();
                    String processDes = PDescription.getText();
                    /////////////////////////
                    for (int j = 0; j < Labels_vector.size(); j++) {
                        if (Labels_vector.get(j) instanceof ProcessLabel) {
                            MainProcess = ((ProcessLabel) Labels_vector.get(j)).MP;
                        }
                    }
                    List<SubProcess> subProcesses = new ArrayList<SubProcess>(MainProcess.getSubProcesses());


                    boolean RemoveParagraph = false;


                    XmlCursor cursor = null;
                    XWPFParagraph paragraph = null;

                    boolean foundTablePosition = false;
                    boolean thereWasParagraphAfter = false;
                    for (IBodyElement element : doc.getBodyElements()) {
                        if (element instanceof XWPFParagraph) {
                            paragraph = (XWPFParagraph) element;
                            StringBuilder sb = new StringBuilder();
                            for (XWPFRun irun : paragraph.getRuns()) {
                                sb.append(irun.getText(0));
                                System.out.println(sb);
                                if (sb.toString().contains("SubPrcoessDesc")) {
                                    cursor = paragraph.getCTP().newCursor();
                                    thereWasParagraphAfter = cursor.toNextSibling();
                                    foundTablePosition = true;
                                }
                            }
                        }
                        if (foundTablePosition) break;
                    }

                    if (cursor != null) {
                        if (thereWasParagraphAfter) {
                            paragraph = doc.insertNewParagraph(cursor);
                        } else {
                            paragraph = doc.createParagraph();
                        }
                        cursor = paragraph.getCTP().newCursor();
                        XWPFParagraph px = doc.insertNewParagraph(cursor);
                        for (int i = 0; i < subProcesses.size(); i++) {
                            subProcesses.get(i).PrintToTheDoc31(px);
                        }
                    }

                    XWPFParagraph pEM=InsertParagraph(doc,"Employees");
                    for (int i = 0; i < Labels_vector.size(); i++) {
                        if (Labels_vector.get(i) != null && Labels_vector.get(i) instanceof EmployeeLabel) {
                            ((EmployeeLabel) Labels_vector.get(i)).EM.PrintToTheDoc3(pEM);
                        }
                    }

                    for (XWPFParagraph p : doc.getParagraphs()) {
                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (XWPFRun r : runs) {
                                String text = r.getText(0);
                                if (text != null && text.contains("MainProcessName")) {
                                    text = text.replace("MainProcessName", mainProcessName);//your content
                                    r.setText(text, 0);
                                }
                                if (text != null && text.contains("EmployeeRole")) {
                                    text = text.replace("EmployeeRole", procedureOwner);//your content
                                    r.setText(text, 0);
                                }
                                if (text != null && text.contains("MainProcessVersion")) {
                                    text = text.replace("MainProcessVersion", procedureVersion);//your content
                                    r.setText(text, 0);
                                }
                                if (text != null && text.contains("MainProcessShortDesc")) {
                                    text = text.replace("MainProcessShortDesc", processShort);//your content
                                    r.addCarriageReturn();
                                    r.setText(text, 0);
//                                    r.setColor("68f2b6");
                                    r.addCarriageReturn();
                                }
                                if (text != null && text.contains("MainProcessDesc")) {
                                    text = text.replace("MainProcessDesc", processDes);//your content
                                    r.addCarriageReturn();
                                    r.setText(text, 0);
                                    r.addCarriageReturn();
                                }
                                if (text != null && text.contains("SubPrcoessDesc")) {
//                                    String c = "";
//                                    for (int i = 0; i < subProcesses.size(); i++) {
//                                        String temp_c = subProcesses.get(i).PrintToTheDoc3(r, c);
//                                        c = c + temp_c;
//                                    }
//                                    System.out.println(c);
                                    text = text.replace("SubPrcoessDesc", "");//your content
                                    r.addCarriageReturn();
                                    r.setText(text, 0);
////                                    r.setColor("68f2b6");
//                                    r.addCarriageReturn();
//                                    String lines[] = c.split("\\r?\\n");
//                                    for (int i = 0; i < lines.length; i++) {
//                                        System.out.println("line " + i + " " + lines[i]);
//                                        r.addCarriageReturn();
//                                        int ii = i + 1;
//                                        r.setText(lines[i], ii);
//                                        r.addCarriageReturn();
//                                    }
                                }
                                if (text != null && text.contains("Employees")) {
//
//                                    String c = "";
//                                    for (int i = 0; i < Labels_vector.size(); i++) {
////                                        String temp_c = subProcesses.get(i).PrintToTheDoc3(r, c);
////                                        c = c + temp_c;
//                                        if (Labels_vector.get(i) != null && Labels_vector.get(i) instanceof EmployeeLabel) {
//                                            String c_temp = ((EmployeeLabel) Labels_vector.get(i)).EM.PrintToTheDoc33(r);
//                                            c = c + c_temp;
//                                        }
//                                    }
//                                    System.out.println(c);
                                    text = text.replace("Employees", "");//your content
//                                    r.addCarriageReturn();
                                    r.setText(text, 0);
//                                    r.addCarriageReturn();
//                                    String lines[] = c.split("\\r?\\n");
//                                    for (int i = 0; i < lines.length; i++) {
//                                        System.out.println("line " + i + " " + lines[i]);
//                                        r.addCarriageReturn();
//                                        int ii = i + 1;
//                                        r.setText(lines[i], ii);
//                                        r.addCarriageReturn();
//                                    }
                                }
                            }
                        }
                    }
                    System.out.println("TTTTT" + doc.getTables().size());

                    for (XWPFTable tbl : doc.getTables()) {
                        for (XWPFTableRow row : tbl.getRows()) {
                            System.out.println("RRRR" + tbl.getRows().size());
                            for (XWPFTableCell cell : row.getTableCells()) {
                                System.out.println("CCC" + row.getTableCells().size());
                                for (XWPFParagraph p : cell.getParagraphs()) {
                                    for (XWPFRun r : p.getRuns()) {
                                        String text = r.getText(0);
                                        if (text != null && text.contains("MainProcessVersion")) {
                                            System.out.println("hhhjjhj= " + text.toString());
                                            System.out.println("hhhjjhj1= " + text);

                                            text = text.replace("MainProcessVersion", procedureVersion);
                                            r.setText(text, 0);
                                        }
                                    }
                                }
                            }
                        }
                    }
//                    ChangeTextColorInDoc(doc);

                    XWPFRun ImageHeader = doc.createParagraph().createRun();
                    ImageHeader.setBold(true);
//                    SubProcessL.setColor("005828");
                    ImageHeader.setText("MainProcess Workflow: ");
                    ImageHeader.addBreak();

                    InputStream pic = null;
                    pic = new FileInputStream(GenerateScreenShot());
                    XWPFParagraph paragraphFive = doc.createParagraph();
                    paragraphFive.setAlignment(ParagraphAlignment.CENTER);
                    XWPFRun paragraphFiveRunOne = paragraphFive.createRun();
                    paragraphFiveRunOne.addPicture(pic, XWPFDocument.PICTURE_TYPE_JPEG, "New_Screen.png", Units.toEMU(200), Units.toEMU(200));

//                    InputStream pic1 = null;
//                    pic1 = new FileInputStream("D:\\PD\\Images\\4d6.png");
//                    XWPFParagraph paragraphFive1 = doc.createParagraph();
//                    paragraphFive1.setAlignment(ParagraphAlignment.CENTER);
//                    XWPFRun paragraphFiveRunOne1 = paragraphFive1.createRun();
//                    paragraphFiveRunOne1.addPicture(pic1, XWPFDocument.PICTURE_TYPE_PNG, "4d6.png", Units.toEMU(200), Units.toEMU(200));


                    InputStream pic2 = null;
                    pic2 = new FileInputStream(GenerateScreenShot());
                    XWPFParagraph paragraphFive2 = doc.createParagraph();
                    paragraphFive2.setAlignment(ParagraphAlignment.CENTER);
                    XWPFRun paragraphFiveRunOne2 = paragraphFive2.createRun();
                    paragraphFiveRunOne2.addPicture(pic2, XWPFDocument.PICTURE_TYPE_JPEG, "New_Screen.png", Units.toEMU(200), Units.toEMU(200));

                    doc.getPackage().replaceContentType(
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.template.main+xml",
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");


                    if (GenerateDOCXCheckBoxItem.isSelected()) {
//                        doc.enforceUpdateFields();
                        doc.write(new FileOutputStream(file.getAbsolutePath()));
                        Desktop desktop = Desktop.getDesktop();
                        if (file.exists()) desktop.open(file);
                    }
                    if (GeneratePDFCheckBoxItem.isSelected()) {
                        String PDFPath = file.getAbsolutePath();
                        PDFPath = PDFPath.replace(".docx", ".pdf");
                        OutputStream out = new FileOutputStream(PDFPath);
                        doc.createNumbering();
                        PdfOptions options = PdfOptions.create();
                        PdfConverter.getInstance().convert(doc, out, options);
                        out.close();
                    }
                    Reconnect();
//                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        arabic.setOnAction(e -> {
            araabc = true;
            Setup_Root_1_To_Arabic();
            Setup_Root_2_To_Arabic();
            Setup_Main_Menu_To_Arabic();
            Set_Tooltip_Language_To_Arabic();
            Setup_Main_Shortcut_Buttons_To_Arabic();
            stage.setTitle("مصمم اجراءات");
            Insert_New_SubProcess.setText("اضافة عملية فرعية جديدة");
            Update_Sub_Process.setText("تعديل العملية الفرعية");
            Save_Employee_Tasks.setText("حفظ مهام الموظف");
            Save_Process.setText("حفظ اجراءات العمل");
            Save_Process2.setText("اضافة اجراءات العمل");
            Insert_Employee.setText("اضافة موظف");

        });

        english.setOnAction(e -> {

            araabc = false;
            Setup_Root_1_To_English();
            Setup_Root_2_To_English();
            Setup_Main_Menu_To_English();
            Set_Tooltip_Language_To_English();
            Setup_Main_Shortcut_Buttons_To_English();

            stage.setTitle("Procedures Designer");
            Operation.setText("Operation");
            Save_Employee_Tasks.setText("Update JD");
            Save_Process.setText("Add New Operating Procedure to Data base");
            Save_Process2.setText("Update Operating Procedure");
            Insert_Employee.setText("Add JD to Database");
            Insert_New_SubProcess.setText("Insert SubProcess");
            Update_Sub_Process.setText("Update SubProcess");


        });

        close.setOnAction(e -> {
            fleMenue4.getItems().removeAll(fleMenue4.getItems());
            Clear_All();
        });

        newob.setOnAction(e -> {
            M_R_1.Get_Roor_1_Scene();
        });

        newjb.setOnAction(e -> {
            M_R_2.Get_Roor_2_Scene();
        });

        savee.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).SaveLabelToDB(stage);
                }
            }
        });

        exit.setOnAction(e -> {
            if (araabc == true) {
                closeProgramArabic(stage, RecentOpenn);

            } else {
                closeProgram(stage, RecentOpenn);
            }
        });

        saveeAs.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).SaveLabelToDB(stage);
                }
            }
        });

        print.setOnAction(e -> {
            GenerateScreenShot();
//               Group rrr=new Group();
//                  for(int i=0;i<Labels_vector{})
//                    rrr.getChildren().add(Labels_vector);

        });

//        Delete.setOnAction(e -> {
//            OnClickOnDelete();
//            if (DeleteOn == true) {
//                DeleteOn = false;
        //         Delete.setStyle(null);
//            } else {
//                DeleteOn = true;
//                Delete.setStyle("-fx-background-color: #9bc1ff;");
//            }
//            Link.setStyle(null);
//            LinkOn = false;
//        });
//
//        Link.setOnAction(e -> {
//            OnClickOnLink();
//            if (LinkOn == true) {
//                LinkOn = false;
//                Link.setStyle(null);
//            } else {
//                LinkOn = true;
//                Link.setStyle("-fx-background-color: #9bc1ff;");
//            }
//            Delete.setStyle(null);
//            DeleteOn = false;
//        });

        openn.setOnAction(e -> {
            OpenFileAndAddItToOpenRecentIfItWasnotAdded(stage);
        });

        GeneratePdf.setOnAction(e -> {
            FileChooser fcWord = new FileChooser();
            String path = "";
            FileChooser fc2 = new FileChooser();
            fc2.setTitle("Open Word File");
            File file = fc2.showOpenDialog(stage);
            path = file.getPath();
            // String dataDir = sample.Utils.getDataDir(Main.class);
            try {
                InputStream is = new FileInputStream(new File(
                        path));
                XWPFDocument document = new XWPFDocument(is);

                fcWord.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
                fcWord.setTitle("Save to word");
                String fileName = file.getName();
                int lastPeriodPos = fileName.lastIndexOf('.');
                if (lastPeriodPos > 0)
                    fileName = fileName.substring(0, lastPeriodPos);
                //System.out.println("File name is " + fileName);
                fcWord.setInitialFileName(fileName + ".pdf");
                File file1 = fcWord.showSaveDialog(stage);
                String str = file1.getAbsolutePath();

                PdfOptions options = PdfOptions.create();
                OutputStream out = new FileOutputStream(new File(str));
                System.out.println("\nDocument converted to PDF successfully.\nFile saved at " + str);
                document.createNumbering();
                PdfConverter.getInstance().convert(document, out, options);

             /*   Document doc = new Document( path);
                fcWord.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
                fcWord.setTitle("Save to word");
                fcWord.setInitialFileName("output" + ".pdf");
                File file1 = fcWord.showSaveDialog(stage);
                String str = file1.getAbsolutePath();
               // dataDir = "output.pdf";
                doc.save(str);*/
                System.out.println("\nDocument converted to PDF successfully.\nFile saved at " + str);
                document.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        zoomInMenuItem.setAccelerator(new

                KeyCodeCombination(KeyCode.I));
        zoomInMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i) instanceof General_Label) {
                        if (((General_Label) root.getChildren().get(i)).getScaleX() < 1.2 && ((General_Label) root.getChildren().get(i)).getScaleY() < 1.2) {
                            ((General_Label) root.getChildren().get(i)).setScaleX(((General_Label) root.getChildren().get(i)).getScaleX() * 1.1);
                            ((General_Label) root.getChildren().get(i)).setScaleY(((General_Label) root.getChildren().get(i)).getScaleY() * 1.1);
                        }
                    }
                }
                if (Zoom_Scale < 1.2) {
                    Zoom_Scale = Zoom_Scale * 1.1;
                }
            }
        });

        zoomOutMenuItem.setAccelerator(new

                KeyCodeCombination(KeyCode.O));
        zoomOutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i) instanceof General_Label) {
                        if (((General_Label) root.getChildren().get(i)).getScaleX() != 1 && ((General_Label) root.getChildren().get(i)).getScaleY() != 1) {
                            ((General_Label) root.getChildren().get(i)).setScaleX(((General_Label) root.getChildren().get(i)).getScaleX() * 1 / 1.1);
                            ((General_Label) root.getChildren().get(i)).setScaleY(((General_Label) root.getChildren().get(i)).getScaleY() * 1 / 1.1);
                        }
                    }
                }
                if (Zoom_Scale != 1) {
                    Zoom_Scale = Zoom_Scale * 1 / 1.1;
                }
            }
        });
    }

    public void Set_Menu_Style() {

//        String Mu_S = "-fx-background-color: #52c2fa;-fx-font-size: 14px;-fx-text-fill: #654b00;-fx-font-weight: bold;";
//        fleMenue.setStyle(Mu_S);
//        fleMenue1.setStyle(Mu_S);
//        fleMenue2.setStyle(Mu_S);
//        fleMenue3.setStyle(Mu_S);
//        fleMenue4.setStyle(Mu_S);
//        fleMenue5.setStyle(Mu_S);
//        fleMenue5.setStyle(Mu_S);
//        language.setStyle(Mu_S);
//        FormsMenu.setStyle(Mu_S);
        GeneratePDFCheckBoxItem.setStyle("-fx-text-fill: #654b00;");
        GenerateDOCXCheckBoxItem.setStyle("-fx-text-fill: #654b00;");
        TasksTableMenuCheckBox.setStyle("-fx-text-fill: #654b00;");
        ProcessTableMenuCheckBox.setStyle("-fx-text-fill: #654b00;");
        EmployeeTableMenuCheckBox.setStyle("-fx-text-fill: #654b00;");
        DurationTableMenuCheckBox.setStyle("-fx-text-fill: #654b00;");
    }

    public static void Setup_Main_Menu_To_English() {

        language.setText("Language");
        arabic.setText("Arabic");
        english.setText("English");
        fleMenue.setText("File");
        savee.setText("Save");
        openn.setText("Open");
        saveeAs.setText("Save as");
        close.setText("Close");
        exit.setText("Exit");
        fleMenue1.setText("Edit");
        Undo.setText("Undo Ctrl+Z");
        Redo.setText("Redo Ctrl+X");

        fleMenue2.setText("Action");
        Delete.setText("Delete");
        Link.setText("Link");
        print.setText("Print");

        fleMenue3.setText("View");
        zoomInMenuItem.setText("Zoom In");
        zoomOutMenuItem.setText("Zoom Out");

        fleMenue4.setText("Generate");
        GenerateOP.setText("Generate OP");
        GenerateJD.setText("Generate JD");
        GeneratePdf.setText("Generate PDF");

        FormsMenu.setText("Forms");
        JobDescription_Form_Menu_Item.setText("Job Description Form");
        Operating_Procedure_Form_Menu_Item.setText("Operating Procedure Form");
        New_SubProcess_Menu_Item.setText("New SubProcess Form");

        Close_Form_Menu_Item.setText("Close the Form");
        RecentOpenn.setText("Recent Open");
        fleMenue5.setText("Help");
        Help.setText("Open Help file");
        New.setText("New");
        newob.setText("OP");
        newjb.setText("JD");
        Load_Template_Menu_Item.setText("Load Template");
    }

    public static void Setup_Main_Menu_To_Arabic() {

        language.setText("اللغة");
        arabic.setText("عربي");
        english.setText("انجليزي");
        fleMenue.setText("ملف");
        savee.setText("حفظ");
        openn.setText("فتح");
        saveeAs.setText("حفظ كـ");
        close.setText("اغلاق");
        exit.setText("خروج");
        fleMenue1.setText("تعديل");
        Undo.setText("الغاء الفعل");
        Redo.setText("اعادة الفعل");
        RecentOpenn.setText("الملفات المفتوحة مؤخرا");

        fleMenue2.setText("فعل");
        Delete.setText("مسح");
        Link.setText("ربط");
        //print.setText("????");

        fleMenue3.setText("عرض");
        zoomInMenuItem.setText("تفريب");
        zoomOutMenuItem.setText("ابعاد");

        fleMenue4.setText("إنتاج");
        GenerateOP.setText("إنتاج ملف اجراءات عمل");
        GenerateJD.setText("إنتاج ملف مسمي وظيفي ");
        GeneratePdf.setText("انتاج ملف بصيغة PDF");

        fleMenue5.setText("مساعدة");
        //  Operation.setText("عملية");
        Help.setText("فتح ملف مساعدة");
        New.setText("جديد");
        newob.setText("ملف اجراءات عمل جديد");
        newjb.setText("ملف مسمي وظيفي جديد");
        FormsMenu.setText("النماذج");
        JobDescription_Form_Menu_Item.setText("نموزج المسمي الوظيفي");
        Operating_Procedure_Form_Menu_Item.setText("نموزج إجراءات التشغيل");
        Operating_Procedure_Form_Menu_Item.setText("نموزج العمليات الفرعية");

        Close_Form_Menu_Item.setText("غلق النموزج");
        Load_Template_Menu_Item.setText("استدعاء القالب");

    }

    static void Check_If_This_File_In_Open_recent(char flag, String bath, Stage stage, String name, FileInputStream fi, ObjectInputStream oi) {
        //myText
        boolean found = false;
        for (int i = 0; i < RecentOpenn.getItems().size(); i++) {
            if (name.equalsIgnoreCase(RecentOpenn.getItems().get(i).getText())) {
                found = true;
            }
        }
        if (found == false) {
            N_Menu_item newtem = new N_Menu_item(name);
            newtem.set(bath);
            if (flag == 'j') {
                newtem.setOnAction(e -> {
                    addressoben = bath;
                    M_R_2.OpenFile(stage, fi, oi);
                });

            } else {
                newtem.setOnAction(e -> {
                    addressoben = bath;
                    M_R_1.OpenFile(stage, fi, oi);
                });
            }

            if (RecentOpenn.getItems().size() < 8) {
                RecentOpenn.getItems().add(newtem);
            } else {
                RecentOpenn.getItems().remove(0, 1);
                RecentOpenn.getItems().add(newtem);
            }
        }
    }

    static void Check_If_This_File_In_Open_recent(String bath, Stage stage, String name) {
        try {
            FileInputStream fi = null;
            fi = new FileInputStream(bath);
            ObjectInputStream oi = new ObjectInputStream(fi);
            char flag = oi.readChar();

            N_Menu_item newtem = new N_Menu_item(name);
            newtem.set(bath);
            if (flag == 'j') {
                FileInputStream finalFi = fi;
                newtem.setOnAction(e -> {
                    addressoben = bath;
                    M_R_2.OpenFile(stage, finalFi, oi);
                });

            } else {
                FileInputStream finalFi1 = fi;
                newtem.setOnAction(e -> {
                    addressoben = bath;
                    M_R_1.OpenFile(stage, finalFi1, oi);
                });
            }

            if (RecentOpenn.getItems().size() < 8) {
                RecentOpenn.getItems().add(newtem);
            } else {
                RecentOpenn.getItems().remove(0, 1);
                RecentOpenn.getItems().add(newtem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void OpenFileAndAddItToOpenRecentIfItWasnotAdded(Stage stage) {
        String bath = "";
        FileChooser fc2 = new FileChooser();
        fc2.setTitle("Open Resource File");
        File file = fc2.showOpenDialog(stage);
        bath = file.getPath();
        addressoben = bath;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(new File(file.getPath()));
            ObjectInputStream oi = new ObjectInputStream(fi);
            char flag = oi.readChar();

            Check_If_This_File_In_Open_recent(flag, bath, stage, file.getName(), fi, oi);

            System.out.println("flaaaaaaaaaaaag is" + flag);
            if (flag == 'j') {
                M_R_2.OpenFile(stage, fi, oi);

            } else {
                M_R_1.OpenFile(stage, fi, oi);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void ChangeTextColorInDoc(XWPFDocument doc) {
        for (XWPFParagraph p : doc.getParagraphs()) { //go through all paragraphs
            int runNumber = 0;
            while (runNumber < p.getRuns().size()) { //go through all runs, we cannot use for each since we will possibly insert new runs
                XWPFRun r = p.getRuns().get(runNumber);
                String runText = r.getText(0);
                if (runText != null && runText.contains("a")) { //if we have a run with an "a" in it, then
                    char[] runChars = runText.toCharArray();
                    StringBuffer sb = new StringBuffer();
                    for (int charNumber = 0; charNumber < runChars.length; charNumber++) { //go through all characters in that run
                        if (runChars[charNumber] == 'a') { //if the charcter is an 'a' then
                            r.setText(sb.toString(), 0); //set all characters, which are current buffered, as the text of the actual run
                            r = p.insertNewRun(++runNumber); //insert new run for the '@' as the replacement for the 'a'
                            r.setText("@", 0);
                            r.setColor("DC143C");
                            r = p.insertNewRun(++runNumber); //insert new run for the next characters
                            sb = new StringBuffer(); //empty buffer
                        } else {
                            sb.append(runChars[charNumber]); //buffer all characters which are not 'a's
                        }
                    }
                    r.setText(sb.toString(), 0); //set all characters, which are current buffered, as the text of the actual run
                }
                runNumber++;
            }
        }
    }

    public void ChangeTextColorInDoc1(XWPFDocument doc) {
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
//                    for(int i=0;i<r.get)
                    String text = r.text();
                    System.out.println("----" + r.toString());
                    if (text != null && text.contains("► Activity: ")) {
                        System.out.println("2222222222222222222 they found it");
                        text = text.replace("► Activity: ", "► Activity: ");//your content
                        r.setText(text);
                        r.setColor("68f2b6");
                    }
                    if (text != null && text.contains("► Sub Process: ")) {
                        text = text.replace("► Sub Process: ", "► Sub Process: ");//your content
                        r.setText(text, 0);
                        r.setColor("12db2d");
                    }
                    if (text != null && text.contains("► Owner: ")) {
                        text = text.replace("► Owner: ", "► Owner: ");//your content
                        r.setText(text, 0);
                        r.setColor("327dfc");
                    }
                    if (text != null && text.contains("► Duration: ")) {
                        text = text.replace("► Duration: ", "► Duration: ");//your content
                        r.setText(text, 0);
                        r.setColor("e0762f");
                    }
                    if (text != null && text.contains("► Risks: ")) {
                        text = text.replace("► Risks: ", "► Risks: ");//your content
                        r.setText(text, 0);
                        r.setColor("fc3235");
                    }
                }
            }
        }
    }

    public static void deleteParagraph(XWPFParagraph p) {
        XWPFDocument doc = p.getDocument();
        int pPos = doc.getPosOfParagraph(p);
//        doc.setParagraph( p, int pos)
        //doc.getDocument().getBody().removeP(pPos);
        doc.removeBodyElement(pPos);
    }

    public static XWPFParagraph InsertParagraph(XWPFDocument doc, String SearchForParagraph) {
        XmlCursor cursor = null;
        XWPFParagraph paragraph = null;

        boolean foundTablePosition = false;
        boolean thereWasParagraphAfter = false;
        for (IBodyElement element : doc.getBodyElements()) {
            if (element instanceof XWPFParagraph) {
                paragraph = (XWPFParagraph) element;
                StringBuilder sb = new StringBuilder();
                for (XWPFRun irun : paragraph.getRuns()) {
                    sb.append(irun.getText(0));
                    System.out.println(sb);
                    if (sb.toString().contains(SearchForParagraph)) {
                        cursor = paragraph.getCTP().newCursor();
                        thereWasParagraphAfter = cursor.toNextSibling();
                        foundTablePosition = true;
                    }
                }
            }
            if (foundTablePosition) break;
        }

//        if (cursor != null) {
            if (thereWasParagraphAfter) {
                paragraph = doc.insertNewParagraph(cursor);
            } else {
                paragraph = doc.createParagraph();
            }
            cursor = paragraph.getCTP().newCursor();
            XWPFParagraph px = doc.insertNewParagraph(cursor);
            return px;
//            for (int i = 0; i < subProcesses.size(); i++) {
//                subProcesses.get(i).PrintToTheDoc31(px);
//            }
//        }
    }
}
