package Roots.Root_1;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static Roots.Root_1.Root_1_Forms.Process_Current_Form_Root;
import static Roots.Root_1.Root_1_Tables.*;
import static sample.Main.Screen_Height;
import static sample.Main.Screen_Width;

public class Main_Root_1 {
    public static Root_1_Forms F1;
    public static Root_1_Tables T1;
    public static Modi_Group_Functions_Root_1 M_R_1;
    public static BorderPane Diagram_Play_Ground_1;

    public Main_Root_1(Scene scene) {
        M_R_1 = new Modi_Group_Functions_Root_1();
        F1 = new Root_1_Forms();
        T1 = new Root_1_Tables(scene);

        GridPane GPL = new GridPane();
        VBox GPR = new VBox();

        M_R_1.setPrefWidth(Screen_Width - 12);
        M_R_1.setPrefHeight(Screen_Height - 86);
        Diagram_Play_Ground_1 = new BorderPane();
        Group group = new Group();
        group.getChildren().add(Diagram_Play_Ground_1);

        GPL.add(ProcessTable, 0, 0);
        GPL.add(DurationTable, 0, 1);
        GPL.add(Process_Current_Form_Root, 1, 0, 1, 2);
        GPL.add(group, 2, 0, 1, 2);

        GPR.getChildren().add(EmployeesTable);
        GPR.getChildren().add(RiskTable);

        Diagram_Play_Ground_1.setPrefWidth(Screen_Width - ProcessTree.tree.getMaxWidth() - EmployeesTree.tree.getMaxWidth() - 10);

        M_R_1.setLeft(GPL);
        M_R_1.setRight(GPR);
    }

    public static void Setup_Main_Root_1_To_English() {
        F1.Setup_Root_1_To_English();
    }

    public static void Setup_Main_Root_1_To_Arabic() {
        F1.Setup_Root_1_To_Arabic();
    }
}
