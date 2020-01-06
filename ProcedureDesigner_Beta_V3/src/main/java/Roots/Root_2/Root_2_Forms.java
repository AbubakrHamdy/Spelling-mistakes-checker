package Roots.Root_2;

import Entities.Task;
import Roots.Modi_Group;
import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import com.sun.javafx.scene.control.skin.TextAreaSkin;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static Roots.Modi_Group.*;
import static sample.Main.*;
import static sample.Main_Menus.Close_Form_Menu_Item;
import static sample.Main_Menus.FormsMenu;
import static sample.Test_Data.db;

public class Root_2_Forms {

    public static Button TaskFormClosingButton = new Button();
    public static Button JDFormClosingButton = new Button();

    public Double JDFormWidth = 300.0;


    public static VBox Current_JD_Form_Root;

    public static VBox Employee_Form_Root;

    public static Label Jobdescription = new Label("Job Description");
    public static Label JobTitleL = new Label("Job Title");
    public static Label JobTypeL = new Label("Job Type");
    public static Label JobLocationL = new Label("Job Location");
    public static Label JobManagerL = new Label("Supervisor/Manager");
    public static Label LanguagesL = new Label("Languages");
    public static Label ExperienceL = new Label("Experience");
    public static Label JobQualificationL = new Label("Qualifications");
    public static Label SkillsL = new Label("Skills");

    public static TextField JobTitle = new TextField();
    public static ComboBox JobType = new ComboBox();
    public static ComboBox JobLocation = new ComboBox();
    public static TextField JobManager = new TextField();
    public static ComboBox Languages = new ComboBox();
    public static ComboBox Experience = new ComboBox();
    public static TextArea JobQualification = new TextArea();
    public static TextArea Skills = new TextArea();
    ///////////////////////////////////////////// tasks
    public static VBox Task_Form_Root;

    public static Label TaskFormHeader = new Label("New Task");
    public static Label TaskLanguageL = new Label("Task Language");
    public static Label TaskDescriptionL = new Label("Task Description");
    public static Label TaskCategoryL = new Label("Task Category");
    public static Label TaskShortDescriptionL = new Label("Task Short Description");

    public static TextField TaskDescription = new TextField();
    public static ComboBox TaskCategory = new ComboBox();
    public static TextField TaskShortDescription = new TextField();
    public static ChoiceBox TaskLanguage = new ChoiceBox();

    public static Button Generate_New_Task = new Button("Generate New Task");

