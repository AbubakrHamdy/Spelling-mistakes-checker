package sample;

import Roots.Root_1.Main_Root_1;
import Roots.Root_2.Main_Root_2;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class GUI_Comp {

    public static String addressoben = "";
    public static String picName = "";
    public static String addresssave = "";


    public static MenuItem Save_Employee_Tasks = new MenuItem("Update JD");
    public static MenuItem Insert_Employee = new MenuItem("Add JD to Database");

    ///////////////////// Procedure Form

    public static MenuItem Save_Process = new MenuItem("Add New Operating Procedure to Data base");
    public static MenuItem Save_Process2 = new MenuItem("Update Operating Procedure");


    public static MenuItem Insert_New_SubProcess = new MenuItem("Insert SubProcess to database");
    public static MenuItem Update_Sub_Process = new MenuItem("Update SubProcess to database");

    ///////////////////////////////////////////////////////////////////////////////////////////// in Main class

    public static Menu Operation = new Menu("Operation");
    public static List<String> validExtensions = Arrays.asList("opd", "jd");
    public static Button checkButton = new Button("NOT SAVED");
    public static VBox topContainer ; //Creates a container to hold all Menu Objects.

    //////////////////////////////////////////////////////// Tooltips

    public static Tooltip save_file_to_database_tip = new Tooltip();
    public static Tooltip refresh_tip = new Tooltip();
    public static Tooltip open_tip = new Tooltip();
    public static Tooltip close_tip = new Tooltip();
    public static Tooltip save_tip = new Tooltip();
    public static Tooltip save_as_tip = new Tooltip();
    public static Tooltip help_tip = new Tooltip();
    //ZoomAnBtn.setAccelerator(new KeyCodeCombination(KeyCode.I));
    public static Tooltip zoom_in_tip = new Tooltip();
    public static Tooltip zoom_out_tip = new Tooltip();
    public static Tooltip new_op_tip = new Tooltip();
    public static Tooltip new_jd_tip = new Tooltip();
    public static Tooltip link_tip = new Tooltip();
    public static Tooltip delete_tip = new Tooltip();

    /////////////////////////////////////////////////////////////////////////////////////////////


    GUI_Comp(Stage stage, Scene scene) {
        topContainer = new VBox();
        Main_Root_1 MR1 =new Main_Root_1(scene);
        Main_Root_2 MR2 =new Main_Root_2(scene);
        Main_Shortcut_Buttons MSB=new Main_Shortcut_Buttons(stage,scene);
        Main_Menus MM=new Main_Menus(stage);

        checkButton.setStyle("-fx-font: 18 arial; -fx-base: #d3191c;");
        checkButton.setTranslateX(1250);
        checkButton.setMaxWidth(200);

        topContainer.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);

        MM.menuBar.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        MSB.toolbar.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        System.out.println("menuBar Height: " + MM.menuBar.getPrefHeight() + "   " + MM.menuBar.getMinHeight() + "  " + MM.menuBar.getHeight() + "  " + MM.menuBar.getMaxHeight());
        System.out.println("toolbar Height: " + MSB.toolbar.getPrefHeight() + "   " + MSB.toolbar.getMinHeight() + "  " + MSB.toolbar.getHeight() + "  " + MSB.toolbar.getMaxHeight());

//        topContainer.getChildren().se
        topContainer.getChildren().add(MM.menuBar);
        topContainer.getChildren().add(MSB.toolbar);

        Set_Tooltip_Language_To_English();
    }


    public static void Set_Tooltip_Language_To_English() {

        save_file_to_database_tip.setText("save file to DB");
        save_file_to_database_tip.setFont(Font.font(18));

        refresh_tip.setFont(Font.font(18));
        refresh_tip.setText("Refresh");

        open_tip.setText("Open");
        open_tip.setFont(Font.font(18));

        close_tip.setText("Close File");
        close_tip.setFont(Font.font(18));

        save_tip.setText("Save");
        save_tip.setFont(Font.font(18));

        save_as_tip.setText("Save As");
        save_as_tip.setFont(Font.font(18));

        help_tip.setText("Help");
        help_tip.setFont(Font.font(18));

        zoom_in_tip.setText("Zoom IN");
        zoom_in_tip.setFont(Font.font(18));

        zoom_out_tip.setText("Zoom Out");
        zoom_out_tip.setFont(Font.font(18));

        new_op_tip.setText("New OP");
        new_op_tip.setFont(Font.font(18));

        new_jd_tip.setText("New JD");
        new_jd_tip.setFont(Font.font(18));


        link_tip.setText("Link");
        link_tip.setFont(Font.font(18));

        delete_tip.setText("Delete");
        delete_tip.setFont(Font.font(18));

    }

    public static void Set_Tooltip_Language_To_Arabic() {

        refresh_tip.setText("تحديث");

        open_tip.setText("فتح");

        close_tip.setText("اغلاق");

        save_tip.setText("حفظ");

        save_as_tip.setText("حفظ كـ");

        help_tip.setText("مساعدة");

        zoom_in_tip.setText("تفريب");

        zoom_out_tip.setText("ابعاد");

        new_op_tip.setText("ملف اجراءات عمل جديد");

        new_jd_tip.setText("ملف مسمي وظيفي جديد");

        link_tip.setText("ربط");

        delete_tip.setText("مسح");
    }


}
