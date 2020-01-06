package sample;

import Entities.Activity;
import Entities.Employee;
import Entities.Process;
import Entities.SubProcess;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.*;

import static sample.Image.imageNew;
import static sample.Main.*;
import static sample.Main_Menus.Check_If_This_File_In_Open_recent;

public class Modi_TreeTable implements Serializable {
    public Modi_TreeTable() {
        this.root = new Modi_Tree<>();
        this.tree = new TreeTableView<>(this.root);
        this.tree.setShowRoot(false);
        this.tree.setRowFactory(this::rowFactory);
        this.setupScrolling();
    }


    public Modi_Tree<Map<String, Object>> root;
    public TreeTableView<Map<String, Object>> tree;
    public Timeline scrolltimeline = new Timeline();
    public double scrollDirection = 0;

    public void setupScrolling() {
        scrolltimeline.setCycleCount(Timeline.INDEFINITE);
        scrolltimeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), "Scoll", (ActionEvent) -> {
            dragScroll();
        }));
        tree.setOnDragExited(event -> {
            if (event.getY() > 0) {
                scrollDirection = 1.0 / tree.getExpandedItemCount();
            } else {
                scrollDirection = -1.0 / tree.getExpandedItemCount();
            }
            scrolltimeline.play();
        });
        tree.setOnDragEntered(event -> {
            scrolltimeline.stop();
        });
        tree.setOnDragDone(event -> {
            scrolltimeline.stop();
        });
    }

    public void dragScroll() {
        ScrollBar sb = getVerticalScrollbar();
        if (sb != null) {
            double newValue = sb.getValue() + scrollDirection;
            newValue = Math.min(newValue, 1.0);
            newValue = Math.max(newValue, 0.0);
            sb.setValue(newValue);
        }
    }

    public ScrollBar getVerticalScrollbar() {
        ScrollBar result = null;
        for (Node n : tree.lookupAll(".scroll-bar")) {
            if (n instanceof ScrollBar) {
                ScrollBar bar = (ScrollBar) n;
                if (bar.getOrientation().equals(Orientation.VERTICAL)) {
                    result = bar;
                }
            }
        }
        return result;
    }

    public TreeTableRow<Map<String, Object>> rowFactory(TreeTableView<Map<String, Object>> view) {
        TreeTableRow<Map<String, Object>> row = new TreeTableRow<>();
        return row;
    }

    public void setupDataForEmployees() {
        if (!EmployeeScene) {
            for (int i = 0; i < TT.EmployeesDBList.size(); i++) {
                Modi_Tree<Map<String, Object>> EmployeeTreeItem = createItemForEmployees(root, TT.EmployeesDBList.get(i).getRole(), TT.EmployeesDBList.get(i).getDescription());
                EmployeeTreeItem.EM = TT.EmployeesDBList.get(i);
            }
        }
    }

    public void setupDataForProcess() {

        Process NewMainProcess = new Process("New Procedure", 1, "Shift and mouse left click to Add New Procedure ", 3);
        Modi_Tree<Map<String, Object>> NewMainProcessTreeItem = createItemForProcess(root, NewMainProcess.getShortDescription(), "Main Process", NewMainProcess.getDescription(), 0);
        NewMainProcessTreeItem.MP = NewMainProcess;
        ImageView image_ = new ImageView(imageNew);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        NewMainProcessTreeItem.setGraphic(image_);

//        SubProcess NewSubProcess = new SubProcess("New Sub Process", 11, "Shift and mouse left click to Add new Sub Process 1 ", 3);
//        Modi_Tree<Map<String, Object>> NewSubProcessTreeItem = createItemForProcess(root, NewSubProcess.getName(), "Sub Process", NewSubProcess.getDescription(), -4);
//        NewSubProcessTreeItem.SP = NewSubProcess;
//        NewSubProcessTreeItem.setGraphic(new javafx.scene.image.ImageView(imageNew));

        Modi_Tree<Map<String, Object>> ArabicLanguage = createItemForGat(root, "AR", -44);
        Modi_Tree<Map<String, Object>> EnglishLanguage = createItemForGat(root, "EN", -44);

        Modi_Tree<Map<String, Object>> files = createItemForGat(root, "Procedure", -44);

        for (int j = 0; j < TT.ProcedureFilesDBList.size(); j++) {
            Modi_Tree<Map<String, Object>> FileTreeItem = createItemForFiles(files, TT.ProcedureFilesDBList.get(j).getName());
            FileTreeItem.f0 = TT.ProcedureFilesDBList.get(j);
            if (FileTreeItem.f0.getOpened() == 1) {
                Check_If_This_File_In_Open_recent(FileTreeItem.f0.getPath(), stagedummy, FileTreeItem.f0.getName());
            }
        }
        List<SubProcess> SubProcessList = new ArrayList<SubProcess>(TT.SubProcessDBList);
 /*       for (int i = 0; i < TT.procedures_array.size(); i++) {
            // Modi_Tree<Map<String, Object>> MainProcess = createItemForProcess(root, TT.procedures_array.get(i).getShortDescription(), "Main Process", TT.procedures_array.get(i).getDescription(), 0);
            // MainProcess.MP = TT.procedures_array.get(i);
            // MainProcess.setExpanded(false);
            subProcesses.addAll(TT.procedures_array.get(i).getSubProcesses());
        }*/

        for (int k = 0; k < TT.SubProcessesSTDsDBList.size(); k++) {
            Modi_Tree<Map<String, Object>> CategoryInArabic = createItemForGat(ArabicLanguage, TT.SubProcessesSTDsDBList.get(k), -44);
            Modi_Tree<Map<String, Object>> CategoryInEnglish = createItemForGat(EnglishLanguage, TT.SubProcessesSTDsDBList.get(k), -44);
            ArrayList<String> results = new ArrayList<>();

            for (int x = 0; x < TT.SubProcessCategoriesDBList.size(); x++) {
                //System.out.println("categories " + TT.categoriesSb.get(x));
                ArrayList<Modi_Tree> allobj = new ArrayList<>();
                Modi_Tree<Map<String, Object>> categoryArabic = null;
                Modi_Tree<Map<String, Object>> categoryEnglish = null;
                for (int j = 0; j < SubProcessList.size(); j++) {

                    if (SubProcessList.get(j).getSTD().matches(TT.SubProcessesSTDsDBList.get(k)) && SubProcessList.get(j).getParent() == null
                            && SubProcessList.get(j).getCategory().equals(TT.SubProcessCategoriesDBList.get(x))) {
                        if (!results.contains(SubProcessList.get(j).getCategory())) {
                            Modi_Tree<Map<String, Object>> TSP;

                            categoryArabic = createItemForGat(CategoryInArabic, TT.SubProcessCategoriesDBList.get(x), -44);
                            categoryEnglish = createItemForGat(CategoryInEnglish, TT.SubProcessCategoriesDBList.get(x), -44);
                            if (SubProcessList.get(j).getLanguage().matches("AR")) {
                                TSP = createItemForProcess(categoryArabic, SubProcessList.get(j).getName(), "Sub Process", SubProcessList.get(j).getShortDescription(), 2);
                                allobj.add(TSP);
                            } else {
                                TSP = createItemForProcess(categoryEnglish, SubProcessList.get(j).getName(), "Sub Process", SubProcessList.get(j).getShortDescription(), 2);
                                allobj.add(TSP);
                            }
                            TSP.SP = SubProcessList.get(j);
                            TSP.setExpanded(false);
                            Add_Child(TSP, SubProcessList.get(j), 2);
                            results.add(SubProcessList.get(j).getCategory());
                        } else {
                            Modi_Tree<Map<String, Object>> TSP;
                            //  System.out.println(subProcesses.get(j).getCategory() + "catrogryyy");
                            if (SubProcessList.get(j).getLanguage().matches("AR")) {
                                TSP = createItemForProcess(categoryArabic, SubProcessList.get(j).getName(), "Sub Process", SubProcessList.get(j).getShortDescription(), 2);
                                allobj.add(TSP);
                            } else {
                                TSP = createItemForProcess(categoryEnglish, SubProcessList.get(j).getName(), "Sub Process", SubProcessList.get(j).getShortDescription(), 2);
                                allobj.add(TSP);
                            }
                            TSP.SP = SubProcessList.get(j);
                            TSP.setExpanded(false);
                            Add_Child(TSP, SubProcessList.get(j), 2);
                        }
                    }

                }

                for (int j = 0; j < SubProcessList.size(); j++) {
                    if (SubProcessList.get(j).getParent() != null) {
                        for (int y = 0; y < allobj.size(); y++) {
                            if (SubProcessList.get(j).getParent().getName().equals(allobj.get(y).NAAAMe)) {
                                Add_Child(allobj.get(y), SubProcessList.get(j), 2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setupDataForProcess2() {

        Process NewMainProcess = new Process("New Procedure", 1, "Shift and mouse left click to Add New Procedure ", 3);
        Modi_Tree<Map<String, Object>> NewMainProcessTreeItem = createItemForProcess(root, NewMainProcess.getShortDescription(), "Main Process", NewMainProcess.getDescription(), 0);
        NewMainProcessTreeItem.MP = NewMainProcess;
        ImageView image_ = new ImageView(imageNew);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        NewMainProcessTreeItem.setGraphic(image_);

        Modi_Tree<Map<String, Object>> ArabicLanguage = createItemForGat(root, "AR", -44);
        Modi_Tree<Map<String, Object>> EnglishLanguage = createItemForGat(root, "EN", -44);

        Modi_Tree<Map<String, Object>> files = createItemForGat(root, "Procedure", -44);
        for (int j = 0; j < TT.ProcedureFilesDBList.size(); j++) {
            Modi_Tree<Map<String, Object>> FileTreeItem = createItemForFiles(files, TT.ProcedureFilesDBList.get(j).getName());
            FileTreeItem.f0 = TT.ProcedureFilesDBList.get(j);
            if (FileTreeItem.f0.getOpened() == 1) {
                Check_If_This_File_In_Open_recent(FileTreeItem.f0.getPath(), stagedummy, FileTreeItem.f0.getName());
            }
        }
        List<SubProcess> SubProcessList = new ArrayList<SubProcess>(TT.SubProcessDBList);

        ArrayList<Modi_Tree<Map<String, Object>>> SubProcessTreeItems = new ArrayList<Modi_Tree<Map<String, Object>>>();
        ArrayList<Integer> SubProcessIDs = new ArrayList<Integer>();
        for (int i = 0; i < SubProcessList.size(); i++) {
            Boolean FoundSTD = false;
            Modi_Tree<Map<String, Object>> STDTreeItem = null;
            Modi_Tree<Map<String, Object>> Language = EnglishLanguage;
            if (SubProcessList.get(i).getLanguage().matches("AR")) {
                Language = ArabicLanguage;
            }

            for (int j = 0; j < Language.getChildren().size(); j++) {
                if (((Modi_Tree) Language.getChildren().get(j)).NAAAMe.matches(SubProcessList.get(i).getSTD())) {
                    FoundSTD = true;
                    STDTreeItem = ((Modi_Tree) Language.getChildren().get(j));
                    j = Language.getChildren().size() + 200;
                }
            }
            if (FoundSTD == false) {
                Modi_Tree<Map<String, Object>> NewSTD = createItemForGat(Language, SubProcessList.get(i).getSTD(), -44);
                Modi_Tree<Map<String, Object>> NewCategory = createItemForGat(NewSTD, SubProcessList.get(i).getCategory(), -44);
                if (SubProcessList.get(i).getParent() == null) {
                    Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess(NewCategory, SubProcessList.get(i).getName(), "Sub Process", SubProcessList.get(i).getShortDescription(), 2);
                    NewSubProcess.SP = SubProcessList.get(i);
                    NewSubProcess.setExpanded(false);
                    SubProcessTreeItems.add(NewSubProcess);
                    SubProcessIDs.add(SubProcessList.get(i).getID());
                } else {

                }
            } else {
                Boolean FoundCategory = false;
                for (int j = 0; j < STDTreeItem.getChildren().size(); j++) {
                    if (((Modi_Tree) STDTreeItem.getChildren().get(j)).NAAAMe.matches(SubProcessList.get(i).getCategory())) {
                        FoundCategory = true;
                        if (SubProcessList.get(i).getParent() == null) {
                            Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess((Modi_Tree<Map<String, Object>>) STDTreeItem.getChildren().get(j), SubProcessList.get(i).getName(), "Sub Process", SubProcessList.get(i).getShortDescription(), 2);
                            NewSubProcess.SP = SubProcessList.get(i);
                            NewSubProcess.setExpanded(false);
                            SubProcessTreeItems.add(NewSubProcess);
                            SubProcessIDs.add(SubProcessList.get(i).getID());
                        } else {

                        }
                    }
                }
                if (FoundCategory == false) {
                    Modi_Tree<Map<String, Object>> NewCategory = createItemForGat(STDTreeItem, SubProcessList.get(i).getCategory(), -44);
                    if (SubProcessList.get(i).getParent() == null) {
                        Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess(NewCategory, SubProcessList.get(i).getName(), "Sub Process", SubProcessList.get(i).getShortDescription(), 2);
                        NewSubProcess.SP = SubProcessList.get(i);
                        NewSubProcess.setExpanded(false);
                        SubProcessTreeItems.add(NewSubProcess);
                        SubProcessIDs.add(SubProcessList.get(i).getID());
                    } else {

                    }
                }
            }
        }
    }

    public void setupDataForProcess3() {

        Process NewMainProcess = new Process("New Procedure", 1, "Shift and mouse left click to Add New Procedure ", 3);
        Modi_Tree<Map<String, Object>> NewMainProcessTreeItem = createItemForProcess(root, NewMainProcess.getShortDescription(), "Main Process", NewMainProcess.getDescription(), 0);
        NewMainProcessTreeItem.MP = NewMainProcess;
        ImageView image_ = new ImageView(imageNew);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        NewMainProcessTreeItem.setGraphic(image_);

        Modi_Tree<Map<String, Object>> ArabicLanguage = createItemForGat(root, "AR", -44);
        Modi_Tree<Map<String, Object>> EnglishLanguage = createItemForGat(root, "EN", -44);

        Modi_Tree<Map<String, Object>> files = createItemForGat(root, "Procedure", -44);
        for (int j = 0; j < TT.ProcedureFilesDBList.size(); j++) {
            Modi_Tree<Map<String, Object>> FileTreeItem = createItemForFiles(files, TT.ProcedureFilesDBList.get(j).getName());
            FileTreeItem.f0 = TT.ProcedureFilesDBList.get(j);
            if (FileTreeItem.f0.getOpened() == 1) {
                Check_If_This_File_In_Open_recent(FileTreeItem.f0.getPath(), stagedummy, FileTreeItem.f0.getName());
            }
        }
        List<SubProcess> SubProcessList = new ArrayList<SubProcess>(TT.SubProcessDBList);
        Collections.sort(SubProcessList, new Comparator<SubProcess>() {
            @Override
            public int compare(SubProcess o1, SubProcess o2) {
                return o1.getSTD().compareTo(o2.getSTD());
            }
        });
        ArrayList<Modi_Tree<Map<String, Object>>> SubProcessTreeItems = new ArrayList<Modi_Tree<Map<String, Object>>>();
        ArrayList<Integer> SubProcessIDs = new ArrayList<Integer>();
        ArrayList<Modi_Tree<Map<String, Object>>> STDTreeItems = new ArrayList<Modi_Tree<Map<String, Object>>>();
        ArrayList<String> STDValues = new ArrayList<String>();
        ArrayList<Modi_Tree<Map<String, Object>>> CatsTreeItems = new ArrayList<Modi_Tree<Map<String, Object>>>();
        ArrayList<String> CatsDValues = new ArrayList<String>();
        for (int i = 0; i < SubProcessList.size(); i++) {
            int SPIndex = SubProcessIDs.indexOf(SubProcessList.get(i).getID());
            if (SPIndex > -1) {
            } else {
                Add_Child2(SubProcessTreeItems, SubProcessIDs, STDTreeItems, STDValues, CatsTreeItems, CatsDValues, ArabicLanguage, EnglishLanguage, SubProcessList.get(i));
            }
        }

    }

    public Modi_Tree<Map<String, Object>> Add_Child2(ArrayList<Modi_Tree<Map<String, Object>>> SPTreeItems, ArrayList<Integer> SPids, ArrayList<Modi_Tree<Map<String, Object>>> STDTreeItems, ArrayList<String> STDValues, ArrayList<Modi_Tree<Map<String, Object>>> CatTreeItems, ArrayList<String> CatValues, Modi_Tree<Map<String, Object>> ArLanguage, Modi_Tree<Map<String, Object>> EnLanguage, SubProcess SP) {
        if (SP.getParent() == null) {
            int STDIndex = STDValues.indexOf(SP.getSTD());
            if (STDIndex <= -1) {
                Modi_Tree<Map<String, Object>> Language = EnLanguage;
                if (SP.getLanguage().matches("AR")) {
                    Language = ArLanguage;
                }
                Modi_Tree<Map<String, Object>> NewSTD = createItemForGat(Language, SP.getSTD(), -44);
                STDTreeItems.add(NewSTD);
                STDValues.add(SP.getSTD());
                Modi_Tree<Map<String, Object>> NewCategory = createItemForGat(NewSTD, SP.getCategory(), -44);
                CatTreeItems.add(NewCategory);
                CatValues.add(SP.getCategory());
                Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess(NewCategory, SP.getName(), "Sub Process", SP.getShortDescription(), 2);
                NewSubProcess.SP = SP;
                NewSubProcess.setExpanded(false);
                SPTreeItems.add(NewSubProcess);
                SPids.add(SP.getID());
                AddActivity(SP,NewSubProcess);
                return NewSubProcess;
            } else {
                int catIndex = CatValues.indexOf(SP.getCategory());
                if (catIndex <= -1) {
                    Modi_Tree<Map<String, Object>> NewCategory = createItemForGat(STDTreeItems.get(STDIndex), SP.getCategory(), -44);
                    CatTreeItems.add(NewCategory);
                    CatValues.add(SP.getCategory());
                    Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess(NewCategory, SP.getName(), "Sub Process", SP.getShortDescription(), 2);
                    NewSubProcess.SP = SP;
                    NewSubProcess.setExpanded(false);
                    SPTreeItems.add(NewSubProcess);
                    SPids.add(SP.getID());
                    AddActivity(SP,NewSubProcess);
                    return NewSubProcess;
                } else {
                    Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess(CatTreeItems.get(catIndex), SP.getName(), "Sub Process", SP.getShortDescription(), 2);
                    NewSubProcess.SP = SP;
                    NewSubProcess.setExpanded(false);
                    SPTreeItems.add(NewSubProcess);
                    SPids.add(SP.getID());
                    AddActivity(SP,NewSubProcess);
                    return NewSubProcess;
                }
            }
        } else {
            int parentIndex = SPids.indexOf(SP.getParent().getID());
            if (parentIndex > -1) {
                Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess(SPTreeItems.get(parentIndex), SP.getName(), "Sub Process", SP.getShortDescription(), 2);
                NewSubProcess.SP = SP;
                NewSubProcess.setExpanded(false);
                SPTreeItems.add(NewSubProcess);
                SPids.add(SP.getID());
                AddActivity(SP,NewSubProcess);
                return NewSubProcess;
            } else {
                Modi_Tree<Map<String, Object>> NewSubProcess = createItemForProcess(Add_Child2(SPTreeItems, SPids, STDTreeItems, STDValues, CatTreeItems, CatValues, ArLanguage, EnLanguage, SP.getParent()), SP.getName(), "Sub Process", SP.getShortDescription(), 2);
                NewSubProcess.SP = SP;
                NewSubProcess.setExpanded(false);
                SPTreeItems.add(NewSubProcess);
                SPids.add(SP.getID());
                AddActivity(SP,NewSubProcess);
                return NewSubProcess;
            }
        }
    }

    public void AddActivity(SubProcess SP,Modi_Tree<Map<String, Object>>SPTreeItem) {
        List<Activity> SPA = new ArrayList<Activity>(SP.getActivities());
        Collections.sort(SPA, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o1.getShortDescription().compareTo(o2.getShortDescription());
            }
        });
        for (int i = 0; i < SPA.size(); i++) {
            Modi_Tree<Map<String, Object>> TA = createItemForProcess(SPTreeItem, "--" + SPA.get(i).getShortDescription(), "Activity", SPA.get(i).getShortDescription(),  3);
            TA.AC = SPA.get(i);
//            SPA.get(i).level_num = Level + 3;
        }
    }

    public void setupDataForDuration() {
        for (int i = 0; i < TT.DurationDBList.size(); i++) {
            Modi_Tree<Map<String, Object>> DurationTreeItem = createItemForDuration(root, TT.DurationDBList.get(i).getValue());
            DurationTreeItem.DU = TT.DurationDBList.get(i);
        }
    }

    public void setupDataForRisk() {
        for (int i = 0; i < TT.RiskDBList.size(); i++) {
            Modi_Tree<Map<String, Object>> RiskTreeItem = createItemForRisk(root, TT.RiskDBList.get(i).getRisk());
            RiskTreeItem.RI = TT.RiskDBList.get(i);
            System.out.println("RiskTreeItem.RI= " + RiskTreeItem.RI.getRisk());
        }
    }

    public void setupDataForTasks() {
        Employee NewEmployee = new Employee("New Employee", 1, "New Employee", "This is New Employee");
        Modi_Tree<Map<String, Object>> NewEmployeeTreeItem = createItemForEmployees(root, "New Employee", "Shift and left mouse click for adding new employee");
        NewEmployeeTreeItem.EM = NewEmployee;
        ImageView image_ = new ImageView(imageNew);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        NewEmployeeTreeItem.setGraphic(image_);
        Modi_Tree<Map<String, Object>> ArabicLanguage = createItemForGat(root, "AR", -44);
        Modi_Tree<Map<String, Object>> EnglishLanguage = createItemForGat(root, "EN", -44);
        Modi_Tree<Map<String, Object>> files = createItemForGat(root, "Job Description", -44);
        for (int j = 0; j < TT.JobDescriptionFilesDBList.size(); j++) {
            Modi_Tree<Map<String, Object>> ff = createItemForFiles(files, TT.JobDescriptionFilesDBList.get(j).getName());
            ff.f1 = TT.JobDescriptionFilesDBList.get(j);
//            if(ff.f1.getOpened()==1){
//                Check_If_This_File_In_Open_recent(ff.f1.getPath(),stagedummy,ff.f1.getName());
//            }
        }
        for (int j = 0; j < TT.TasksCategoriesDBList.size(); j++) {

            Modi_Tree<Map<String, Object>> CategoryInArabic = createItemForGat(ArabicLanguage, TT.TasksCategoriesDBList.get(j), -44);
            Modi_Tree<Map<String, Object>> CategoryInEnglish = createItemForGat(EnglishLanguage, TT.TasksCategoriesDBList.get(j), -44);

            for (int i = 0; i < TT.TasksDBList.size(); i++) {


                if (TT.TasksDBList.get(i) != null && TT.TasksCategoriesDBList.get(j) != null && TT.TasksDBList.get(i).getCategory().matches(TT.TasksCategoriesDBList.get(j))) {
                    Modi_Tree<Map<String, Object>> Task;
                    if (TT.TasksDBList.get(i).getLanguage().matches("AR")) {
                        Task = createItemForProcess(CategoryInArabic, TT.TasksDBList.get(i).getShortDesc(), "Task", TT.TasksDBList.get(i).getDescription(), -1);
                    } else {
                        Task = createItemForProcess(CategoryInEnglish, TT.TasksDBList.get(i).getShortDesc(), "Task", TT.TasksDBList.get(i).getDescription(), -1);
                    }
                    Task.TA = TT.TasksDBList.get(i);
                    Task.setExpanded(false);
                }

            }
        }
    }

    public void Add_Child(Modi_Tree<Map<String, Object>> parent, SubProcess SP, int Level) {
        if (SP.getParent() != null) {
            System.out.println("Add child iss " + SP.getParent().getName());

            Modi_Tree<Map<String, Object>> TCH = createItemForProcess(parent, SP.getName(), "Sub Process", SP.getShortDescription(), Level + 2);
            TCH.SP = SP.getParent();
            SP.getParent().level_num = Level + 2;
            Add_Child(TCH, SP.getParent(), Level + 2);
            parent.setExpanded(false);
            List<Activity> SPA = new ArrayList<Activity>(SP.getActivities());
            Collections.sort(SPA, new Comparator<Activity>() {
                @Override
                public int compare(Activity o1, Activity o2) {
                    return o1.getShortDescription().compareTo(o2.getShortDescription());
                }
            });
            for (int i = 0; i < SPA.size(); i++) {
                Modi_Tree<Map<String, Object>> TA = createItemForProcess(TCH, "--" + SPA.get(i).getShortDescription(), "Activity", SPA.get(i).getShortDescription(), Level + 3);
                TA.AC = SPA.get(i);
                SPA.get(i).level_num = Level + 3;
            }
        } else {
            List<Activity> SPA = new ArrayList<Activity>(SP.getActivities());
            Collections.sort(SPA, new Comparator<Activity>() {
                @Override
                public int compare(Activity o1, Activity o2) {
                    return o1.getShortDescription().compareTo(o2.getShortDescription());
                }
            });
            for (int i = 0; i < SPA.size(); i++) {
                Modi_Tree<Map<String, Object>> TA = createItemForProcess(parent, "--" + SPA.get(i).getShortDescription(), "Activity", SPA.get(i).getShortDescription(), Level + 3);
                TA.AC = SPA.get(i);
                SPA.get(i).level_num = Level + 3;
//                List<SubProcess> SPA22 = new ArrayList<SubProcess>(TA.AC.getSubProcesses());
//                System.out.println(TA.AC.getShortDescription() + "•+ "
//                        + SPA22.size() + "\n");
            }
        }
    }


    public Modi_Tree<Map<String, Object>> createItemForActivity(Modi_Tree<Map<String, Object>> parent, String region, String type, String desc, int Level) {
        Modi_Tree<Map<String, Object>> item = new Modi_Tree<>();
        item.NAAAMe = region;
        item.Desc = desc;
        item.Level = Level;
        Map<String, Object> value = new HashMap<>();
        value.put("Process", region);
        value.put("Type", type);

        item.setValue(value);
        if (Level == 0) {
            javafx.scene.image.Image imageMainx = new javafx.scene.image.Image(sample.Image.class.getResourceAsStream("/Images_And_Icons/icon_main.png"), 20, 20, false, true);
            item.setGraphic(new javafx.scene.image.ImageView(imageMainx));
        } else if (Level % 2 == 0) {
            javafx.scene.image.Image image2x = new javafx.scene.image.Image(sample.Image.class.getResourceAsStream("/Images_And_Icons/iconA.png"), 20, 20, false, true);
            item.setGraphic(new javafx.scene.image.ImageView(image2x));
        } else {
            javafx.scene.image.Image imageACx = new javafx.scene.image.Image(Image.class.getResourceAsStream("/Images_And_Icons/iconAc.png"), 20, 20, false, true);
            item.setGraphic(new javafx.scene.image.ImageView(imageACx));
        }

        parent.getChildren().add(item);
        item.setExpanded(false);
        return item;
    }

    public Modi_Tree<Map<String, Object>> createItemForProcess(Modi_Tree<Map<String, Object>> parent, String name, String type, String desc, int Level) {
        Modi_Tree<Map<String, Object>> item = new Modi_Tree<>();
        item.NAAAMe = name;
        item.Desc = desc;
        item.Level = Level;
        Map<String, Object> value = new HashMap<>();
        value.put("Process", name);
        value.put("Type", type);

        item.setValue(value);
        if (Level == 0) {
            javafx.scene.image.Image imageMainx = new javafx.scene.image.Image(sample.Image.class.getResourceAsStream("/Images_And_Icons/icon_main.png"), 20, 20, false, true);
            ImageView image_ = new ImageView(imageMainx);
            image_.setSmooth(true);
            image_.setFitWidth(20);
            image_.setFitHeight(20);
            item.setGraphic(image_);
        } else if (Level % 2 == 0) {
            javafx.scene.image.Image image2x = new javafx.scene.image.Image(sample.Image.class.getResourceAsStream("/Images_And_Icons/iconA.png"), 20, 20, false, true);
            ImageView image_ = new ImageView(image2x);
            image_.setSmooth(true);
            image_.setFitWidth(20);
            image_.setFitHeight(20);
            item.setGraphic(image_);
        } else {
            javafx.scene.image.Image imageACx = new javafx.scene.image.Image(Image.class.getResourceAsStream("/Images_And_Icons/iconAc.png"), 20, 20, false, true);
            ImageView image_ = new ImageView(imageACx);
            image_.setSmooth(true);
            image_.setFitWidth(20);
            image_.setFitHeight(20);
            item.setGraphic(image_);
        }

        parent.getChildren().add(item);
        item.setExpanded(false);
        return item;
    }

    public Modi_Tree<Map<String, Object>> createItemForDuration(Modi_Tree<Map<String, Object>> parent, String valuee) {
        Modi_Tree<Map<String, Object>> item = new Modi_Tree<>();
        String re = valuee;
        item.NAAAMe = re;
        item.Level = 11;
        item.Datee = "true";
        item.Desc = re;
        Map<String, Object> value = new HashMap<>();
        value.put("Process", re);
        item.setValue(value);
        parent.getChildren().add(item);
        item.setExpanded(false);
        javafx.scene.image.Image imagex = new javafx.scene.image.Image(Image.class.getResourceAsStream("/Images_And_Icons/clock.png"), 15, 15, false, true);
        ImageView image_ = new ImageView(imagex);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        item.setGraphic(image_);

        return item;
    }

    public Modi_Tree<Map<String, Object>> createItemForEmployees(Modi_Tree<Map<String, Object>> parent, String role, String Des) {
        Modi_Tree<Map<String, Object>> item = new Modi_Tree<>();
        item.NAAAMe = role;
        item.Level = 1;
        item.Desc = Des;
        Map<String, Object> value = new HashMap<>();
        value.put("Process", role);
        item.setValue(value);
        parent.getChildren().add(item);
        item.setExpanded(false);
        javafx.scene.image.Image imagex = new javafx.scene.image.Image(Image.class.getResourceAsStream("/Images_And_Icons/icon.png"), 30, 30, false, true);
        ImageView image_ = new ImageView(imagex);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        item.setGraphic(image_);
        return item;
    }

    public Modi_Tree<Map<String, Object>> createItemForRisk(Modi_Tree<Map<String, Object>> parent, String Des) {
        Modi_Tree<Map<String, Object>> item = new Modi_Tree<>();
        item.NAAAMe = Des;
        item.Level = 1;
        item.Desc = Des;
        Map<String, Object> value = new HashMap<>();
        value.put("Process", Des);
        item.setValue(value);
        parent.getChildren().add(item);
        item.setExpanded(false);
        javafx.scene.image.Image imagex = new javafx.scene.image.Image(Image.class.getResourceAsStream("/Images_And_Icons/risk.png"), 30, 30, false, true);
        ImageView image_ = new ImageView(imagex);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        item.setGraphic(image_);

        return item;
    }

    public Modi_Tree<Map<String, Object>> createItemForFiles(Modi_Tree<Map<String, Object>> parent, String region) {
        Modi_Tree<Map<String, Object>> item = new Modi_Tree<>();
        item.NAAAMe = region;
        item.Level = -11;
        Map<String, Object> value = new HashMap<>();
        value.put("Process", region);
        item.setValue(value);
        parent.getChildren().add(item);
        item.setExpanded(false);
        javafx.scene.image.Image imagex = new javafx.scene.image.Image(Image.class.getResourceAsStream("/Images_And_Icons/file.png"), 20, 20, false, true);
        item.setGraphic(new javafx.scene.image.ImageView(imagex));

        return item;
    }

    public Modi_Tree<Map<String, Object>> createItemForGat(Modi_Tree<Map<String, Object>> parent, String region, int id) {
        Modi_Tree<Map<String, Object>> item = new Modi_Tree<>();
        item.NAAAMe = region;
        item.Level = id;
        item.Desc = region;
        Map<String, Object> value = new HashMap<>();
        value.put("Process", region);
        item.setValue(value);
        parent.getChildren().add(item);
        item.setExpanded(false);
        return item;
    }

    public void addColumn(String label, String dataIndex) {
        TreeTableColumn<Map<String, Object>, String> column = new TreeTableColumn<>("");
//        column.setTextFill(Color.RED);
//        column.setPrefWidth(227.8);
        column.setPrefWidth(500.8);
        column.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Map<String, Object>, String> param) -> {
                    ObservableValue<String> result = new ReadOnlyStringWrapper("");
                    if (param.getValue().getValue() != null) {
                        result = new ReadOnlyStringWrapper("" + param.getValue().getValue().get(dataIndex));
                    }
                    return result;
                }
        );
//        column.setStyle("-fx-background-color: #52c2fa;");
        tree.getColumns().add(column);
        tree.getStyleClass().add("noheader");
    }
}
