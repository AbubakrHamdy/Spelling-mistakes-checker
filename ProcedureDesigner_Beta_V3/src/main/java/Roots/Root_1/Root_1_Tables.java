package Roots.Root_1;

import Entities.ProcedureFile;
import Roots.EmployeeLabel;
import Roots.General_Label;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeTableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sample.Modi_Tree;
import sample.Modi_TreeTable;

import java.util.Map;

import static Roots.Root_1.Main_Root_1.M_R_1;
import static sample.Main.*;
import static sample.Main_Menus.Operating_Procedure_Form_Menu_Item;

public class Root_1_Tables {
    public static Modi_TreeTable ProcessTree;
    public static VBox ProcessTable;

    public static Modi_TreeTable EmployeesTree;
    public static VBox EmployeesTable;

    public static Modi_TreeTable DurationTree;
    public static VBox DurationTable;

    public static Modi_TreeTable RiskTree;
    public static VBox RiskTable;

    public Root_1_Tables(Scene scene) {
        SetupTablesTypeAndSize();
        SetupTablesMouseFunctions(scene);
    }

    private void SetupTablesTypeAndSize() {

        Label ProcessTableHeader = new Label("Process");
        ProcessTableHeader.setStyle(HeaderStyle);
        ProcessTableHeader.setPrefWidth(200);

        ProcessTree = new Modi_TreeTable();
        ProcessTree.addColumn("Process", "Process");
        ProcessTree.setupDataForProcess3();
        ProcessTree.tree.setMaxWidth(200);
        ProcessTree.tree.setPrefHeight(((Screen_Height - 86) / 3) * 2);
        ProcessTree.tree.setMaxHeight(((Screen_Height - 86) / 3) * 2);
        ProcessTree.tree.setMinHeight(((Screen_Height - 86) / 3) * 2);

        ProcessTable = new VBox();
        ProcessTable.getChildren().add(ProcessTableHeader);
        ProcessTable.getChildren().add(ProcessTree.tree);
        ProcessTable.setSpacing(-.4);
  ///////////////////////////////////////
        Label EmployeesTableHeader = new Label("Employees");
        EmployeesTableHeader.setStyle(HeaderStyle);
        EmployeesTableHeader.setPrefWidth(200);

        EmployeesTree = new Modi_TreeTable();
        EmployeesTree.addColumn("Employees", "Process");
        EmployeesTree.setupDataForEmployees();
        EmployeesTree.tree.setMaxWidth(200);
//        EmployeesTree.tree.setPrefHeight(Screen_Height - 87);
//        EmployeesTree.tree.setMaxHeight(Screen_Height - 87);
//        EmployeesTree.tree.setMinHeight(Screen_Height - 87);
        EmployeesTree.tree.setPrefHeight(((Screen_Height - 86) / 3) * 2);
        EmployeesTree.tree.setMaxHeight(((Screen_Height - 86) / 3) * 2);
        EmployeesTree.tree.setMinHeight(((Screen_Height - 86) / 3) * 2);

        EmployeesTable = new VBox();
        EmployeesTable.getChildren().add(EmployeesTableHeader);
        EmployeesTable.getChildren().add(EmployeesTree.tree);
        EmployeesTable.setSpacing(-.4);
        //////////////////////////////////

        Label DurationTableHeader = new Label("Duration");
        DurationTableHeader.setStyle(HeaderStyle);
        DurationTableHeader.setPrefWidth(200);

        DurationTree = new Modi_TreeTable();
        DurationTree.addColumn("Duration", "Process");
        DurationTree.setupDataForDuration();
        DurationTree.tree.setMaxWidth(200);
        DurationTree.tree.setPrefHeight(((Screen_Height - 86) / 3));
        DurationTree.tree.setMaxHeight(((Screen_Height - 86) / 3));
        DurationTree.tree.setMinHeight(((Screen_Height - 86) / 3));

        DurationTable = new VBox();
        DurationTable.getChildren().add(DurationTableHeader);
        DurationTable.getChildren().add(DurationTree.tree);
        DurationTable.setSpacing(-.4);
//////////////////////////////////////
        Label RiskTableHeader = new Label("Risk");
        RiskTableHeader.setStyle(HeaderStyle);
        RiskTableHeader.setPrefWidth(200);

        RiskTree = new Modi_TreeTable();
        RiskTree.addColumn("Risk", "Process");
        RiskTree.setupDataForRisk();
        RiskTree.tree.setMaxWidth(200);
        RiskTree.tree.setPrefHeight(((Screen_Height - 86) / 3));
        RiskTree.tree.setMaxHeight(((Screen_Height - 86) / 3));
        RiskTree.tree.setMinHeight(((Screen_Height - 86) / 3));

        RiskTable = new VBox();
        RiskTable.getChildren().add(RiskTableHeader);
        RiskTable.getChildren().add(RiskTree.tree);
        RiskTable.setSpacing(-.4);
    }

