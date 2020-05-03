package filters;

import java.io.IOException;

public class CSVColumnFilter extends FilterBooleanLogic {
    private int acceptedColumn;
    private int currentColumn;
    private boolean inQuotes;
    //Static? final? what if given as arguments
    private static final int COLUMN_RESET_INDEX = 1;
    private static final char QUOTE_CHARACTER = '"';
    private static final char DELIMITER = ',';
    private static final char NEWLINE_CHARACTER = '\n';

    public CSVColumnFilter(StatefulFilterInputStream reader, int acceptedColumn) {
        super(reader);
        this.acceptedColumn = acceptedColumn;
        currentColumn = 1;
        inQuotes = false;
    }

    public int read() throws IOException {
        int charValue = filterInputStream.read();
        char ch = (char) charValue;
        isAccepting = (currentColumn == acceptedColumn);
        if (!inQuotes && ch == DELIMITER) {
            isAccepting = false;
            currentColumn++;
        }
        if (ch == QUOTE_CHARACTER) {
            inQuotes = !inQuotes;
        } else if (ch == NEWLINE_CHARACTER) {
            currentColumn = COLUMN_RESET_INDEX;
        }
        return charValue;
    }
}
