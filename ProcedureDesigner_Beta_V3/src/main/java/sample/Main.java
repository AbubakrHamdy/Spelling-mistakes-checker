package sample;
//import Images_And_Icons;

import Entities.Employee;
import Entities.Process;
import Not_Used.ConfirmBox;
import Roots.General_Label;
import Roots.General_Line;
import Roots.Modi_Group;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.fxmisc.richtext.CodeArea;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static Roots.Root_1.Main_Root_1.M_R_1;
import static Roots.Root_1.Root_1_Forms.*;
import static Roots.Root_1.Root_1_Tables.*;
import static Roots.Root_2.Main_Root_2.M_R_2;
import static Roots.Root_2.Root_2_Forms.*;
import static Roots.Root_2.Root_2_Tables.TasksTree;
import static sample.GUI_Comp.*;
import static sample.Main_Menus.*;
import static sample.Test_Data.db;


public class Main extends Application {
    private static final long serialVersionUID = 287183983634357625L;
    static String EnglishWordsString = "";

    public static Test_Data TT;
    String dragAndClick = "https://stackoverflow.com/questions/41655507/javafx-distinguish-drag-and-click";
    String Maven_Goal = " clean package assembly:single ";
    public static String HeaderStyle = " -fx-background-color:#000000" +
            "        ,linear-gradient(#7ebcea, #2f4b8f),\n" +
            "        linear-gradient(#426ab7, #263e75),\n" +
            "        linear-gradient(#395cab, #223768);\n" +
            "    -fx-background-insets: 0,1,2,3;\n" +
            "    -fx-background-radius: 3,2,2,2;\n" +
            "    -fx-padding: 3 0 3 0;\n" +
            "    -fx-text-fill: white;\n" +
            "    -fx-font-size: 12px;-fx-font-weight: bold;-fx-alignment: center; ";
    public static BorderPane Current_Diagram_workSpace;
    public static Double Zoom_Scale = 1.0;
    public static General_Line Dummy_Line = new General_Line(3);

    public static Double Screen_Width = 0.0;
    public static Double Screen_Height = 0.0;

    public static Boolean prcess_form_Flag = false;
    public static Boolean subProcess_form_flag = false;
    public static ConfirmBoxsFunctions CBF = new ConfirmBoxsFunctions();
    public static Group Current_Root = new Group();

    public static Scene sx;
    public static Process MainProcess;
    public static Vector<General_Label> Labels_vector = new Vector<General_Label>();
    public static Vector<General_Line> Lines_vector = new Vector<General_Line>();
    public static Boolean EmployeeScene = false;
    public static Employee emp;
    public static Process proc;
    public static int start = -1;
    public static History H = new History();

    public static Group root = new Group();
    public static MouseGestures mg = new MouseGestures();
    public static boolean DeleteOn = false;
    public static boolean LinkOn = false;


    public static boolean araabc = false;
    public static GUI_Comp GUI;
    public static Modi_Tree DragedTreeItem = null;

    public static TemplatesCreator templatescreator;
    public static Stage stagedummy;
    public static VBox layout1 = new VBox();
    public static ScrollPane root_scrollPane = new ScrollPane();
    public static Diagram_WorkSpace_Dimensions WorkSpace;

    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage stage) throws IOException {

        Scene scene_w = new Scene(layout1, java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,
                java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        stage.setScene(scene_w);
        stage.setTitle("Procedures Designer");
        stage.setOnCloseRequest(e -> {
            if (araabc == false) {
                e.consume();
                closeProgram(stage, RecentOpenn);
            } else {
                e.consume();
                closeProgramArabic(stage, RecentOpenn);
            }
        });
        scene_w.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setMaximized(true);
        javafx.scene.image.Image Main_ICON = new javafx.scene.image.Image(getClass().getResourceAsStream("/Images_And_Icons/icon-v2.png"));
        stage.getIcons().add(Main_ICON);
        stagedummy = stage;
        stage.show();
        System.out.println("Screen Height: " + scene_w.getHeight());
        System.out.println("Screen Width: " + scene_w.getWidth());
        Screen_Width = scene_w.getWidth();
        Screen_Height = scene_w.getHeight();

        scene_w.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (start != -1 && event.isShiftDown()) {
                    System.out.println("Draw");
                    Dummy_Line.Start_ConnectionBetweenNodeAndMouse(start, Dummy_Line, event);
                    root.getChildren().remove(Dummy_Line);
                    root.getChildren().add(Dummy_Line);
                } else {
                    // System.out.println("Don't Draw");
                    root.getChildren().remove(Dummy_Line);
                }
                //   System.out.println("the mouse is moving bro");
            }
        });
