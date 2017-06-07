package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by mashy on 26-Apr-17.
 */
public class ResultTableRow {
    private StringProperty name = new SimpleStringProperty("");

    public final StringProperty nameProperty() {
        return this.name;
    }

    public final String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String name) {
        this.name.set(name);
    }
}
