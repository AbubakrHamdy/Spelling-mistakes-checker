package Roots;

import Entities.JobDescriptionFile;
import Entities.ProcedureFile;
import Not_Used.ConfirmBox;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.ConfirmBoxArabic;
import sample.Modi_Tree;

import java.io.Serializable;
import java.util.Vector;

import static sample.Main.*;

public class General_Label extends Label implements Serializable {

    public String Name = "";
    public int index;
    public Vector<Integer> lines_id = new Vector<Integer>();
    public String Des = "";
    public double Type_id = -44;
    public String Type = "";
    public JobDescriptionFile ff1;
    public ProcedureFile ff0;
    public boolean Exist;

    public General_Label(boolean ex) {
        Exist = ex;
        this.setScaleX(this.getScaleX() * Zoom_Scale);
        this.setScaleY(this.getScaleY() * Zoom_Scale);
    }

    public General_Label(Modi_Tree t) {
        if (Type_id == -11) {
            if (t.f1 != null) {
                ff1 = new JobDescriptionFile(t.f1);
            } else {
                ff0 = new ProcedureFile((t.f0));
            }
            Type = "File";
        }
    }

    General_Label(General_Label l) {
        if (Type_id == -11) {
            if (l.ff1 != null) {
                ff1 = new JobDescriptionFile(l.ff1);
            } else {
                ff0 = new ProcedureFile((l.ff0));
            }
        }
    }

    public General_Label() {
    }

    public void LabelStyle() {
        if (Type_id == -11) {
            this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
            if (ff1 != null) {
                Des = ff1.getName();
            } else {
                Des = ff0.getName();
            }
        }
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);
    }

    public void LabelMouseClickFunctions(General_Label labex) {
        labex.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (labex instanceof General_Label) {
                    if (LinkOn || event.isShiftDown()) {
                        RequestToLinkLabels(labex);
                    }
                    if (DeleteOn || event.isAltDown()) {
                        boolean answer;
                        if (araabc) {
                            answer = ConfirmBoxArabic.display("مسح", "هل تريد الحذف ؟");
                        } else {
                            answer = ConfirmBox.display("Delete ", "Are you want to Delete it ?");
                        }
                        if (answer) {
                            System.out.println("Node Deleted");
                            DeleteLabel();
//                            H.add(Labels_vector, Lines_vector);
                        }
                        DeleteOn = false;
//                        UnClick();
                    }
                    if (event.isControlDown()) {
                        GetLabelInfo();
                    }
                }
            }
        });
        mg.makeDraggable(labex);
    }

    public void FillLabelFields(General_Label label, String name, double type_id, boolean exist, int index) {
        label.setText(name);
        label.Name = name;
        label.Type_id = type_id;
        label.Exist = exist;
        label.index = index;
        label.setScaleX(this.getScaleX() * Zoom_Scale);
        label.setScaleY(this.getScaleY() * Zoom_Scale);
        if( !(label instanceof LabelStorage)){
            root.getChildren().add(label);
        }
    }

    public void GetLabelInfo() {
    }

    public void LabelMouseHoverFunctions(General_Label labelx) {
        labelx.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (labelx instanceof General_Label) {
                    final Tooltip tooltip = new Tooltip();
                    tooltip.setFont(Font.font(12));
                    tooltip.setText(labelx.Des);
                    labelx.setTooltip(tooltip);
                }
            }

        });
    }
    public void DeleteLabel() {
        for (int i = 0; i < this.lines_id.size(); i++) {
            General_Line line = Lines_vector.get(this.lines_id.get(i));
            if (line != null) {
                line.DeleteLine(line);
            }
        }
        this.Exist = false;
        Labels_vector.set(this.index, null);
        root.getChildren().remove(this);
    }
    public void RequestToLinkLabels(General_Label labex) {
        if (start != -1) {
            if (start != labex.index) {
                if (Exist && CheckIFThe2LabelsCanBeConnectedAndConncetThem()) {

                    General_Line line = new General_Line(Labels_vector.get(start), labex);
                    labex.lines_id.add(line.Line_Index);
                    Labels_vector.get(start).lines_id.add(line.Line_Index);
//                    H.add(Labels_vector, Lines_vector);
                }
                Labels_vector.get(start).setTextFill(Color.BLACK);
                start = -1;
                labex.setTextFill(Color.BLACK);
                root.getChildren().remove(Dummy_Line);
            } else {
                Labels_vector.get(start).setTextFill(Color.BLACK);
                start = -1;
                labex.setTextFill(Color.BLACK);
            }
        } else {
            start = labex.index;
            labex.setTextFill(Color.ROYALBLUE);
        }
    }

    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        return false;
    }
    public void DeleteConnection(General_Label label) {
    }
    public void ClearAll() {
    }
}
