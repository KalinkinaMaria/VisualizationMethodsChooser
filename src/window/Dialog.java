package window;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by mashy on 23-May-17.
 */
public abstract class Dialog implements DialogControllerEventListener{
    protected Stage dialogStage;
    protected Stage parentStage;
    protected FXMLLoader dialogLoader;

    protected Boolean closed = false;

    public Dialog(Stage dialogStage, Stage parentStage) {
        this.dialogStage = dialogStage;
        this.parentStage = parentStage;

        this.dialogStage.initModality(Modality.WINDOW_MODAL);
        this.dialogStage.initOwner(parentStage);
    }

    public void show() {
        this.dialogStage.showAndWait();
    }

    @Override
    public void onClose() {
        this.closed = true;

        this.dialogStage.close();
    }

    public Boolean isClosed() {
        return this.closed;
    }
}
