package sample;

import javafx.geometry.Bounds;
import javafx.scene.layout.VBox;

import static Roots.Root_1.Main_Root_1.Diagram_Play_Ground_1;
import static Roots.Root_1.Root_1_Tables.ProcessTree;
import static Roots.Root_2.Main_Root_2.Diagram_Play_Ground_2;
import static Roots.Root_2.Root_2_Tables.TasksTree;
import static sample.Main.*;

public class Diagram_WorkSpace_Dimensions {
    public static Double UperBound =0.0;
    public static Double LowerBound =0.0;
    public static Double RightBound = 0.0;
    public static Double LeftBound = 0.0;

    Diagram_WorkSpace_Dimensions() {
        UperBound = 87.0;
        LowerBound = Screen_Height - 87;
    }

    public void WorkSpace_With_Closed_Form(VBox Form) {
//        Bounds boundsInScene = Form.getBoundsInParent();
        Double form_Width = Form.getPrefWidth();;
        System.out.println("form bounds before close ="+LeftBound);

        RightBound = RightBound - form_Width;
        LeftBound = LeftBound - form_Width;
        System.out.println("form bounds after close ="+LeftBound);

        for (int i = 0; i < Labels_vector.size(); i++) {
            if (Labels_vector.get(i) != null) {
                Labels_vector.get(i).setTranslateX(Labels_vector.get(i).getTranslateX() - form_Width);
            }
        }
        Reconnect();
    }

    public void WorkSpace_With_opened_Form(VBox Form) {
//        Bounds boundsInScene = Form.getLayoutBounds();
        Double form_Width =Form.getPrefWidth();
//        System.out.println("form bounds before  open="+LeftBound);
//
//        System.out.println("form bounds width1="+boundsInScene.getWidth());
//        System.out.println("form bounds width2="+Form.getPrefWidth());
//

        RightBound = RightBound + form_Width;
        LeftBound = LeftBound + form_Width;
//        System.out.println("form bounds after open ="+LeftBound);

        for (int i = 0; i < Labels_vector.size(); i++) {
            if (Labels_vector.get(i) != null) {
                Labels_vector.get(i).setTranslateX(Labels_vector.get(i).getTranslateX() + form_Width);
            }
        }
        Reconnect();
    }

    public void WorkSpace_With_Root_1() {
        Double Tree_Width = ProcessTree.tree.getMaxWidth();
        Bounds WorkSpaceBounds = Diagram_Play_Ground_1.getBoundsInParent();
        Double WorkSpace_Width = WorkSpaceBounds.getWidth();

//        System.out.println("tree bounds= "+TreeBounds.getWidth());
        System.out.println("tree width= "+ProcessTree.tree.getMaxWidth());
        System.out.println("work bounds= "+WorkSpaceBounds.getWidth());
        System.out.println("work width= "+Diagram_Play_Ground_1.getWidth());

        LeftBound = Tree_Width;
        RightBound = Tree_Width + WorkSpace_Width;
    }

    public void WorkSpace_With_Root_2() {
        Double Tree_Width = TasksTree.tree.getMaxWidth();
        Bounds WorkSpaceBounds = Diagram_Play_Ground_2.getBoundsInParent();
        Double WorkSpace_Width = WorkSpaceBounds.getWidth();
        LeftBound = Tree_Width;
        RightBound = Tree_Width + WorkSpace_Width;
    }

}

