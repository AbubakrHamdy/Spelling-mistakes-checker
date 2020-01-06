package Roots.Root_1;

import Entities.SubProcess;
import Roots.EmployeeLabel;
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

import static sample.Image.image2;
import static sample.Main.Labels_vector;
import static sample.Main.start;

public class SubProcessLabel extends General_Label {
    public SubProcess SP;

    SubProcessLabel() {
        Type = "SubProcess";
    }

    public SubProcessLabel(Modi_Tree t) {
        SP = new SubProcess(t.SP);
        SP.setCategory(t.SP.getCategory());
        SP.ClearChildern();
        Type = "SubProcess";

        FillLabelFields(this, t.NAAAMe, t.Level, true, Labels_vector.size());
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        Labels_vector.add(this);
//        H.add(Labels_vector, Lines_vector)
        this.relocate(500, 500);
        this.toFront();
    }

    public SubProcessLabel(LabelStorage l) {
        SP = new SubProcess(l.SP);
        SP.ClearChildern();
        Type = "SubProcess";

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
        this.setStyle("-fx-font: 12 Tahoma;-fx-padding: 5;-fx-border-width: 3;-fx-border-radius: 100px;-fx-border-color: #40f5a9; -fx-border-style: solid;");
        ImageView image_ = new ImageView(image2);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        this.setGraphic(image_);
        Des = SP.getDescription();
    }

    @Override
    public void GetLabelInfo() {
        System.out.println("My index is " + this.index + " and CanBeConnceted is " + Exist);
        System.out.println("and it's type is " + Type);
        System.out.println(SP.toString());
    }

    @Override
    public void ClearAll() {
        SP.ClearChildern();
    }

    @Override
    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        General_Label label = Labels_vector.get(start);
        if (label.Type.matches("Employee") && !SP.getEmployees().contains(((EmployeeLabel) label).EM)) {
            SP.getEmployees().add(((EmployeeLabel) label).EM);
            ((EmployeeLabel) label).EM.getSubProcesses().add(SP);
            return true;
        } else if (label.Type.matches("Duration") && SP.getDuration_id() == -1) {
            SP.setDuration_id(((DurationLabel) label).DU.getId());
            return true;
        } else if (label.Type.matches("MainProcess") && !((ProcessLabel) label).MP.getSubProcesses().contains(SP)) {
            ((ProcessLabel) label).MP.getSubProcesses().add(SP);
            return true;
        } else if (label.Type.matches("SubProcess") && ((SubProcessLabel) label).SP.getParent() == null) {
            ((SubProcessLabel) label).SP.setParent(SP);
            return true;
        } else if (label.Type.matches("Activity") && !SP.getActivities().contains(((ActivityLabel) label).AC)) {
            SP.getActivities().add(((ActivityLabel) label).AC);
            ((ActivityLabel) label).AC.getSubProcesses().add(SP);
            return true;
        } else if (label.Type.matches("Risk") && !SP.getRisks().contains(((RiskLabel) label).RI)) {
            SP.getRisks().add(((RiskLabel) label).RI);
            (((RiskLabel) label).RI).getSubProcesses().add(SP);
            return true;
        }
        return false;
    }

    @Override
    public void DeleteConnection(General_Label label) {
        if (label.Type.matches("Employee")) {
            SP.getEmployees().remove(((EmployeeLabel) label).EM);
        } else if (label.Type.matches("Duration")) {
            SP.setDuration_id(-1);
        } else if (label.Type.matches("MainProcess")) {
            ((ProcessLabel) label).MP.getSubProcesses().remove(SP);
        } else if (label.Type.matches("SubProcess")) {
            if (SP.getParent().getID() == ((SubProcessLabel) label).SP.getID()) {
                SP.setParent(null);
            }
        } else if (label.Type.matches("Activity")) {
            SP.getActivities().remove(((ActivityLabel) label).AC);
        }
        else if (label.Type.matches("Risk")) {
            SP.getRisks().remove(((RiskLabel) label).RI);
        }
    }
}
