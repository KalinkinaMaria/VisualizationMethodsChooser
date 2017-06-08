package parser;

import parser.table.KTable;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by maria on 6/7/17.
 */
public abstract class KParser {

    protected final Reader fileReader;

    public KParser(@NotNull String fileName) throws FileNotFoundException {
        this.fileReader = new FileReader(fileName);
    }

    public abstract KTable parse() throws IOException;

}
