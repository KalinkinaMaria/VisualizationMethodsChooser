package window.main;

import window.Window;
import window.WindowControllerEventListener;
import window.choose_delimeter.ChooseDelimeterDialog;
import window.choose_delimeter.ChooseDelimeterDialogController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataTableRow;
import org.apache.commons.csv.*;
import javafx.scene.control.*;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;
import org.swrlapi.sqwrl.values.SQWRLResultValue;
import window.result.ResultDialog;
import window.result.ResultDialogController;

/**
 * Created by mashy on 24-Apr-17.
 */
public class MainWindowController {
    @FXML private Button buttonRun;

    @FXML private TableView<DataTableRow> dataTable;
    @FXML private TableColumn<DataTableRow, String> nameDataColumn;
    @FXML private TableColumn<DataTableRow, DataTableRow> dataTypeColumn;
    @FXML private TableColumn<DataTableRow, DataTableRow> scaleColumn;
    @FXML private TableColumn<DataTableRow, DataTableRow> isVisualizeColumn;

    private ArrayList<WindowControllerEventListener> eventListeners = new ArrayList<WindowControllerEventListener>();
    private final ObservableList<String> dataTypeList = FXCollections.observableArrayList(Arrays.asList("Числовой", "Строка", "Географические", "Логический", "Время"));;
    private final ObservableList<String> scaleList = FXCollections.observableArrayList(Arrays.asList("Номинальная", "Порядковая", "Интервальная", "Абсолютная"));;

    @FXML protected void initialize() {
        nameDataColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dataTypeColumn.setCellValueFactory(param -> new SimpleObjectProperty<DataTableRow>(param.getValue()));
        scaleColumn.setCellValueFactory(param -> new SimpleObjectProperty<DataTableRow>(param.getValue()));
        isVisualizeColumn.setCellValueFactory(param -> new SimpleObjectProperty<DataTableRow>(param.getValue()));

        isVisualizeColumn.setCellFactory(param -> new TableCell<DataTableRow, DataTableRow>() {
            @Override
            protected void updateItem(DataTableRow item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    CheckBox cb = new CheckBox();
                    cb.selectedProperty().bindBidirectional(item.isVisualizeProperty());
                    setGraphic(cb);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
            }
        });

        dataTypeColumn.setCellFactory(param -> new TableCell<DataTableRow, DataTableRow>() {
            @Override
            protected void updateItem(DataTableRow item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    ComboBox<String> cb = new ComboBox<>(dataTypeList);
                    cb.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                        item.setDataType(newValue);
                        System.out.println(item.getDataType());
                    });
                    setGraphic(cb);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
            }
        });

        scaleColumn.setCellFactory(param -> new TableCell<DataTableRow, DataTableRow>() {
            @Override
            protected void updateItem(DataTableRow item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    ComboBox<String> cb = new ComboBox<>(scaleList);
                    cb.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                        item.setScale(newValue);
                        System.out.println(item.getScale());
                    });
                    setGraphic(cb);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
            }
        });
    }

    public void addEventListener(WindowControllerEventListener eventListener) {
        if (this.eventListeners.contains(eventListener)) return;

        this.eventListeners.add(eventListener);
    }

    public void removeEventListener(WindowControllerEventListener eventListener) {
        this.eventListeners.remove(eventListener);
    }

    public void setHeaderItems(ObservableList<DataTableRow> headerItems) {
        this.dataTable.setItems(headerItems);
    }

    @FXML public void openFile(ActionEvent actionEvent) {
        for (WindowControllerEventListener eventListener : this.eventListeners) {
            eventListener.onOpenFile();
        }
    }

    @FXML protected void buttonRunClick(ActionEvent event) {
        for (WindowControllerEventListener eventListener : this.eventListeners) {
            eventListener.onRun();
        }
    }
}
