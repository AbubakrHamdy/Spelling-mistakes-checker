package sample;

import java.io.Serializable;

public class History implements Serializable {
//
//    int Current = 0;
//    public Vector<SceneComponents> SC = new Vector<SceneComponents>();
//
//    History() {
//        SceneComponents sc = new SceneComponents();
//        sc.Labels = new Vector<Modi_Label>();
//        sc.Lines = new Vector<Modi_Line>();
//        SC.add(sc);
//    }
//
//    public void add(Vector<Modi_Label> Labels, Vector<Modi_Line> Lines) {
//        if (EmployeeScene == false) {
//            SceneComponents sc = new SceneComponents();
//            for (int i = 0; i < Labels.size(); i++) {
//                if (Labels.get(i) != null) {
//                    Modi_Label L = new Modi_Label(Labels.get(i));
//                    sc.Labels.add(L);
//
//                } else {
//                    Modi_Label L = null;
//                    sc.Labels.add(L);
//                }
//            }
//
//            for (int i = 0; i < Lines.size(); i++) {
//                if (Lines.get(i) != null) {
//                    Modi_Line L = new Modi_Line(Lines.get(i), sc.Labels);
//                    sc.Lines.add(L);
//                } else {
//                    Modi_Line L = null;
//                    sc.Lines.add(L);
//                }
//            }
//            if (MainProcess != null) {
//                for (int i = 0; i < sc.Labels.size(); i++) {
//                    if (sc.Labels.get(i) != null && sc.Labels.get(i).Type_id == 0) {
//                        sc.Label_MP_Index = sc.Labels.get(i).index;
//                    }
//                }
//            }
//            Current++;
//            SC.add(Current, sc);
//            for (int i = Current + 1; i < SC.size(); i++) {
//                SC.remove(i);
//            }
//            System.out.println("Currunt pos= " + Current);
//        }
//    }
//
//    public void Undo(Group root, Vector<Modi_Label> Labels, Vector<Modi_Line> Lines) {
//        if (Current >= 0) {
//            Current--;
//            changeCurrentScene(root, Labels, Lines);
//            System.out.println("Currunt pos now= " + Current);
//        }
//    }
//
//    public void Redo(Group root, Vector<Modi_Label> Labels, Vector<Modi_Line> Lines) {
//        System.out.println("REDO  Curr= " + Current + " < Size = " + (SC.size() - 1));
//        if (Current < SC.size() - 1) {
//            Current++;
//            changeCurrentScene(root, Labels, Lines);
//        }
//    }
//
//    public void changeCurrentScene(Group root, Vector<Modi_Label> Labels, Vector<Modi_Line> Lines) {
//        root.getChildren().removeAll(Labels);
//        root.getChildren().removeAll(Lines);
//
//        System.out.println("Currunt pos now = " + Current);
//        System.out.println("Size = " + SC.size());
//
//        Vector<Modi_Label> TLabels = SC.get(Current).Labels;
//        Vector<Modi_Line> TLines = SC.get(Current).Lines;
//
//        Labels.clear();
//        Lines.clear();
//        //     Labels.addAll(TLabels);
//        for (int i = 0; i < TLabels.size(); i++) {
//            if (TLabels.get(i) != null) {
//                Modi_Label L = new Modi_Label(TLabels.get(i));
//                Labels.add(L);
//            } else {
//                Modi_Label L = null;
//                Labels.add(L);
//            }
//        }
//
//        for (int i = 0; i < Labels.size(); i++) {
//            if (Labels.get(i) != null) {
//                root.getChildren().add(Labels.get(i));
//            }
//        }
//
//        for (int i = 0; i < TLines.size(); i++) {
//            if (TLines.get(i) == null) {
//                Modi_Line L = null;
//                Lines.add(L);
//            } else {
//                Modi_Line L = new Modi_Line(Labels.get(TLines.get(i).S_Index), Labels.get(TLines.get(i).E_Index));
//                Labels.get(TLines.get(i).S_Index).lines_id.add(L.Line_Index);
//                Labels.get(TLines.get(i).E_Index).lines_id.add(L.Line_Index);
//            }
//        }
//        if (SC.get(Current).Label_MP_Index != -1) {
//            MainProcess = Labels.get(SC.get(Current).Label_MP_Index).MP;
//        }
//    }
}
