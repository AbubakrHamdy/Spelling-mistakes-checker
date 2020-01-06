package sample;
import Entities.*;
import Entities.Process;
import javafx.scene.control.TreeItem;

import java.io.Serializable;

public class Modi_Tree<T> extends TreeItem<T> implements Serializable {

    public String NAAAMe;
    public String Desc = "_____---";
    public  int Level;
    public String Datee="false";
    public Process MP = null;
    public SubProcess SP = null;
    public Activity AC = null;
    public Duration DU = null;
    public Employee EM = null;
    public Risk RI = null;
    public Task TA=null;
    public JobDescriptionFile f1 = null;
    public ProcedureFile f0 = null;

}