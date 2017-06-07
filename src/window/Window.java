package window;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Created by mashy on 23-May-17.
 */
public abstract class Window {
    protected Stage windowStage;
    protected FXMLLoader windowLoader;

    public Window(Stage windowStage) {
        this.windowStage = windowStage;
    }
}
