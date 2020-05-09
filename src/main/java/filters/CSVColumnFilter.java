package filters;

import java.io.IOException;

public class CSVColumnFilter extends CSVReader {
    private static final int COLUMN_RESET_INDEX = 1;
    private static final char NEWLINE_CHARACTER = '\n';
    private int acceptedColumn;
    private int currentColumn;

    public CSVColumnFilter(StatefulFilterInputStream reader, int acceptedColumn) {
        super(reader);
        this.acceptedColumn = acceptedColumn;
        currentColumn = 1;
    }

    /**
     * CSVColumnFilter's read method detects whether the current field being read is
     * in the appropriate column given to the object and changes the state
     * of its filter accordingly.
     */
    public int read() throws IOException {
        int charValue = super.read();
        char ch = (char) charValue;
        isAccepting = (currentColumn == acceptedColumn && isAccepting);
        if (!inQuotes && ch == DELIMITER) {
            currentColumn++;
        }
        if (ch == NEWLINE_CHARACTER) {
            currentColumn = COLUMN_RESET_INDEX;
        }
        return charValue;
    }
}
