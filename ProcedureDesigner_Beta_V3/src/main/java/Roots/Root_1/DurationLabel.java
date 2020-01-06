package Roots.Root_1;

import Entities.Duration;
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

import static sample.Image.imageClock;
import static sample.Main.Labels_vector;
import static sample.Main.start;

public class DurationLabel extends General_Label {
    public Duration DU;

    DurationLabel() {
        Type = "Duration";
    }

    public DurationLabel(Modi_Tree t) {
        DU = new Duration(t.DU);
        Type = "Duration";

        FillLabelFields(this, t.NAAAMe, t.Level, true, Labels_vector.size());
        LabelStyle();
        LabelMouseClickFunctions(this);
        LabelMouseHoverFunctions(this);

        Labels_vector.add(this);
//        H.add(Labels_vector, Lines_vector)
        this.relocate(500, 500);
        this.toFront();

    }

    public DurationLabel(LabelStorage l) {
        DU = new Duration(l.DU.getId(), l.DU.getValue());
        Type = "Duration";

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
        this.setStyle("-fx-font: 12 Tahoma;-fx-padding: 5;-fx-border-width: 3;-fx-border-radius: 100px; -fx-border-style: solid;");
        ImageView image_ = new ImageView(imageClock);
        image_.setSmooth(true);
        image_.setFitWidth(20);
        image_.setFitHeight(20);
        this.setGraphic(image_);
        Des = DU.getValue();
    }

    @Override
    public void GetLabelInfo() {
        System.out.println("My index is " + this.index + " and CanBeConnceted is " + Exist);
        System.out.println("and it's type is " + Type);
        System.out.println(DU.toString());
    }

    @Override
    public boolean CheckIFThe2LabelsCanBeConnectedAndConncetThem() {
        General_Label label=Labels_vector.get(start);

        if (label.Type.matches("SubProcess")&&((SubProcessLabel) label).SP.getDuration_id()==-1) {
            ((SubProcessLabel) label).SP.setDuration_id(DU.getId());
            return true;
        } else if (label.Type.matches("Activity")&&((ActivityLabel) label).AC.getDuration_id()==-1) {
            ((ActivityLabel) label).AC.setDuration_id(DU.getId());
            return true;
        }
        return false;
    }

    @Override
    public void DeleteConnection(General_Label label) {
        if (label.Type.matches("SubProcess")) {
        } else if (label.Type.matches("Activity")) {
        }
    }
}
