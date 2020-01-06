package Roots.Root_2;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import static Roots.Root_2.Root_2_Forms.Current_JD_Form_Root;
import static Roots.Root_2.Root_2_Tables.TasksTable;
import static Roots.Root_2.Root_2_Tables.TasksTree;
import static sample.Main.Screen_Height;
import static sample.Main.Screen_Width;

public class Main_Root_2 {
    public static Root_2_Forms R_2_F;
    public static Root_2_Tables R_2_T;
    public static Modi_Group_Functions_Root_2 M_R_2;
    public static BorderPane Diagram_Play_Ground_2;
    public Main_Root_2(Scene scene) {

        M_R_2=new Modi_Group_Functions_Root_2();
        R_2_F = new Root_2_Forms();
        R_2_T = new Root_2_Tables(scene);
        GridPane GP=new GridPane();

//        M_R_2.setStyle("-fx-background-color:#52c2fa;");

        M_R_2.setPrefWidth(Screen_Width-12);
        M_R_2.setPrefHeight(Screen_Height-86);

        Diagram_Play_Ground_2=new BorderPane();
        Group group=new Group();
        group.getChildren().add(Diagram_Play_Ground_2);
        Diagram_Play_Ground_2.setPrefWidth(Screen_Width-TasksTree.tree.getMaxWidth()-10);

        GP.add(TasksTable,0,0);
        GP.add(Current_JD_Form_Root,1,0);
        GP.add(group,2,0);

        M_R_2.setLeft(GP);
//        M_R_2.getChildren().setAll(Task_Form_Root,TasksTree.tree);
    }

    public static void Setup_Main_Root_1_To_English() {
        R_2_F.Setup_Root_2_To_English();
    }

    public static void Setup_Main_Root_1_To_Arabic() {
        R_2_F.Setup_Root_2_To_Arabic();
    }
}
