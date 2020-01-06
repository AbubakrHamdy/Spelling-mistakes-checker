import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
//import org.fxmisc.richtext.StyleSpans;
//import org.fxmisc.richtext.StyleSpansBuilder;
//import org.fxmisc.richtext.demo.JavaKeywords;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import scala.reflect.internal.StdNames;

import java.util.Collection;
import java.util.Collections;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFX extends Application {
    private CodeArea codeArea = new CodeArea();
    private String exampleString = "This is a WARNING for an INFO! Please stay tuned";

    private static final String[] KEYWORDS = new String[] { "INFO", "WARNING" };
    /* This keyword could get another color by css */
    private static final String[] DEBUG_KEYWORD = new String[] { "DEBUG" };

    private static final String KEYWORD_PATTERN = "\\b("
            + String.join("|", KEYWORDS) + ")\\b";
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"]|\\\")*\"";
    private static final Pattern PATTERN = Pattern.compile("(?<KEYWORD>"
            + KEYWORD_PATTERN + ")" + "|(?<PAREN>" + PAREN_PATTERN + ")"
            + "|(?<BRACE>" + BRACE_PATTERN + ")" + "|(?<BRACKET>"
            + BRACKET_PATTERN + ")" + "|(?<SEMICOLON>" + SEMICOLON_PATTERN
            + ")" + "|(?<STRING>" + STRING_PATTERN + ")");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Richtext demo");
        String stylesheet = getClass().getResource("/style.css").toExternalForm();
        IntFunction<String> format = (digits -> " %" + digits + "d ");
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea, format));
//        codeArea.setParagraphGraphicFactory(LineNumberFactory.get());
        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            codeArea.setStyleSpans(0, computeHighlighting(newText));
        });
        codeArea.replaceText(0, 0, exampleString);
        Scene scene = new Scene(new StackPane(codeArea), 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static StyleSpans<Collection<String>> computeHighlighting(
            String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass = matcher.group("KEYWORD") != null ? "keyword"
                    : matcher.group("PAREN") != null ? "paren" : matcher
                    .group("BRACE") != null ? "brace" : matcher
                    .group("BRACKET") != null ? "bracket" : matcher
                    .group("SEMICOLON") != null ? "semicolon" : matcher
                    .group("STRING") != null ? "string" : null; /* never happens */
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start()
                    - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end()
                    - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}