    public Root_2_Forms() {

        Current_JD_Form_Root = new VBox();
        Employee_Form_Root = new VBox();
        Task_Form_Root = new VBox();

        JobQualification.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    TextAreaSkin skin = (TextAreaSkin) JobQualification.getSkin();
                    if (skin.getBehavior() instanceof TextAreaBehavior) {
                        TextAreaBehavior behavior = (TextAreaBehavior) skin.getBehavior();
                        if (event.isControlDown()) {
                            behavior.callAction("InsertTab");
                        } else {
                            behavior.callAction("TraverseNext");
                        }
                        event.consume();
                    }

                }
            }
        });
        Skills.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    TextAreaSkin skin = (TextAreaSkin) Skills.getSkin();
                    if (skin.getBehavior() instanceof TextAreaBehavior) {
                        TextAreaBehavior behavior = (TextAreaBehavior) skin.getBehavior();
                        if (event.isControlDown()) {
                            behavior.callAction("InsertTab");
                        } else {
                            behavior.callAction("TraverseNext");
                        }
                        event.consume();
                    }

                }
            }
        });
        Setup_All_Root_2_Forms();
    }

    public void Setup_All_Root_2_Forms() {
        Current_JD_Form_Root.setVisible(false);
        Setup_JD_Form();
        Setup_Task_Form();
    }

    public void Setup_JD_Form() {
        Employee_Form_Root.setPrefHeight(Screen_Height - 137);
        Employee_Form_Root.setPrefWidth(JDFormWidth);
        Setup_JD_Form_Header();
        Setup_JD_Form_Body();
    }

    public void Setup_JD_Form_Header() {
        Jobdescription.setTextFill(Color.web("#ffffff"));
        JDFormClosingButton.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).close_Forms();
                }
            }
            FormsMenu.getItems().remove(Close_Form_Menu_Item);
        });

        JDFormClosingButton.setTranslateX(-5);
        JDFormClosingButton.setPrefWidth(50);
        JDFormClosingButton.setMinHeight(15);
        JDFormClosingButton.setPrefHeight(15);
        JDFormClosingButton.setMaxHeight(15);
        JDFormClosingButton.setStyle(FormClosingButtonStyle_Idle);
        JDFormClosingButton.setOnMouseEntered(e -> JDFormClosingButton.setStyle(FormClosingButtonStyle_Hover));
        JDFormClosingButton.setOnMouseExited(e -> JDFormClosingButton.setStyle(FormClosingButtonStyle_Idle));
        JDFormClosingButton.setOnMouseClicked(e -> JDFormClosingButton.setStyle(FormClosingButtonStyle_Click));

        BorderPane HeaderLayout = new BorderPane();
        HeaderLayout.setCenter(Jobdescription);
        HeaderLayout.setRight(JDFormClosingButton);
        HeaderLayout.setStyle(HeaderStyle);

        Employee_Form_Root.getChildren().add(HeaderLayout);
    }

    public void Setup_JD_Form_Body() {

        VBox InnerForm = new VBox();
        InnerForm.setStyle(FormBodyBackGround);
        InnerForm.setPrefWidth(JDFormWidth);
        InnerForm.setSpacing(10);

        JobTitle.setPromptText("Ex:IT Administrator .");
        JobManager.setPromptText("EX:IT Infrastructure Head .");
        JobQualification.setPrefRowCount(5);
        Skills.setPrefRowCount(5);

        Skills.setPromptText("Ex: Team work, work under pressure .");
        Skills.setWrapText(true);

        JobType.setPromptText("Ex:Full Time .");
        JobType.setEditable(true);

        JobLocation.setPromptText("Ex:Cairo/Egypt .");
        JobLocation.setEditable(true);

        Experience.setPromptText("Ex:3-5 Years .");
        Experience.setEditable(true);

        Languages.setEditable(true);


        InnerForm.getChildren().add(JobTitleL);
        InnerForm.getChildren().add(JobTitle);

        InnerForm.getChildren().add(JobTypeL);
        InnerForm.getChildren().add(JobType);

        InnerForm.getChildren().add(JobLocationL);
        InnerForm.getChildren().add(JobLocation);

        InnerForm.getChildren().add(JobManagerL);
        InnerForm.getChildren().add(JobManager);

        InnerForm.getChildren().add(LanguagesL);
        InnerForm.getChildren().add(Languages);

        InnerForm.getChildren().add(ExperienceL);
        InnerForm.getChildren().add(Experience);

        InnerForm.getChildren().add(JobQualificationL);
        InnerForm.getChildren().add(JobQualification);

        InnerForm.getChildren().add(SkillsL);
        InnerForm.getChildren().add(Skills);

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
        Inner_Tasks_Form_scrollPane.setPrefHeight(Screen_Height - 137);
        Inner_Tasks_Form_scrollPane.setMinHeight(Screen_Height - 137);
        Inner_Tasks_Form_scrollPane.setMaxHeight(Screen_Height - 137);

        Employee_Form_Root.getChildren().add(Inner_Tasks_Form_scrollPane);
    }

    public void Setup_Task_Form() {
        Task_Form_Root.setPrefHeight(Screen_Height - 137);
        Task_Form_Root.setPrefWidth(JDFormWidth);
        Setup_Task_Form_Header();
        Setup_Task_Form_Body();
    }

    public void Setup_Task_Form_Header() {
        TaskFormHeader.setTextFill(Color.web("#ffffff"));
        TaskFormClosingButton.setTranslateX(-5);
        TaskFormClosingButton.setPrefWidth(50);
        TaskFormClosingButton.setMinHeight(15);
        TaskFormClosingButton.setPrefHeight(15);
        TaskFormClosingButton.setMaxHeight(15);
        TaskFormClosingButton.setStyle(FormClosingButtonStyle_Idle);
        TaskFormClosingButton.setOnMouseEntered(e -> TaskFormClosingButton.setStyle(FormClosingButtonStyle_Hover));
        TaskFormClosingButton.setOnMouseExited(e -> TaskFormClosingButton.setStyle(FormClosingButtonStyle_Idle));
        TaskFormClosingButton.setOnMouseClicked(e -> TaskFormClosingButton.setStyle(FormClosingButtonStyle_Click));
        TaskFormClosingButton.setOnAction(e -> {
            for (int i = 0; i < Current_Root.getChildren().size(); i++) {
                if (Current_Root.getChildren().get(i) instanceof Modi_Group) {
                    ((Modi_Group) Current_Root.getChildren().get(i)).close_Forms();
                }
            }
            FormsMenu.getItems().remove(Close_Form_Menu_Item);
        });
        BorderPane HeaderLayout = new BorderPane();
        HeaderLayout.setCenter(TaskFormHeader);
        HeaderLayout.setRight(TaskFormClosingButton);
        HeaderLayout.setStyle(FormHeaderBackGround);
        Task_Form_Root.getChildren().add(HeaderLayout);
    }

    public void Setup_Task_Form_Body() {

        VBox Inner_Form = new VBox();
        Inner_Form.setPrefHeight(Screen_Height - 137);
        Inner_Form.setPrefWidth(JDFormWidth);
        Inner_Form.setStyle(FormBodyBackGround);
        Inner_Form.setSpacing(15);

        TaskLanguage.getItems().add("English");
        TaskLanguage.getItems().add("Arabic");
        TaskLanguage.setValue("English");

        TaskCategory.setEditable(true);
        for (int i = 0; i < TT.TasksDBList.size(); i++) {
            if (!TaskCategory.getItems().contains(TT.TasksDBList.get(i).getCategory())) {
                TaskCategory.getItems().add(TT.TasksDBList.get(i).getCategory());
                System.out.println(" sub to string =" + TT.TasksDBList.get(i).toString());
            }
        }

        Generate_New_Task.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;-fx-alignment: center;");
        Generate_New_Task.setOnAction(e -> {
            Task new_task = new Task();
            new_task.setDescription(TaskDescription.getText());
            new_task.setShortDesc(TaskShortDescription.getText());
            new_task.setCategory(TaskCategory.getEditor().getText());
            db.addTask(new_task);
        });

        Inner_Form.getChildren().add(TaskDescriptionL);
        Inner_Form.getChildren().add(TaskDescription);
        Inner_Form.getChildren().add(TaskShortDescriptionL);
        Inner_Form.getChildren().add(TaskShortDescription);
        Inner_Form.getChildren().add(TaskCategoryL);
        Inner_Form.getChildren().add(TaskCategory);
        Inner_Form.getChildren().add(TaskLanguageL);
        Inner_Form.getChildren().add(TaskLanguage);
        Inner_Form.getChildren().add(Generate_New_Task);

        for (int i = 0; i < Inner_Form.getChildren().size(); i++) {
            Inner_Form.getChildren().get(i).setTranslateX(30);
            if (Inner_Form.getChildren().get(i) instanceof Label) {
                Inner_Form.getChildren().get(i).setTranslateY(5);
                Inner_Form.getChildren().get(i).setStyle(Labels_Style);
                ((Label) Inner_Form.getChildren().get(i)).setMaxWidth(250);
            } else if (Inner_Form.getChildren().get(i) instanceof TextArea) {
                ((TextArea) Inner_Form.getChildren().get(i)).setMaxWidth(250);
            } else if (Inner_Form.getChildren().get(i) instanceof ComboBox) {
                ((ComboBox) Inner_Form.getChildren().get(i)).setMaxWidth(250);
            } else if (Inner_Form.getChildren().get(i) instanceof TextField) {
                ((TextField) Inner_Form.getChildren().get(i)).setMaxWidth(250);
            } else if (Inner_Form.getChildren().get(i) instanceof ChoiceBox) {
                ((ChoiceBox) Inner_Form.getChildren().get(i)).setMaxWidth(250);
            }
        }

        ScrollPane Inner_Tasks_Form_scrollPane = new ScrollPane();
        Inner_Tasks_Form_scrollPane.setContent(Inner_Form);
        Inner_Tasks_Form_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Inner_Tasks_Form_scrollPane.setPrefHeight(Task_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMinHeight(Task_Form_Root.getPrefHeight());
        Inner_Tasks_Form_scrollPane.setMaxHeight(Task_Form_Root.getPrefHeight());
        Task_Form_Root.getChildren().add(Inner_Tasks_Form_scrollPane);

    }

    public static void Setup_Root_2_To_English() {
        JobTitleL.setText("Job Title");
        JobTitle.setPromptText("Ex:IT Administrator .");
        JobTypeL.setText("Job Type");
        JobType.setPromptText("Ex:Full Time .");
        JobLocationL.setText("Job Location");
        JobLocation.setPromptText("Ex:Cairo/Egypt .");
        JobManagerL.setText("Supervisor/Manager");
        JobManager.setPromptText("EX:IT Infrastructure Head .");
        LanguagesL.setText("Languages");
        ExperienceL.setText("Experience");
        Experience.setPromptText("Ex:3-5 Years .");
        JobQualificationL.setText("Qualifications");
        SkillsL.setText("Skills");
        Skills.setPromptText("Ex: Team work, work under pressure .");

        // Languages.setPromptText("Ex:English - Arabic .");

        Jobdescription.setText("Job Description");

    }

    public static void Setup_Root_2_To_Arabic() {

        JobTitleL.setText("اسم الوظيفة");
        JobTitle.setPromptText("مسؤول قاعدة البيانات.");

        JobTypeL.setText("نوع الوظيفة");
        JobType.setPromptText("وقت كامل.");

        JobLocationL.setText("مكان الوظيفة");
        JobLocation.setPromptText(" القاهرة / مصر.");

        JobManagerL.setText("مدير الوظيفة");
        JobManager.setPromptText("رئيس البنية التحتية لتكنولوجيا المعلومات.");

        JobQualificationL.setText("مؤهلات الوظيفة");

        ExperienceL.setText("الخبرة");
        Experience.setPromptText("3-5 سنوات.");
        LanguagesL.setText("اللغات");
        // Languages.setPromptText("عربي-انجليزي");


        SkillsL.setText("المهارات");
        Skills.setPromptText("العمل تحت الضغط.");

        Jobdescription.setText("المسمى الوظيفي");


    }
}
