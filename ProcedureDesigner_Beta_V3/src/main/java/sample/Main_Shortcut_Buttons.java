package sample;

import Roots.General_Label;
import Roots.Modi_Group;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static Roots.Root_1.Main_Root_1.M_R_1;
import static Roots.Root_2.Main_Root_2.M_R_2;
import static sample.GUI_Comp.*;
import static sample.Main.*;
import static sample.Main_Menus.*;

public class Main_Shortcut_Buttons {

    public static Button Refresh = new Button();
    public static Button Generate = new Button("Generate");
    public static Button openFileBtn = new Button();
    public static Button closeFileBtn = new Button();
    public static Button SaveFileBtn = new Button();
    public static Button saveFile = new Button();
    public static Button SaveAsFileBtn = new Button();
    public static Button ZoomAnBtn = new Button();
    public static Button ZoomOutFileBtn = new Button();
    public static Button OpenHelpfile = new Button();
    public static Button NewOP = new Button();
    public static Button NewJD = new Button();
    public static Button link = new Button();
    public static Button printt = new Button();
    public static Button delete = new Button();
    public static Button btnn = new Button("Add new sub process");
    public static Button btnnTask = new Button("Add new Task");

    public static ToolBar toolbar;


    Main_Shortcut_Buttons(Stage stage, Scene scene) {
        toolbar = new ToolBar();
        Set_Main_Shortcut_Buttons_Style();
        toolbar.getItems().addAll(NewJD, NewOP, link, delete, Refresh, openFileBtn, closeFileBtn, SaveFileBtn, SaveAsFileBtn, ZoomAnBtn, ZoomOutFileBtn, OpenHelpfile);
//        51c6e0
//        ffffff
        toolbar.setStyle("-fx-background-color: #51c6e0;-fx-border-width: .5;" + "-fx-border-color: #0b7eb8;");
        Set_Main_Shortcut_Buttons_Style();
        Setup_Main_Shortcut_Buttons_To_English();
        Set_Action_To_Shortcut_Buttons(stage, scene);
    }

