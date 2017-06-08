package parser.table;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by maria on 6/7/17.
 */
public class KTable {

    protected final Point size;

    protected HashMap<Integer, KTableHeadCell> tableHeadCells;

    protected HashMap<Point, KTableDataCell> tableDataCells;

    public KTable(int rows, int columns) {
        this.size = new Point(rows, columns);
        this.tableHeadCells = new HashMap<>();
        this.tableDataCells = new HashMap<>();
    }

    public int getCountRows() {
        return this.size.x;
    }

    public int getCountColumns() {
        return this.size.y;
    }

    public KTableHeadCell getHeadCell(int column) {
        if (column >= 0 && column < this.size.y) {
            Integer key = new Integer(column);
            KTableHeadCell value = this.tableHeadCells.get(key);
            if (value != null) {
                return value;
            } else {
                return new KTableHeadCell();
            }
        } else {
            return null;
        }
    }

    public void setHeadCell(int column, KTableHeadCell cell) {
        if (column >= 0 && column < this.size.y) {
            Integer key = new Integer(column);
            this.tableHeadCells.put(key, cell);
        }
    }

    public KTableDataCell getDataCell(int row, int column) {
        if (row >= 0 && row < this.size.x && column >= 0 && column < this.size.y) {
            Point key = new Point(row, column);
            KTableDataCell value = this.tableDataCells.get(key);
            if (value != null) {
                return value;
            } else {
                return new KTableDataCell();
            }
        } else {
            return null;
        }
    }

    public void setDataCell(int row, int column, KTableDataCell cell) {
        if (row >= 0 && row < this.size.x && column >= 0 && column < this.size.y) {
            Point key = new Point(row, column);
            this.tableDataCells.put(key, cell);
        }
    }

}
