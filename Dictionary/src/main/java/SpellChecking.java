///*
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//public class SpellChecking extends Application {
//
//    static List<String> misspells = new ArrayList<>();
//    static List<RuleMatch> matches = new ArrayList<>();
//
//    StyleClassedTextArea textArea = new StyleClassedTextArea();
//
//    JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
//    String input = "this is a sentence with soeme errors";
//
//    @Override
//    public void start(Stage primaryStage) throws IOException {
//        textArea.setWrapText(true);
//        textArea.insertText(0, input);
//
//        Scene scene = new Scene(new VirtualizedScrollPane<>(textArea), 600, 400);
//        scene.getStylesheets().add(getClass().getResource("spellchecking.css").toExternalForm());
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Spell Checking Demo");
//        primaryStage.show();
//
//
//        checkSpelling();
//    }
//
//
//    private void checkSpelling() throws IOException {
//        matches = langTool.check(input);
//        matches.forEach(match -> {
//            int start = match.getFromPos();
//            int end = match.getToPos();
//            misspells.add(input.substring(start, end));
//        });
//
//        textArea.setStyleSpans(0, computeHighlighting(textArea.getText()));
//    }
//
//
//
//    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
//
//        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
//
//        BreakIterator wb = BreakIterator.getWordInstance();
//        wb.setText(text);
//
//        int lastIndex = wb.first();
//        int lastKwEnd = 0;
//        while(lastIndex != BreakIterator.DONE) {
//            int firstIndex = lastIndex;
//            lastIndex = wb.next();
//
//            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
//                String word = text.substring(firstIndex, lastIndex).toLowerCase();
//
//                if (misspells.contains(word)) {
//                    spansBuilder.add(Collections.emptyList(), firstIndex - lastKwEnd);
//                    spansBuilder.add(Collections.singleton("underlined"), lastIndex - firstIndex);
//                    lastKwEnd = lastIndex;
//                }
//
//            }
//        }
//        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
//
//
//        return spansBuilder.create();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}*/
