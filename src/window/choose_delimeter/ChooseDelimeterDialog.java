package window.choose_delimeter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import window.Dialog;

import java.io.IOException;

/**
 * Created by mashy on 23-May-17.
 */
public class ChooseDelimeterDialog extends Dialog {

    public final static String TITLE = "Выбор разделителя";

    public ChooseDelimeterDialog(Stage dialogStage, Stage parentStage) {
        super(dialogStage, parentStage);

        try {
            this.dialogLoader = new FXMLLoader(this.getClass().getResource("ChooseDelimeterDialogView.fxml"));

            AnchorPane pane = this.dialogLoader.load();
            Scene scene = new Scene(pane);

            this.dialogStage.setTitle(this.TITLE);
            this.dialogStage.setScene(scene);

            ChooseDelimeterDialogController controller = this.dialogLoader.getController();

            controller.addEventListener(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Character getDelimeter() {
        ChooseDelimeterDialogController controller = this.dialogLoader.getController();

        return controller.getDelimeter();
    }
}
