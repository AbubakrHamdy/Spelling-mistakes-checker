package Roots;

import Roots.Root_1.DurationLabel;
import Roots.Root_1.ProcessLabel;
import Roots.Root_1.RiskLabel;
import Roots.Root_2.TaskLabel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import sample.Main_Shortcut_Buttons;

import java.io.Serializable;
import java.util.Vector;

import static sample.Main.*;

public class General_Line extends Line implements Serializable {
    public int Line_Index;
    public int S_Index;
    public int E_Index;
    public boolean CanBeConnceted = true;


    public General_Line(boolean ex) {
        CanBeConnceted = ex;
    }

    public General_Line(General_Label L1, General_Label L2) {
        this.toBack();
        S_Index = L1.index;
        E_Index = L2.index;
        Line_Index = Lines_vector.size();
        Add_Style(Labels_vector, Lines_vector);

    }

    public General_Line(General_Line l, Vector<General_Label> labels) {
        CanBeConnceted = l.CanBeConnceted;
        S_Index = l.S_Index;
        E_Index = l.E_Index;
    }

    public General_Line() {
    }

    public void Add_Style(Vector<General_Label> labels, Vector<General_Line> Lines) {
        Line_Index = Lines_vector.size();
        Lines.add(this);
//        setStrokeWidth(1);
//        Reconnect();
        CreateLine(this);
        Line_Functions(this);
        root.getChildren().add(this);
        Lines_vector.get(Line_Index).CreateLine(Lines_vector.get(Line_Index));//may make connection problem

    }

    public General_Line(int s) {
    }

    public void Start_ConnectionBetweenNodeAndMouse(int s, General_Line L, MouseEvent mouseEvent) {

        General_Label S = Labels_vector.get(s);

        L.setStartX(S.getTranslateX() + (S.widthProperty().get() / 2) + S.layoutXProperty().getValue());
        L.setStartY(S.getTranslateY() + (S.heightProperty().get() / 2) + S.layoutYProperty().getValue());
        L.setEndX(mouseEvent.getSceneX());
        L.setEndY(mouseEvent.getSceneY());
        L.toBack();
        S.toFront();
        L.setStrokeLineCap(StrokeLineCap.ROUND);
//        L.getStrokeDashArray().setAll(20.0, 1.4);
    }

    public void CreateLine(Line L) {

        General_Label S = Labels_vector.get(S_Index);
        General_Label E = Labels_vector.get(E_Index);

        L.setStartX(S.getTranslateX() + (S.widthProperty().get() / 2) + S.layoutXProperty().getValue());
        L.setStartY(S.getTranslateY() + (S.heightProperty().get() / 2) + S.layoutYProperty().getValue());
        L.setEndX(E.getTranslateX() + (E.widthProperty().get() / 2) + E.layoutXProperty().getValue());
        L.setEndY(E.getTranslateY() + (E.heightProperty().get() / 2) + E.layoutYProperty().getValue());
//        Reconnect();
//        L.toBack();
        S.toFront();
        E.toFront();
        L.setStrokeLineCap(StrokeLineCap.BUTT);
        L.setStrokeWidth(2);
        L.getStrokeDashArray().setAll(10.0, 2.0);
        if (S instanceof TaskLabel || E instanceof TaskLabel) {
            L.setStroke(Color.YELLOW);
        } else if (S instanceof EmployeeLabel || E instanceof EmployeeLabel) {
            L.setStroke(Color.BLUE);
        } else if (S instanceof ProcessLabel || E instanceof ProcessLabel) {
            L.setStroke(Color.valueOf("#ff3def"));
        } else if (S instanceof DurationLabel || E instanceof DurationLabel) {
        } else if (S instanceof RiskLabel || E instanceof RiskLabel) {
            L.setStroke(Color.RED);
        } else {
            L.setStroke(Color.YELLOWGREEN);
        }


    }

    public void Line_Functions(General_Line line) {
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (line instanceof General_Line) {
                    if (DeleteOn || event.isAltDown()) {
                        DeleteLine(line);
                    }
                    if (event.isControlDown()) {
                        System.out.println("ID of this line is " + line.Line_Index);
                    }
                }

            }
        });
    }

    public void DeleteLine(General_Line line) {
        General_Label S = Labels_vector.get(S_Index);
        General_Label E = Labels_vector.get(E_Index);
        S.DeleteConnection(E);
        E.DeleteConnection(S);
        System.out.println("line Deleted");
        int idn = root.getChildren().indexOf(line);
        System.out.println("before adding = " + root.getChildren().size());
        root.getChildren().remove(line);
        System.out.println("after adding = " + root.getChildren().size());
        CanBeConnceted = false;
        Lines_vector.set(line.Line_Index, null);
//                        H.add(Labels_vector, Lines_vector);
        DeleteOn = false;
        Main_Shortcut_Buttons.UnClick();
    }


}