    private void SetupTablesMouseFunctions(Scene scene) {
//        ProcessTree.tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (ProcessTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree
//                        && event.isShiftDown() &&
//                        ((Modi_Tree) ProcessTree.tree.getTreeItem(ProcessTree.tree.getSelectionModel().getSelectedIndex())).Level != -44) {
//                    int index = ProcessTree.tree.getSelectionModel().getSelectedIndex();
//                    General_Label labex = new ProcessLabel(((Modi_Tree) ProcessTree.tree.getTreeItem(index)));
//                    if (labex.Type_id == 0) {
//                        MainProcess = ((ProcessLabel)labex).MP;
//                    };
//                }
//            }
//        });
//
/////////////////////////////////////////// i there is a problem un comment it
////        final Label label = new Label("Address Book");
////        label.setFont(new Font("Arial", 20));
//
//        EmployeesTree.tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (EmployeesTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree && event.isShiftDown()) {
//                    int index = EmployeesTree.tree.getSelectionModel().getSelectedIndex();
//                    Modi_Label labex = new Modi_Label(((Modi_Tree) EmployeesTree.tree.getTreeItem(index)));
//                    Operation.getItems().remove(Save_Employee_Tasks);
//                    Operation.getItems().remove(Insert_Employee);
//                    if (((Modi_Tree<Map<String, Object>>) EmployeesTree.tree.getTreeItem(index)).EM.getRole().matches("_New Employee_J1")) {
//                        //System.out.println("Hoba Lala");
//                        //     root.getChildren().removeAll(Save_Employee_Tasks);
//                    } else {
//                        Operation.getItems().add(Save_Employee_Tasks);
//                    }
//                    Operation.getItems().add(Insert_Employee);
//
//                }
//            }
//        });
//
//        DurationTree.tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (DurationTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree && event.isShiftDown()) {
//                    int index = DurationTree.tree.getSelectionModel().getSelectedIndex();
//                    Modi_Label labex = new Modi_Label(((Modi_Tree) DurationTree.tree.getTreeItem(index)));
//                }
//            }
//        });

        DurationTree.tree.setRowFactory(t -> {
            TreeTableRow<Map<String, Object>> cell = new TreeTableRow<Map<String, Object>>() {
                @Override
                public void updateItem(Map<String, Object> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setTooltip(null);
                    } else if (!empty) {
                        setTooltip(new Tooltip(((Modi_Tree<Map<String, Object>>) getTreeItem()).Desc));
                    }
                }
            };
            return cell;
        });

