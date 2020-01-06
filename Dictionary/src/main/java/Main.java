import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class Main extends Application {
    static String EnglishWordsString = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Hello World!");
        File EnglishWordsFile = new File("words_alpha.txt");
        EnglishWordsString = FileUtils.readFileToString(EnglishWordsFile, String.valueOf(StandardCharsets.UTF_8));
        CodeArea codeArea = new CodeArea();
        codeArea.textProperty().addListener((observable, oldText, newText) -> {
            GetWord(newText, codeArea.getCaretPosition(), codeArea);
//            System.out.println("OB= " + codeArea.getCaretPosition());

//            for (Modi_IndexRange error : errors) {
//                codeArea.setStyleClass(error.Start, error.end, error.Style);
//            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(codeArea);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    List<Modi_IndexRange> spellCheck(String text) {
        String[] dic = text.split("\\W+");
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

    String GetWord(String text, int index, CodeArea codeArea) {
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
