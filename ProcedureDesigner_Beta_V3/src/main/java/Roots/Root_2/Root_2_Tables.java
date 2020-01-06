package Roots.Root_2;

import Entities.JobDescriptionFile;
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

import java.io.IOException;
import java.util.Map;

import static Roots.Root_2.Main_Root_2.M_R_2;
import static sample.Main.*;
import static sample.Main_Menus.JobDescription_Form_Menu_Item;
public class Root_2_Tables {

    public static Modi_TreeTable TasksTree;
    public static VBox TasksTable;

    public Root_2_Tables(Scene scene) {
        SetupTablesTypeAndSize();
        SetupTablesMouseFunctions(scene);
    }

    private void SetupTablesTypeAndSize() {
        Label TasksTableHeader = new Label("Tasks");
        TasksTableHeader.setStyle(HeaderStyle);
        TasksTableHeader.setPrefWidth(200);
//        TasksTableHeader.setOnMouseClicked(event -> {
//            CloseVBoxForm(TasksTable);
//        });
        TasksTree = new Modi_TreeTable();
        TasksTree.addColumn("Tasks", "Process");
        TasksTree.setupDataForTasks();
        TasksTree.tree.setPrefHeight(Screen_Height - 87);
        TasksTree.tree.setMaxWidth(200);

        TasksTable = new VBox();
        TasksTable.getChildren().add(TasksTableHeader);
        TasksTable.getChildren().add(TasksTree.tree);
        TasksTable.setSpacing(-.4);
    }

    private void SetupTablesMouseFunctions(Scene scene) {

        TasksTree.tree.setRowFactory(t -> {
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
        TasksTree.tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2&&TasksTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree){
                        int index = TasksTree.tree.getSelectionModel().getSelectedIndex();
                        Modi_Tree MT = ((Modi_Tree) TasksTree.tree.getTreeItem(index));
                        if (MT.Level == 1) {
                            General_Label labex = new EmployeeLabel(MT);
//                            labex.relocate(event.getSceneX(), event.getSceneY());
                            JobDescription_Form_Menu_Item.fire();
                        }
                        System.out.println("Double clicked");
                    }
                }
            }
        });
//        TasksTree.tree.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
////                if (TasksTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree && event.isShiftDown()) {
////                    int index = TasksTree.tree.getSelectionModel().getSelectedIndex();
////                    Modi_Label labex = new Modi_Label(((Modi_Tree) TasksTree.tree.getTreeItem(index)));
////                }
//            }
//        });
        TasksTree.tree.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isStillSincePress() && TasksTree.tree.getSelectionModel().getSelectedItem() instanceof Modi_Tree) {
                    int index = TasksTree.tree.getSelectionModel().getSelectedIndex();
                    DragedTreeItem = ((Modi_Tree) TasksTree.tree.getTreeItem(index));
                    scene.setCursor(Cursor.CLOSED_HAND);
                    TasksTree.tree.getSelectionModel().clearSelection();
                }
            }
        });
        TasksTree.tree.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (DragedTreeItem != null && DragedTreeItem.Level != -44 && RequestToAddLabel(DragedTreeItem)) {
                    if (DragedTreeItem.Level == 1) {
                        General_Label labex = new EmployeeLabel(DragedTreeItem);
                        labex.relocate(event.getSceneX(), event.getSceneY());
                        JobDescription_Form_Menu_Item.fire();
                    } else if (DragedTreeItem.Level == -1) {
                        General_Label labex = new TaskLabel(DragedTreeItem);
                        labex.relocate(event.getSceneX(), event.getSceneY());
                    } else if (DragedTreeItem.Level == -11) {
                        try {
                            JobDescriptionFile fileDrgged = DragedTreeItem.f1;
                            OpenTheDraggedFile(M_R_2, fileDrgged.getPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }
                scene.setCursor(Cursor.DEFAULT);
                DragedTreeItem = null;
            }
        });
    }
}
