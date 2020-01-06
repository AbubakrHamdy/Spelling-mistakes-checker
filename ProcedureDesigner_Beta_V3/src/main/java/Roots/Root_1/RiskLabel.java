package Roots.Root_1;

import Entities.Risk;
import Roots.General_Label;
import Roots.LabelStorage;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import sample.Modi_Tree;

import java.util.Vector;

import static sample.Image.imagerr;
import static sample.Main.Labels_vector;
import static sample.Main.start;

public class RiskLabel extends General_Label {
    public Risk RI;

    RiskLabel() {
        Type = "Risk";
    }

    public Risk getRI() {
        return RI;
    }

    public void setRI(Risk RI) {
        this.RI = RI;
    }

    public RiskLabel(Modi_Tree t) {
        RI=new Risk(t.RI);
        RI.ClearChildren();
        Type = "Risk";

        FillLabelFields(this, t.NAAAMe, t.Level, true, Labels_vector.size());
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        Labels_vector.add(this);
//        H.add(Labels_vector, Lines_vector)
        this.relocate(500, 500);
        this.toFront();
    }

    public RiskLabel(LabelStorage l) {
        RI =l.RI;
        RI.ClearChildren();
        Type = "Risk";
        Exist = l.Exist;

        FillLabelFields(this, l.getText(), l.Type_id, l.Exist, l.index);
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        this.setTranslateX(l.getTranslateX() + l.layoutXProperty().getValue());
        this.setTranslateY(l.getTranslateY() + l.layoutYProperty().getValue());
        this.lines_id = (Vector<Integer>) l.lines_id.clone();
//        this.Type = l.Type;
    }

    @Override
    public void LabelStyle() {
        CornerRadii corn = new CornerRadii(100);
        Color col = Color.WHITE;
        Background background = new Background(new BackgroundFill(col, corn, Insets.EMPTY));
        this.setBackground(background);
        this.setStyle("-fx-font: 12 Tahoma;-fx-padding: 5;-fx-border-width: 3;-fx-border-radius: 100px;-fx-border-color: #cf3434; -fx-border-style: solid;");
        ImageView image_ = new ImageView(imagerr);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        this.setGraphic(image_);
        Des = RI.getRisk();
    }

    @Override
    public void GetLabelInfo() {
        System.out.println("My index is " + this.index + " and CanBeConnceted is " + Exist);
        System.out.println("and it's type is " + Type);
        System.out.println(RI.toString());
    }

    @Override
    public void ClearAll() {
        RI.ClearChildren();
    }

    @Override
    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        General_Label label = Labels_vector.get(start);

        if (label.Type.matches("MainProcess") && !((ProcessLabel) label).MP.getMainProcessOwner().matches("")) {
//            ((ProcessLabel) label).MP.setMainProcessOwner(RI.getName());
//            return true;
        } else if (label.Type.matches("SubProcess") && RI.getSubProcesses().size()==0) {
//            ((SubProcessLabel) label).SP.getEmployees().add(RI);
            ((((SubProcessLabel) label).SP)).getRisks().add(RI);
            RI.getSubProcesses().add((((SubProcessLabel) label).SP));
            return true;
        } else if (label.Type.matches("Activity")&&RI.getActivity().size()==0) {
            RI.getActivity().add(((ActivityLabel) label).AC) ;
            return true;
        }
        return false;
    }

    @Override
    public void DeleteConnection(General_Label label) {
        if (label.Type.matches("MainProcess")) {
        }
        if (label.Type.matches("SubProcess")) {
            RI.getSubProcesses().remove(((SubProcessLabel) label).SP);
        }
        if (label.Type.matches("Activity")){
            RI.getActivity().remove(((ActivityLabel) label).AC);
        }
    }
}
