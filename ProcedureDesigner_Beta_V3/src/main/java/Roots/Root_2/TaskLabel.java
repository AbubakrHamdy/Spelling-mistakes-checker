package Roots.Root_2;

import Entities.Task;
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

import static sample.Image.imageAC;
import static sample.Main.*;

public class TaskLabel extends General_Label {
    public Task TA;

    TaskLabel() {
        Type = "Task";
    }

    public  TaskLabel(Modi_Tree t) {
        TA = new Task(t.TA);
        TA.ClearChildern();
        Type = "Task";

        FillLabelFields(this, t.NAAAMe, t.Level, true, Labels_vector.size());
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        Labels_vector.add(this);
//        H.add(Labels_vector, Lines_vector)
        this.relocate(500, 500);
        this.toFront();
    }

    public   TaskLabel(LabelStorage l) {
        TA = new Task(l.TA);
        TA.ClearChildern();
        Type = "Task";

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
        this.setStyle("-fx-font: 12 Tahoma;-fx-padding: 5;-fx-border-width: 3;-fx-border-radius: 100px;-fx-border-color: #dcf51b; -fx-border-style: solid;");

//        this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
        ImageView image_ = new ImageView(imageAC);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        this.setGraphic(image_);
        Des = TA.getDescription();
    }

    @Override
    public void GetLabelInfo() {
        System.out.println("My index is " + this.index + " and CanBeConnceted is " + Exist);
        System.out.println("and it's type is " + Type);
        System.out.println(TA.toString());
    }

    @Override
    public void ClearAll() {
        TA.ClearChildern();
    }

    @Override
    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        if (Labels_vector.get(start).Type.matches("Employee")) {
            TA.Assign(((EmployeeLabel) Labels_vector.get(start)).EM);
            return true;
        }
        return false;
    }

    @Override
    public void DeleteConnection(General_Label label) {
        if (label.Type.matches("Employee")) {
            TA.getEmployees().remove(((EmployeeLabel) label).EM);
        }
    }

}
