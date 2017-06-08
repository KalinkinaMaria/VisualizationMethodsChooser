package parser.table;

/**
 * Created by maria on 6/7/17.
 */
public class KTableHeadCell {

    public final String title;

    public KTableHeadCell() {
        this.title = new String("");
    }

    public KTableHeadCell(String title) {
        if (title != null) {
            this.title = new String(title);
        } else {
            this.title = new String("");
        }
    }

}
