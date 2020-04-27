package filters;

import java.io.IOException;

public class RowCountFilter extends FilterBooleanLogic {
    private int acceptedRowCount;
    private int currentRowCount;

    public RowCountFilter(StatefulFilterInputStream filterInputStream, int rowCount) {
        super(filterInputStream);
        acceptedRowCount = rowCount;
        currentRowCount = 0;
    }


    public int read() throws IOException {
        int charvalue = filterInputStream.read();
        if ((char) charvalue == '\n') {
            this.currentRowCount++;
        }
        isAccepting = !(currentRowCount >= acceptedRowCount);
        return charvalue;
    }
}