        RiskTree.tree.setRowFactory(t -> {
            TreeTableRow<Map<String, Object>> cell = new TreeTableRow<Map<String, Object>>() {
                @Override
                public void updateItem(Map<String, Object> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setTooltip(null);
                    } else if (!empty) {
                        setTooltip(new Tooltip(((Modi_Tree<Map<String, Object>>) getTreeItem()).Desc));
                    }
                }
            };
            return cell;
        });

        ProcessTree.tree.setRowFactory(t -> {
            TreeTableRow<Map<String, Object>> cell = new TreeTableRow<Map<String, Object>>() {
                @Override
                public void updateItem(Map<String, Object> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setTooltip(null);
                    } else if (!empty) {
                        setTooltip(new Tooltip(((Modi_Tree<Map<String, Object>>) getTreeItem()).Desc));
                    }
                }
            };
            return cell;
        });

        EmployeesTree.tree.setRowFactory(t -> {
            TreeTableRow<Map<String, Object>> cell = new TreeTableRow<Map<String, Object>>() {
                @Override
                public void updateItem(Map<String, Object> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setTooltip(null);
                    } else if (!empty) {
                        setTooltip(new Tooltip(((Modi_Tree<Map<String, Object>>) getTreeItem()).Desc));
                    }
                }
            };
            return cell;
        });

        ProcessTree.tree.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ProcessTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree) {
                    // System.out.println("setOnMouseDragged");
                    int index = ProcessTree.tree.getSelectionModel().getSelectedIndex();
                    DragedTreeItem = ((Modi_Tree) ProcessTree.tree.getTreeItem(index));
                    scene.setCursor(Cursor.CLOSED_HAND);
                }
            }
        });

        ProcessTree.tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2&&ProcessTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree){
                        int index = ProcessTree.tree.getSelectionModel().getSelectedIndex();
                        Modi_Tree MT = ((Modi_Tree) ProcessTree.tree.getTreeItem(index));
                        if (MT.Level == 0) {
                            General_Label labex = new ProcessLabel(MT);
//                            labex.relocate(event.getSceneX(), event.getSceneY());
                            DragedTreeItem = null;
                            MainProcess = ((ProcessLabel) labex).MP;
                            Operating_Procedure_Form_Menu_Item.fire();                        }
                        System.out.println("Double clicked");
                    }
                }
            }
        });

        ProcessTree.tree.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ProcessTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree && DragedTreeItem != null && ((Modi_Tree) DragedTreeItem).Level != -44 && RequestToAddLabel(DragedTreeItem)) {
                    if (DragedTreeItem.Level == -11) {
                        try {
                            ProcedureFile fileDrgged = DragedTreeItem.f0;
                            OpenTheDraggedFile(M_R_1,fileDrgged.getPath());
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                    else if (DragedTreeItem.Level == 0) {
                        General_Label labex = new ProcessLabel(DragedTreeItem);
                        labex.relocate(event.getSceneX(), event.getSceneY());
                        DragedTreeItem = null;
                        MainProcess = ((ProcessLabel) labex).MP;
                        Operating_Procedure_Form_Menu_Item.fire();
                    }
                    else if (((Modi_Tree) DragedTreeItem).SP != null) {
                        General_Label labex = new SubProcessLabel(DragedTreeItem);
                        labex.relocate(event.getSceneX(), event.getSceneY());
                        DragedTreeItem = null;
                    }
                    else if (((Modi_Tree) DragedTreeItem).AC != null) {
                        General_Label labex = new ActivityLabel(DragedTreeItem);
                        labex.relocate(event.getSceneX(), event.getSceneY());
                        DragedTreeItem = null;
                    }
                }
                scene.setCursor(Cursor.DEFAULT);
                DragedTreeItem = null;
            }
        });

        EmployeesTree.tree.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (EmployeesTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree) {
                    int index = EmployeesTree.tree.getSelectionModel().getSelectedIndex();
                    DragedTreeItem = ((Modi_Tree) EmployeesTree.tree.getTreeItem(index));
                    scene.setCursor(Cursor.CLOSED_HAND);
                }
            }
        });
        EmployeesTree.tree.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (DragedTreeItem != null && RequestToAddLabel(DragedTreeItem)) {
                    General_Label labex = new EmployeeLabel(DragedTreeItem);
                    labex.relocate(event.getSceneX(), event.getSceneY());
                }
                scene.setCursor(Cursor.DEFAULT);
                DragedTreeItem = null;
            }
        });

        DurationTree.tree.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (DurationTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree) {
                    int index = DurationTree.tree.getSelectionModel().getSelectedIndex();
                    DragedTreeItem = ((Modi_Tree) DurationTree.tree.getTreeItem(index));
                    scene.setCursor(Cursor.CLOSED_HAND);
                }
            }
        });
        DurationTree.tree.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (DragedTreeItem != null) {
                    General_Label labex = new DurationLabel(DragedTreeItem);
                    labex.relocate(event.getSceneX(), event.getSceneY());
                }
                scene.setCursor(Cursor.DEFAULT);
                DragedTreeItem = null;
            }
        });

        RiskTree.tree.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (RiskTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree) {
                    int index = RiskTree.tree.getSelectionModel().getSelectedIndex();
                    DragedTreeItem = ((Modi_Tree) RiskTree.tree.getTreeItem(index));
                    scene.setCursor(Cursor.CLOSED_HAND);
                }
            }
        });
        RiskTree.tree.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (DragedTreeItem != null) {
                    General_Label labex = new RiskLabel(DragedTreeItem);
                    labex.relocate(event.getSceneX(), event.getSceneY());
                }
                scene.setCursor(Cursor.DEFAULT);
                DragedTreeItem = null;
            }
        });

    }
}
