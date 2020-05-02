package filters;

import java.io.IOException;

public class CSVColumnFilter extends FilterBooleanLogic {
    private int acceptedColumn;
    private int currentColumn;
    private boolean inQuotes;
    //Static? final? what if given as arguments
    private static final int COLUMN_RESET_INDEX = 1;
    private static final char QUOTE_CHARACTER = '"';
    private static final char DELIMETER = ',';

    public CSVColumnFilter(StatefulFilterInputStream reader, int acceptedColumn) {
        super(reader);
        this.acceptedColumn = acceptedColumn;
        currentColumn = 1;
        inQuotes = false;
    }
    // Improper use of constants
    public int read() throws IOException {
        int charValue = filterInputStream.read();
        char ch = (char) charValue;
        isAccepting = (currentColumn == acceptedColumn);
        if (!inQuotes && ch == ',')
            isAccepting = false;
        if (ch == QUOTE_CHARACTER) {
            inQuotes = !inQuotes;
        } else if (!inQuotes && ch == ',') {
            currentColumn++;
        } else if (ch == '\n') {
            currentColumn = COLUMN_RESET_INDEX;
        }
        return charValue;
    }
}
