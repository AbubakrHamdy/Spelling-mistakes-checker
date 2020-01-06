package Roots.Root_1;

import Entities.SubProcess;
import Roots.General_Label;
import Roots.Modi_Group;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import static Roots.Modi_Group.*;
import static sample.Main.*;
import static sample.Main_Menus.Close_Form_Menu_Item;
import static sample.Main_Menus.FormsMenu;
import static sample.Test_Data.db;

public class Root_1_Forms {
    public static Button ProcessFormClosingButton = new Button();
    public static Button SubProcessClosingButton = new Button();
    public static Button ImageAndDurationClosingButton = new Button();

    public static Double ProcessFormWidth = 300.0;

    public static VBox Process_Current_Form_Root;
    public static VBox Process_Form_Root;
    public static VBox SubProcess_Form_Root;
    public static VBox SubProcess_Form_Root2;
    public static VBox ImageAndDuration_Form_Root;

    //////////////////////////// ImagesAndDuration

    public static Label ImageAndDurationFormHeader = new Label("Image And Duration Form");

    ///////////////////// Main Process
    public static Label PDescriptionL = new Label("Procedure Description");
    public static Label POwnerL = new Label("Procedure Owner");
    public static Label PSNameL = new Label("Procedure Name");
    public static Label PShortDL = new Label("Procedure Short Description");
    public static Label PVersionL = new Label("Procedure Version");
    public static Label Pdescription = new Label("Operating Procedure");

    public static CodeArea PDescription = new CodeArea();
    public static TextField POwner = new TextField();
    public static TextField PShortD = new TextField();
    public static TextField PVersion = new TextField();
    public static TextField PName = new TextField();

    ///////////////////// SubProcess Form 1
    public static Label SDescribtionL = new Label("New SubProcess");/// i think it is form headline
    public static Label langChoice = new Label("SubProcess Language");
    public static Label SdescriptionL = new Label("SubProcess Description");
    public static Label SCategoryL = new Label("SubProcess Category");
    public static Label SNameL = new Label("SubProcess Name");
    public static Label ParentNameL = new Label("Parent Name");
    public static Label SSTDL = new Label("SubProcess standard");
    public static Label SShortDL = new Label("SubProcess Short Description");
    public static Label SPImagesL = new Label("SubProcess Image");
    public static Label SPDurationL = new Label("SubProcess Duration");

    public static TextField Sdescription = new TextField();
    public static ComboBox SCategory = new ComboBox();
    public static TextField SName = new TextField();
    public static ComboBox SSTD = new ComboBox();
    public static TextField SShortD = new TextField();
    public static ComboBox ParentNameCat = new ComboBox();
    public static ChoiceBox langCat = new ChoiceBox();
    public static ComboBox SPImage = new ComboBox();
    public static ComboBox SPDuration = new ComboBox();

    public static Button Generate_New_SubProcess = new Button("Generate New SubProcess");

    /////////////////////


    ///////////////////// SubProcess Form 2
    public static Label SDescribtionL2 = new Label("New SubProcess");
    public static Label SdescriptionL2 = new Label("SubProcess Description");
    public static Label SCategoryL2 = new Label("SubProcess Category");
    public static Label SNameL2 = new Label("SubProcess Name");
    public static Label SSTDL2 = new Label("SubProcess standard");
    public static Label SShortDL2 = new Label("SubProcess Short Description");

    public static TextField Sdescription2 = new TextField();
    public static TextField SCategory2 = new TextField();
    public static TextField SName2 = new TextField();
    public static ComboBox SSTD2 = new ComboBox();
    public static TextField SShortD2 = new TextField();
    public static Label langChoice2 = new Label("SubProcess Language");
    public static ChoiceBox langCat2 = new ChoiceBox();


    public Root_1_Forms() {
        Process_Current_Form_Root = new VBox();
        Process_Form_Root = new VBox();
        SubProcess_Form_Root = new VBox();
        SubProcess_Form_Root2 = new VBox();
        ImageAndDuration_Form_Root = new VBox();

        Setup_All_Root_1_Forms();
    }

