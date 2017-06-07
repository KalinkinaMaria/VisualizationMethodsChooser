package model;

import javafx.beans.property.*;

/**
 * Created by mashy on 24-Apr-17.
 */
public class DataTableRow {
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty dataType = new SimpleStringProperty("");
    private StringProperty scale = new SimpleStringProperty("");
    private BooleanProperty isVisualize = new SimpleBooleanProperty(false);

    public final StringProperty nameProperty() {
        return this.name;
    }

    public final String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String name) {
        this.name.set(name);
    }

    public final StringProperty dataTypeProperty() {
        return this.dataType;
    }

    public final String getDataType() {
        return this.dataTypeProperty().get();
    }

    public final void setDataType(final String dataType) {
        this.dataType.set(dataType);
    }

    public final StringProperty scaleProperty() {
        return this.scale;
    }

    public final String getScale() {
        return this.scaleProperty().get();
    }

    public final void setScale(final String scale) {
        this.scale.set(scale);
    }

    public final BooleanProperty isVisualizeProperty() {
        return this.isVisualize;
    }

    public final boolean isVisualize() {
        return this.isVisualizeProperty().get();
    }

    public final void setIsVisualize(final boolean flag) {
        this.isVisualize.set(flag);
    }
}
