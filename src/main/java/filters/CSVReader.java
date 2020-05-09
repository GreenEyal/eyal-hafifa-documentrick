package filters;

import java.io.IOException;

public class CSVReader extends FilterBooleanLogic {
    protected static final char QUOTE_CHARACTER = '"';
    protected static final char DELIMITER = ',';
    protected boolean inQuotes;

    public CSVReader(StatefulFilterInputStream filterInputStream) {
        super(filterInputStream);
        inQuotes = false;
    }

    /**
     * CSVReader's read method detects whether the characters being read are appropriate
     * fields in the csv data and changes the state of its filter accordingly.
     */
    public int read() throws IOException {
        int charValue = filterInputStream.read();
        char ch = (char) charValue;
        isAccepting = inQuotes || ch != DELIMITER;
        if (ch == QUOTE_CHARACTER) {
            inQuotes = !inQuotes;
        }
        return charValue;
    }

}