    public void Setup_All_Root_1_Forms() {
        Process_Current_Form_Root.setVisible(false);
        Setup_OP_Form();
        Setup_SupOP_Form1();
        Setup_SupOP_Form2();
        Setup_ImageAndDuration_Form();
    }

    public void Setup_ImageAndDuration_Form() {
        ImageAndDuration_Form_Root.setPrefHeight(Screen_Height - 137);
        ImageAndDuration_Form_Root.setPrefWidth(ProcessFormWidth);
        Setup_ImageAndDuration_Form_Header();
        Setup_ImageAndDuration_Form_Body();
    }

    public void Setup_ImageAndDuration_Form_Header() {
        ImageAndDurationFormHeader.setTextFill(Color.web("#ffffff"));
        ImageAndDurationClosingButton.setTranslateX(-5);
        ImageAndDurationClosingButton.setPrefWidth(50);
        ImageAndDurationClosingButton.setMinHeight(15);
        ImageAndDurationClosingButton.setPrefHeight(15);
        ImageAndDurationClosingButton.setMaxHeight(15);
        ImageAndDurationClosingButton.setStyle(FormClosingButtonStyle_Idle);
        ImageAndDurationClosingButton.setOnMouseEntered(e -> ImageAndDurationClosingButton.setStyle(FormClosingButtonStyle_Hover));
        ImageAndDurationClosingButton.setOnMouseExited(e -> ImageAndDurationClosingButton.setStyle(FormClosingButtonStyle_Idle));
        ImageAndDurationClosingButton.setOnMouseClicked(e -> ImageAndDurationClosingButton.setStyle(FormClosingButtonStyle_Click));
        ImageAndDurationClosingButton.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).close_Forms();
                }
            }
            FormsMenu.getItems().remove(Close_Form_Menu_Item);
        });
        BorderPane HeaderLayout = new BorderPane();
        HeaderLayout.setCenter(ImageAndDurationFormHeader);
        HeaderLayout.setRight(ImageAndDurationClosingButton);
        HeaderLayout.setStyle(FormHeaderBackGround);

        ImageAndDuration_Form_Root.getChildren().add(HeaderLayout);
    }

    public void Setup_ImageAndDuration_Form_Body() {
        VBox InnerForm = new VBox();
        InnerForm.setPrefHeight(Screen_Height - 137);
        InnerForm.setMinWidth(ProcessFormWidth);
        InnerForm.setSpacing(15);
        InnerForm.setStyle(FormBodyBackGround);

        for (int i = 0; i < Labels_vector.size(); i++) {
            if (Labels_vector.get(i) != null && (Labels_vector.get(i) instanceof SubProcessLabel || Labels_vector.get(i) instanceof ActivityLabel)) {
                General_Label Labelx = Labels_vector.get(i);


//                ComboBox DurationCombo = new ComboBox();
//                DurationCombo.setMaxWidth(100);
//                Label DurationLabel = new Label("Duration: ");
//                HBox DurationHBox = new HBox();
//                DurationHBox.setTranslateX(10);
//                DurationHBox.getChildren().add(DurationLabel);
//                DurationHBox.getChildren().add(DurationCombo);
//
//                for (int j = 0; j < TT.DurationDBList.size(); j++) {
//                    DurationCombo.getItems().add(TT.DurationDBList.get(j).getValue());
//                }

//                DurationCombo.valueProperty().addListener(new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue ov, String t, String t1) {
//                        if (Labelx instanceof SubProcessLabel) {
//                            ((SubProcessLabel) Labelx).SP.setImageName(t1);
//                        } else if (Labelx instanceof ActivityLabel) {
//                            ((ActivityLabel) Labelx).AC.setImageName(t1);
//                        }
//                    }
//                });
                ImageView image;
                image = new ImageView();

                ComboBox ImageChoice = new ComboBox();
                ImageChoice.setMaxWidth(100);
                Label ImagesLabel = new Label("Images: ");
                HBox ImagesHBox = new HBox();
                ImagesHBox.setTranslateX(10);
                ImagesHBox.getChildren().add(ImagesLabel);
                ImagesHBox.getChildren().add(ImageChoice);

                ImageChoice.getItems().add("None");
                ImageChoice.setValue("None");
                for (int j = 0; j < TT.ImagesList.size(); j++) {
                    ImageChoice.getItems().add(TT.ImagesList.get(j));
                }

                String path = "";
                if (Labelx instanceof SubProcessLabel) {
                    ImageChoice.setValue(((SubProcessLabel) Labelx).SP.getImageName());
                    Image ImageURL = new Image("file:///D:/PD/Images/" + ((SubProcessLabel) Labelx).SP.getImageName());
                    image.setImage(ImageURL);
                    path = "file:///D:/PD/Images/" + ((SubProcessLabel) Labelx).SP.getImageName();
                } else if (Labelx instanceof ActivityLabel) {
                    ImageChoice.setValue(((ActivityLabel) Labelx).AC.getImageName());
                    Image ImageURL = new Image("file:///D:/PD/Images/" + ((ActivityLabel) Labelx).AC.getImageName());
                    image.setImage(ImageURL);
                    path = "file:///D:/PD/Images/" + ((ActivityLabel) Labelx).AC.getImageName();
                }
                Image ImageURL2 = new Image(path, 600, 400, false, false);

                image.setVisible(true);
                image.setSmooth(true);
                image.setFitWidth(150);
                image.setFitHeight(80);
                image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                            if (mouseEvent.getClickCount() == 2) {
                                BackgroundImage background = new BackgroundImage(ImageURL2,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.DEFAULT,
                                        BackgroundSize.DEFAULT);
                                VBox myPane = new VBox();
                                myPane.setBackground(new Background(background));
                                Scene scene = new Scene(myPane, 600, 400);
                                Stage stage = new Stage();
                                stage.setTitle("New Window");
                                stage.setScene(scene);
                                stage.show();
                            }
                        }
                    }
                });

                ImageChoice.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {
                        if (Labelx instanceof SubProcessLabel) {
                            ((SubProcessLabel) Labelx).SP.setImageName(t1);
                        } else if (Labelx instanceof ActivityLabel) {
                            ((ActivityLabel) Labelx).AC.setImageName(t1);
                        }
                        if (t1.matches("None")) {
                            image.setVisible(false);
                            image.setFitWidth(1);
                            image.setFitHeight(1);
                        } else {
                            Image ImageURL = new Image("file:///D:/PD/Images/" + t1);
                            image.setImage(ImageURL);
                            image.setVisible(true);
                            image.setSmooth(true);
                            image.setFitWidth(150);
                            image.setFitHeight(80);
                            image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                        if (mouseEvent.getClickCount() == 2) {
                                            Image ImageURL1 = new Image("file:///D:/PD/Images/" + t1, 600, 400, false, false);
                                            BackgroundImage background = new BackgroundImage(ImageURL1,
                                                    BackgroundRepeat.NO_REPEAT,
                                                    BackgroundRepeat.NO_REPEAT,
                                                    BackgroundPosition.DEFAULT,
                                                    BackgroundSize.DEFAULT);
                                            VBox myPane = new VBox();
                                            myPane.setBackground(new Background(background));
                                            Scene scene = new Scene(myPane, 600, 400);
                                            Stage stage = new Stage();
                                            stage.setTitle("New Window");
                                            stage.setScene(scene);
                                            stage.show();
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
                ImageChoice.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> p) {
                        return new ListCell<String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                System.out.println("rrrrrrrrrrrrrrrrrrrr1" + item + " " + empty + " " + this.getIndex());
                                super.updateItem(item, empty);
                                setText(item);
                                if (item == null || empty) {
                                    setGraphic(null);
                                } else {
                                    Image icon;
                                    try {
                                        String iconPath = "D:\\PD\\Images\\" + item;
//                                        System.out.println("rrrrrrrrrrrrrrrrrrrr2"+iconPath+" "+empty);

                                        icon = new Image(getClass().getClassLoader().getResourceAsStream("/Images_And_Icons/Link-icon.png"));
                                        ImageView iconImageView = new ImageView(icon);
                                        iconImageView.setFitHeight(30);
                                        iconImageView.setPreserveRatio(true);
                                        setGraphic(iconImageView);
                                    } catch (NullPointerException ex) {
                                        // in case the above image doesn't exist, use a default one
//                                        String iconPath = "New_Screen.png";
//                                        icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                                    }

                                }
                            }
                        };
                    }
                });
                Label ObjLabel = new Label();
                if (Labelx instanceof SubProcessLabel) {
                    ObjLabel.setText("SubProcess: " + Labels_vector.get(i).Name + "                          ");
                } else if (Labelx instanceof ActivityLabel) {
                    ObjLabel.setText("Activity: " + Labels_vector.get(i).Name + "                          ");
                }
                ObjLabel.setStyle("-fx-font-size: 12px;-fx-text-fill: #654b00;-fx-font-weight: bold;");
                ObjLabel.setTranslateX(10);
                ObjLabel.setTranslateY(5);
                InnerForm.getChildren().add(ObjLabel);
                InnerForm.getChildren().add(ImagesHBox);
                InnerForm.getChildren().add(image);

