package window.choose_delimeter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import window.DialogControllerEventListener;

import java.util.ArrayList;

/**
 * Created by mashy on 25-Apr-17.
 */
public class ChooseDelimeterDialogController {
    @FXML private RadioButton delimeterComma;
    @FXML private RadioButton delimeterSemicolon;
    @FXML private RadioButton delimeterTabulation;

    private ArrayList<DialogControllerEventListener> eventListeners = new ArrayList<DialogControllerEventListener>();

    private Character delimeter = null;

    public void addEventListener(DialogControllerEventListener eventListener) {
        if (this.eventListeners.contains(eventListener)) return;

        this.eventListeners.add(eventListener);
    }

    public void removeEventListener(DialogControllerEventListener eventListener) {
        this.eventListeners.remove(eventListener);
    }

    public char getDelimeter() {
        return this.delimeter;
    }

    @FXML protected void close(ActionEvent event) {
        if (delimeterComma.isSelected()) {
            this.delimeter = ',';
        } else if (delimeterSemicolon.isSelected()) {
            this.delimeter = ';';
        } else if (delimeterTabulation.isSelected()) {
            this.delimeter = '\t';
        }

        for (DialogControllerEventListener eventListener : this.eventListeners) {
            eventListener.onClose();
        }
    }
}
