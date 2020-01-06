package Roots.Root_1;

import Entities.Activity;
import Entities.SubProcess;
import Roots.EmployeeLabel;
import Roots.General_Label;
import Roots.General_Line;
import Roots.LabelStorage;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import sample.Modi_Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static sample.Image.imageAC;
import static sample.Main.*;

public class ActivityLabel extends General_Label {
    public Activity AC;

    ActivityLabel() {
        Type = "Activity";
    }

    public ActivityLabel(Modi_Tree t) {
        AC = new Activity(t.AC);
        AC.setCategory(t.AC.getCategory());
        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww + == " + AC.getCategory());
        AC.ClearChildern();
        Type = "Activity";

        FillLabelFields(this, t.NAAAMe, t.Level, true, Labels_vector.size());
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        Labels_vector.add(this);
//        H.add(Labels_vector, Lines_vector)
        this.relocate(500, 500);
        this.toFront();
        LinkActivityWithItsParent(t);
        Reconnect();
    }

    public void LinkActivityWithItsParent(Modi_Tree t) {
        SubProcessLabel SPL = null;
        List<SubProcess> SPA = new ArrayList<SubProcess>(t.AC.getSubProcesses());
        for (int i = 0; i < Labels_vector.size(); i++) {
            if (Labels_vector.get(i) instanceof SubProcessLabel) {
                if (SPA.get(0).getID() == ((SubProcessLabel) Labels_vector.get(i)).SP.getID()) {
                    General_Line line = new General_Line(this, ((SubProcessLabel) Labels_vector.get(i)));
                    this.lines_id.add(line.Line_Index);
                    ((SubProcessLabel) Labels_vector.get(i)).lines_id.add(line.Line_Index);

                    ((SubProcessLabel) Labels_vector.get(i)).SP.getActivities().add(AC);
                    AC.getSubProcesses().add(((SubProcessLabel) Labels_vector.get(i)).SP);
                    break;
                }
            }
        }
    }

    public ActivityLabel(LabelStorage l) {
        AC = new Activity(l.AC);
        AC.ClearChildern();
        Type = "Activity";
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
        this.setStyle("-fx-font: 12 Tahoma;-fx-padding: 5;-fx-border-width: 4;-fx-border-radius: 100px;-fx-border-color: #6ff51b; -fx-border-style: solid;");
        ImageView image_ = new ImageView(imageAC);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        this.setGraphic(image_);
        Des = AC.getDescription();
    }

    @Override
    public void GetLabelInfo() {
        System.out.println("My index is " + this.index + " and CanBeConnceted is " + Exist);
        System.out.println("and it's type is " + Type);
        System.out.println(AC.toString());
    }

    @Override
    public void ClearAll() {
        AC.ClearChildern();
    }

    @Override
    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        General_Label label = Labels_vector.get(start);
        if (label.Type.matches("Employee") && AC.getEmployees().size() == 0) {
            AC.getEmployees().add(((EmployeeLabel) label).EM);
            ((EmployeeLabel) label).EM.getActivities().add(AC);
            return true;
        } else if (label.Type.matches("SubProcess") && !((SubProcessLabel) label).SP.getActivities().contains(AC)) {
            ((SubProcessLabel) label).SP.getActivities().add(AC);
            AC.getSubProcesses().add(((SubProcessLabel) label).SP);
            return true;
        } else if (label.Type.matches("Duration") && AC.getDuration_id() == -1) {
            AC.setDuration_id(((DurationLabel) label).DU.getId());
            return true;
        } else if (label.Type.matches("Risk") && !AC.getRisks().contains(((RiskLabel) label).RI)) {
            AC.getRisks().add(((RiskLabel) label).RI);
            return true;
        }
        return false;
    }

    @Override
    public void DeleteConnection(General_Label label) {
        if (label.Type.matches("Employee")) {

        } else if (label.Type.matches("SubProcess")) {
        } else if (label.Type.matches("Duration")) {
            AC.setDuration_id(-1);
        } else if (label.Type.matches("Risk")) {
            AC.getRisks().remove(((RiskLabel) label).RI);
        }
    }
}
