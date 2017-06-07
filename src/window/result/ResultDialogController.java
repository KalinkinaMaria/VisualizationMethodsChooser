package window.result;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DataTableRow;
import model.ResultTableRow;
import window.DialogControllerEventListener;

import java.util.ArrayList;

/**
 * Created by mashy on 26-Apr-17.
 */
public class ResultDialogController {
    @FXML private TableView<ResultTableRow> resultTable;
    @FXML private TableColumn<DataTableRow, String> methodColumn;

    private ArrayList<DialogControllerEventListener> eventListeners = new ArrayList<DialogControllerEventListener>();

    @FXML protected void initialize() {
        methodColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void addEventListener(DialogControllerEventListener eventListener) {
        if (this.eventListeners.contains(eventListener)) return;

        this.eventListeners.add(eventListener);
    }

    public void removeEventListener(DialogControllerEventListener eventListener) {
        this.eventListeners.remove(eventListener);
    }

    public void setItems(ObservableList<ResultTableRow> items) {
        this.resultTable.setItems(items);
    }

    @FXML public void close(ActionEvent actionEvent) {
        for (DialogControllerEventListener eventListener : this.eventListeners) {
            eventListener.onClose();
        }
    }
}