    public void Set_Action_To_Shortcut_Buttons(Stage stage, Scene scene) {

        NewJD.setOnAction(e -> {
            M_R_2.Get_Roor_2_Scene();
        });

        NewOP.setOnAction(e -> {
            M_R_1.Get_Roor_1_Scene();
        });

        link.setOnAction(e -> {
            OnClickOnLink();
//            if (LinkOn == true) {
//                LinkOn = false;
//                link.setStyle(null);
//            } else {
////                link.setStyle("-fx-background-color: #9bc1ff;");
//                LinkOn = true;
//            }
////            delete.setStyle(null);
//            DeleteOn = false;
        });

        printt.setOnAction(e -> {
            GenerateScreenShot();
        });

        delete.setOnAction(e -> {
            OnClickOnDelete();

//            if (DeleteOn == true) {
//                DeleteOn = false;
//                delete.setStyle(null);
//            } else {
//                DeleteOn = true;
////                delete.setStyle("-fx-background-color: #9bc1ff;");
//            }
////            link.setStyle(null);
//            LinkOn = false;
        });

        closeFileBtn.setOnAction(e -> {
            fleMenue4.getItems().removeAll(fleMenue4.getItems());
            Clear_All();
        });

        SaveAsFileBtn.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).SaveLabelToDB(stage);
                }
            }
        });

        ZoomAnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i) instanceof General_Label) {
                        if (((General_Label) root.getChildren().get(i)).getScaleX() < 1.2 && ((General_Label) root.getChildren().get(i)).getScaleY() < 1.2) {
                            ((General_Label) root.getChildren().get(i)).setScaleX(((General_Label) root.getChildren().get(i)).getScaleX() * 1.1);
                            ((General_Label) root.getChildren().get(i)).setScaleY(((General_Label) root.getChildren().get(i)).getScaleY() * 1.1);
                        }
                    }
                    if (Zoom_Scale < 1.2) {
                        Zoom_Scale = Zoom_Scale * 1.1;
                    }
                }
            }
        });

        ZoomOutFileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < root.getChildren().size(); i++) {
                    if (root.getChildren().get(i) instanceof General_Label) {
                        if (((General_Label) root.getChildren().get(i)).getScaleX() != 1 && ((General_Label) root.getChildren().get(i)).getScaleY() != 1) {
                            ((General_Label) root.getChildren().get(i)).setScaleX(((General_Label) root.getChildren().get(i)).getScaleX() * 1 / 1.1);
                            ((General_Label) root.getChildren().get(i)).setScaleY(((General_Label) root.getChildren().get(i)).getScaleY() * 1 / 1.1);
                        }
                    }
                }
                if (Zoom_Scale != 1) {
                    Zoom_Scale = Zoom_Scale * 1 / 1.1;
                }
            }
        });

        SaveFileBtn.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).SaveLabelToDB(stage);
                }
            }
        });

        openFileBtn.setOnAction(e -> {
            OpenFileAndAddItToOpenRecentIfItWasnotAdded(stage);
        });

        Refresh.setOnAction(e -> {
            Refressh();
        });

    }

    public void Set_Main_Shortcut_Buttons_Style() {

        String IDLE_BUTTON_STYLE = " -fx-background-color: \n" +
                "        rgba(0,0,0,0.08),\n" +
                "        linear-gradient(#5a61af, #51536d),\n" +
                "        linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);\n" +
                "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
                "    -fx-background-radius: 5,5,4;\n" +
                "    -fx-padding: 3 15 3 15;\n" +
                "    -fx-text-fill: #242d35;\n" +
                "    -fx-font-size: 14px;";


        String HOVERED_BUTTON_STYLE = " -fx-background-color: \n" +
                "        #3c7fb1,\n" +
                "        linear-gradient(#fafdfe, #e8f5fc),\n" +
                "        linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);\n" +
                "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
                "    -fx-background-radius: 5,5,4;\n" +
                "    -fx-padding: 3 22 3 22;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 14px;";

        NewOP.setStyle(IDLE_BUTTON_STYLE);
        NewJD.setStyle(IDLE_BUTTON_STYLE);
        link.setStyle(IDLE_BUTTON_STYLE);
        delete.setStyle(IDLE_BUTTON_STYLE);
        Refresh.setStyle(IDLE_BUTTON_STYLE);
        openFileBtn.setStyle(IDLE_BUTTON_STYLE);
        closeFileBtn.setStyle(IDLE_BUTTON_STYLE);
        SaveFileBtn.setStyle(IDLE_BUTTON_STYLE);
        SaveAsFileBtn.setStyle(IDLE_BUTTON_STYLE);
        ZoomAnBtn.setStyle(IDLE_BUTTON_STYLE);
        ZoomOutFileBtn.setStyle(IDLE_BUTTON_STYLE);
        OpenHelpfile.setStyle(IDLE_BUTTON_STYLE);

        NewOP.setOnMouseEntered(e -> NewOP.setStyle(HOVERED_BUTTON_STYLE));
        NewOP.setOnMouseExited(e -> NewOP.setStyle(IDLE_BUTTON_STYLE));
        NewOP.setOnMouseClicked(e -> NewOP.setStyle(IDLE_BUTTON_STYLE));

        closeFileBtn.setOnMouseEntered(e -> closeFileBtn.setStyle(HOVERED_BUTTON_STYLE));
        closeFileBtn.setOnMouseExited(e -> closeFileBtn.setStyle(IDLE_BUTTON_STYLE));
        closeFileBtn.setOnMouseClicked(e -> closeFileBtn.setStyle(IDLE_BUTTON_STYLE));

        openFileBtn.setOnMouseEntered(e -> openFileBtn.setStyle(HOVERED_BUTTON_STYLE));
        openFileBtn.setOnMouseExited(e -> openFileBtn.setStyle(IDLE_BUTTON_STYLE));
        openFileBtn.setOnMouseClicked(e -> openFileBtn.setStyle(IDLE_BUTTON_STYLE));

        NewJD.setOnMouseEntered(e -> NewJD.setStyle(HOVERED_BUTTON_STYLE));
        NewJD.setOnMouseClicked(e -> NewJD.setStyle(IDLE_BUTTON_STYLE));
        NewJD.setOnMouseExited(e -> NewJD.setStyle(IDLE_BUTTON_STYLE));

        link.setOnMouseEntered(e -> link.setStyle(HOVERED_BUTTON_STYLE));
        link.setOnMouseExited(e -> link.setStyle(IDLE_BUTTON_STYLE));
        link.setOnMouseClicked(e -> link.setStyle(IDLE_BUTTON_STYLE));

        delete.setOnMouseEntered(e -> delete.setStyle(HOVERED_BUTTON_STYLE));
        delete.setOnMouseExited(e -> delete.setStyle(IDLE_BUTTON_STYLE));
        delete.setOnMouseClicked(e -> delete.setStyle(IDLE_BUTTON_STYLE));

        Refresh.setOnMouseEntered(e -> Refresh.setStyle(HOVERED_BUTTON_STYLE));
        Refresh.setOnMouseExited(e -> Refresh.setStyle(IDLE_BUTTON_STYLE));
        Refresh.setOnMouseClicked(e -> Refresh.setStyle(IDLE_BUTTON_STYLE));

        SaveFileBtn.setOnMouseEntered(e -> SaveFileBtn.setStyle(HOVERED_BUTTON_STYLE));
        SaveFileBtn.setOnMouseExited(e -> SaveFileBtn.setStyle(IDLE_BUTTON_STYLE));
        SaveFileBtn.setOnMouseClicked(e -> SaveFileBtn.setStyle(IDLE_BUTTON_STYLE));

        SaveAsFileBtn.setOnMouseEntered(e -> SaveAsFileBtn.setStyle(HOVERED_BUTTON_STYLE));
        SaveAsFileBtn.setOnMouseExited(e -> SaveAsFileBtn.setStyle(IDLE_BUTTON_STYLE));
        SaveAsFileBtn.setOnMouseClicked(e -> SaveAsFileBtn.setStyle(IDLE_BUTTON_STYLE));

        ZoomAnBtn.setOnMouseEntered(e -> ZoomAnBtn.setStyle(HOVERED_BUTTON_STYLE));
        ZoomAnBtn.setOnMouseExited(e -> ZoomAnBtn.setStyle(IDLE_BUTTON_STYLE));
        ZoomAnBtn.setOnMouseClicked(e -> ZoomAnBtn.setStyle(IDLE_BUTTON_STYLE));

        ZoomOutFileBtn.setOnMouseEntered(e -> ZoomOutFileBtn.setStyle(HOVERED_BUTTON_STYLE));
        ZoomOutFileBtn.setOnMouseExited(e -> ZoomOutFileBtn.setStyle(IDLE_BUTTON_STYLE));
        ZoomOutFileBtn.setOnMouseClicked(e -> ZoomOutFileBtn.setStyle(IDLE_BUTTON_STYLE));

        OpenHelpfile.setOnMouseEntered(e -> OpenHelpfile.setStyle(HOVERED_BUTTON_STYLE));
        OpenHelpfile.setOnMouseExited(e -> OpenHelpfile.setStyle(IDLE_BUTTON_STYLE));
        OpenHelpfile.setOnMouseClicked(e -> OpenHelpfile.setStyle(IDLE_BUTTON_STYLE));


        Generate.setTranslateX(1210);
        Generate.setTranslateY(650);

    }

    public static void Setup_Main_Shortcut_Buttons_To_English() {
        Refresh.setTooltip(refresh_tip);
        openFileBtn.setTooltip(open_tip);
        closeFileBtn.setTooltip(close_tip);
        SaveFileBtn.setTooltip(save_tip);
        SaveAsFileBtn.setTooltip(save_as_tip);
        OpenHelpfile.setTooltip(help_tip);
        ZoomAnBtn.setTooltip(zoom_in_tip);
        ZoomOutFileBtn.setTooltip(zoom_out_tip);
        NewOP.setTooltip(new_op_tip);
        NewJD.setTooltip(new_jd_tip);
        link.setTooltip(link_tip);
        delete.setTooltip(delete_tip);
        Generate.setText("Generate");
    }

    public static void Setup_Main_Shortcut_Buttons_To_Arabic() {

        Refresh.setTooltip(refresh_tip);
        openFileBtn.setTooltip(open_tip);
        closeFileBtn.setTooltip(close_tip);
        SaveFileBtn.setTooltip(save_tip);
        SaveAsFileBtn.setTooltip(save_as_tip);
        OpenHelpfile.setTooltip(help_tip);
        ZoomAnBtn.setTooltip(zoom_in_tip);
        ZoomOutFileBtn.setTooltip(zoom_out_tip);
        NewOP.setTooltip(new_op_tip);
        NewJD.setTooltip(new_jd_tip);
        link.setTooltip(link_tip);
        delete.setTooltip(delete_tip);
        Generate.setText("انتاج");
    }

    public static void UnClick() {
//        Link.setStyle(null);
//        Delete.setStyle(null);
//        delete.setStyle(null);

        System.out.println("unclick");
    }

    public void OnClickOnLink(){
        if(LinkOn==true){
            LinkOn=false;
            javafx.scene.image.Image LINK_ICON = new javafx.scene.image.Image(getClass().getResourceAsStream("/Images_And_Icons/Link-icon.png"), 32, 32, false, false);
            ImageView image19 = new ImageView(LINK_ICON);
            image19.setSmooth(true);
            image19.setFitWidth(20);
            image19.setFitHeight(20);
            link.setGraphic(image19);
        }
        else{
            LinkOn=true;
            if(DeleteOn==true){
                OnClickOnDelete();
            }
            javafx.scene.image.Image LINK_ICON = new Image(getClass().getResourceAsStream("/Images_And_Icons/Link-icon11.png"), 32, 32, false, false);
            ImageView image19 = new ImageView(LINK_ICON);
            image19.setSmooth(true);
            image19.setFitWidth(20);
            image19.setFitHeight(20);
            link.setGraphic(image19);
        }
    }
    public void OnClickOnDelete(){
        if(DeleteOn==true){
            DeleteOn=false;
            javafx.scene.image.Image LINK_ICON = new Image(getClass().getResourceAsStream("/Images_And_Icons/delete (1).png"), 32, 32, false, false);
            ImageView image19 = new ImageView(LINK_ICON);
            image19.setSmooth(true);
            image19.setFitWidth(20);
            image19.setFitHeight(20);
            delete.setGraphic(image19);
        }
        else{
            System.out.println("you clicked on delete!");
            DeleteOn=true;
            if(LinkOn==true){
                OnClickOnLink();
            }
            javafx.scene.image.Image LINK_ICON = new Image(getClass().getResourceAsStream("/Images_And_Icons/delete (3).png"), 32, 32, false, false);
            ImageView image19 = new ImageView(LINK_ICON);
            image19.setSmooth(true);
            image19.setFitWidth(20);
            image19.setFitHeight(20);
            delete.setGraphic(image19);
//            Image image = new Image(getClass().getResourceAsStream("/Images_And_Icons/delete (2).png"));
//            Delete.setGraphic(new ImageView(image));

        }
    }
}
