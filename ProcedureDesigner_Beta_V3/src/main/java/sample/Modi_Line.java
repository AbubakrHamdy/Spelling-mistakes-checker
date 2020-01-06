package sample;

import javafx.scene.shape.Line;

import java.io.Serializable;

public class Modi_Line extends Line implements Serializable {
//    public int Line_Index;
//    public int S_Index;
//    public int E_Index;
//    public boolean CanBeConnceted;
//
//    public Modi_Line(boolean ex) {
//        CanBeConnceted = ex;
//    }
//
//    public Modi_Line(Modi_Label L1, Modi_Label L2) {
//        CanBeConnceted = true;
//        this.toBack();
//        Modi_Label S;
//        Modi_Label E;
//        if ((L1.Type_id <= L2.Type_id && L1.Type_id != 1) || L2.Type_id == 1) {
//            S_Index = L1.index;
//            E_Index = L2.index;
//            S = Labels_vector.get(S_Index);
//            E = Labels_vector.get(E_Index);
//            CanBeConnceted = AddSourceAndTargetFor2DifferentLevels();
//        } else {
//            S_Index = L2.index;
//            E_Index = L1.index;
//            S = Labels_vector.get(S_Index);
//            E = Labels_vector.get(E_Index);
//            CanBeConnceted = AddSourceAndTargetFor2DifferentLevels();
//        }
//        System.out.println("connect " + S.getText() + " with " + E.getText());
//
//        if (CanBeConnceted == true) {
//            Add_Style(Labels_vector, Lines_vector);
//        }
//    }
//
//
//    Modi_Line(Modi_Line l, Vector<Modi_Label> labels) {
//        CanBeConnceted = l.CanBeConnceted;
//        S_Index = l.S_Index;
//        E_Index = l.E_Index;
//    }
//
//    public Modi_Line() {
//    }
//
//
//    public void Add_Style(Vector<Modi_Label> labels, Vector<Modi_Line> Lines) {
////        this.toBack();
////        Modi_Label S = labels.get(S_Index);
////        Modi_Label E = labels.get(E_Index);
////
////        S = labels.get(S.index);
////        E = labels.get(E.index);
////        if (S.Type_id != 1 && E.Type_id != 1) {
////            this.setStyle("-fx-stroke: #0dffca;");
////        } else {
////            this.setStyle("-fx-stroke: #4443ff;");
////        }
////        if (CanBeConnceted == true) {
////            S.toFront();
////            E.toFront();
////            Line_Index = Lines.size();
////            Lines.add(this);
////            setStrokeWidth(3);
////            toBack();
////            Start_ConnectionBetween2Nodes(this);
////            Line_Functions(this);
////            root.getChildren().add(this);
////        }
//    }
//
//    Modi_Line(int s) {
//    }
//
//    public void Start_ConnectionBetweenNodeAndMouse(int s, Modi_Line L, MouseEvent mouseEvent) {
//
////        Modi_Label S = Labels_vector.get(s);
////
////        L.setStartX(S.getTranslateX() + (S.widthProperty().get() / 2) + S.layoutXProperty().getValue());
////        L.setStartY(S.getTranslateY() + (S.heightProperty().get() / 2) + S.layoutYProperty().getValue());
//        L.setEndX(mouseEvent.getSceneX());
//        L.setEndY(mouseEvent.getSceneY());
//        L.toBack();
////        S.toFront();
////        L.setStrokeLineCap(StrokeLineCap.BUTT);
////        L.getStrokeDashArray().setAll(20.0, 1.4);
//    }
//
//
//    public void Start_ConnectionBetween2Nodes(Line L) {
//
////        Modi_Label S = Labels_vector.get(S_Index);
////        Modi_Label E = Labels_vector.get(E_Index);
////
////        L.setStartX(S.getTranslateX() + (S.widthProperty().get() / 2) + S.layoutXProperty().getValue());
////        L.setStartY(S.getTranslateY() + (S.heightProperty().get() / 2) + S.layoutYProperty().getValue());
////        L.setEndX(E.getTranslateX() + (E.widthProperty().get() / 2) + E.layoutXProperty().getValue());
////        L.setEndY(E.getTranslateY() + (E.heightProperty().get() / 2) + E.layoutYProperty().getValue());
////        L.toBack();
////        S.toFront();
////        E.toFront();
////        if (S.Type_id == 1 || E.Type_id == 1) {
////            L.setStrokeWidth(4);
////            L.setStrokeLineCap(StrokeLineCap.BUTT);
////            L.getStrokeDashArray().setAll(6.0, 10.0);
////        }
////        L.setStrokeLineCap(StrokeLineCap.BUTT);
////        L.getStrokeDashArray().setAll(20.0, 1.4);
//    }
//
//    public void Line_Functions(Modi_Line line) {
//        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (line instanceof Modi_Line) {
//                    if (DeleteOn || event.isAltDown()) {
//                        System.out.println("line Deleted");
//                        int idn = root.getChildren().indexOf(line);
//                        System.out.println("before adding = " + root.getChildren().size());
//                        root.getChildren().remove(line);
//                        System.out.println("after adding = " + root.getChildren().size());
//                        CanBeConnceted = false;
//                        Lines_vector.set(line.Line_Index, null);
//                        Disconnect();
////                        H.add(Labels_vector, Lines_vector);
//                        DeleteOn = false;
//                        Main_Shortcut_Buttons.UnClick();
//                    }
//                    if (event.isControlDown()) {
//                        System.out.println("ID of this line is " + line.Line_Index);
//                    }
//                }
//            }
//        });
//    }
//
//    public boolean AddSourceAndTargetFor2DifferentLevels() {
//        Modi_Label S = Labels_vector.get(S_Index);
//        Modi_Label E = Labels_vector.get(E_Index);
//        if (S.Datee.matches("true")) {
//            if (E.Type.matches("Activity")) {
//                E.AC.setDuration_id(S.DU.getId());
//                return true;
//            } else if (E.Type.matches("SubProcess")) {
//                E.SP.setDuration_id(S.DU.getId());
//                return true;
//
//            } else {
//                return false;
//            }
//        }
//        else if (E.Datee.matches("true")) {
//            if (S.Type.matches("Activity")) {
//                S.AC.setDuration_id(E.DU.getId());
//                return true;
//            } else if (S.Type.matches("SubProcess")) {
//                S.SP.setDuration_id(E.DU.getId());
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            if (S.Type_id == 0) {
//                if (E.Type.matches("SubProcess")) {
//                    S.MP.getSubProcesses().add(E.SP);
//                    S.MP.Get_Info();
//                    return true;
//                } else if (E.Type.matches("Employee")) {
//                    S.MP.setMainProcessOwner(E.EM.getName());
//                    return true;
//                } else {
//                    return false;
//                }
//            } else if (S.Type_id == -1) {
//                if (E.Type.matches("Employee")) {
//                    S.TA.Assign(E.EM);
//                    S.TA.Get_Info("         ");
//                    return true;
//                } else {
//                    return false;
//                }
//            } else if (S.Type.matches("SubProcess")) {
//
//                if (E.Type.matches("Activity")) {
//                    S.SP.getActivities().add(E.AC);
//                    S.SP.Get_Info("  ");
//                    return true;
//                } else if (E.Type.matches("SubProcess")) {
//                    S.SP.setParent(E.SP);
//                    S.SP.Get_Info("  ");
//                    return true;
//                } else if (E.Type.matches("Employee")) {
//                    S.SP.getEmployees().add(E.EM);
//                    // E.EM.AddSub(S.SP);
//                    E.EM.getSubProcesses().add(S.SP);
//                    S.SP.Get_Info("  ");
//                    return true;
//                } else {
//                    return false;
//                }
//            } else if (S.Type.matches("Activity")) {
//                if (E.Type.matches("Employee")) {
//                    // S.AC.Assign(E.EM);
//                    S.AC.Get_Info("         ");
//                    return true;
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        }
//    }
//
//    public void Disconnect() {
//        Modi_Label S = Labels_vector.get(S_Index);
//        Modi_Label E = Labels_vector.get(E_Index);
//        if (S == null || E == null) {
//            System.out.println("S index= " + S_Index + " E index= " + E_Index);
//            System.out.println("Labels size= " + Labels_vector.size());
//            int counter = 0;
//            for (int i = 0; i < Labels_vector.size(); i++) {
//
//                if (Labels_vector.get(i) == null) {
//                    counter++;
//                    System.out.println("Label indxex is null= " + i);
//
//                } else {
//                    System.out.println("Label not null= " + Labels_vector.get(i).getText());
//                }
//            }
//            System.out.println("counter size= " + counter);
//
//        } else {
//            Disconnect2Labels(S, E);
//        }
//    }
//
//    public void Disconnect2Labels(Modi_Label L1, Modi_Label L2) {
//        if (L1.Datee.matches("true")) {
//            if (L2.Type.matches("Activity")) {
//                L2.AC.setDuration_id(-1);
//            } else if (L2.Type.matches("SubProcess")) {
//                L2.SP.setDuration_id(-1);
//
//            }
//        } else if (L2.Datee.matches("true")) {
//            if (L1.Type.matches("Activity")) {
//                L1.AC.setDuration_id(-1);
//            } else if (L1.Type.matches("SubProcess")) {
//                L1.SP.setDuration_id(-1);
//            }
//        } else {
//            if (L1.MP != null) {
//                if (L2.SP != null) {
//                    L1.MP.getSubProcesses().remove(L2.SP);
//                } else if (L2.EM != null && L1.MP.getMainProcessOwner().matches(L2.EM.getName())) {
//                    L1.MP.setMainProcessOwner("");
//                }
//            } else if (L1.TA != null) {
//                if (L2.EM != null) {
//                    L1.TA.getEmployees().remove(L2.EM);
//                    List<Task> tasks = new ArrayList<Task>(L2.EM.getTasks());
//                    for (int i = 0; i < tasks.size(); i++) {
//                        if (L1.TA.getShortDesc().matches(tasks.get(i).getShortDesc())) {
//                            tasks.remove(i);
//                            break;
//                        }
//                    }
//                }
//            } else if (L1.SP != null) {
//                if (L2.SP != null && L1.SP.getParent() == L2.SP) {  //***************** should be L1.SP.child.ID.matches( L2.SP.ID)
//                    L1.SP.setParent(null);
//                } else if (L2.EM != null) {
//                    L1.SP.getEmployees().remove(L2.EM);
//                } else if (L2.AC != null) {
//                    L1.SP.getActivities().remove(L2.AC);
//                }
//            } //else if (L1.AC != null) {
//            //if (L2.EM != null) {
//            //  L1.AC.Responsible.remove(L2.EM);
//            //for (int i = 0; i < L2.EM.Activities.size(); i++) {
//            //  if (L1.AC.ID.matches(L2.EM.Activities.get(i))) {
//            //    L2.EM.Activities.remove(i);
//            //  break;
//            //}
//            // }
//            // }
//            //}
//        }
//
//    }
}