//
            }
        }

        ScrollPane Inner_Tasks_Form_scrollPane = new ScrollPane();
        Inner_Tasks_Form_scrollPane.setContent(InnerForm);
        Inner_Tasks_Form_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Inner_Tasks_Form_scrollPane.setPrefHeight(ImageAndDuration_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMinHeight(ImageAndDuration_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMaxHeight(ImageAndDuration_Form_Root.getPrefHeight());
        ImageAndDuration_Form_Root.getChildren().add(1, Inner_Tasks_Form_scrollPane);

//        ImageAndDuration_Form_Root.getChildren().add(1,Inner_Tasks_Form_scrollPane);
//        if(ImageAndDuration_Form_Root.getChildren().size()>2){
//            ImageAndDuration_Form_Root.getChildren().remove(2);
//        }

    }


    public void Setup_OP_Form() {
        Process_Form_Root.setPrefHeight(Screen_Height - 137);
        Process_Form_Root.setPrefWidth(ProcessFormWidth);
        Setup_OP_Form_Header();
        Setup_OP_Form_Body();
    }

    public void Setup_OP_Form_Header() {
        Pdescription.setTextFill(Color.web("#ffffff"));
        ProcessFormClosingButton.setTranslateX(-5);
        ProcessFormClosingButton.setPrefWidth(50);
        ProcessFormClosingButton.setMinHeight(15);
        ProcessFormClosingButton.setPrefHeight(15);
        ProcessFormClosingButton.setMaxHeight(15);
        ProcessFormClosingButton.setStyle(FormClosingButtonStyle_Idle);
        ProcessFormClosingButton.setOnMouseEntered(e -> ProcessFormClosingButton.setStyle(FormClosingButtonStyle_Hover));
        ProcessFormClosingButton.setOnMouseExited(e -> ProcessFormClosingButton.setStyle(FormClosingButtonStyle_Idle));
        ProcessFormClosingButton.setOnMouseClicked(e -> ProcessFormClosingButton.setStyle(FormClosingButtonStyle_Click));
        ProcessFormClosingButton.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).close_Forms();
                }
            }
            FormsMenu.getItems().remove(Close_Form_Menu_Item);
        });
        BorderPane HeaderLayout = new BorderPane();
        HeaderLayout.setCenter(Pdescription);
        HeaderLayout.setRight(ProcessFormClosingButton);
        HeaderLayout.setStyle(FormHeaderBackGround);

        Process_Form_Root.getChildren().add(HeaderLayout);
    }

    public void Setup_OP_Form_Body() {
        VBox InnerForm = new VBox();
        InnerForm.setPrefHeight(Screen_Height - 137);
        InnerForm.setPrefWidth(ProcessFormWidth);
        InnerForm.setSpacing(15);
        InnerForm.setStyle(FormBodyBackGround);

        PDescription.setWrapText(true);
        PDescription.setMinHeight(60);
        PDescription.setMaxHeight(60);
        PDescription.setPrefHeight(60);
        PDescription.textProperty().addListener((observable, oldText, newText) -> {
            GetWord(newText, PDescription.getCaretPosition(), PDescription);
//            List<Modi_IndexRange> errors = spellCheck(newText);
//            for (Modi_IndexRange error : errors) {
//                PDescription.setStyleClass(error.Start, error.end, error.Style);
//            }
        });
        VirtualizedScrollPane PDescriptionVSP = new VirtualizedScrollPane<>(PDescription);
        PDescriptionVSP.setMaxWidth(250);

        InnerForm.getChildren().add(PSNameL);
        InnerForm.getChildren().add(PName);
        InnerForm.getChildren().add(PDescriptionL);
        InnerForm.getChildren().add(PDescriptionVSP);
        InnerForm.getChildren().add(PShortDL);
        InnerForm.getChildren().add(PShortD);
        InnerForm.getChildren().add(POwnerL);
        InnerForm.getChildren().add(POwner);
        InnerForm.getChildren().add(PVersionL);
        InnerForm.getChildren().add(PVersion);

//        PDescription.setPrefRowCount(5);

        for (int i = 0; i < InnerForm.getChildren().size(); i++) {
            InnerForm.getChildren().get(i).setTranslateX(30);
            if (InnerForm.getChildren().get(i) instanceof Label) {
                InnerForm.getChildren().get(i).setTranslateY(5);
                InnerForm.getChildren().get(i).setStyle(Labels_Style);
                ((Label) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof CodeArea) {
                ((CodeArea) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof ComboBox) {
                ((ComboBox) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof TextField) {
                ((TextField) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof ChoiceBox) {
                ((ChoiceBox) InnerForm.getChildren().get(i)).setMaxWidth(250);
            }
        }
        ScrollPane Inner_Tasks_Form_scrollPane = new ScrollPane();
        Inner_Tasks_Form_scrollPane.setContent(InnerForm);
        Inner_Tasks_Form_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Inner_Tasks_Form_scrollPane.setPrefHeight(Process_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMinHeight(Process_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMaxHeight(Process_Form_Root.getPrefHeight());
        Process_Form_Root.getChildren().add(Inner_Tasks_Form_scrollPane);
    }

    public void Setup_SupOP_Form1() {
        SubProcess_Form_Root.setPrefHeight(Screen_Height - 137);
        SubProcess_Form_Root.setPrefWidth(ProcessFormWidth);
        Setup_SupOP_Form_Header();
        Setup_SupOP_Form_Body();
    }

    public void Setup_SupOP_Form_Header() {
        SDescribtionL.setTextFill(Color.web("#ffffff"));
        SubProcessClosingButton.setTranslateX(-5);
        SubProcessClosingButton.setPrefWidth(50);
        SubProcessClosingButton.setMinHeight(15);
        SubProcessClosingButton.setPrefHeight(15);
        SubProcessClosingButton.setMaxHeight(15);
        SubProcessClosingButton.setStyle(FormClosingButtonStyle_Idle);
        SubProcessClosingButton.setOnMouseEntered(e -> SubProcessClosingButton.setStyle(FormClosingButtonStyle_Hover));
        SubProcessClosingButton.setOnMouseExited(e -> SubProcessClosingButton.setStyle(FormClosingButtonStyle_Idle));
        SubProcessClosingButton.setOnMouseClicked(e -> SubProcessClosingButton.setStyle(FormClosingButtonStyle_Click));
        SubProcessClosingButton.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).close_Forms();
                }
            }
            FormsMenu.getItems().remove(Close_Form_Menu_Item);
        });
        BorderPane HeaderLayout = new BorderPane();
        HeaderLayout.setCenter(SDescribtionL);
        HeaderLayout.setRight(SubProcessClosingButton);
        HeaderLayout.setStyle(FormHeaderBackGround);

        SubProcess_Form_Root.getChildren().add(HeaderLayout);
    }

    public void Setup_SupOP_Form_Body() {
        VBox InnerForm = new VBox();
        InnerForm.setPrefHeight(Screen_Height - 137);
        InnerForm.setPrefWidth(ProcessFormWidth);
        InnerForm.setSpacing(15);
        InnerForm.setStyle(FormBodyBackGround);

        InnerForm.getChildren().add(SNameL);
        InnerForm.getChildren().add(SName);
        InnerForm.getChildren().add(SdescriptionL);
        InnerForm.getChildren().add(Sdescription);
        InnerForm.getChildren().add(SShortDL);
        InnerForm.getChildren().add(SShortD);
        InnerForm.getChildren().add(SSTDL);
        InnerForm.getChildren().add(SSTD);
        InnerForm.getChildren().add(SCategoryL);
        InnerForm.getChildren().add(SCategory);
        InnerForm.getChildren().add(ParentNameL);
        InnerForm.getChildren().add(ParentNameCat);
        InnerForm.getChildren().add(langChoice);
        InnerForm.getChildren().add(langCat);
        InnerForm.getChildren().add(SPImagesL);
        InnerForm.getChildren().add(SPImage);
//        InnerForm.getChildren().add(SPDurationL);
//        InnerForm.getChildren().add(SPDuration);
        InnerForm.getChildren().add(Generate_New_SubProcess);

        langCat.getItems().add("English");
        langCat.getItems().add("Arabic");
        langCat.setValue("English");

        Generate_New_SubProcess.setOnAction(e -> {
            SubProcess new_subProcess = new SubProcess();
            new_subProcess.setDescription(Sdescription.getText());
            new_subProcess.setName(SName.getText());
            new_subProcess.setShortDescription(SShortD.getText());
            new_subProcess.setCategory(SCategory.getEditor().getText());
            new_subProcess.setSTD(SSTD.getEditor().getText());
            new_subProcess.setParent((SubProcess) ParentNameCat.getValue());
            String imgName = (String) SPImage.getValue();
            new_subProcess.setImageName(imgName);
            new_subProcess.setDuration_id(-1);
            db.addSubProcess(new_subProcess);

        });
        Generate_New_SubProcess.setStyle("-fx-font-size: 11px;-fx-font-weight: bold;-fx-alignment: center;");

        SSTD.setEditable(true);
        SSTD.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                SCategory.getItems().clear();
                ParentNameCat.getItems().clear();
                for (int i = 0; i < TT.SubProcessDBList.size(); i++) {
                    if (t1.matches(TT.SubProcessDBList.get(i).getSTD()) && !SCategory.getItems().contains(TT.SubProcessDBList.get(i).getCategory())) {
                        SCategory.getItems().add(TT.SubProcessDBList.get(i).getCategory());
                        ParentNameCat.getItems().add(TT.SubProcessDBList.get(i));
                        System.out.println(" sub to string =" + TT.SubProcessDBList.get(i).toString());
                    }
                }
            }
        });

        SCategory.setEditable(true);
        SCategory.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                ParentNameCat.getItems().clear();
                for (int i = 0; i < TT.SubProcessDBList.size(); i++) {
                    if (t1.matches(TT.SubProcessDBList.get(i).getCategory())) {
                        ParentNameCat.getItems().add(TT.SubProcessDBList.get(i));
                    }
                }
            }
        });

        for (int j = 0; j < TT.ImagesList.size(); j++) {
            SPImage.getItems().add(TT.ImagesList.get(j));
        }

        for (int j = 0; j < TT.DurationDBList.size(); j++) {
            SPDuration.getItems().add(TT.DurationDBList.get(j).getValue());
        }

        for (int i = 0; i < InnerForm.getChildren().size(); i++) {
            InnerForm.getChildren().get(i).setTranslateX(30);
            if (InnerForm.getChildren().get(i) instanceof Label) {
                InnerForm.getChildren().get(i).setTranslateY(5);
                InnerForm.getChildren().get(i).setStyle(Labels_Style);
                ((Label) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof TextArea) {
                ((TextArea) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof ComboBox) {
                ((ComboBox) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof TextField) {
                ((TextField) InnerForm.getChildren().get(i)).setMaxWidth(250);
            } else if (InnerForm.getChildren().get(i) instanceof ChoiceBox) {
                ((ChoiceBox) InnerForm.getChildren().get(i)).setMaxWidth(250);
            }
        }
        ScrollPane Inner_Tasks_Form_scrollPane = new ScrollPane();
        Inner_Tasks_Form_scrollPane.setContent(InnerForm);
        Inner_Tasks_Form_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Inner_Tasks_Form_scrollPane.setPrefHeight(SubProcess_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMinHeight(SubProcess_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMaxHeight(SubProcess_Form_Root.getPrefHeight());
        SubProcess_Form_Root.getChildren().add(Inner_Tasks_Form_scrollPane);
    }

    public static void Setup_SupOP_Form2() {

        SubProcess_Form_Root2.getChildren().add(SDescribtionL2);
        SubProcess_Form_Root2.getChildren().add(SdescriptionL2);
        SubProcess_Form_Root2.getChildren().add(Sdescription2);
        SubProcess_Form_Root2.getChildren().add(SShortDL2);
        SubProcess_Form_Root2.getChildren().add(SShortD2);
        SubProcess_Form_Root2.getChildren().add(SCategoryL2);
        SubProcess_Form_Root2.getChildren().add(SCategory2);
        SubProcess_Form_Root2.getChildren().add(SNameL2);
        SubProcess_Form_Root2.getChildren().add(SName2);
        SubProcess_Form_Root2.getChildren().add(SSTDL2);
        SubProcess_Form_Root2.getChildren().add(SSTD2);
        SubProcess_Form_Root2.getChildren().add(langChoice2);
        SubProcess_Form_Root2.getChildren().add(langCat2);

        SdescriptionL2.setTranslateX(1);
        SdescriptionL2.setTranslateY(40);

        Sdescription2.setTranslateX(1);
        Sdescription2.setTranslateY(70);

        SCategoryL2.setTranslateX(1);
        SCategoryL2.setTranslateY(110);

        SCategory2.setTranslateX(1);
        SCategory2.setTranslateY(140);

        SNameL2.setTranslateX(1);
        SNameL2.setTranslateY(180);

        SName2.setTranslateX(1);
        SName2.setTranslateY(210);

        SShortDL2.setTranslateX(1);
        SShortDL2.setTranslateY(250);

        SShortD2.setTranslateX(1);
        SShortD2.setTranslateY(250);

        SSTDL2.setTranslateX(1);
        SSTDL2.setTranslateY(320);

        SSTD2.setTranslateX(1);
        SSTD2.setTranslateY(350);
        SSTD2.setMaxWidth(150);

        langCat2.setTranslateX(1);
        langCat2.setTranslateY(420);
        langCat2.getItems().add("English");
        langCat2.getItems().add("Arabic");
        langCat2.setValue("English");

        langChoice2.setTranslateX(1);
        langChoice2.setTranslateY(390);
        SSTD2.setEditable(true);

//        PDescription.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.TAB) {
//                    TextAreaSkin skin = (TextAreaSkin) PDescription.getSkin();
//                    if (skin.getBehavior() instanceof TextAreaBehavior) {
//                        TextAreaBehavior behavior = (TextAreaBehavior) skin.getBehavior();
//                        if (event.isControlDown()) {
//                            behavior.callAction("InsertTab");
//                        } else {
//                            behavior.callAction("TraverseNext");
//                        }
//                        event.consume();
//                    }
//
//                }
//            }
//        });


    }

    public static void Setup_Root_1_To_English() {

        SDescribtionL.setText("New SubProcess");
        SdescriptionL.setText("SubProcess Description");
        SCategoryL.setText("SubProcess Category");
        SNameL.setText("SubProcess Name");
        SSTDL.setText("SubProcess standard");
        langChoice.setText("SubProcess Language");
        SShortDL.setText("SubProcess Short Description");
        PDescriptionL.setText("Procedure Description");
        POwnerL.setText("Procedure Owner");
        PShortDL.setText("Procedure Short Description");
        PVersionL.setText("Procedure Version");
        Pdescription.setText("Operating Procedure");
        PSNameL.setText("Procedure Name");
    }

    public static void Setup_Root_1_To_Arabic() {

        Pdescription.setText("اجرات العمل");
        PDescriptionL.setText("وصف الإجراء");
        PShortDL.setText("وصف مختصر للأجراء");
        PSNameL.setText("اسم الاجراء");
        POwnerL.setText("صاحب الاجراء");
        PVersionL.setText("نسخة الاجراء");
        SDescribtionL.setText("عملية فرعية جديدة");
        SdescriptionL.setText("وصف العملية الفرعية");
        SCategoryL.setText("فئة العملية الفرعية");
        SNameL.setText("اسم العملية الفرعية");
        langChoice.setText("لغة العملية الفرعية");
        SSTDL.setText("معيار العملية الفرعية");
        SShortDL.setText("وصف مختصر للعملية الفرعية");
    }
}
