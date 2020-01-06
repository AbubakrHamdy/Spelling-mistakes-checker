package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBoxArabic {
    static boolean answer;
    public static boolean display(String titile,String message ){
        Stage window2=new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle(titile);
        window2.setMinWidth(400);
        Label lable=new Label();
        lable.setText(message);

        Button yesButton=new Button("نعم");
        Button noButton=new Button("لا");

        yesButton.setOnAction(e->{
            answer=true;
            window2.close();
        });
        noButton.setOnAction(e->{
            answer=false;
            window2.close();
        });

        VBox layout=new VBox(10);
        layout.getChildren().addAll(lable,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout,250,150);
        window2.setScene(scene);
        window2.showAndWait();
        return answer;
    }
}

