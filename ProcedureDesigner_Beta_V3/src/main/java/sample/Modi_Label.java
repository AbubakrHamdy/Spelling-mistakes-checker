package sample;

import javafx.scene.control.Label;

import java.io.Serializable;

//import static GUI.Image.*;

public class Modi_Label extends Label implements Serializable {
    private static final long serialVersionUID = 8131742784303653976L;


//    public String Namee = "";
//    public int index;
//    public Vector<Integer> lines_id = new Vector<Integer>();
//    public String Des = "";
//    public double Type_id = -44;
//    public String Type = "";
//    public String Datee = "false";
//    public Process MP;
//    public Duration DU;
//    public SubProcess SP;
//    public Activity AC;
//    public Task TA;
//    public Employee EM;
//    public JobDescriptionFile ff1;
//    public ProcedureFile ff0;
//    public boolean exist;
//
//    public Modi_Label(boolean ex) {
//        exist = ex;
//        this.setScaleX(this.getScaleX()*Zoom_Scale);
//        this.setScaleY(this.getScaleY()*Zoom_Scale);
//    }
//
//    public Modi_Label(Modi_Tree t) {
//
//        this.setText(t.NAAAMe);
//        this.Namee=t.NAAAMe;
//        Type_id = t.Level;
//        if (t.Datee.matches("true")) {
//            DU = new Duration(t.DU);
//            Type = "Duration";
//            Datee = t.Datee;
//        } else {
//            if (Type_id == 0) {
//                MP = new Process(t.MP);
//                MP.ClearChildern();
//                Type = "MainProcess";
//            } else if (Type_id == 1) {
//                EM = new Employee(t.EM);
//                EM.ClearChildern();
//                Type = "Employee";
//            } else if (Type_id == -1) {
//                TA = new Task(t.TA);
//                TA.ClearChildern();
//                Type = "Task_";
//            } else if (Type_id == -11) {
//                if (t.f1 != null) {
//                    ff1 = new JobDescriptionFile(t.f1);
//                } else {
//                    ff0 = new ProcedureFile((t.f0));
//                }
//                Type = "File";
//
//            } else if (Type_id % 2 == 0) {
//                SP = new SubProcess(t.SP);
//                SP.ClearChildern();
//                Type = "SubProcess";
//            } else {
//                AC = new Activity(t.AC);
//                AC.ClearChildern();
//                Type = "Activity";
//            }
//        }
//
//        exist = true;
//        this.relocate(500, 500);
//        this.toFront();
//        this.index = Labels_vector.size();
//        root.getChildren().add(this);
//        LabelStyle();
//        Labels_vector.add(this);
////        H.add(Labels_vector, Lines_vector);
//        this.setScaleX(this.getScaleX()*Zoom_Scale);
//        this.setScaleY(this.getScaleY()*Zoom_Scale);
//    }
//
//    Modi_Label(Modi_Label l) {
//        this.Datee = l.Datee;
//        this.setText(l.getText());
//        this.Namee=l.getText();
//        this.setTranslateX(l.getTranslateX() + l.layoutXProperty().getValue());
//        this.setTranslateY(l.getTranslateY() + l.layoutYProperty().getValue());
//        this.index = l.index;
//        this.Type = l.Type;
//        this.lines_id = (Vector<Integer>) l.lines_id.clone();
//        this.Type_id = l.Type_id;
//
//        if (l.Datee.matches("true")) {
//            DU = new Duration(l.DU.getId(), l.DU.getValue());
//            Type = "Duration";
//        } else {
//            if (Type_id == 0) {
//                MP = new Process(l.MP);
//                MP.ClearChildern();
//            } else if (Type_id == -1) {
//                TA = new Task(l.TA);
//                TA.ClearChildern();
//            } else if (Type_id == 1) {
//                EM = new Employee(l.EM);
//                EM.ClearChildern();
//            } else if (Type_id == -11) {
//                if (l.ff1 != null) {
//                    ff1 = new JobDescriptionFile(l.ff1);
//                } else {
//                    ff0 = new ProcedureFile((l.ff0));
//                }
//            } else if (Type_id % 2 == 0) {
//                SP = new SubProcess(l.SP);
//                SP.ClearChildern();
//            } else {
//                AC = new Activity(l.AC);
//                AC.ClearChildern();
//            }
//        }
//        exist = l.exist;
//        LabelStyle();
//        this.setScaleX(this.getScaleX()*Zoom_Scale);
//        this.setScaleY(this.getScaleY()*Zoom_Scale);
//    }
//
//    public Modi_Label() {
//    }
//
//    public void LabelStyle() {
//        if (Datee.matches("true")) {
//            this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
//            this.setGraphic(new ImageView(imageClock));
//            // this.setFont(Font.font(12));
//            Date date = new Date();
//            Des = DU.getValue();
//        } else {
//            if (Type_id == 0) {
//                this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
//                this.setGraphic(new ImageView(imageMain));
//                // this.setFont(Font.font(12));
//                Des = MP.getDescription();
//            } else if (Type_id == -1) {
//                this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
//                this.setGraphic(new ImageView(imageAC));
//                // this.setFont(Font.font(12));
//                Des = TA.getDescription();
//            } else if (Type_id == 1) {
//                this.setStyle("-fx-font: 12 Tahoma;-fx-border-color: #4e2aff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
//                this.setGraphic(new ImageView(image));
//                // this.setFont(Font.font(12));
//                Des = EM.getDescription();
//            } else if (Type_id % 2 == 0) {
//                this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
//                this.setGraphic(new ImageView(image2));
////            this.setFont(Font.font(8));
//                Des = SP.getDescription();
//            } else if (Type_id == -11) {
//                this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
//                if (ff1 != null) {
//                    Des = ff1.getName();
//                } else {
//                    Des = ff0.getName();
//                }
//            } else {
//                this.setStyle("-fx-font: 12 arial;-fx-border-color: #b1c1ff;-fx-border-radius: 100;-fx-padding: 5;-fx-background-color: rgba(255,252,249,3);");
//                this.setGraphic(new ImageView(imageAC));
//                // this.setFont(Font.font(12));
//                Des = AC.getDescription();
//            }
//        }
//        LabelMouseClickFunctions(this);
//        LabelMouseHoverFunctions(this);
//    }
//
//    public void LabelMouseClickFunctions(Modi_Label labex) {
//        labex.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (labex instanceof Modi_Label) {
//                    if (LinkOn || event.isShiftDown()) {
//                        LinkLabels(labex);
//                    }
//                    if (DeleteOn || event.isAltDown()) {
//                        boolean answer;
//                        if (araabc) {
//                            answer = ConfirmBoxArabic.display("مسح", "هل تريد الحذف ؟");
//
//                        } else {
//                            answer = ConfirmBox.display("Delete ", "Are you want to Delete it ?");
//                        }
//                        if (answer) {
//                            System.out.println("Node Deleted");
//                            exist = false;
//                            for (int i = 0; i < labex.lines_id.size(); i++) {
//                                Modi_Line LL = Lines_vector.get(labex.lines_id.get(i));
//                                if (LL != null) {
//                                    LL.CanBeConnceted = false;
//                                    Lines_vector.set(LL.Line_Index, null);
//                                    root.getChildren().remove(LL);
//                                    LL.Disconnect();
//                                }
//                            }
//                            Labels_vector.set(labex.index, null);
//                            root.getChildren().remove(labex);
////                            H.add(Labels_vector, Lines_vector);
//                            DeleteOn = false;
//                            UnClick();
//                        } else {
//                            DeleteOn = false;
//                            UnClick();
//                        }
//
//                    }
//                    if (event.isControlDown()) {
//                        GetLabelInfo();
//                    }
//                }
//            }
//        });
//        mg.makeDraggable(labex);
//    }
//
//    public void GetLabelInfo() {
//        System.out.println("My index is " + this.index + " and CanBeConnceted is " + exist);
//        if (Datee.matches("true")) {
//            //  DU.Get_Info();
//        } else {
//            if (Type_id == 0) {
//                MP.Get_Info();
//            } else if (Type.matches("SubProcess")) {
//                SP.Get_Info("");
//            } else if (Type.matches("Activity")) {
//                AC.Get_Info("");
//            } else if (Type.matches("Task_")) {
//                TA.Get_Info("");
//            } else {
//                System.out.println(EM.GetTasksString());
//                //    System.out.println(EM.GetTasksString());
//            }
//        }
//        System.out.println("and it's type is " + Type);
//    }
//
//    public void LabelMouseHoverFunctions(Modi_Label labelx) {
//        labelx.setOnMouseEntered(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (labelx instanceof Modi_Label) {
//                    final Tooltip tooltip = new Tooltip();
//                    tooltip.setFont(Font.font(12));
//                    tooltip.setText(labelx.Des);
//                    labelx.setTooltip(tooltip);
//                }
//            }
//
//        });
//    }
//
//    public void LinkLabels(Modi_Label labex) {
//        if (start != -1) {
//            if (start != labex.index) {
//                Modi_Line line = new Modi_Line(Labels_vector.get(start), labex);
//                if (exist = true) {
//                    labex.lines_id.add(line.Line_Index);
//                    Labels_vector.get(start).lines_id.add(line.Line_Index);
////                    H.add(Labels_vector, Lines_vector);
//
//                }
//                //set every thing back to normal
//                Labels_vector.get(start).setTextFill(Color.BLACK);
//                start = -1;
//                labex.setTextFill(Color.BLACK);
//                // LinkOn = false;
//                // UnClick();
//                root.getChildren().remove(Dummy_Line);
//            } else {
//                Labels_vector.get(start).setTextFill(Color.BLACK);
//                start = -1;
//                labex.setTextFill(Color.BLACK);
//                // LinkOn = false;
//                //  UnClick();
//            }
//        } else {
//            start = labex.index;
//            if (labex.Type_id == 1) {
//                labex.setTextFill(Color.GOLD);
//            } else {
//                labex.setTextFill(Color.ROYALBLUE);
//            }
//        }
//    }
//
//    public void clearAll() {
//        if (Type_id == 0) {
//            MP.ClearChildern();
//        } else if (Type_id == -1) {
//            TA.ClearChildern();
//        } else if (Type_id == 1) {
//            EM.ClearChildern();
//        } else if (Type_id % 2 == 0) {
//            SP.ClearChildern();
//        } else if (Type_id != 11) {
//            AC.ClearChildern();
//        }
//    }

}
