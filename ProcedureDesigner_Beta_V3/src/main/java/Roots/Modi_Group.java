package Roots;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public abstract class Modi_Group extends BorderPane {
    public static String  Labels_Style = "-fx-font-size: 11px;-fx-text-fill: #654b00;-fx-font-weight: bold;";

    public static String FormClosingButtonStyle_Idle = " -fx-background-color:#000000" +
            "        ,linear-gradient(#7ebcea, #2f4b8f),\n" +
            "        linear-gradient(#426ab7, #263e75),\n" +
            "        linear-gradient(#395cab, #223768);\n" +
            "    -fx-background-insets: 0,1,2,3;\n" +
            "    -fx-background-radius: 3,2,2,2;\n" +
            "    -fx-padding: 3 0 3 0;\n";
    public static String FormClosingButtonStyle_Hover = " -fx-background-color:#000000" +
            "        ,linear-gradient(#7ebcea, #2f4b8f),\n" +
            "        linear-gradient(#7b9ce8, #7b9ce8),\n" +
            "        linear-gradient(#395cab, #223768);\n" +
            "    -fx-background-insets: 0,1,2,3;\n" +
            "    -fx-background-radius: 3,2,2,2;\n" +
            "    -fx-padding: 3 0 3 0;";

    public static String FormClosingButtonStyle_Click = " -fx-background-color:#000000" +
            "        ,linear-gradient(#7ebcea, #2f4b8f),\n" +
            "        linear-gradient(#426ab7, #263e75),\n" +
            "        linear-gradient(#0a2a73, #112961);\n" +
            "    -fx-background-insets: 0,1,2,3;\n" +
            "    -fx-background-radius: 3,2,2,2;\n" +
            "    -fx-padding: 3 0 3 0;";

    public static String FormHeaderBackGround=" -fx-background-color:#000000" +
            "        ,linear-gradient(#7ebcea, #2f4b8f),\n" +
            "        linear-gradient(#426ab7, #263e75),\n" +
            "        linear-gradient(#395cab, #223768);\n" +
            "    -fx-background-insets: 0,1,2,3;\n" +
            "    -fx-background-radius: 3,2,2,2;\n" +
            "    -fx-padding: 3 0 3 0;\n" +
            "    -fx-text-fill: white;\n" +
            "    -fx-font-size: 12px;-fx-font-weight: bold;-fx-alignment: center; ";

    public static String FormBodyBackGround="-fx-background-color: #87d7ff;-fx-border-width: 1.5;" + "-fx-border-color: #0b7eb8;";

    public  void close_Forms() {

    }
    public static void SaveLabelToDB() {
    }

    public static void Save_As() {
    }

    public static void CloseVBoxForm(VBox VB){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(VB.prefWidthProperty(), 230)
                ),
                new KeyFrame(Duration.millis(500.0d),
                        new KeyValue(VB.prefWidthProperty(), 0)
                )
        );
        timeline.play();
        timeline.setOnFinished((evt) -> {
            VB.setVisible(false);
            VB.getChildren().clear();
        });
    }

    public static void CloseVBoxTable(VBox VB){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(VB.prefWidthProperty(), 230)
                ),
                new KeyFrame(Duration.millis(500.0d),
                        new KeyValue(VB.prefWidthProperty(), 0)
                )
        );
        timeline.play();
        timeline.setOnFinished((evt) -> {
            VB.setVisible(false);
        });
    }

    public static void OpenVBox(VBox VB){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(VB.prefWidthProperty(), 0)
                ),
                new KeyFrame(Duration.millis(500.0d),
                        new KeyValue(VB.prefWidthProperty(), 230)
                )
        );
        timeline.play();
        VB.setVisible(true);
    }

    public abstract void SaveLabelToDB(Stage primaryStage);

    public  String SaveToFile(Stage primaryStage, String Path){
        return null;
    }


    public abstract String OpenFile(Stage primaryStage, FileInputStream fi , ObjectInputStream oi);


    public void Save_File_To_DB(String p) {

    }
}
