package parser.table;

/**
 * Created by maria on 6/7/17.
 */
public class KTableDataCell {

    public final String content;

    public KTableDataCell() {
        this.content = new String("");
    }

    public KTableDataCell(String content) {
        if (content != null) {
            this.content = new String(content);
        } else {
            this.content = new String("");
        }
    }

}