//        b1ebfa
        layout1.setStyle("-fx-background-color: #ededed");
        root.getChildren().add(Current_Root);
        root_scrollPane.setContent(root);
//        root_scrollPane.setContent(Current_Root);
        root_scrollPane.setPrefHeight(Screen_Height - 76.0);
        root_scrollPane.setPrefWidth(Screen_Width - 5);
        root_scrollPane.setStyle("-fx-background: #ededed;");

        stagedummy = stage;
        sx = scene_w;

        Image_Setter IM = new Image_Setter(this);
        layout1.setOnDragOver(event -> {
            // On drag over if the DragBoard has files
//            if (event.getGestureSource() != root && event.getDragboard().hasFiles()) {
//                // All files on the dragboard must have an accepted extension
//                if (!validExtensions.containsAll(
//                        event.getDragboard().getFiles().stream()
//                                .map(file -> getExtension(file.getName()))
//                                .collect(Collectors.toList()))) {
//
//                    event.consume();
//                    return;
//                }
//
//                // Allow for both copying and moving
//                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//            }
//            event.consume();
        });
        layout1.setOnDragDropped(event -> {
            boolean success = false;
            if (event.getGestureSource() != root && event.getDragboard().hasFiles()) {
                event.getDragboard().getFiles().forEach(file -> System.out.println(file.getAbsolutePath()));
                List<File> x = event.getDragboard().getFiles();
                for (int i = 0; i < x.size(); i++) {
//                    try {
////                        addressoben = OpenTheDraggedFile(Labels_vector, Lines_vector, x.get(i));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        AddKeyPress(root);

        Start_Loading(stage, scene_w);
        WorkSpace = new Diagram_WorkSpace_Dimensions();

//        System.out.println("topContainer Height: " + topContainer.getPrefHeight() + "   " + topContainer.getMinHeight() + "  " + topContainer.getHeight() + "  " + topContainer.getMaxHeight());
        /*WritableImage snapshot = root.getScene().snapshot(null);
        //  ((ImageView) snapshot).setImage("Desktop");
        File file = new File("chsdgs1.png");
        try {
            //     BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            //    ImageIO.write(bImage, "png", file);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);


        } catch (Exception s) {
            System.out.println(s);
        }*/
        //        Languages.getItems().clear();
//        GUI = new GUI_Comp(stage, scene_w);


        //    paragraphs1 = SaveToWord();
//        oPenProgram(RecentOpenn, Labels_vector, Lines_vector, stage);
//

//

//

//

//        Undo.setOnAction(e -> H.Undo(root, Labels_vector, Lines_vector));
//
//        Redo.setOnAction(e -> H.Redo(root, Labels_vector, Lines_vector));
//
//
//

//
//        Image_Setter IM_SE = new Image_Setter(this);
//


//


//

//        int MAX_CHARS = 20;


        //  Parent zoomPane = FXMLLoader.load(getClass().getResource("/UI.fxml"));
//        Parent zoomPane = createZoomPane(root, stage);
        //layout1.setPadding(new Insets(0, 0, 0, 0));
        //layout1.setAlignment(Pos.BASELINE_LEFT);
        //layout1.setMargin(root,new Insets(0,0,0,0));
        //System.out.println("Margiiiiiiiiiin" + layout1.getSpacing());
//        layout1.getChildren().setAll(topContainer, root);

//        VBox.setVgrow(zoomPane, Priority.ALWAYS);
    }

    public static void InitializeTemplates() throws IOException, InvalidFormatException {
        File TemplatesFolderPath = new File("D:\\PD");
        templatescreator = new TemplatesCreator(TemplatesFolderPath.getPath());
        boolean exists = TemplatesFolderPath.exists();
        File Images_Path = new File(TemplatesFolderPath.getPath() + "\\" + "Images");

        if (exists) {
            File JD_AR_Path = new File(TemplatesFolderPath.getPath() + "\\" + templatescreator.JD_AR_NameFile);
            File JD_EN_Path = new File(TemplatesFolderPath.getPath() + "\\" + templatescreator.JD_EN_NameFile);
            File P_AR_Path = new File(TemplatesFolderPath.getPath() + "\\" + templatescreator.P_AR_NameFile);
            File P_EN_Path = new File(TemplatesFolderPath.getPath() + "\\" + templatescreator.P_EN_NameFile);


            if (!JD_AR_Path.exists()) {
                System.out.println("create JD_AR_Temp");
                templatescreator.Create_JD_AR_Template();
            }
            if (!JD_EN_Path.exists()) {
                System.out.println("create JD_EN_Temp");
                templatescreator.Create_JD_EN_Template();
            }
            if (!P_AR_Path.exists()) {
                System.out.println("create P_AR_Temp");
                templatescreator.Create_P_AR_Template();
            }
            if (!P_EN_Path.exists()) {
                templatescreator.Create_P_EN_Template();
            }
            if (!Images_Path.exists()) {
                new File(Images_Path.getPath()).mkdirs();
            }
        } else {
            System.out.println("Create All");
            new File(TemplatesFolderPath.getPath()).mkdirs();
            new File(Images_Path.getPath()).mkdirs();

            templatescreator.CreateAllTemplate();
        }
    }

    public static void Start_Loading(Stage stage, Scene scene) {
        Label WelcomeLabel = new Label();
        WelcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px;-fx-font-weight: bold;-fx-alignment: center;");
        WelcomeLabel.setText("Welcome to Procedure Designer ");
        WelcomeLabel.setTranslateX(130);
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(600);
        progressBar.setTranslateY(20);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        Label LoadingLabel = new Label();
        LoadingLabel.setStyle("-fx-text-fill: white; -fx-font-size: 30px;-fx-font-weight: bold;");
        LoadingLabel.setText("Connecting to DataBase ...");


        HBox LoadingHbox = new HBox(20, LoadingLabel, progressBar, progressIndicator);
        LoadingHbox.setTranslateX(100);

        Label WaitLabel = new Label();
        WaitLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;-fx-font-weight: bold;-fx-alignment: center;");
        WaitLabel.setText("Please wait until loading is finished.");
        WaitLabel.setTranslateX(300);

        VBox LoadingVbox = new VBox(170, WelcomeLabel, LoadingHbox, WaitLabel);
        LoadingVbox.setTranslateY(80);
        LoadingVbox.setStyle("-fx-background-color: #ededed;");
        LoadingVbox.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        layout1.getChildren().clear();
        layout1.getChildren().add(LoadingVbox);

        javafx.scene.image.Image Main_ICON = new javafx.scene.image.Image(Main.class.getResourceAsStream("/Images_And_Icons/icon-v2.png"));
        stage.getIcons().add(Main_ICON);

        new Thread() {
            public void run() {
                try {
                    InitializeTemplates();
                    File EnglishWordsFile = new File("words_alpha.txt");
                    EnglishWordsString = FileUtils.readFileToString(EnglishWordsFile, String.valueOf(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidFormatException e) {
                    e.printStackTrace();
                }
                TT = new Test_Data();
                GUI = new GUI_Comp(stage, scene);
                Update_attributes();

                Platform.runLater(new Runnable() {

                    public void run() {
                        layout1.getChildren().clear();
                        layout1.getChildren().setAll(topContainer, root_scrollPane);
                    }
                });

            }
        }.start();
    }

    public static void Refressh() {
        Label WelcomeLabel = new Label();
        WelcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 50px;-fx-font-weight: bold;-fx-alignment: center;");
        WelcomeLabel.setText("Refreshing the data ");
        WelcomeLabel.setTranslateX(130);
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(600);
        progressBar.setTranslateY(20);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setPrefSize(300, 300);
        Label LoadingLabel = new Label();
        LoadingLabel.setStyle("-fx-text-fill: white; -fx-font-size: 30px;-fx-font-weight: bold;");
        LoadingLabel.setText("Connecting to DataBase ...");

        HBox LoadingHbox = new HBox(20, LoadingLabel, progressBar, progressIndicator);
        LoadingHbox.setTranslateX(100);

        Label WaitLabel = new Label();
        WaitLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;-fx-font-weight: bold;-fx-alignment: center;");
        WaitLabel.setText("Please wait until loading is finished.");
        WaitLabel.setTranslateX(300);

        VBox LoadingVbox = new VBox(170, WelcomeLabel, LoadingHbox, WaitLabel);
        LoadingVbox.setTranslateY(80);
        LoadingVbox.setStyle("-fx-background-color: #85f0f2;");
        LoadingVbox.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        layout1.getChildren().clear();
        layout1.getChildren().add(LoadingVbox);

        new Thread() {
            public void run() {
                TT = new Test_Data();

                TasksTree.root.getChildren().clear();
                TasksTree.setupDataForTasks();

                ProcessTree.root.getChildren().clear();
                ProcessTree.setupDataForProcess3();

                EmployeesTree.root.getChildren().clear();
                EmployeesTree.setupDataForEmployees();

                DurationTree.root.getChildren().clear();
                DurationTree.setupDataForDuration();
                Update_attributes();
//                GUI = new GUI_Comp(stagedummy, sx);
                Platform.runLater(new Runnable() {

                    public void run() {
                        layout1.getChildren().clear();
                        layout1.getChildren().setAll(topContainer, root_scrollPane);
                    }
                });

            }
        }.start();
    }

    public static void Update_attributes() {
        List<String> jobTypeStrings = new ArrayList<String>(db.getJobType());
        List<String> jobLocationStrings = new ArrayList<String>(db.getJopLocation());
        List<String> jobLanguages = new ArrayList<String>(db.getLanguages());
        List<String> jobExperiences = new ArrayList<String>(db.getExperience());
        List<String> SubSTDS = new ArrayList<String>(db.getSTDS());

        for (int i = 0; i < jobTypeStrings.size(); i++) {
            //  if(jobTypeStrings.get(i).isEmpty()==false)
            JobType.getItems().add(jobTypeStrings.get(i));
        }

        for (int i = 0; i < jobLocationStrings.size(); i++) {
            // if(jobLocationStrings.get(i).isEmpty()==false)
            JobLocation.getItems().add(jobLocationStrings.get(i));
        }

        for (int i = 0; i < jobExperiences.size(); i++) {
            //  if(jobExperiences.get(i).isEmpty()==false)
            Experience.getItems().add(jobExperiences.get(i));
        }

        for (int i = 0; i < jobLanguages.size(); i++) {
//            if(jobLanguages.get(i).isEmpty()==false)
            Languages.getItems().add(jobLanguages.get(i));
        }

        for (int i = 0; i < SubSTDS.size(); i++) {
            SSTD.getItems().add(SubSTDS.get(i));
        }


    }

    public static boolean RequestToAddLabel(Modi_Tree Tree) {

        boolean TreeItmeHasNewLabelWithForm = false;
        boolean AlreadyThereISNewLabelWithForm = false;
        for (int i = 0; i < Labels_vector.size(); i++) {
            if (Labels_vector.get(i) != null) {
                if (Labels_vector.get(i).Type_id == 0 || Labels_vector.get(i).getText().matches("New Employee") || Labels_vector.get(i).Type_id == -4) {
                    AlreadyThereISNewLabelWithForm = true;
                    break;
                }
            }
        }
        if (Tree.Level == 0 || Tree.Level == -4) {
            TreeItmeHasNewLabelWithForm = true;
        } else if (Tree.EM != null && Tree.EM.getRole().matches("_New Employee_J1")) {
            TreeItmeHasNewLabelWithForm = true;
        }
        ;

        if (TreeItmeHasNewLabelWithForm && AlreadyThereISNewLabelWithForm) {
            return false;
        } else {
            return true;
        }
    }

/* public Boolean Only_One_EMployee_In_JD() {
        for (int i = 0; i < Labels_vector.size(); i++) {
            if (Labels_vector.get(i).Type_id == 1 && EmployeeScene) {
                return false;
            }
        }
        return true;
    }*/


    public static void Clear_All() {
        FormsMenu.getItems().clear();
        TablesMenu.getItems().clear();

        fleMenue4.getItems().removeAll(fleMenue4.getItems());
//        Modi_Group_Functions_Root_2.close_Forms();
        M_R_2.close_Forms();
        M_R_1.close_Forms();
        Operation.getItems().clear();
        Current_Root.getChildren().clear();
        langCat.setValue("English");
        root.getChildren().clear();
        Current_Root.getChildren().clear();
        root.getChildren().add(Current_Root);
        JobLocation.getEditor().clear();
        JobType.getEditor().clear();
        JobTitle.clear();
        JobManager.clear();
        JobQualification.clear();
        PDescription.clear();
        PName.clear();
        POwner.clear();
        PShortD.clear();
        PVersion.clear();
        Languages.getEditor().clear();
        Skills.clear();
        SSTD.getEditor().clear();
        Experience.getEditor().clear();
        SCategory.getEditor().clear();
        Sdescription.clear();
        SName.clear();
        SShortD.clear();
        Labels_vector.clear();
        Lines_vector.clear();
        MainProcess = null;
        prcess_form_Flag = false;
        subProcess_form_flag = false;
        addresssave = "";
        addressoben = "";
    }

    public static void closeProgram(Stage stage, Menu m) {
        boolean answer = ConfirmBox.display("Exit  ", "Are you sure ?");
        try {
            FileOutputStream f = new FileOutputStream("myText2");
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.write(m.getItems().size());
            System.out.println(m.getItems().size());
            for (int i = 0; i < m.getItems().size(); i++) {
                o.writeObject(((N_Menu_item) m.getItems().get(i)).getPath());
                o.writeObject(m.getItems().get(i).getText());
            }
            // Always close files.
            f.close();
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answer) {
            stage.close();
        }
    }

    public static void closeProgramArabic(Stage stage, Menu m) {
        boolean answer = ConfirmBoxArabic.display("خروج", "هل انت متأكد انك تريد الخروج ؟");
        try {

            FileOutputStream f = new FileOutputStream("myText2");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.write(m.getItems().size());

            for (int i = 0; i < m.getItems().size(); i++) {
                o.writeObject(((N_Menu_item) m.getItems().get(i)).getPath());
                o.writeObject(m.getItems().get(i).getText());
            }
            // Always close files.
            f.close();
            o.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answer) {
            stage.close();
        }
    }

    public static void OpenTheDraggedFile(Modi_Group M, String Path) throws IOException, ClassNotFoundException {
        addresssave = "";
        addressoben = "";
        addressoben = Path;
        FileInputStream fi = new FileInputStream(new File(addressoben));
        ObjectInputStream oi = new ObjectInputStream(fi);
        char flag = oi.readChar();
        M.OpenFile(stagedummy, fi, oi);
    }

    public static void Reconnect() {
        System.out.println("size " + Lines_vector.size());
        for (int i = 0; i < Lines_vector.size(); i++) {
            if (Lines_vector.get(i) != null) {
                Lines_vector.get(i).CreateLine(Lines_vector.get(i));
            }
        }
    }

    public static class MouseGestures {
        class DragContext {
            double x;
            double y;
        }

        DragContext dragContext = new DragContext();

        public void makeDraggable(Node node) {
            node.setOnMousePressed(onMousePressedEventHandler);
            node.setOnMouseDragged(onMouseDraggedEventHandler);
        }


        EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
            if (!event.isShiftDown()) {
                Node node = ((Node) (event.getSource()));
                dragContext.x = node.getTranslateX() - event.getSceneX();
                dragContext.y = node.getTranslateY() - event.getSceneY();
            }
        };

        EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
            if (!event.isShiftDown()) {

                Node node = ((Node) (event.getSource()));

                Bounds boundsInScene = node.getBoundsInParent();
                node.setTranslateX(dragContext.x + event.getSceneX());
                node.setTranslateY(dragContext.y + event.getSceneY());
                Reconnect();

//                if (boundsInScene.getMinX() > LeftBound && boundsInScene.getMaxX() < RightBound) {
//                    node.toFront();
//                    node.setTranslateX(dragContext.x + event.getSceneX());
//                    Reconnect();
//                }
//                if (boundsInScene.getMinX() <= LeftBound) {
//                    node.setTranslateX(node.getTranslateX() + 3 + (LeftBound - boundsInScene.getMinX()));
//                    Reconnect();
//                }
//                if (boundsInScene.getMaxX() >= RightBound) {
//                    node.setTranslateX(node.getTranslateX() - 3 - (boundsInScene.getMaxX() - RightBound));
//                    Reconnect();
//                }

//                if (boundsInScene.getMinY() > UperBound) {
//                    node.toFront();
//                    node.setTranslateY(dragContext.y + event.getSceneY()); // uncomment this if you want x/y dragging
//                    Reconnect();
//                }
//                if (boundsInScene.getMinY() <= UperBound) {
//                    node.setTranslateY(node.getTranslateY() + 3 + (UperBound - boundsInScene.getMinY()));
//                    Reconnect();
//                }
//                if(boundsInScene.getMaxX()>=LowerBound){
//                    node.setTranslateY(node.getTranslateY()-3-(boundsInScene.getMaxY()-LowerBound));
//                    Reconnect();
//                }

//                System.out.println("boundsInScene.getMinX()= " + node.getTranslateX());
//                System.out.println("boundsInScene.getMinY()= " + boundsInScene.getMinY());
//                System.out.println("boundsInScene.getMaxX()= " + boundsInScene.getMaxX());
//                System.out.println("boundsInScene.getMaxY()= " + boundsInScene.getMaxY());
//                System.out.println("boundsInScene.getWidth()= " + boundsInScene.getWidth());
//                System.out.println("boundsInScene.getHeight()= " + boundsInScene.getHeight());
////                System.out.println("currunt work space xL = " + Current_Diagram_workSpace.getLayoutX());
////                System.out.println("currunt work space xT = " + Current_Diagram_workSpace.getTranslateX());
//                System.out.println("currunt work space Miy = " + UperBound);
//                System.out.println("currunt work space May = " + LowerBound);
//
//                System.out.println("currunt work space Mix = " + LeftBound);
//                System.out.println("currunt work space Max = " + RightBound);


            }
        };
    }

    private void AddKeyPress(Group scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                scene.setOnKeyPressed((KeyEvent e) -> {
                    if (e.isControlDown()) {
                        if (e.getCode().isLetterKey()) {
                            if (e.getCode().toString() == "Z" || e.getCode().toString() == "z") {
//                                H.Undo(scene, Labels_vector, Lines_vector);
                            } else if (e.getCode().toString() == "X" || e.getCode().toString() == "x") {
//                                H.Redo(scene, Labels_vector, Lines_vector);
                            } else if (e.getCode().toString() == "F" || e.getCode().toString() == "f" && MainProcess != null) {
                                MainProcess.Get_Info();
                            }
                            System.out.println("Get letter = " + e.getText() + " get char= " + e.getCode().toString());
                        }
                    }
                });
            }
        });
    }

    public static String GenerateScreenShot() {
        String Screen_shot_name = "New_Screen";
        root.getChildren().remove(Current_Root);
        File file = new File(Screen_shot_name + ".png");
        WritableImage image = root.snapshot(new SnapshotParameters(), null);
        root.getChildren().add(Current_Root);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }
        return Screen_shot_name + ".png";
    }

    public static List<Modi_IndexRange> spellCheck(String text) {
        String[] dic = text.replaceAll("\\d", "").split("\\W+");
        List<Modi_IndexRange> WordsIndexWithStyle = new ArrayList<Modi_IndexRange>();

        int index = 0;
        for (int i = 0; i < dic.length; i++) {
            String Style = "spell-error";
            if (EnglishWordsString.contains(dic[i].toLowerCase())) {
                Style = "spell-found";
            }
            int indexs = index;
            System.out.println(indexs);
            indexs = text.indexOf(dic[i], indexs);
            Modi_IndexRange IR = new Modi_IndexRange(Style, indexs, indexs + dic[i].length());
            WordsIndexWithStyle.add(IR);
            index = indexs + dic[i].length();
        }
        return WordsIndexWithStyle;
    }

    public static String GetWord(String text, int index, CodeArea codeArea) {
        int beg = 0;
        int end = index;
        for (int i = index; i < text.length(); i++) {
            if (text.charAt(i) == ' ' || text.charAt(i) == ',' || text.charAt(i) == '.' || text.charAt(i) == '\n') {
                break;
            }
            end = i;
        }
        for (int i = index - 1; i >= 0; i--) {
            System.out.println("char =" + text.charAt(i));
            if (text.charAt(i) == ' ' || text.charAt(i) == ',' || text.charAt(i) == '.' || text.charAt(i) == '\n') {
                if (i == index - 1) {
                    beg = i;
                }
                break;
            }
            beg = i;
        }

        String Word = "";
        if (index == text.length()) {
        } else if (index != text.length()) {
            end++;
        }
        Word = text.substring(beg, end);
        Word = Word.replaceAll("\\d", "");
        Word = Word.replaceAll("\\W+", "");
        String Style = "spell-error";

        if (Word.isEmpty()) {
        } else if (EnglishWordsString.contains(Word.toLowerCase())) {
            codeArea.setStyleClass(beg, end, "spell-found");
        } else {
            codeArea.setStyleClass(beg, end, "spell-error");
        }
        System.out.println("word=|" + Word + "|");
        return Word;
    }
}