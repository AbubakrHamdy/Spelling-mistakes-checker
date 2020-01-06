import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

/******************************************************************************
 * SpellCheckController.java
 * @author Jeramy Singleton
 * Description: SpellCheckController handles the spell checking functionality
 * of the SpellChecker view.
 *******************************************************************************/
public class SpellCheckerController {

    // FXML Fields
    @FXML private Button ignore;
    @FXML private Button ignoreAll;
    @FXML private Button change;
    @FXML private Button changeAll;
    @FXML private Button accept;
    @FXML private Button cancel;
    @FXML private TextField misspelled;
    @FXML private TextField changeTo;
    @FXML private ListView<String> suggestions;


    private TextArea input;         // input text for the spell checker
    private String replacementText; // the replacement text that will be 
    // returned to the input text area
    private List<String> misspells; // list of misspelled words
    private ObservableList<String> suggestionsList; // list for suggestions 
    // listview
    List<RuleMatch> matches;      // list of misspellings
    private int currentIndex;       // index for current position in misspells list

    /******************************************************************************
     * SpellCheckerController
     * Default constructor.
     *******************************************************************************/
    public SpellCheckerController(TextArea input)
    {
        currentIndex = -1;
        this.input = input;
        replacementText = input.getText();
        misspells = new ArrayList<String>();
        matches = null;
        suggestionsList = FXCollections.observableArrayList();
    }

    /******************************************************************************
     * initialize
     * initialize sets up the suggestion list with an event listener that changes
     * the changeTo text to the user selected listcell contents.  initialize also
     * runs the spell checking tool on the input and populates the corresponding
     * lists needed for the rest of the spell checking functionality
     *******************************************************************************/
    public void initialize()
    {
        suggestions.setItems(suggestionsList);
        // set a listener on the list views selection model to get the text of any
        // selected row
        suggestions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
                changeTo.setText(newValue);
            }
        });

        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

        try
        {
            matches = langTool.check(input.getText());
            matches.forEach(new Consumer<RuleMatch>() {
                public void accept(RuleMatch match) {
                    // to get the actual word that is misspelled we must get the starting
                    // position of the word, in the input text, and the end position
                    int start = match.getFromPos();
                    int end = match.getToPos();
                    // use the start and end positions to get the substring of the input
                    // text
                    misspells.add(input.getText().substring(start, end));
                }
            });

            // set the misspelled text area to the first misspelled word
            nextMisspelling(0);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    /******************************************************************************
     * nextMisspelling
     * nextMisspelling sets the view up for the next misspelled word and suggestions
     * @param index - the index of the misspelled word in the misspelled list
     *******************************************************************************/
    private void nextMisspelling(int index)
    {
        if(index >= misspells.size())
        {
            disableAndClearControls();
            return;
        }

        currentIndex = index;

        misspelled.setText(misspells.get(index));
        suggestionsList.clear();
        suggestionsList.addAll(matches.get(index).getSuggestedReplacements());
        changeTo.setText(suggestionsList.get(0));
    }

    /******************************************************************************
     * disableAndClearControls
     * disableAndClearControls sets the text fields, buttons, and listview into an
     * unusable state while also resetting the values.
     *******************************************************************************/
    private void disableAndClearControls()
    {
        misspelled.setText("");
        changeTo.setText("");
        suggestionsList.clear();
        ignore.setDisable(true);
        ignoreAll.setDisable(true);
        change.setDisable(true);
        changeAll.setDisable(true);
    }


    /******************************************************************************
     * changeAction
     * changeAction initiates the spelling change and sets up the gui for the next
     * misspelled word
     *******************************************************************************/
    @FXML
    public void changeAction()
    {
        changeMisspelledWord(misspells.get(currentIndex), changeTo.getText());
        nextMisspelling(currentIndex);
    }

    /******************************************************************************
     * changeMisspelledWord
     * changeMisspelledWord updates the replacement text to the value of the string 
     * parameter.
     * @param misspell - the word(s) to replace
     * @param replacement - the string to change the misspelled word to
     *******************************************************************************/
    private void changeMisspelledWord(String misspell, String replacement) {
        replacementText = replacementText.replace(misspell, replacement);
        currentIndex += 1;
    }

    /******************************************************************************
     * changeAllAction
     * changeAllAction replaces all misspelled words with the default suggestion
     *******************************************************************************/
    @FXML
    public void changeAllAction()
    {
        for(int i = 0; i < misspells.size(); i++)
        {
            String suggestion = matches.get(i).getSuggestedReplacements().get(0);
            if(suggestion != null && !suggestion.isEmpty())
            {
                changeMisspelledWord(misspells.get(i), suggestion);
            }
        }

        disableAndClearControls();
    }


    /******************************************************************************
     * ignoreAction
     * ignoreAction skips the current misspelling and moves on to the next 
     * misspelled word
     *******************************************************************************/
    @FXML
    public void ignoreAction()
    {
        currentIndex += 1;
        nextMisspelling(currentIndex);
    }

    /******************************************************************************
     * ignoreAllAction
     * ignoreAllAction does nothing except call a method to clear and disable the
     * GUI controls on the view
     *******************************************************************************/
    @FXML
    public void ignoreAllAction()
    {
        disableAndClearControls();
    }

    /******************************************************************************
     * acceptAction
     * acceptAction changes the original input text to replacement text that has all
     * the spell checker's changes and closes the spell checker window
     * @param event - the source of the ActionEvent (i.e. a button press)
     *******************************************************************************/
    @FXML
    public void acceptAction(ActionEvent event)
    {
        input.setText(replacementText);
        closeWindow((Button)event.getSource());
    }

    /******************************************************************************
     * closeWindow
     * closeWindow gets the current stage from a control on the view and closes it
     * @param button - the button that is responsible for calling the closeWindow
     * method
     *******************************************************************************/
    private void closeWindow(Button button)
    {
        Stage stage = (Stage) button.getParent().getScene().getWindow();
        stage.close();
    }

    /******************************************************************************
     * cancelAction
     * cancelAction closes the spell checker window without making any changes
     * @param event - the source of the ActionEvent (i.e. a button press)
     *******************************************************************************/
    @FXML
    public void cancelAction(ActionEvent event)
    {
        closeWindow((Button)event.getSource());
    }
}