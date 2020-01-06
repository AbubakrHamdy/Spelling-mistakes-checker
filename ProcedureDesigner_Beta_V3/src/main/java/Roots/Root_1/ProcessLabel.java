package Roots.Root_1;

import Entities.Process;
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

import static sample.Image.imageMain;
import static sample.Main.Labels_vector;
import static sample.Main.start;

public class ProcessLabel extends General_Label {
    public Process MP;

    ProcessLabel() {
        Type = "MainProcess";
    }

    public ProcessLabel(Modi_Tree t) {
        MP = new Process(t.MP);
        MP.ClearChildern();
        Type = "MainProcess";


        FillLabelFields(this, t.NAAAMe, t.Level, true, Labels_vector.size());
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        Labels_vector.add(this);
//        H.add(Labels_vector, Lines_vector)
        this.relocate(500, 200);
        this.toFront();
    }

    public ProcessLabel(LabelStorage l) {
        MP = new Process(l.MP);
        MP.ClearChildern();
        Type = "MainProcess";

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
        this.setStyle("-fx-font: 12 Tahoma;-fx-padding: 5;-fx-border-width: 3;-fx-border-radius: 100px;-fx-border-color: #b1c1ff; -fx-border-style: solid;");
        ImageView image_ = new ImageView(imageMain);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        this.setGraphic(image_);
        Des = MP.getDescription();
    }

    @Override
    public void GetLabelInfo() {
        System.out.println("My index is " + this.index + " and CanBeConnceted is " + Exist);
        System.out.println("and it's type is " + Type);
        System.out.println(MP.toString());
    }

    @Override
    public void ClearAll() {
        MP.ClearChildern();
    }

    @Override
    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        General_Label label=Labels_vector.get(start);

        if (label.Type.matches("Employee")&&!MP.getMainProcessOwner().matches("")) {
            MP.setMainProcessOwner(((EmployeeLabel) label).EM.getName());
            return true;
        } else if (label.Type.matches("SubProcess")&&!MP.getSubProcesses().contains(((SubProcessLabel) label).SP)) {
            MP.getSubProcesses().add(((SubProcessLabel) label).SP);
            return true;
        }
        return false;
    }

    @Override
    public void DeleteConnection(General_Label label) {
        if (label.Type.matches("Employee")) {
            MP.setMainProcessOwner("");
        } else if (label.Type.matches("SubProcess")) {
            MP.getSubProcesses().remove(((SubProcessLabel) label).SP);
        }
    }
}
