package Roots;

import Entities.Employee;
import Roots.Root_1.ActivityLabel;
import Roots.Root_1.ProcessLabel;
import Roots.Root_1.SubProcessLabel;
import Roots.Root_2.TaskLabel;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import sample.Modi_Tree;

import java.util.Vector;

import static sample.Image.image;
import static sample.Main.Labels_vector;
import static sample.Main.start;

public class EmployeeLabel extends General_Label {
    public Employee EM;

    EmployeeLabel() {
        Type = "Employee";
    }

    public EmployeeLabel(Modi_Tree t) {
        EM = new Employee(t.EM);
        EM.ClearChildern();
        Type = "Employee";

        FillLabelFields(this, t.NAAAMe, t.Level, true, Labels_vector.size());
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        Labels_vector.add(this);
//        H.add(Labels_vector, Lines_vector)
        this.relocate(500, 200);
        this.toFront();
    }

    public EmployeeLabel(LabelStorage l) {
        EM = new Employee(l.EM);
        EM.ClearChildern();
        Type = "Employee";
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
//        this.setStyle("-fx-font: 12 Tahoma;-fx-border-color: #4e2aff;-fx-padding: 5;");
        this.setStyle("-fx-font: 12 Tahoma;-fx-padding: 5;-fx-border-width: 3;-fx-border-radius: 100px;-fx-border-color: #1b4ef5; -fx-border-style: solid;");
        ImageView image_ = new ImageView(image);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        this.setGraphic(image_);
        Des = EM.getDescription();
    }

    @Override
    public void GetLabelInfo() {
        System.out.println("My index is " + this.index + " and CanBeConnceted is " + Exist);
        System.out.println("and it's type is " + Type);
        System.out.println(EM.toString());
    }

    @Override
    public void ClearAll() {
        EM.ClearChildern();
    }

    @Override
    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        General_Label label = Labels_vector.get(start);

        if (label.Type.matches("Task")) {
            ((TaskLabel) label).TA.Assign(EM);
            return true;
        } else if (label.Type.matches("MainProcess") && !((ProcessLabel) label).MP.getMainProcessOwner().matches("")) {
            ((ProcessLabel) label).MP.setMainProcessOwner(EM.getName());
            return true;
        } else if (label.Type.matches("SubProcess") && !EM.getSubProcesses().contains(((SubProcessLabel) label).SP)) {
            ((SubProcessLabel) label).SP.getEmployees().add(EM);
            EM.getSubProcesses().add(((SubProcessLabel) label).SP);
            return true;
        } else if (label.Type.matches("Activity")&&EM.getActivities().size()==0) {
            EM.getActivities().add(((ActivityLabel) label).AC);
            ((ActivityLabel) label).AC.getEmployees().add(EM);
            return true;
        }
        return false;
    }

    @Override
    public void DeleteConnection(General_Label label) {
        if (label.Type.matches("Task")) {
            EM.getTasks().remove(((TaskLabel) label).TA);
        }
        if (label.Type.matches("MainProcess")) {
        }
        if (label.Type.matches("SubProcess")) {
            EM.getSubProcesses().remove(((SubProcessLabel) label).SP);
        }
        if (label.Type.matches("Activity")) {
        }
    }
}
