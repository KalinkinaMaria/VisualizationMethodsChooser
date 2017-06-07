package window.result;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ResultTableRow;
import window.Dialog;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mashy on 23-May-17.
 */
public class ResultDialog extends Dialog {

    public final static String TITLE = "Результат";

    public ResultDialog(Stage resultDialogStage, Stage parentStage) {
        super(resultDialogStage, parentStage);

        try {
            this.dialogLoader = new FXMLLoader(this.getClass().getResource("ResultDialogView.fxml"));

            AnchorPane pane = this.dialogLoader.load();
            Scene scene = new Scene(pane);

            this.dialogStage.setTitle(this.TITLE);
            this.dialogStage.setScene(scene);

            ResultDialogController controller = this.dialogLoader.getController();

            controller.addEventListener(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setVisualizationMethods(ArrayList<String> visualizationMethods) {
        ObservableList<ResultTableRow> methods = FXCollections.observableArrayList();

        for (String visualizationMethod : visualizationMethods) {
            ResultTableRow item = new ResultTableRow();
            item.setName(visualizationMethod);
            methods.add(item);
        }

        ResultDialogController controller = this.dialogLoader.getController();

        controller.setItems(methods);
    }
}
