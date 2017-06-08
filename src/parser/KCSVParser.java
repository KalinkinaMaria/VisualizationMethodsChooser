package parser;

import parser.table.KTable;
import parser.table.KTableDataCell;
import parser.table.KTableHeadCell;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by maria on 6/7/17.
 */
public class KCSVParser extends KParser {

    protected final Character delimeter;

    public KCSVParser(String fileName) throws FileNotFoundException {
        super(fileName);
        this.delimeter = new Character(';');
    }

    public KCSVParser(String fileName, char delimeter) throws FileNotFoundException {
        super(fileName);
        this.delimeter = new Character(delimeter);
    }

    @Override
    public KTable parse() throws IOException {
        CSVFormat format = CSVFormat.EXCEL.withDelimiter(delimeter).withHeader();
        CSVParser parser = new CSVParser(this.fileReader, format);

        ArrayList<String> headCells = new ArrayList<>(parser.getHeaderMap().keySet());
        ArrayList<ArrayList<String>> dataCells = new ArrayList<>();

        for (int i = 0; i < parser.getRecords().size();) {
            ArrayList<String> rowDataCells = new ArrayList<>();
            for (int j = 0; j < headCells.size(); j++) {
                rowDataCells.add(parser.getRecords().get(i).toString());
                i++;
            }
            dataCells.add(rowDataCells);
        }

        KTable table = new KTable(dataCells.size(), headCells.size());

        for (int i = 0; i < headCells.size(); i++) {
            table.setHeadCell(i, new KTableHeadCell(headCells.get(i)));
        }

        for (int i = 0; i < dataCells.size(); i++) {
            for (int j = 0; j < dataCells.get(i).size(); j++) {
                table.setDataCell(i, j, new KTableDataCell(dataCells.get(i).get(j)));
            }
        }

        return table;
    }

}
