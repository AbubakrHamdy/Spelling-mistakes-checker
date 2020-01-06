package Roots;

import Entities.Process;
import Entities.*;
import Roots.Root_1.*;
import Roots.Root_2.TaskLabel;

import java.util.Vector;

import static sample.Main.Zoom_Scale;

public class LabelStorage extends General_Label {

    public Process MP;
    public Duration DU;
    public SubProcess SP;
    public Activity AC;
    public Task TA;
    public Employee EM;
    public Risk RI;

    public JobDescriptionFile ff1;
    public ProcedureFile ff0;

    public LabelStorage(boolean ex) {
        Exist = ex;
        this.setScaleX(this.getScaleX() * Zoom_Scale);
        this.setScaleY(this.getScaleY() * Zoom_Scale);
    }

    public  LabelStorage(General_Label l) {
        Exist = l.Exist;
        Type=l.Type;
        FillLabelFields(this, l.getText(), l.Type_id, l.Exist, l.index);
        this.setTranslateX(l.getTranslateX() + l.layoutXProperty().getValue());
        this.setTranslateY(l.getTranslateY() + l.layoutYProperty().getValue());
        this.lines_id = (Vector<Integer>) l.lines_id.clone();
        if (l.Type.matches("MainProcess")) {
            MP = ((ProcessLabel) l).MP;
        } else if (l.Type.matches("Employee")) {
            EM = ((EmployeeLabel) l).EM;
        } else if (l.Type.matches("Task")) {
            TA = ((TaskLabel) l).TA;
        } else if (l.Type.matches("SubProcess")) {
            SP = ((SubProcessLabel) l).SP;
        } else if (l.Type.matches("Activity")) {
            AC = ((ActivityLabel) l).AC;
        } else if (l.Type.matches("Duration")) {
            DU = new Duration(((DurationLabel) l).DU.getId(), ((DurationLabel) l).DU.getValue());
        }else if (l.Type.matches("Risk")) {
            RI = ((RiskLabel) l).RI;
        }
    }
}
